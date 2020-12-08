package src.Strategy.BuscaSala;

import src.Strategy.BuscaSala.BuscaSalaStrategy;
import src.model.Sala;

import java.util.ArrayList;
import java.util.List;

public class BuscaTempoLimpeza implements BuscaSalaStrategy {


    @Override
    public List<Sala> buscar(List<Sala> listaSalas, String qtdDias) {
        List<Sala> salasAchadas = new ArrayList<>();
        for(Sala sala: listaSalas){
            if(sala.getTempoLimpeza().equals(qtdDias)){
                salasAchadas.add(sala);
            }
        }
        return salasAchadas;
    }
}
