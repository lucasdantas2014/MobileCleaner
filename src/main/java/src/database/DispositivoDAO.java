package src.database;

import src.model.Dispositivo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class DispositivoDAO{

    private static DispositivoDAO dispositivoDAO;
    private static EntityManager manager = ConexaoBanco.getManager();

    DispositivoDAO() {
        try {
            ConexaoBanco conexaoBanco = ConexaoBanco.getInstance();
            manager = conexaoBanco.getManager();

        }catch (Exception e){
            System.out.println(e);
        }

    }


    public List<Dispositivo> listarTudo(){
        try {
            List<Dispositivo> dispositivos = ConexaoBanco.loadAllData(Dispositivo.class);
            return dispositivos;
        }catch (Exception e){
            System.out.println(e);
            return new ArrayList<Dispositivo>();
        }
    }

    public Dispositivo buscarPorCodigo(int codigo){
        Dispositivo dispositivo;
        try {
            dispositivo = (Dispositivo) manager.find(Dispositivo.class, codigo);
            return dispositivo;
        }catch (Exception e){
            System.out.println(e);
            return new Dispositivo();
        }

    }

    public boolean inserir(Dispositivo dispositivo) {
        try {
            ConexaoBanco.conectar();
            manager.persist(dispositivo);
            manager.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean apagar(Dispositivo dispositivo) {
        try {
            ConexaoBanco.conectar();
            manager.remove(dispositivo);
            manager.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public void atualizar(Dispositivo dispositivo) {
        try {
            ConexaoBanco.conectar();
            manager.merge(dispositivo);
            manager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static DispositivoDAO getInstance(){
        if(dispositivoDAO == null){
            dispositivoDAO = new DispositivoDAO();
        }
        return dispositivoDAO;
    }

}
