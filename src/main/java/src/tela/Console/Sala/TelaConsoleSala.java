package src.tela.Console.Sala;

import src.Facade.Sistema;
import src.Facade.SistemaBusca;
import src.Facade.SistemaLimpeza;
import src.model.Dispositivo;
import src.model.Sala;
import src.tela.Console.Ferramentas;
import src.tela.Tela;

import java.util.List;
import java.util.Map;

public class TelaConsoleSala implements Tela {

    Sistema sistema = Sistema.getInstance();

    public Object telaInicial() {

        System.out.println("°°° SALA °º°");
        SistemaBusca sistemaBusca = new SistemaBusca();

        int codigo = Ferramentas.lerInteiro("Digite o codigo da sala: ");
        Ferramentas.mensagemCrregando("Carregando");
        Sala sala = sistemaBusca.buscarSalaCodigo(codigo);
        return sala;

    }

    public void telaPrincipal(Object obj) {

        String codigoUsuario = "";

        SistemaLimpeza sistemaLimpeza = SistemaLimpeza.getInstance();
        Sala sala = (Sala) obj;

        while (codigoUsuario.equals("00000") == false) {

            System.out.println("°°° "+ sala.getNome() +" °º°");
            System.out.println("Requisito de Limpeza: " + sala.getTempoLimpeza() + " dias");

            codigoUsuario = Ferramentas.lerString("Digite o seu codigo de usuario");
            if(codigoUsuario.equals("00000")){
                break;
            }
            Map<String, List<Dispositivo>> resposta = sistemaLimpeza.verificarUsuario(sala.getTempoLimpeza(), codigoUsuario);

            System.out.println("Autorizados:");
            for (Dispositivo dispositivo : resposta.get("autorizados")) {
                System.out.println(dispositivo);
            }

            System.out.println("\n\n");
            System.out.println("Bloqueados:");
            for (Dispositivo dispositivo : resposta.get("bloqueados")) {
                System.out.println(dispositivo);
            }

        }
    }
}
