package src;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import src.Facade.Sistema;
import src.model.Usuario;

import src.tela.Console.Ferramentas;
import src.tela.Console.Sala.TelaConsoleSala;
import src.tela.Console.TelaConsoleUsuario;
import src.tela.Tela;
import src.tela.TelaConsoleLimpeza;

import java.util.Collections;
import java.util.List;

public class UsuarioMain {

    private static Logger logger = LogManager.getLogger(UsuarioMain.class);


    public static void main(String[] args) throws InterruptedException {

        RemoveLog.removerLog();
        Object object;
        Tela tela = new TelaConsoleUsuario();
         do {
                object = tela.telaInicial();
                if(object != null) {
                    tela.telaPrincipal(object);
                }
            }while(object != null);
            Sistema.fecharConexao();
        }
}

