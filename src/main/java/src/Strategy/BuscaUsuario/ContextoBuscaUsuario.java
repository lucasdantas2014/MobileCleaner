package src.Strategy.BuscaUsuario;

import src.model.Usuario;

import java.util.List;

public class ContextoBuscaUsuario {

    BuscaUsuarioStrategy buscaUsuarioStrategy;

    public ContextoBuscaUsuario(BuscaUsuarioStrategy buscaUsuarioStrategy){
        this.buscaUsuarioStrategy = buscaUsuarioStrategy;
    }

    public ContextoBuscaUsuario() {
    }

    public void setBuscaUsuarioStrategy(BuscaUsuarioStrategy buscaUsuarioStrategy) {
        this.buscaUsuarioStrategy = buscaUsuarioStrategy;
    }

    public void executar(List<Usuario> listaUsuarios, String parametro){
        this.buscaUsuarioStrategy.buscar(listaUsuarios, parametro);
    }
}
