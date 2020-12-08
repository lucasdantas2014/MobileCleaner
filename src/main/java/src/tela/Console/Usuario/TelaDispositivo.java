package src.tela.Console.Usuario;

import src.Facade.Sistema;
import src.model.Dispositivo;
import src.tela.Console.Ferramentas;

import java.util.List;

public class TelaDispositivo {


    public static List<Dispositivo> menuDispositivos(List<Dispositivo> dispositivos, String codigoUsuario){

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("=== Dispositivos ===");
            System.out.println("1. Ver dispositivos");
            System.out.println("2. Adicionar dispositivo");
            System.out.println("3. Editar dispositivo");
            System.out.println("4. Remover dispositivo");
            System.out.println("0. Sair");

            opcao = Ferramentas.lerInteiro("Digite a opção desejada");

            switch (opcao){
                case 1:
                    exibirDispositvos(dispositivos);
                    break;
                case 2:
                    adicionarDispositivo(dispositivos, codigoUsuario);
                    break;
                case 3:
                    editarDispositivo(dispositivos);
                    break;
                case 4:
                    removerDispositivo(dispositivos);
                    break;
                case 0:
                    return dispositivos;
                default:
                    Ferramentas.mensagemErro("Opcção inválida");
            }
        }

        return dispositivos;
    }

    private static void adicionarDispositivo(List<Dispositivo> dispositivos, String cod_usuario) {
        System.out.println("----- Adicionar Dispositivo ------");
        String nome = Ferramentas.lerString("Digite um nome para dispositivo:");

        int alertaLimpeza = Ferramentas.lerInteiro("Informe a quantidade de dias para que seja enviado o alerta de limpeza");
        Dispositivo dispositivo = new Dispositivo(nome, cod_usuario);
        dispositivo.setTempoAlerta("" + alertaLimpeza);

        Sistema sistema = Sistema.getInstance();
        boolean suceeso = sistema.adicionarDispositivo(dispositivo);
        if (suceeso) {
            dispositivos.add(dispositivo);
        }
    }

    private static void editarDispositivo(List<Dispositivo> dispositivos){
        exibirDispositvos(dispositivos);
        int idDispositivo = Ferramentas.lerInteiro("Digite o indice do dispositivo para editar");

        Dispositivo dispositivo = dispositivos.get(idDispositivo);
        int opcao = -1;
        while (opcao != 0){
            System.out.println("Dispositivo - " + dispositivo.getNome() + " Codigo: " + dispositivo.getCodigo() + " Tempo do alerta: " + dispositivo.getTempoAlerta());
            System.out.println("1 - Alterar nome:");
            System.out.println("2 - Alterar tempo de Alerta");
            System.out.println("0 - Sair da Edicao");

            opcao = Ferramentas.lerInteiro("Digite a opcao deseja:");

            switch (opcao){
                case 1:
                    String novoNome = Ferramentas.lerString("Insira o novo nome:");
                    dispositivo.setNome(novoNome);
                    break;
                case 2:
                    int novoTempo = Ferramentas.lerInteiro("Insira o novo nome:");
                    dispositivo.setTempoAlerta("" + novoTempo);
                    break;
                case 0:
                    Sistema sistema = Sistema.getInstance();
                    sistema.atualizarDispositivo(dispositivo);
                    break;
                default:
                    Ferramentas.mensagemErro("Opção Inválida");

            }
        }
    }

    private static void removerDispositivo(List<Dispositivo> dispositivos){
        exibirDispositvos(dispositivos);
        int idDispositivo = Ferramentas.lerInteiro("Digite o indice do dispositivo para remover");

        Dispositivo dispositivo = dispositivos.get(idDispositivo);

        System.out.println("Você deseja mesmo remover o dispositivo?: ");
        System.out.println("Codigo:" + dispositivo.getCodigo());
        System.out.println("Nome: " + dispositivo.getNome());
        System.out.println("Ultima limpeza:" + dispositivo.getUltimaLimpeza());

        int opcao;
        do {
            opcao = Ferramentas.lerInteiro("Digite 1 para confirmar | Digite 2 para cancelar");
            if (opcao == 1){
                Sistema sistema = Sistema.getInstance();
                boolean sucesso = sistema.removerDispositivo(dispositivo);
                if(sucesso){
                    dispositivos.remove(dispositivo);
                }
            }else if(opcao == 2){
                return;
            }else{
                Ferramentas.mensagemErro("Opção Inválida");
            }
        }while (opcao != 1 && opcao != 2);

    }

    private static void exibirDispositvos(List<Dispositivo> dispositivos){

        System.out.println("----- Seus Dispositivos -----");
        Dispositivo dispositivo;
        for (int i = 0; i < dispositivos.size(); i++){
            dispositivo = dispositivos.get(i);
            System.out.println("Dispositvo - " + i);
            System.out.println("Codigo: " + dispositivo.getCodigo());
            System.out.println("Nome: " + dispositivo.getNome());
            System.out.println("Ultima Limpeza: " + dispositivo.getUltimaLimpeza());
            System.out.println("\n\n");
        }

    }
}
