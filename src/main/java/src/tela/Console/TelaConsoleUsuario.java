package src.tela.Console;

import java.util.Scanner;

import src.model.Usuario;

import src.tela.Console.Admin.TelaMenuAdmin;
import src.tela.Console.Usuario.*;
import src.tela.Tela;


public class TelaConsoleUsuario implements Tela {

    private Scanner inputStr;
    private Scanner inputInt;

    public TelaConsoleUsuario(){
        inputStr = new Scanner(System.in);
        inputInt = new Scanner(System.in);

    }
    public Usuario telaInicial() {

        TelaInicial telaInicial = new TelaInicial();

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("======Login======");
            System.out.println("1. Logar");
            System.out.println("2. Cadastrar");
            System.out.println("0. Sair");

            opcao = Ferramentas.lerInteiro("Digite a opcao desejada:");

            switch (opcao) {
                case 1:
                    Usuario usuario = telaInicial.login();
                    if (usuario == null){
                        continue;
                    }
                    return usuario;
                case 2:
                    telaInicial.cadastrar();
                    break;
                case 0:
                    return null;
            }
        }

        return null;
    }

    public void telaPrincipal(Object object) {

        Usuario usuario = (Usuario) object;
        TelaMenu telaMenu;
        if(usuario.getCodigo().equals("admin")){
            telaMenu = new TelaMenuAdmin();
        }else{
            telaMenu = new TelaMenuUsuario();
        }

        telaMenu.telaPrincipal(usuario);
    }
}
