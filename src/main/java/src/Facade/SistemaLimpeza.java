package src.Facade;

import src.NotificacaoFactory.CaixaNotificacao;
import src.NotificacaoFactory.CaixaNotificacaoAlertaFactory;
import src.NotificacaoFactory.CaixaNotificacaoFactory;
import src.database.DispositivoDAO;
import src.model.Dispositivo;
import src.model.Notificacao;
import src.model.Usuario;
import src.tela.Console.Ferramentas;

import java.util.*;

public class SistemaLimpeza {

    private static SistemaLimpeza sistemaLimpeza;

    private SistemaLimpeza(){};

    public static SistemaLimpeza getInstance() {
        if(sistemaLimpeza == null){
            sistemaLimpeza = new SistemaLimpeza();
        }
        return sistemaLimpeza;
    }

    public void executarLimpeza(String codigoUsuario, int codigoDispositivo){
        DispositivoDAO dispositivoDAO = DispositivoDAO.getInstance();
        Dispositivo dispositivo = dispositivoDAO.buscarPorCodigo(codigoDispositivo);
        if(dispositivo.getCodigoUsuario().equals(codigoUsuario)){
            dispositivo.setUltimaLimpeza(new Date());
            dispositivoDAO.atualizar(dispositivo);
        }else{
            Ferramentas.mensagemErro("Dados Inválidos");
        }


    }

    public Map<String, List<Dispositivo>> verificarUsuario(String tempo_limpeza, String codigoUsuario) {

        int qtdDias = Integer.parseInt(tempo_limpeza);
        System.out.println(qtdDias);

        DispositivoDAO dispositivoDAO = DispositivoDAO.getInstance();
        List<Dispositivo> dispositivos = dispositivoDAO.listarTudo();

        Calendar dataLimite = new GregorianCalendar();
        dataLimite.setTime(new Date());
        dataLimite.add(Calendar.DAY_OF_MONTH, - qtdDias);

        List<Dispositivo> dispositivosAutorizados = new ArrayList<Dispositivo>();
        List<Dispositivo> dispositivosBloqueados = new ArrayList<Dispositivo>();

        Calendar dataLimpezaDispositivo = new GregorianCalendar();
        for (Dispositivo dispositivo: dispositivos){
            if(dispositivo.getCodigoUsuario().equals(codigoUsuario) == false){
                continue;
            }
            if(dispositivo.getUltimaLimpeza() == null){
                dispositivosBloqueados.add(dispositivo);
                continue;
            }
            dataLimpezaDispositivo.setTime(dispositivo.getUltimaLimpeza());
            if(dataLimite.before(dataLimpezaDispositivo)){
                dispositivosAutorizados.add(dispositivo);
            }else{
                dispositivosBloqueados.add(dispositivo);
            }

        }

        Map<String, List<Dispositivo>> resposta = new HashMap<String, List<Dispositivo>>();
        resposta.put("autorizados", dispositivosAutorizados);
        resposta.put("bloqueados", dispositivosBloqueados);
        return resposta;
    }



    public void verificarDispositivos(Usuario usuario) {

        List<Notificacao> notificacoes = new ArrayList<>();
        CaixaNotificacaoFactory caixaNotificacaoFactory = new CaixaNotificacaoAlertaFactory();
        CaixaNotificacao caixaNotificacao;

        Date tempoAlerta = new Date();
        for (Dispositivo dispositivo: usuario.getDispositivos()){
            tempoAlerta.setDate(tempoAlerta.getDay() - new Integer(dispositivo.getTempoAlerta()));
            if(dispositivo.getUltimaLimpeza() == null){
                caixaNotificacao = caixaNotificacaoFactory.criar("sistema", "O dispositido " + dispositivo.getNome() +" precisa ser higienizado", usuario.getCodigo());
                caixaNotificacao.exibirNotificacao();
                caixaNotificacao.marcarLida();
                usuario.adicionarNotificacao(caixaNotificacao.getNotificacao());
            }
            else if(dispositivo.getUltimaLimpeza().before(tempoAlerta)){
                caixaNotificacao = caixaNotificacaoFactory.criar("sistema", "O dispositido " + dispositivo.getNome() +" precisa ser higienizado", usuario.getCodigo());
                caixaNotificacao.exibirNotificacao();
                caixaNotificacao.marcarLida();
                usuario.adicionarNotificacao(caixaNotificacao.getNotificacao());
            }
        }
    }
}
