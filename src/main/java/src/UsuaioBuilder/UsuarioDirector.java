package src.UsuaioBuilder;

import src.model.Usuario;

public class UsuarioDirector {
    protected UsuarioBuilder builder;

    public UsuarioDirector(UsuarioBuilder builder) {
        this.builder = builder;
    }

    public void construirUsuario(Usuario usuario) {
        builder.informacoeBasicas(usuario);
        builder.buscarDispositivos();
        builder.buscarNotificacoes();
        builder.buscarSalasInscrito();
    }

    public Usuario getUsuario() {
        return builder.getUsuario();
    }

}
