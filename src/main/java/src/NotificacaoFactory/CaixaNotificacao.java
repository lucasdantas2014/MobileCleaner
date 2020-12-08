package src.NotificacaoFactory;

import src.model.Notificacao;

public interface CaixaNotificacao {

    public void exibirNotificacao();
    public void marcarLida();
    public void gerarBorda();
    public Notificacao getNotificacao();
}
