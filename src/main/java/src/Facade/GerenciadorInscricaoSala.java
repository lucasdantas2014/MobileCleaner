package src.Facade;

import src.database.DispositivoDAO;
import src.database.InscricaoSalaDAO;
import src.model.Assinante;
import src.model.InscricaoSala;
import src.model.Sala;
import src.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorInscricaoSala {

    public boolean cancelarInscricao(int codSala, String codUsuario) {
        try {
            SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();
            List<InscricaoSala> inscricoes = sistemaGetDados.listarInscricoes();
            InscricaoSalaDAO inscricaoSalaDAO = InscricaoSalaDAO.getInstance();

            for(InscricaoSala inscricao: inscricoes){
                if(inscricao.getCodUsuario().equals(codUsuario) && inscricao.getCodSala() == codSala){
                    inscricaoSalaDAO.apagar(inscricao);
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Não foi possível cancelar a inscrição, tente novamente");
            return false;
        }
    }

    public void inscreverSala(String cod_usuario, Integer cod_sala){
        InscricaoSalaDAO inscricaoSalaDAO = InscricaoSalaDAO.getInstance();
        InscricaoSala inscricaoSala = new InscricaoSala();
        inscricaoSala.setCodSala(cod_sala);
        inscricaoSala.setCodUsuario(cod_usuario);
        inscricaoSalaDAO.inserir(inscricaoSala);

        SistemaBusca sistemaBusca = new SistemaBusca();
        Sala sala = sistemaBusca.buscarSalaCodigo(cod_sala);
        Usuario usuario = sistemaBusca.buscarUsuarioCodigo(cod_usuario);
        List<Assinante>  assinantes = sala.getAssinantesCadsastrados();
        assinantes.add(usuario);
        sala.setAssinantesCadsastrados((ArrayList<Assinante>) assinantes);
    }
}
