/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladores;

import algoritmos.AlgoritmoFIFO;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.chart.XYChart;
import modelo.Pagina;
import modelo.TabelaController;

/**
 *
 * @author Vinicios
 */
public class FIFOSimulador extends Thread{
    private int min;
    private int max;
    private ArrayList<Pagina> paginas;
    public XYChart.Series serie;

    public FIFOSimulador(int min, int max, ArrayList<Pagina> paginas) {
        this.min = min;
        this.max = max;
        this.paginas = paginas;
        this.serie = new XYChart.Series();
        this.serie.setName("FIFO");
    }

    @Override
    public void run() {
        simularFifo();
    }

    private void simularFifo() {
        System.out.println("Iniciando simulação algoritmo Segunda Chance");
        AlgoritmoFIFO algoritmo;
        for (int i = min; i <= max; i++) {
            algoritmo = new AlgoritmoFIFO(i, cloneArray());
            int acertos = algoritmo.simular();
            TabelaController.getInstance().editFifo(acertos, i);
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
