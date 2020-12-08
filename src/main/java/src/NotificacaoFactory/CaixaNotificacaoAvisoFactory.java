package src.NotificacaoFactory;

import src.model.Notificacao;

public class CaixaNotificacaoAvisoFactory implements CaixaNotificacaoFactory{

    public CaixaNotificacao criar(Notificacao notificacao){
        return new CaixaNotificacaoAviso(notificacao);
    }

    public CaixaNotificacao criar(String emissor, String mensagem, String codReceptor){
        Notificacao notificacao = new Notificacao(emissor, mensagem, codReceptor);
        return new CaixaNotificacaoAviso(notificacao);
    }
}
