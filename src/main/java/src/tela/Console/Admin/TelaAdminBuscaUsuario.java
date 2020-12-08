package src.tela.Console.Admin;

import src.Facade.Sistema;
import src.Facade.SistemaBusca;
import src.Facade.SistemaGetDados;
import src.Strategy.BuscaUsuario.BuscaUsuarioEmail;
import src.Strategy.BuscaUsuario.BuscaUsuarioNome;
import src.Strategy.BuscaUsuario.BuscaUsuarioStrategy;
import src.Strategy.BuscaUsuario.BuscaUsuarioTelefone;
import src.model.Usuario;
import src.tela.Console.Ferramentas;

import java.util.List;

public class TelaAdminBuscaUsuario {

    BuscaUsuarioStrategy buscaUsuarioStrategy;

    public void setBuscaUsuarioStrategy(BuscaUsuarioStrategy buscaUsuarioStrategy){

    }

    public void exibir(){

        Sistema sistema = Sistema.getInstance();

        int opcao = -1;
        while(opcao != 0){

            System.out.println("_-_- Buscar Usuarios _-_-");
            System.out.println("1. Listar Usuários");
            System.out.println("2. Buscar Usuaário por Nome");
            System.out.println("3. Buscar Usuaário por Telefone");
            System.out.println("4. Buscar Usuaário por Email");
            System.out.println("0. Sair");

            opcao = Ferramentas.lerInteiro("Digite a opção que deseja: ");
            switch (opcao){
                case 1:
                    listarTudo();
                case 2:
                    buscaUsuarioStrategy = new BuscaUsuarioNome();
                    buscar("Digite o nome para ser pesquisado");
                    break;
                case 3:
                    buscaUsuarioStrategy = new BuscaUsuarioTelefone();
                    buscar("Digite o telefone para ser pesquisado");
                    break;
                case 4:
                    buscaUsuarioStrategy = new BuscaUsuarioEmail();
                    buscar("Digite o Email a ser pesquisado");
                    break;
                case 0:
                    return;
                default:
                    Ferramentas.mensagemErro("Opção Inválida");

            }
        }


    }

    private void listarTudo() {
        SistemaGetDados sistema = SistemaGetDados.getInstance();
        List<Usuario> usuarios = sistema.listarUsuarios();
        for (Usuario usuario: usuarios){
            System.out.println(usuario);
        }
    }

    public void buscar(String mensagem){
        String parametro = Ferramentas.lerString(mensagem);
        SistemaBusca sistemaBusca = new SistemaBusca();
        List<Usuario> usuariosAchados = sistemaBusca.buscarUsuario(buscaUsuarioStrategy, parametro);
        for (Usuario usuario: usuariosAchados){
            System.out.println(usuario);
        }

    }
}



