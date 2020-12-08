package src.UsuaioBuilder;

import src.Facade.Sistema;
import src.Facade.SistemaBusca;
import src.Facade.SistemaGetDados;
import src.model.*;

import java.util.ArrayList;
import java.util.List;

public class UsuarioPadraoBuilder extends UsuarioBuilder{
    @Override
    public void buscarDispositivos() {
        SistemaGetDados sistema = SistemaGetDados.getInstance();
        List<Dispositivo> dispositivos = sistema.listarDispositivos();
        List<Dispositivo> dispositivosUsuario = new ArrayList<Dispositivo>();
        for (Dispositivo dispositivo : dispositivos){
            if(dispositivo.getCodigoUsuario().equals(this.usuario.getCodigo())){
                dispositivosUsuario.add(dispositivo);
            }
        }
        this.usuario.setDispositivos(dispositivosUsuario);
    }

    @Override
    public void buscarSalasInscrito() {
        SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();
        SistemaBusca sistemaBusca = new SistemaBusca();
        ArrayList<InscricaoSala> inscricoes = (ArrayList<InscricaoSala>) sistemaGetDados.listarInscricoes();
        ArrayList<Sala> salasUsuario = new ArrayList<>();
        for (InscricaoSala inscricao: inscricoes){
            if(inscricao.getCodUsuario().equals(this.usuario.getCodigo())){
                salasUsuario.add(sistemaBusca.buscarSalaCodigo(inscricao.getCodSala()));
            }
        }
        this.usuario.setSalasCadastradas(salasUsuario);
    }

    @Override
    public void buscarNotificacoes() {
        SistemaGetDados sistema = SistemaGetDados.getInstance();
        List<Notificacao> notificacoes = sistema.listarNotificacoes();
        List<Notificacao> notificacoesUsuario = new ArrayList<Notificacao>();

        int qtdNotificacao = 0;
        for (Notificacao notificacao: notificacoes){
            if(notificacao.getCod_receptor().equals(this.usuario.getCodigo())){
                notificacoesUsuario.add(notificacao);
                if(notificacao.isLida() == false){
                    qtdNotificacao += 1;
                }
            }
        }
        this.usuario.setNotificacoes((ArrayList<Notificacao>) notificacoesUsuario);
    }

    @Override
    public void informacoeBasicas(Usuario usuarioLoagado) {
        this.usuario = usuarioLoagado;
    }
}
