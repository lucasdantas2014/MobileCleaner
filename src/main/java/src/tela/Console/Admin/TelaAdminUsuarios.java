package src.tela.Console.Admin;

import src.Facade.Sistema;
import src.Facade.SistemaBusca;
import src.Facade.SistemaGetDados;
import src.model.Usuario;
import src.tela.Console.Ferramentas;
import src.tela.Console.Usuario.TelaInicial;
import src.tela.Console.Usuario.TelaPerfil;

import java.util.List;

public class TelaAdminUsuarios {

    public void exibir(){
        int opcao = -1;
        while(opcao != 0){

            System.out.println("_-_- Usuarios _-_-");
            System.out.println("1. Ver Usuários");
            System.out.println("5. Cadastrar Usuario");
            System.out.println("6. Remover Usuario");
            System.out.println("7. Editar Usuario");
            System.out.println("0. Sair");

            opcao = Ferramentas.lerInteiro("Digite a opção desejda:");

            switch (opcao){
                case 1:
                    TelaAdminBuscaUsuario telaAdminBuscaUsuario = new TelaAdminBuscaUsuario();
                    telaAdminBuscaUsuario.exibir();
                    break;
                case 5:
                    TelaInicial telaInicialUsuario = new TelaInicial();
                    telaInicialUsuario.cadastrar();
                    break;
                case 6:
                    removerUsuario();
                    break;
                case 7:
                    editarUsuario();
                case 0:
                    return;
                default:
                    Ferramentas.mensagemErro("Opção Inválida");
            }
        }
    }

    private void editarUsuario() {
        SistemaGetDados sistema = SistemaGetDados.getInstance();
        List<Usuario> usuarios = sistema.listarUsuarios();
        int cont = 1;
        for (Usuario usuario: usuarios){
            System.out.println("" + cont + " - " + usuario);
        }

        int indice = Ferramentas.lerInteiro("Digite o indice do usuario para ser editado");

        Usuario usuario = usuarios.get(indice);

        TelaPerfil telaPerfil = new TelaPerfil();
        telaPerfil.editarPerfil(usuario);
    }

    private void removerUsuario() {
        Sistema sistema = Sistema.getInstance();
        SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();
        List<Usuario> usuarios = sistemaGetDados.listarUsuarios();
        int cont = 1;
        for (Usuario usuario: usuarios){
            System.out.println("" + cont + " - " + usuario);
        }

        int indice = Ferramentas.lerInteiro("Digite o indice do usuario para ser removido");

        Usuario usuario = usuarios.get(indice);


        int confirmacao = 0;

        System.out.println("Você tem certeza que quer remover:" + usuario + "?");
        while (confirmacao != 2) {
            System.out.println("Digite 1 para remover | Digite 2 para cancelar ");

             confirmacao = Ferramentas.lerInteiro("");


            switch (confirmacao) {
                case 1:
                    boolean sucesso = sistema.removerUsuario(usuario);
                    if (sucesso) {
                        Ferramentas.mensagemSucesso("Usuario Remocvido com sucesso");
                    } else {
                        Ferramentas.mensagemErro("Não foi possivel remover o usuário");
                    }
                    confirmacao = 2;
                    break;
                case 2:
                    return;
                default:
                    Ferramentas.mensagemErro("Opcção Inválida");
            }
        }

    }

    private void buscarUsuarioPorCodigo() {
        SistemaBusca sistemaBusca = new SistemaBusca();
        String codigo = Ferramentas.lerString("Digite o codigo do usuario");
        Usuario usuario = sistemaBusca.buscarUsuarioCodigo(codigo);
        System.out.println(usuario);
    }

    private void listarUsuarios() {
        SistemaGetDados sistema = SistemaGetDados.getInstance();
        List<Usuario> usuarios = sistema.listarUsuarios();
        for (Usuario usuario: usuarios){
            System.out.println(usuario);
        }
    }
}
