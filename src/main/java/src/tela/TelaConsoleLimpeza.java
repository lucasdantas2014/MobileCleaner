package src.tela;

import src.Facade.SistemaBusca;
import src.Facade.SistemaLimpeza;
import src.model.Dispositivo;
import src.model.Limpeza;
import src.tela.Console.Ferramentas;

import java.util.List;

public class TelaConsoleLimpeza implements Tela{
    public Object telaInicial() {
        int opcao = -1;
        while(opcao != 0) {

            System.out.println("====LIMPEZA====");
            System.out.println("1. Executar Limpeza");
            System.out.println("0. Desligar");


            opcao = Ferramentas.lerInteiro("Digite a opção desejada");


            switch (opcao) {
                case 1:
                    return "ir para tela principal";
                case 0:
                    return null;
                default:
                    Ferramentas.mensagemErro("Opcão Inválida");

            }
        }
        return null;
    }


    public void telaPrincipal(Object object) {


            SistemaLimpeza sistemaLimpeza = SistemaLimpeza.getInstance();
            String codigoUsuario = Ferramentas.lerString("Digite o codigo do usuario");

            SistemaBusca sistemaBusca = new SistemaBusca();
            List<Dispositivo> dispositivos = sistemaBusca.buscarDispositivoUsuario(codigoUsuario);

            int indice_dispositivo = Ferramentas.selecionarOpcao(dispositivos, "Digite o indice do dispositivo que deseja limpar: ");
            if(indice_dispositivo == -1){
                return;
            }
            sistemaLimpeza.executarLimpeza(codigoUsuario,dispositivos.get(indice_dispositivo).getCodigo());

    }
}
