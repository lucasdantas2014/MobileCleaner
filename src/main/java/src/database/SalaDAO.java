package src.database;

import src.model.Dispositivo;
import src.model.Sala;
import src.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {

    private static SalaDAO salaDAO;
    private static EntityManager manager = ConexaoBanco.getManager();


    SalaDAO(){
        ConexaoBanco conexaoBanco = ConexaoBanco.getInstance();
        manager = conexaoBanco.getManager();
    }


    public List<Sala> listarTudo(){
            List<Sala> salas = ConexaoBanco.loadAllData(Sala.class);
            return salas;
    }

    public Sala buscarPorCodigo(int codigo){
        try {
            ConexaoBanco.conectar();
            Sala sala = (Sala) manager.find(Sala.class, codigo);
            return sala;
        }catch (Exception e){
            System.out.println(e);
        }
        return new Sala();
    }

    public boolean inserir(Sala sala) {
        try {
            ConexaoBanco.conectar();
            manager.persist(sala);
            manager.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public void apagar(Sala sala) {
        try {
            ConexaoBanco.conectar();
            manager.remove(sala);
            manager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void atualizar(Sala sala) {
        try {
            ConexaoBanco.conectar();
            manager.merge(sala);
            manager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static SalaDAO getInstance(){
        if(salaDAO == null){
            salaDAO = new SalaDAO();
        }

        return salaDAO;
    }

}
