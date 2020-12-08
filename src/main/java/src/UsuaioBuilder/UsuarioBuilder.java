package src.UsuaioBuilder;

import src.model.Usuario;

public abstract class UsuarioBuilder {

    protected Usuario usuario;

    public Usuario getUsuario(){
        return usuario;
    }

    public abstract void buscarDispositivos();

    public abstract void buscarSalasInscrito();

    public abstract void buscarNotificacoes();

    public abstract void informacoeBasicas(Usuario usuario);
}
