package src.Strategy.BuscaUsuario;

import src.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BuscaUsuarioNome implements BuscaUsuarioStrategy{

    @Override
    public List<Usuario> buscar(List<Usuario> lista, String parametro) {
        List <Usuario> usuariosAchados = new ArrayList<Usuario>();

        for (Usuario usuario: lista){
            if(usuario.getNome().toUpperCase().contains(parametro.toUpperCase())){
                usuariosAchados.add(usuario);
            }
        }
        return usuariosAchados;
    }
}
