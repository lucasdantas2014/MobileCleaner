package src.database;

import src.model.Notificacao;
import src.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAO {


    private static NotificacaoDAO notificacaoDAO;
    private EntityManager manager = ConexaoBanco.getManager();

    NotificacaoDAO(){

        ConexaoBanco conexaoBanco = ConexaoBanco.getInstance();
        manager = conexaoBanco.getManager();

    }

    public List<Notificacao> listarTudo(){
        try {
            List<Notificacao> notificacoes = ConexaoBanco.loadAllData(Notificacao.class);
            return notificacoes;
        }catch (Exception e){
            System.out.println(e);
            return new ArrayList<Notificacao>();
        }
    }

    public Notificacao buscarPorCodigo(String codigo){
        Notificacao notificacao;
        try {
            notificacao = (Notificacao) manager.find(Notificacao.class, codigo);
            return notificacao;
        }catch (Exception e){
            System.out.println(e);
            return new Notificacao();
        }
    }

    public boolean inserir(Notificacao notificacao) {
            ConexaoBanco.conectar();
            manager.persist(notificacao);
            manager.getTransaction().commit();
            return true;
    }

    public void apagar(Notificacao notificacao) {
        try {
            ConexaoBanco.conectar();
            manager.remove(notificacao);
            manager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void atualizar(Notificacao notificacao) {
        try {
            ConexaoBanco.conectar();
            manager.merge(notificacao);
            manager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static NotificacaoDAO getInstance(){
        if(notificacaoDAO == null){
            notificacaoDAO = new NotificacaoDAO();
        }
        return notificacaoDAO;
    }

}
