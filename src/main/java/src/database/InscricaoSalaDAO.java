package src.database;

import src.model.InscricaoSala;
import src.model.InscricaoSala;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

import static src.database.ConexaoBanco.loadAllData;

public class InscricaoSalaDAO {
    private static InscricaoSalaDAO inscricaoSalaDAO;
    private static EntityManager manager = ConexaoBanco.getManager();

    InscricaoSalaDAO() {
        try {
            ConexaoBanco conexaoBanco = ConexaoBanco.getInstance();
            manager = conexaoBanco.getManager();

        }catch (Exception e){
            System.out.println(e);
        }

    }


    public List<InscricaoSala> listarTudo(){
        try {
            List<InscricaoSala> InscricaoSalas = ConexaoBanco.loadAllData(InscricaoSala.class);
            return InscricaoSalas;
        }catch (Exception e){
            System.out.println(e);
            return new ArrayList<InscricaoSala>();
        }
    }

    public InscricaoSala buscarPorCodigo(int codigo){
        InscricaoSala inscricaoSala;
        try {
            inscricaoSala = (InscricaoSala) manager.find(InscricaoSala.class, codigo);
            return inscricaoSala;
        }catch (Exception e){
            System.out.println(e);
            return new InscricaoSala();
        }

    }

    public boolean inserir(InscricaoSala inscricaoSala) {
        try {
            ConexaoBanco.conectar();
            manager.persist(inscricaoSala);
            manager.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public void apagar(InscricaoSala inscricaoSala) {
        try {
            ConexaoBanco.conectar();
            manager.remove(inscricaoSala);
            manager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void atualizar(InscricaoSala inscricaoSala) {
        try {
            ConexaoBanco.conectar();
            manager.merge(inscricaoSala);
            manager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static src.database.InscricaoSalaDAO getInstance() {
        if(inscricaoSalaDAO == null){
            inscricaoSalaDAO = new InscricaoSalaDAO();
        }
        return inscricaoSalaDAO;
    }
}
