package src.NotificacaoFactory;

import src.model.Notificacao;

public interface CaixaNotificacaoFactory {

    public CaixaNotificacao criar(Notificacao notificacao);
    public CaixaNotificacao criar(String emissor, String mensagem, String receptor);

}
