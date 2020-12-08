package src.model;

import javassist.SerialVersionUID;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dispositivo")
public class Dispositivo implements Serializable {

    private static final long serialVersionUID = 8440894002496591808L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ultima_limpeza")
    private Date ultimaLimpeza;

    @Column(name = "codigo_usuario")
    public String codigoUsuario;

    @Column(name="tempo_alerta")
    public String tempoAlerta;

    public Dispositivo(){}

    public Dispositivo(String nome){
        this.nome = nome;
    }

    public Dispositivo(String nome, String cod_usuario) {
        this.nome = nome;
        this.codigoUsuario = cod_usuario;
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

    public Date getUltimaLimpeza() {
        return ultimaLimpeza;
    }

    public void setUltimaLimpeza(Date ultimaLimpeza) {
        this.ultimaLimpeza = ultimaLimpeza;
    }

    public String getCodigoUsuario() {
        return this.codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getTempoAlerta() {
        return tempoAlerta;
    }

    public void setTempoAlerta(String tempoAlerta) {
        this.tempoAlerta = tempoAlerta;
    }

    @Override
    public String toString() {
        return "Dispositivo{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", ultimaLimpeza=" + ultimaLimpeza +
                ", CodigoUsuario='" + codigoUsuario + '\'' +
                '}';
    }
}