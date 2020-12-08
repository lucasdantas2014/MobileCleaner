package src;

import src.Facade.Sistema;
import src.tela.Console.Sala.TelaConsoleSala;
import src.tela.Console.TelaConsoleUsuario;
import src.tela.Tela;
import src.model.Sala;

public class SalaMain {

    public static void main(String[] args) {
        RemoveLog.removerLog();
        Object object;
        Tela tela = new TelaConsoleSala();
        do {
            object = tela.telaInicial();
            if(object != null) {
                tela.telaPrincipal(object);
            }
        }while(object != null);
        Sistema.fecharConexao();
    }
}
