package src.NotificacaoFactory;

import src.model.Notificacao;

import java.util.Date;

public class CaixaNotificacaoAviso implements CaixaNotificacao{

    private Notificacao notificacao;
    private String borda;

    public CaixaNotificacaoAviso(Notificacao notificacao) {
        this.notificacao = notificacao;
    }

    @Override
    public void exibirNotificacao() {
        gerarBorda();
        System.out.println("\n" + borda);
        System.out.println("Enviado por: " + notificacao.getEmissor());
        System.out.println("Data Envio:" + notificacao.getDataEnvio());
        System.out.println("Mensagem: " + notificacao.getMensagem());
        System.out.println(borda + "\n");
    }

    @Override
    public void marcarLida() {
        notificacao.setLida(true);
    }

    @Override
    public void gerarBorda() {
        borda = ".°.°.Aviso.°.°.°";
    }

    @Override
    public Notificacao getNotificacao() {
        return notificacao;
    }
}
