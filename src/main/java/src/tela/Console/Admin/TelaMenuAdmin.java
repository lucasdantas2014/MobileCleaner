package src.tela.Console.Admin;

import src.Facade.Sistema;
import src.model.Usuario;
import src.tela.Console.Ferramentas;
import src.tela.Console.TelaMenu;

public class TelaMenuAdmin implements TelaMenu {

    public void telaPrincipal(Usuario usuario) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("===== TELA _ ADMIN =====");
            System.out.println("1. Usuarios");
            System.out.println("2. Salas");
            System.out.println("3. Perfil");
            System.out.println("0. Sair");

            opcao = Ferramentas.lerInteiro("Digite a opcao que deseja:");

            switch (opcao) {
                case 1:
                    TelaAdminUsuarios telaAdminUsuarios = new TelaAdminUsuarios();
                    telaAdminUsuarios.exibir();
                    break;
                case 2:
                    TelaAdminSalas telaAdminSalas = new TelaAdminSalas();
                    telaAdminSalas.executar();
                    break;
                case 3:
                    usuario = telaAdminPerfil(usuario);
                    break;
                case 0:
                    return;
            }
        }
    }

    private Usuario telaAdminPerfil(Usuario usuario){

        int modificado = 0;
        int op = -1;
        String senhaAntiga = usuario.getSenha();
        while (op != 0) {
            System.out.println("Perfil Admin");
            System.out.println("1. Alterar Senha:");
            System.out.println("0. Sair");

            op = Ferramentas.lerInteiro("Digite a opção desejada:");
            switch (op) {
                case 1:
                    String novaSenha = Ferramentas.lerString("Digite a nova senha");
                    usuario.setSenha(novaSenha);
                    modificado = 1;
                    break;
                case 0:
                    if (modificado == 1) {
                        Sistema sistema = Sistema.getInstance();
                        boolean sucesso = sistema.atualizarUsuario(usuario);
                        if (sucesso) {
                            return usuario;
                        } else {
                            usuario.setSenha(senhaAntiga);
                            return usuario;
                        }
                    }
                    return null;
                default:
                    Ferramentas.mensagemErro("Opção Inválida");
            }
        }
       return null;
    }
}
