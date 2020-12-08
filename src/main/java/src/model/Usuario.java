package src.model;

import src.Facade.Sistema;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable, Assinante {

    @Transient
    private ArrayList<Sala> salasCadastradas;

    public void notificar(Notificacao notificacao){
        notificacao.setCod_receptor(codigo);
        this.qtdNovasNotificacoes += 1;
        this.notificacoes.add(notificacao);
    }

    @Id
    private String codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "senha")
    private String senha;

    @Column(name = "qtd_notificacao")
    private int qtdNovasNotificacoes;

    @Transient
    private ArrayList<Dispositivo> dispositivos;


    @Transient
    private ArrayList<Notificacao> notificacoes;

    public Usuario(){
        this.dispositivos = new ArrayList<>();
        this.salasCadastradas = new ArrayList<>();
        this.notificacoes = new ArrayList<>();
    };

    public Usuario (String nome){
        this.nome = nome;
        dispositivos = new ArrayList<>();
        salasCadastradas = new ArrayList<>();
        notificacoes = new ArrayList<>();
    }

    public Usuario(String nome, String cpf, String email, String telefone, String senha) {
        this.nome = nome;
        this.codigo = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.qtdNovasNotificacoes = 0;
        salasCadastradas = new ArrayList<Sala>();

    }

    public void adicionarDispositivo (Dispositivo dispositivo){
        this.dispositivos.add(dispositivo);
    }

    public void removerDispositivo (Dispositivo dispositivo){
        this.dispositivos.remove(dispositivo);
    }

    public void notificacaoSala(Notificacao notificacao){
           this.notificacoes.add(notificacao);
    }



    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setDispositivos(ArrayList<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public ArrayList<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = (ArrayList<Dispositivo>) dispositivos;
    }

    public ArrayList<Sala> getSalasCadastradas() {
        return salasCadastradas;
    }

    public void setSalasCadastradas(ArrayList<Sala> salasCadastradas) {
        this.salasCadastradas = salasCadastradas;
    }

    public ArrayList<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(ArrayList<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getQtdNovasNotificacoes() {
        return qtdNovasNotificacoes;
    }

    public void setQtdNovasNotificacoes(int qtdNovasNotificacoes) {
        this.qtdNovasNotificacoes = qtdNovasNotificacoes;
    }

    public void adicionarNotificacao(Notificacao notificacao){
        this.notificacoes.add(notificacao);
    }

    @Override
    public String toString() {

        return "______Usuario______" +
                "\ncodigo='" + codigo + '\'' +
                "\t nome='" + nome + '\'' +
                "\n email='" + email + '\'' +
                "\t telefone='" + telefone + '\'' +
                "\n senha='" + "********" + '\'' +
                "\t dispositivos=" + dispositivos +
                "\n salasCadastradas=" + salasCadastradas +
                "\t notificacoes=" + notificacoes +
                '}';
    }

    public void adicionarSala(Sala sala) {
        this.salasCadastradas.add(sala);
    }

}
