package src.Facade;

import src.Strategy.BuscaSala.BuscaSalaStrategy;
import src.Strategy.BuscaUsuario.BuscaUsuarioStrategy;
import src.UsuaioBuilder.UsuarioPadraoBuilder;
import src.database.SalaDAO;
import src.database.UsuarioDAO;
import src.model.*;

import java.util.ArrayList;
import java.util.List;

public class SistemaBusca {

    public List<Usuario> buscarUsuario(BuscaUsuarioStrategy buscaUsuarioStrategy, String parametro) {
        SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();
        List<Usuario> listaUsuarios = sistemaGetDados.listarUsuarios();
        List<Usuario> usuariosAchados = buscaUsuarioStrategy.buscar(listaUsuarios, parametro);
        return usuariosAchados;
    }

    public Usuario buscarUsuarioCodigo(String codigo) {
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        Usuario usuario = usuarioDAO.buscarPorCodigo(codigo);
        return usuario;
    }

    public Sala buscarSalaCodigo(int codigo) {
        SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();
        SistemaBusca sistemaBusca = new SistemaBusca();
        SalaDAO salaDAO = SalaDAO.getInstance();
        Sala sala = salaDAO.buscarPorCodigo(codigo);
        if(sala == null){
            return null;
        }
        List<Assinante> assinantes = new ArrayList<>();
        for (InscricaoSala inscricaoSala: sistemaGetDados.listarInscricoes()){
            if(inscricaoSala.getCodSala() == codigo){
                assinantes.add(sistemaBusca.buscarUsuarioCodigo(inscricaoSala.getCodUsuario()));
            }
        }
        sala.setAssinantesCadsastrados((ArrayList<Assinante>) assinantes);
        return sala;
    }

    public List<Sala> buscarSala(BuscaSalaStrategy buscaSalaStrategy, String parametro){
        SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();
        List<Sala> salas = sistemaGetDados.listarSalas();
        List<Sala> salasAchadas = buscaSalaStrategy.buscar(salas,parametro);
        return  salasAchadas;
    }

    public List<Dispositivo> buscarDispositivoUsuario(String codigoUsuario) {
        Usuario usuario = new Usuario();
        usuario.setCodigo(codigoUsuario);
        UsuarioPadraoBuilder usuarioPadraoBuilder = new UsuarioPadraoBuilder();
        usuarioPadraoBuilder.informacoeBasicas(usuario);
        usuarioPadraoBuilder.buscarDispositivos();

        usuario = usuarioPadraoBuilder.getUsuario();

        return usuario.getDispositivos();
    }
}
