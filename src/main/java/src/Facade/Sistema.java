package src.Facade;

import src.database.*;
import src.model.*;

import java.util.*;

public class Sistema {

    public boolean removerUsuario(Usuario usuario) {
        try {
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            DispositivoDAO dispositivoDAO = DispositivoDAO.getInstance();
            NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance();
            InscricaoSalaDAO inscricaoSalaDAO = InscricaoSalaDAO.getInstance();
            SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();

            System.out.println("Apagando Dispositivos do Sistema");
            for (Dispositivo dispositivo: usuario.getDispositivos()){
                dispositivoDAO.apagar(dispositivo);
            }
            System.out.println("Apagando Notificações do Sistema");
            for (Notificacao notificacao: usuario.getNotificacoes()){
                notificacaoDAO.apagar(notificacao);
            }
            for (InscricaoSala inscricaoSala: sistemaGetDados.listarInscricoes()){
                inscricaoSalaDAO.apagar(inscricaoSala);
            }
            usuarioDAO.apagar(usuario);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean removerDispositivo(Dispositivo dispositivo){
        DispositivoDAO dispositivoDAO = DispositivoDAO.getInstance();
        boolean sucesso = dispositivoDAO.apagar(dispositivo);
        if(sucesso){
            System.out.println("Dispositivo removido com sucesso");
            return true;
        }else{
            System.out.println("Dispositivo não removiddo");
            return false;
        }
    }

    private static Sistema sistema;

    Sistema(){}

    public boolean adicionarDispositivo(Dispositivo dispositivo){
        try {
            DispositivoDAO dispositivoDAO = DispositivoDAO.getInstance();
            boolean suceeso = dispositivoDAO.inserir(dispositivo);
            System.out.println("Dispositivo adicionado");
            return suceeso;
        }catch (Exception e) {
            System.out.println("Não foi possível adicionar o dispositivo, tente novamente.");
            return false;
        }
    }

    public void atualizarDispositivo(Dispositivo dispositivo){
        DispositivoDAO dispositivoDAO = DispositivoDAO.getInstance();
        dispositivoDAO.atualizar(dispositivo);
    }



    public static void fecharConexao(){
        ConexaoBanco.finalizarConexao();

    }

    public static Sistema getInstance(){
        if (sistema == null){
            sistema = new Sistema();
        }
        return sistema;
    }


    public boolean atualizarUsuario(Usuario usuario) {
        try {
            usuario.setDispositivos(new ArrayList<Dispositivo>());
            usuario.setNotificacoes(new ArrayList<Notificacao>());
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            usuarioDAO.atualizar(usuario);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean cadastrarSala(Sala sala) {
        try {
            SalaDAO salaDAO = SalaDAO.getInstance();
            salaDAO.inserir(sala);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean removerSala(Sala sala) {
        try {
            SalaDAO salaDAO = SalaDAO.getInstance();
            salaDAO.apagar(sala);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean atualizarSala(Sala sala) {
        try {
            SalaDAO salaDAO = SalaDAO.getInstance();
            salaDAO.atualizar(sala);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public void enviarNotificaco(Notificacao notificacao){

        NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance();
        SistemaBusca sistemaBusca = new SistemaBusca();

        boolean sucesso = notificacaoDAO.inserir(notificacao);

        Usuario usuario = sistemaBusca.buscarUsuarioCodigo(notificacao.getCod_receptor());

        if(sucesso){
            usuario.notificar(notificacao);
        }else{
            System.out.println("Usuário não notificado");
        }
    }
}
