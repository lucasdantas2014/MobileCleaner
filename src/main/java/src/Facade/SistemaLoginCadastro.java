package src.Facade;

import src.UsuaioBuilder.UsuarioAdminBuilder;
import src.UsuaioBuilder.UsuarioBuilder;
import src.UsuaioBuilder.UsuarioDirector;
import src.UsuaioBuilder.UsuarioPadraoBuilder;
import src.database.UsuarioDAO;
import src.model.Usuario;

public class SistemaLoginCadastro {

    private static SistemaLoginCadastro sistemaLoginCadastro;

    private SistemaLoginCadastro(){};

    public Usuario logar(String cpf, String senha){

        Usuario resultadoBusca;

        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        resultadoBusca = usuarioDAO.buscarPorCodigo(cpf);

        return resultadoBusca;
    }

    public void cadastrarUsuario(Usuario usuario) {
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        usuarioDAO.inserir(usuario);
    }

    public static SistemaLoginCadastro getInstance(){
        if(sistemaLoginCadastro == null){
            sistemaLoginCadastro = new SistemaLoginCadastro();
        }
        return sistemaLoginCadastro;
    }

    public Usuario construirUsuario(Usuario usuario) {
        UsuarioBuilder usuarioBuilder;
        if(usuario.getCodigo() == "admin"){
            usuarioBuilder = new UsuarioAdminBuilder();
        }else{
            usuarioBuilder = new UsuarioPadraoBuilder();
        }
        UsuarioDirector usuarioDirector = new UsuarioDirector(usuarioBuilder);
        usuarioDirector.construirUsuario(usuario);
        return usuarioDirector.getUsuario();

    }
}
