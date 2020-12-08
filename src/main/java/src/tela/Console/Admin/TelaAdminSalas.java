package src.tela.Console.Admin;

import src.Facade.Sistema;
import src.Facade.SistemaBusca;
import src.Facade.SistemaGetDados;
import src.Strategy.BuscaSala.BuscaSalaNome;
import src.Strategy.BuscaSala.BuscaSalaStrategy;
import src.model.Sala;
import src.tela.Console.Ferramentas;

import java.util.List;

public class TelaAdminSalas {

    public void executar(){

        int op = -1;
        while (op != 0) {

            System.out.println("_-_- Salas _-_-");
            System.out.println("1. Listar Salas");
            System.out.println("2. Buscar Sala por Nome");
            System.out.println("3. Buscar Sala por codigo");
            System.out.println("4. Cadastrar Sala");
            System.out.println("5. Remover Sala");
            System.out.println("6. Editar Sala");
            System.out.println("0. Sair");

            op = Ferramentas.lerInteiro("Digite a opção que deseja");

            switch (op) {
                case 1:
                    listarSalas();
                    break;
                case 2:
                    buscarSalaPorNome();
                    break;
                case 3:
                    buscarSalaPorCodigo();
                    break;
                case 4:
                    cadastrarSala();
                    break;
                case 5:
                    removerSala();
                    break;
                case 6:
                    editarSala();
                    break;
                case 0:
                    return;
                default:
                    Ferramentas.mensagemErro("Opção Inválida");
            }
        }
    }

    private Sala editarSala() {
        SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();
        SistemaBusca sistemaBusca = new SistemaBusca();
        Sistema sistema = Sistema.getInstance();
//        int indice = Ferramentas.selecionarOpcao(salas, "Digite o indice da sala que deseja editar:");


        List<Sala> salas = sistemaGetDados.listarSalas();
        int indice = Ferramentas.selecionarOpcao(salas, "Digite o indice da sala em que deseja se inscrever");

        Sala sala = sistemaBusca.buscarSalaCodigo(salas.get(indice).getCodigo());

        System.out.println("Editar Sala");
        int op = -1;
        int modificado = 0;

        String nomeAntigo = sala.getNome();
        String tempoLimpezaAntigo = sala.getTempoLimpeza();

        String novoNome = sala.getNome();
        String novoTempoLimpeza = sala.getTempoLimpeza();

        while (op != 0){

            System.out.println("==== Editar Sala ====");
            System.out.println("1- Editar Nome");
            System.out.println("2- Editar Tempo de Limpeza");
            System.out.println("0- Sair");

            op = Ferramentas.lerInteiro("Digite a opção desejada: ");


            switch (op){
                case 1:
                    novoNome = Ferramentas.lerString("Digite o novo nome: ");
                    modificado = 1;
                    break;
                case 2:
                    novoTempoLimpeza = Ferramentas.lerString("Digite o novo tempo de limpeza: ");

                    modificado = 1;
                    break;
                case 0:
                    if(modificado == 1) {
                        sala.setNome(novoNome);
                        sala.setTempoLimpeza(novoTempoLimpeza);
                        boolean suceeso = sistema.atualizarSala(sala);

                        if (suceeso) {
                            System.out.println("Atualização realizada");

                            return sala;
                        } else {
                            Ferramentas.mensagemErro("Falha na atualização");
                            sala.setNome(nomeAntigo);
                            sala.setTempoLimpeza(tempoLimpezaAntigo);
                            return sala;
                        }
                    }else{
                        return sala;
                    }
            }
        }

        return null;
    }

    private void removerSala() {
        SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();
        Sistema sistema = Sistema.getInstance();
        List<Sala> salas = sistemaGetDados.listarSalas();
        int indice = Ferramentas.selecionarOpcao(salas, "Digite o indicecda sala que deseja remover");

        Sala sala = salas.get(indice);

        int confirmacao = 0;
        System.out.println("Você tem certeza que quer remover:" + sala + "?");

        while (confirmacao != 2) {
            System.out.println("Digite 1 para remover | Digite 2 para cancelar ");

            confirmacao = Ferramentas.lerInteiro("");


            switch (confirmacao) {
                case 1:
                    boolean sucesso = sistema.removerSala(sala);
                    if (sucesso) {
                        Ferramentas.mensagemSucesso("Sala Remocvida com sucesso");
                    } else {
                        Ferramentas.mensagemErro("Não foi possivel remover a sala");
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

    private void cadastrarSala() {
        System.out.println("Cadastrar Sala");
        String nome = Ferramentas.lerString("Insira o nome da sala");
        String tempoLimpeza = Ferramentas.lerString("Digite o tempo de limpeza:");

        Sala sala = new Sala(nome, tempoLimpeza);

        Sistema sistema = Sistema.getInstance();
        boolean sucesso = sistema.cadastrarSala(sala);
        if(sucesso){
            Ferramentas.mensagemSucesso("Sala Cadastrada");
        }else{
            Ferramentas.mensagemErro("Falha no Cadastro");
        }
    }

    private void buscarSalaPorCodigo() {

        int codigo = Ferramentas.lerInteiro("Digite o codigo da sala:");
        SistemaBusca sistemaBusca = new SistemaBusca();
        Sala sala = sistemaBusca.buscarSalaCodigo(codigo);
        System.out.println(sala);

    }

    private void buscarSalaPorNome() {

        String nome = Ferramentas.lerString("Digite o nome da sala");

        BuscaSalaStrategy buscaSalaNomeStrategy = new BuscaSalaNome();
        SistemaBusca sistemaBusca = new SistemaBusca();
        List<Sala> salas = sistemaBusca.buscarSala(buscaSalaNomeStrategy, nome);
        for (Sala sala: salas){
            System.out.println(sala);
        }
    }

    private void listarSalas() {
        SistemaGetDados sistemaGetDados = SistemaGetDados.getInstance();
        List<Sala> salas = sistemaGetDados.listarSalas();
        for (Sala sala: salas){
            System.out.println(sala);
        }
    }
}
