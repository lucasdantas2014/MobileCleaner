package src.Strategy.BuscaSala;

import src.model.Sala;

import java.util.ArrayList;
import java.util.List;

public class BuscaSalaNome implements BuscaSalaStrategy{

    @Override
    public List<Sala> buscar(List<Sala> listaSalas, String nome) {
        List<Sala> salasAchadas = new ArrayList<Sala>();
        for(Sala sala: listaSalas){
            if(sala.getNome().toUpperCase().contains(nome.toUpperCase())){
                salasAchadas.add(sala);
            }
        }
        return salasAchadas;
    }
}
