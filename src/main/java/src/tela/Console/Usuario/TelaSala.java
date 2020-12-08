package src.tela.Console.Usuario;

import src.Facade.GerenciadorInscricaoSala;
import src.Facade.Sistema;
import src.Facade.SistemaBusca;
import src.Facade.SistemaGetDados;
import src.Strategy.BuscaSala.BuscaSalaNome;
import src.Strategy.BuscaSala.BuscaSalaStrategy;
import src.model.Sala;
import src.model.Usuario;
import src.tela.Console.Ferramentas;

import java.util.ArrayList;
import java.util.List;

public class TelaSala{

    public static void mostrarSalas(Usuario usuario){

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("--- Salas ---");
            System.out.println("1 - pesquisar Sala por nome:");
            System.out.println("2 - Pesquisar Sala por codigo");
            System.out.println("3 - Exibir todas as salas:");
            System.out.println("4 - Inscrever Na Sala");
            System.out.println("5 - Cancelar Inscricao");
            System.out.println("6 - Ver salas em que está inscrito");
            System.out.println("0 - Sair:");

            opcao = Ferramentas.lerInteiro("Digite a opcao desejada");

            switch (opcao){
                case 1:
                    pesquisarSalaNome();
                    break;

                case 2:
                    pesquisarSalaCodigo();
                    break;

                case 3:
                    ExibirTodasSalas();
                    break;

                case 4:
                    inscreverNaSala(usuario);
                    break;
                case 5:
                    cancelarIncricaoNaSala(usuario);
                    break;
                case 6:
                    verSalasInscrito(usuario);
                    break;
                case 0:
                    return;

                default:
                    Ferramentas.mensagemErro("Opcao Inválida");
            }
        }
    }

    private static void verSalasInscrito(Usuario usuario) {
        for (Sala sala: usuario.getSalasCadastradas()){
            System.out.println(sala);
        }
    }

    private static void cancelarIncricaoNaSala(Usuario usuario) {
        ArrayList<Sala> salas = usuario.getSalasCadastradas();
        int op = Ferramentas.selecionarOpcao(salas, "Digite o indice da sala em que deseja cancelar a inscrição");
        Sistema sistema = Sistema.getInstance();
        salas.get(op).cancelarInscricao(usuario);
        salas.remove(op);
        usuario.setSalasCadastradas(salas);
    }
    private static void inscreverNaSala(Usuario usuario) {
        SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();
        List<Sala> salas = sistemaGetDados.listarSalas();
        int indice = Ferramentas.selecionarOpcao(salas, "Digite o indice da sala em que deseja se inscrever");
        if(usuario.getSalasCadastradas().contains(salas.get(indice))){
            return;
        }
        salas.get(indice).inscrever(usuario);

    }

    private static void ExibirTodasSalas() {
        SistemaBusca sistemaBusca = new SistemaBusca();
        BuscaSalaStrategy buscaSalaStrategyNome = new BuscaSalaNome();
        List<Sala> salas = sistemaBusca.buscarSala(buscaSalaStrategyNome, "");
        for(Sala sala: salas){
            System.out.println(sala);
        }
    }

    private static void pesquisarSalaCodigo() {
        SistemaBusca sistemaBusca = new SistemaBusca();
        int codigo_sala = Ferramentas.lerInteiro("Digite o codigo da sala");

        Sala sala = sistemaBusca.buscarSalaCodigo(codigo_sala);
        System.out.println(sala);
    }

    private static void pesquisarSalaNome() {
        String nome = Ferramentas.lerString("Digite o nome da sala:");
        SistemaBusca sistemaBusca = new SistemaBusca();
        BuscaSalaStrategy buscaSalaStrategyNome = new BuscaSalaNome();
        List<Sala> salas = sistemaBusca.buscarSala(buscaSalaStrategyNome, nome);
        int aux = 1;
        for(Sala sala: salas){
            System.out.println();
            System.out.println("Sala - " + aux);
            System.out.println("Nome - " + sala.getNome());
            System.out.println("Codigo - " + sala.getCodigo());
            System.out.println("Tempo Limpeza" + sala.getTempoLimpeza());
        }
    }
}
