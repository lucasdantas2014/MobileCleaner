package src.Strategy.BuscaSala;

import src.model.Sala;

import java.util.List;

public interface BuscaSalaStrategy {

    public List<Sala> buscar(List<Sala> lista, String parametro);
}
