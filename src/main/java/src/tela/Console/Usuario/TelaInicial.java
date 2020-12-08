package src.tela.Console.Usuario;

import src.Facade.SistemaLoginCadastro;
import src.model.Usuario;
import src.tela.Console.Ferramentas;

public class TelaInicial{

    private SistemaLoginCadastro sistemaLoginCadastro;

    public TelaInicial(){
        sistemaLoginCadastro = SistemaLoginCadastro.getInstance();
    }

    public void cadastrar() {
        System.out.println("|=== Cadastrar ===|");

        String nome = Ferramentas.lerString("Insira o seu nome:");
        if(nome == "-1") return;
        String cpf = Ferramentas.lerString("Insira o seu cpf");
        if(cpf == "-1") return;
        String email = Ferramentas.lerString("Insira o seu emal:");
        if(email == "-1") return;
        String telefone = Ferramentas.lerString("Insira o seu telefone:");
        if(telefone == "-1") return;
        String senha = Ferramentas.lerString("Insira a sua senha:");
        if(senha == "-1") return;

        Usuario usuario = new Usuario(nome, cpf, email, telefone, senha);

        sistemaLoginCadastro.cadastrarUsuario(usuario);
    }

    public Usuario login() {
        System.out.println("====LOGIN====");

        String cpf = Ferramentas.lerString("Digite o seu CPF:");

        String senha = Ferramentas.lerString("Digite a sua senha:");

        System.out.println("Carregando...");

        Usuario usuario = sistemaLoginCadastro.logar(cpf, senha);
        if(usuario == null) {
            Ferramentas.mensagemErro("Dados Inválidos");
        }else{
            if(usuario.getSenha().equals(senha) == false){
                Ferramentas.mensagemErro("Senha Incorreta");
                usuario = null;
            }else{
                usuario = sistemaLoginCadastro.construirUsuario(usuario);
            }
        }

        return usuario;
    }
}
