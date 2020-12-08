package src.tela.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ferramentas {

    private static Scanner inputStr = new Scanner(System.in);
    private static Scanner inputInt = new Scanner(System.in);

    public static String lerString(String mensagem){
        System.out.println(mensagem);
        String resposta = "";
        try{
            resposta = inputStr.nextLine();
        }catch (Exception e){
            System.out.println(e);
        }

        return resposta;
    }

    public static int lerInteiro(String mensagem){
        System.out.println(mensagem);
        int resposta = 0;
        boolean ok = false;
        while (!ok) {
            try {
                resposta = inputInt.nextInt();
                ok = true;
            } catch (Exception e) {
                System.out.println("Você precisa digitar um número");
                inputInt = new Scanner(System.in);
            }
        }

        return resposta;
    }


    public static void mensagemCrregando(String mensagem) {
        System.out.println( ". º . º ." + mensagem);
    }

    public static void mensagemSucesso(String mensagem) {
        System.out.println("_ > _ > _ > Sucesso: " + mensagem);
    }

    public static void mensagemErro(String mensagem) {
        System.out.println("!! !! !!" + mensagem + "!! !! !!");
    }

    public static int selecionarOpcao(List lista, String mensagemInput){
        try {

            int indice = -1;
            int max = lista.size();
            while (indice < 0 || indice >= max) {
                int cont = 0;
                for (Object obj : lista) {
                    System.out.println("" + cont + " - " + obj);
                    cont++;
                }

                indice = lerInteiro(mensagemInput);
                if(indice == -1){
                    return -1;
                }
                if(indice < 0 || indice >= max){
                    System.out.println("opcao Inválida");
                }

            }
            return indice;
        }catch (Exception e){
            System.out.println(e);
            return -1;
        }

    }
}
