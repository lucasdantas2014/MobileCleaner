package src.database;

import src.model.Dispositivo;
import src.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static UsuarioDAO usuarioDAO;
    private  static EntityManager manager = ConexaoBanco.getManager();

    UsuarioDAO(){
        ConexaoBanco conexaoBanco = ConexaoBanco.getInstance();
        manager = conexaoBanco.getManager();
        System.out.println("Conexão com o banco de dados estabelecida");

    }

    public List<Usuario> listarTudo(){
        try {
            List<Usuario> usuarios = ConexaoBanco.loadAllData(Usuario.class);
            return usuarios;
        }catch (Exception e){
            System.out.println(e);
            return new ArrayList<Usuario>();
        }
    }

    public Usuario buscarPorCodigo(String codigo){
        Usuario usuario;
        try {
            usuario = (Usuario) manager.find(Usuario.class, codigo);
            return usuario;
        }catch (Exception e){
            System.out.println(e);
            return new Usuario();
        }
    }

    public boolean inserir(Usuario usuario) {
        try {
            ConexaoBanco.conectar();
            manager.persist(usuario);
            manager.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public void apagar(Usuario usuario) {
        try {
            ConexaoBanco.conectar();
            manager.remove(usuario);
            manager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void atualizar(Usuario usuario) {
        try {
            ConexaoBanco.conectar();
            manager.merge(usuario);
            manager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static UsuarioDAO getInstance(){
        if(usuarioDAO == null){
            usuarioDAO = new UsuarioDAO();
        }
        return usuarioDAO;
    }
}
