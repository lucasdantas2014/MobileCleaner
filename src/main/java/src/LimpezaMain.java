package src;

import src.Facade.Sistema;
import src.model.Limpeza;
import src.tela.Console.TelaConsoleUsuario;
import src.tela.Tela;
import src.tela.TelaConsoleLimpeza;


public class LimpezaMain {
    public static void main(String[] args) {
        RemoveLog.removerLog();
        Object object;
        Tela tela = new TelaConsoleLimpeza();
        do {
            object = tela.telaInicial();
            if(object != null) {
                tela.telaPrincipal(object);
            }
        }while(object != null);
        Sistema.fecharConexao();
    }
}

