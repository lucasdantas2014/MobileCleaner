package src.NotificacaoFactory;

import src.model.Notificacao;

public class CaixaNotificacaoAlerta implements CaixaNotificacao{

    private Notificacao notificacao;
    private String borda;

    public CaixaNotificacaoAlerta(Notificacao notificacao) {
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
        this.notificacao.setLida(true);
    }

    @Override
    public void gerarBorda() {
        borda = "!!!_____Alerta____!!!";
    }

    @Override
    public Notificacao getNotificacao() {
        return notificacao;
    }
}
