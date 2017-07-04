/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladores;

import algoritmos.AlgoritmoNRU;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.chart.XYChart;
import modelo.Pagina;
import modelo.TabelaController;

/**
 *
 * @author Vinicios
 */
public class NRUSimulador extends Thread{
    private int min;
    private int max;
    private int reset;
    private ArrayList<Pagina> paginas;
    public XYChart.Series serie;

    public NRUSimulador(int min, int max, int reset, ArrayList<Pagina> paginas) {
        this.min = min;
        this.max = max;
        this.reset = reset;
        this.paginas = paginas;
        this.serie = new XYChart.Series();
        this.serie.setName("NRU");
    }

    @Override
    public void run() {
        simularNru();
    }

    private void simularNru() {
        System.out.println("Iniciando simulação algoritmo Segunda Chance");
        AlgoritmoNRU algoritmo;
        for (int i = min; i <= max; i++) {
            algoritmo = new AlgoritmoNRU(i, cloneArray(), reset);
            int acertos = algoritmo.simular();
            TabelaController.getInstance().editNru(acertos, i);
            att(i, acertos);
            System.out.println("Simulação com " + i + " frames: " + acertos + " acertos.");
        }
    }

    private void att(int x, int y) {
        Platform.runLater(() -> {
            this.serie.getData().add(new XYChart.Data(x, y));
        });
    }

    private ArrayList cloneArray() {
        ArrayList<Pagina> newArray = new ArrayList();
        for (Pagina p : paginas) {
            newArray.add(new Pagina(p));
        }
        return newArray;
    }
}
