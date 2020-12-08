package src.NotificacaoFactory;

import src.model.Notificacao;

public class CaixaNotificacaoAlertaFactory implements CaixaNotificacaoFactory{
    @Override
    public CaixaNotificacao criar(Notificacao notificacao) {
        return new CaixaNotificacaoAlerta(notificacao);
    }

    @Override
    public CaixaNotificacao criar(String emissor, String mensagem, String receptor) {
        Notificacao notificacao = new Notificacao(emissor, mensagem, receptor);
        return new CaixaNotificacaoAlerta(notificacao);
    }
}
