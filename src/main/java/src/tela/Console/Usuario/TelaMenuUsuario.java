package src.tela.Console.Usuario;

import src.Facade.Sistema;
import src.Facade.SistemaLimpeza;
import src.model.Usuario;
import src.tela.Console.Ferramentas;
import src.tela.Console.TelaMenu;

public class TelaMenuUsuario implements TelaMenu {

    private Sistema sistema = Sistema.getInstance();

    public void telaPrincipal(Usuario usuario) {

        SistemaLimpeza sistemaLimpeza = SistemaLimpeza.getInstance();
        sistemaLimpeza.verificarDispositivos(usuario);

        int opcao = -1;
        while (opcao != 0) {

            System.out.println("===== Olá " + usuario.getNome() + "=======");
            System.out.println("1. Dispositivos");
            System.out.println("2. Salas");
            System.out.println("3. Perfil");
            System.out.println("4. Notificacoes");
            System.out.println("0. Sair");

            opcao = Ferramentas.lerInteiro("Digite a opcao que deseja:");

            switch (opcao){
                case 1:
                    usuario.setDispositivos(TelaDispositivo.menuDispositivos(usuario.getDispositivos(), usuario.getCodigo()));
                    break;
                case 2:
                    TelaSala.mostrarSalas(usuario);
                    break;
                case 3:
                    TelaPerfil telaPerfil = new TelaPerfil();
                    usuario = telaPerfil.menuPerfil(usuario);
                    break;
                case 4:
                    TelaNotificacoes telaNotificacoes = new TelaNotificacoes();
                    telaNotificacoes.exibirNotificacoes(usuario);
                    break;
                case 0:
                    return;
                default:
                    Ferramentas.mensagemErro("Opcao Inválida");
            }
        }
    }
}
