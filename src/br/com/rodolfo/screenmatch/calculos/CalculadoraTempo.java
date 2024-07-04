package br.com.rodolfo.screenmatch.calculos;

import br.com.rodolfo.screenmatch.modelos.Filme;
import br.com.rodolfo.screenmatch.modelos.Titulo;

public class CalculadoraTempo {
    private int tempoTotal;

    public int getTempoTotal() {
        return tempoTotal;
    }

    public void inclui(Titulo titulo){
        this.tempoTotal += titulo.getDuracaoEmMinutos();
    }
}
