package src.database;

import org.apache.log4j.Logger;
import src.UsuarioMain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class ConexaoBanco {

    private static ConexaoBanco conexaoBanco;
    private static EntityManager manager;
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernate");

    private ConexaoBanco(){
        this.manager = factory.createEntityManager();
    };


    public static ConexaoBanco getInstance(){
        if(conexaoBanco == null){
            conexaoBanco = new ConexaoBanco();
        }
        return conexaoBanco;
    }
    public static void conectar(){
        try{
            manager.getTransaction().begin();
        }catch (IllegalStateException e){
            System.out.println("Conexão já estabelecida");
        }
    }

    public static void finalizarConexao(){
        if(manager != null){
            manager.close();
        }
    }

    public static <T> List<T> loadAllData(Class<T> type) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(type);
            criteria.from(type);
            List<T> data = manager.createQuery(criteria).getResultList();
            return data;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<T>();
        }
    }

    public static EntityManager getManager() {
        return manager;
    }
}
