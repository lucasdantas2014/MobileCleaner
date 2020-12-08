package src.Strategy.BuscaUsuario;

import src.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BuscaUsuarioEmail implements BuscaUsuarioStrategy{
    @Override
    public List<Usuario> buscar(List<Usuario> listaUsuarios, String email) {
        List <Usuario> usuariosAchados = new ArrayList<Usuario>();

        for (Usuario usuario: listaUsuarios){
            if(usuario.getEmail().toUpperCase().contains(email.toUpperCase())){
                usuariosAchados.add(usuario);
            }
        }
        return usuariosAchados;

    }
}
