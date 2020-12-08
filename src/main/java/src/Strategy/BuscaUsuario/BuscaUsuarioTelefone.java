package src.Strategy.BuscaUsuario;

import src.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BuscaUsuarioTelefone implements BuscaUsuarioStrategy{
    @Override
    public List<Usuario> buscar(List<Usuario> listaUsuarios, String telefone) {

        List <Usuario> usuariosAchados = new ArrayList<Usuario>();
        for (Usuario usuario: listaUsuarios){
            if(usuario.getTelefone().contains(telefone)){
                usuariosAchados.add(usuario);
            }
        }
        return usuariosAchados;
    }
}
