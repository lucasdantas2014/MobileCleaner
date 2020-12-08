package src.Strategy.BuscaUsuario;

import src.model.Usuario;

import java.util.List;

public interface BuscaUsuarioStrategy {

    public List<Usuario> buscar(List<Usuario> lista, String parametro);
}
