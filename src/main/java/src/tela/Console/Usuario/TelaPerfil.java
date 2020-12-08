package src.tela.Console.Usuario;

import src.Facade.Sistema;
import src.model.Usuario;
import src.tela.Console.Ferramentas;

public class TelaPerfil {

    public Usuario menuPerfil(Usuario usuario){
        int op = -1;
        while (op != 0) {
            System.out.println("===== Perfil ----- ");
            System.out.println(usuario);
            System.out.println("----- Menu -----");
            System.out.println("1. Editar Perfil");
            System.out.println("0. Sair");

            op = Ferramentas.lerInteiro("Digite a opção desejada: ");

            switch (op) {
                case 1:
                    usuario = editarPerfil(usuario);
                    break;
                case 0:
                    return usuario;
                default:
                    System.out.println("Opcção Inválida");
            }
        }

        return usuario;
    }

    public Usuario editarPerfil(Usuario usuario) {
        int op = -1;
        int modificado = 0;

        String nomeAntigo = usuario.getNome();
        String emailAntigo = usuario.getEmail();
        String senhaAntiga = usuario.getSenha();
        String telefoneAntigo = usuario.getTelefone();

        while (op != 0){

            System.out.println("==== Editar Perfil ====");
            System.out.println("1- Editar Nome");
            System.out.println("2- Editar email");
            System.out.println("3- Alterar senha");
            System.out.println("4- Editar Telefone");
            System.out.println("0- Sair");

            op = Ferramentas.lerInteiro("Digite a opção desejada: ");

            switch (op){
                case 1:
                    String novoNome = Ferramentas.lerString("Digite o novo nome: ");
                    usuario.setNome(novoNome);
                    modificado = 1;
                    break;
                case 2:
                    String novoEmail = Ferramentas.lerString("Digite o novo email: ");
                    usuario.setEmail(novoEmail);
                    modificado = 1;
                    break;
                case 3:
                    String novaSenha = Ferramentas.lerString("Digite a nova senha: ");
                    usuario.setSenha(novaSenha);
                    modificado = 1;
                    break;
                case 4:
                    String novoTelefone = Ferramentas.lerString("Digite o novo Telefone: ");
                    usuario.setTelefone(novoTelefone);
                    modificado = 1;
                    break;
                case 0:
                    if(modificado == 1) {
                        Sistema sistema = Sistema.getInstance();
                        boolean suceeso = sistema.atualizarUsuario(usuario);

                        if (suceeso) {
                            System.out.println("Atualização realizada");
                            return usuario;
                        } else {
                            Ferramentas.mensagemErro("Falha na atualização");
                            usuario.setNome(nomeAntigo);
                            usuario.setEmail(emailAntigo);
                            usuario.setTelefone(telefoneAntigo);
                            usuario.setSenha(senhaAntiga);
                            return usuario;
                        }
                    }else{
                        return usuario;
                    }
            }
        }

        return null;
    }
}
