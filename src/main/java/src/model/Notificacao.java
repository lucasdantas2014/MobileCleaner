package src.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Notificacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    @Column(name = "mensagem")
    private String mensagem;

    @Column(name = "data_envio")
    private String dataEnvio;

    @Column(name = "data_vizualido")
    private String dataVisualizacao;

    @Column(name = "cod_receptor")
    private String cod_receptor;

    @Column(name = "emissor")
    private String emissor;

    public Notificacao(){};

    public Notificacao(String emissor, String mensagem) {
        this.setEmissor(emissor);
        this.setMensagem(mensagem);
        this.setDataEnvio(new Date().toString());
        this.setLida(false);
    }

    public Notificacao(String emissor, String mensagem, String codReceptor) {
        this.setEmissor(emissor);
        this.setMensagem(mensagem);
        this.setDataEnvio(new Date().toString());
        this.setCod_receptor(cod_receptor);
        this.setLida(false);
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(String dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getDataVisualizacao() {
        return dataVisualizacao;
    }

    public void setDataVisualizacao(String dataVisualizacao) {
        this.dataVisualizacao = dataVisualizacao;
    }

    public String getCod_receptor() {
        return cod_receptor;
    }

    public void setCod_receptor(String cod_receptor) {
        this.cod_receptor = cod_receptor;
    }

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    @Column(name = "lida")
    private boolean lida = false;


    @Override
    public String toString() {
        return "\nmensagem='" + mensagem + '\'' +
                "\tdataEnvio='" + dataEnvio + '\'' +
                "\nemissor='" + emissor + '\'';
    }

}
