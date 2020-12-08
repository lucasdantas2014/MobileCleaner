package src.UsuaioBuilder;

import src.Facade.SistemaGetDados;
import src.model.Dispositivo;
import src.model.Notificacao;
import src.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAdminBuilder extends UsuarioBuilder{
    @Override
    public void buscarDispositivos() {
        SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();
        List<Dispositivo> dispositivos = sistemaGetDados.listarDispositivos();
        List<Dispositivo> dispositivosUsuario = new ArrayList<Dispositivo>();
        for (Dispositivo dispositivo : dispositivos){
            if(dispositivo.getCodigoUsuario().equals(usuario.getCodigo())) {
                dispositivosUsuario.add(dispositivo);
            }
        }
        usuario.setDispositivos(dispositivosUsuario);
    }
    @Override
    public void buscarSalasInscrito() {
        usuario.setSalasCadastradas(new ArrayList<>());
    }
    @Override
    public void buscarNotificacoes() {
        SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();
        List<Notificacao> notificacoes = sistemaGetDados.listarNotificacoes();
        List<Notificacao> notificacoesUsuario = new ArrayList<Notificacao>();

        for (Notificacao notificacao: notificacoes){
            if(notificacao.getEmissor().equals(usuario.getCodigo())){
                notificacoesUsuario.add(notificacao);
            }
        }
        usuario.setNotificacoes((ArrayList<Notificacao>) notificacoesUsuario);
    }

    @Override
    public void informacoeBasicas(Usuario usuarioLogado) {
        this.usuario = usuarioLogado;
    }
}
