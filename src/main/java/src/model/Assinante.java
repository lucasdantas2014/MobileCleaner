package src.model;

import src.model.Notificacao;

public interface Assinante {
    public void notificar(Notificacao notificacao);

    String getCodigo();

    String getNome();
}
