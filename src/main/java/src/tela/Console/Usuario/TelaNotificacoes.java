package src.tela.Console.Usuario;

import src.NotificacaoFactory.CaixaNotificacao;
import src.NotificacaoFactory.CaixaNotificacaoAlertaFactory;
import src.NotificacaoFactory.CaixaNotificacaoAvisoFactory;
import src.NotificacaoFactory.CaixaNotificacaoFactory;
import src.model.Notificacao;
import src.model.Usuario;
import src.tela.Console.Ferramentas;

public class TelaNotificacoes {

    public void exibirNotificacoes(Usuario usuario){
        int op = -1;

        while (op != 0) {
            System.out.println("\n\n====== Notificacoes ======");
            System.out.println("1. Ver Todas as notificacoes - "  + "(" + usuario.getQtdNovasNotificacoes() +") notificações não lidas");
            System.out.println("0. Sair");

            op = Ferramentas.lerInteiro("Digite a opção que deseja:");
            switch (op) {
                case 1:
                    verTodasNotificacoes(usuario);
                    break;
                case 0:
                    return;
            }
        }
    }

    private void verTodasNotificacoes(Usuario usuario) {
        CaixaNotificacaoFactory caixaNotificacaoFactory;
        CaixaNotificacao caixaNotificacao;

        for (Notificacao notificacao: usuario.getNotificacoes()){
            if(notificacao.getEmissor().equals("sistema")){
                caixaNotificacaoFactory = new CaixaNotificacaoAlertaFactory();
            }else{
                caixaNotificacaoFactory = new CaixaNotificacaoAvisoFactory();
            }

            caixaNotificacao = caixaNotificacaoFactory.criar(notificacao);
            caixaNotificacao.marcarLida();
            caixaNotificacao.exibirNotificacao();
        }
        usuario.setQtdNovasNotificacoes(0);
    }
}
