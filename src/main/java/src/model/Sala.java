package src.model;

import src.Facade.GerenciadorInscricaoSala;
import src.Facade.Sistema;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Sala implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;
    @Column(name = "nome")
    private String nome;
    @Column(name = "tempo_limpeza")
    private String tempoLimpeza;
    @Transient
    private ArrayList<Assinante> assinantesCadsastrados;

    public void inscrever(Assinante assinante) {
        assinantesCadsastrados.add(assinante);
        GerenciadorInscricaoSala gerenciadorInscricaoSala = new GerenciadorInscricaoSala();
        gerenciadorInscricaoSala.inscreverSala(assinante.getCodigo(), codigo);
    }
    public void cancelarInscricao(Assinante assinante){
        assinantesCadsastrados.remove(assinante);
        GerenciadorInscricaoSala gerenciadorInscricaoSala = new GerenciadorInscricaoSala();
        gerenciadorInscricaoSala.cancelarInscricao(this.codigo, assinante.getCodigo());
    };
    public void notificarAssinantes(Notificacao notificacao){
        Sistema sistema = Sistema.getInstance();
        for(Assinante assinante: assinantesCadsastrados){
            notificacao.setCod_receptor(assinante.getCodigo());
            assinante.notificar(notificacao);
            sistema.enviarNotificaco(notificacao);
        }
    }


    private static final long serialVersionUID = 1L;

    public Sala(){
        this.assinantesCadsastrados = new ArrayList<>();
    }

    public Sala(String nome, String tempoLimpeza) {
        this.nome = nome;
        this.tempoLimpeza = tempoLimpeza;
        this.assinantesCadsastrados = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTempoLimpeza() {
        return tempoLimpeza;
    }

    public void setTempoLimpeza(String tempoLimpeza) {
        this.tempoLimpeza = tempoLimpeza;
        Notificacao notificacao = new Notificacao(nome, "Tempo de limpeza para entrar na sala " + nome + ". Agora o tempo é: " + tempoLimpeza + "dias");
        notificarAssinantes(notificacao);
    }

    public ArrayList<Assinante> getAssinantesCadsastrados() {
        return assinantesCadsastrados;
    }

    public void setAssinantesCadsastrados(ArrayList<Assinante> assinantesCadsastrados) {
        this.assinantesCadsastrados = assinantesCadsastrados;
    }

    @Override
    public String toString() {
        return "______Sala______" +
                "\ncodigo=" + codigo +
                "\t nome='" + nome + '\'' +
                "\n tempoLimpeza='" + tempoLimpeza + '\'';
    }
}
