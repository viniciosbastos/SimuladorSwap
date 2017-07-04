package simuladores;

import algoritmos.AlgoritmoSegundaChance;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.chart.XYChart;
import modelo.Pagina;
import modelo.TabelaController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vinicios
 */
public class SCSimulador extends Thread {

    private int min;
    private int max;
    private int reset;
    private ArrayList<Pagina> paginas;
    public XYChart.Series serie;

    public SCSimulador(int min, int max, int reset, ArrayList<Pagina> paginas) {
        this.min = min;
        this.max = max;
        this.reset = reset;
        this.paginas = paginas;
        this.serie = new XYChart.Series();
        this.serie.setName("SC");
    }

    @Override
    public void run() {
        simularSC();
    }

    private void simularSC() {
        System.out.println("Iniciando simulação algoritmo Segunda Chance");
        AlgoritmoSegundaChance algoritmo;
        for (int i = min; i <= max; i++) {
            algoritmo = new AlgoritmoSegundaChance(i, cloneArray(), reset);
            int acertos = algoritmo.simular();
            TabelaController.getInstance().editSc(acertos, i);
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
