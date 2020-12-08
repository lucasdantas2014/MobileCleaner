package src.Facade;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import src.database.*;
import src.model.*;

import java.util.List;

public class SistemaGetDados{

    private static SistemaGetDados sistemaGetDados;

    private SistemaGetDados(){}

    public static SistemaGetDados getInstance(){
        if(sistemaGetDados == null){
            sistemaGetDados = new SistemaGetDados();
        }
        return sistemaGetDados;
    }

    public List<Sala> listarSalas() {
            SalaDAO salaDAO = SalaDAO.getInstance();
            return salaDAO.listarTudo();
    }

    public List<Usuario> listarUsuarios() {
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        return usuarioDAO.listarTudo();
    }

    public List<Dispositivo> listarDispositivos() {
        DispositivoDAO dispositivoDAO = DispositivoDAO.getInstance();
        List<Dispositivo> dispositivos = dispositivoDAO.listarTudo();
        return dispositivos;
    }


    public List<InscricaoSala> listarInscricoes() {
        InscricaoSalaDAO inscricaoDAO = InscricaoSalaDAO.getInstance();
        List<InscricaoSala> inscricoes = inscricaoDAO.listarTudo();
        return inscricoes;
    }

    public List<Notificacao> listarNotificacoes() {
        NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance();
        List<Notificacao> notificacoes = notificacaoDAO.listarTudo();
        return notificacoes;
    }


}
