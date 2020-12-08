package src.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inscricao_sala")
public class InscricaoSala implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cod_inscricao")
    private int codInscricaoSala;

    @Column(name = "cod_usuario")
    private String codUsuario;

    @Column(name = "cod_sala")
    private int codSala;

    public InscricaoSala(){}

    public InscricaoSala(int codSala, String codUsuario) {
        this.codSala = codSala;
        this.codUsuario = codUsuario;
    }

    public int getCodInscricaoSala() {
        return codInscricaoSala;
    }

    public void setCodInscricaoSala(int codInscricaoSala) {
        this.codInscricaoSala = codInscricaoSala;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public int getCodSala() {
        return codSala;
    }

    public void setCodSala(int codSala) {
        this.codSala = codSala;
    }

    @Override
    public String toString() {
        return "InscricaoSala{" +
                "codInscricaoSala=" + codInscricaoSala +
                ", codUsuario='" + codUsuario + '\'' +
                ", codSala=" + codSala +
                '}';
    }
}
