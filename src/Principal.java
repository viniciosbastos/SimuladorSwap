/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import simuladores.SCSimulador;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import modelo.Pagina;
import modelo.TabelaController;
import simuladores.BestSimulador;
import simuladores.FIFOSimulador;
import simuladores.LRUSimulador;
import simuladores.NRUSimulador;
import utils.ConversorModelo;
import utils.LeitorArquivo;

/**
 *
 * @author Vinicios
 */
public class Principal extends Application {

    int minFrameInt;
    int maxFrameInt;
    int resetRInt;

    private File arquivo;
    private Button searchButton;
    private Button initButton;

    private TextField minFrame;
    private TextField maxFrame;
    private TextField resetR;

    private LineChart<Number, Number> resultGrafico;

    private TabelaController tabela;

    @Override
    public void start(Stage primaryStage) {
        initGrafico();
        createTabela();
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5, 5, 5, 5));
        GridPane grid = new GridPane();

        grid.add(new Label("Arquivo"), 0, 0);

        searchButton = new Button("Procurar");
        searchButton.setOnAction((ActionEvent ev) -> {
            procurarArquivo(primaryStage);
        });
        searchButton.prefWidthProperty().bind(grid.widthProperty());
        grid.add(searchButton, 1, 0);

        grid.add(new Label("Min Quadro"), 0, 1);
        minFrame = new TextField();
        minFrame.prefWidthProperty().bind(grid.widthProperty());
        grid.add(minFrame, 1, 1);

        grid.add(new Label("Max Quadro"), 0, 2);
        maxFrame = new TextField();
        maxFrame.prefWidthProperty().bind(grid.widthProperty());
        grid.add(maxFrame, 1, 2);

        grid.add(new Label("Reset R"), 0, 3);
        resetR = new TextField();
        resetR.prefWidthProperty().bind(grid.widthProperty());
        grid.add(resetR, 1, 3);

        grid.getColumnConstraints().add(new ColumnConstraints(80));

        vbox.getChildren().add(grid);

        initButton = new Button("Iniciar Simulação");
        initButton.setOnAction((ActionEvent ev) -> {
            initTabela();
            iniciarSimulacao();
            primaryStage.close();
        });

        initButton.prefWidthProperty().bind(vbox.widthProperty());
        vbox.getChildren().add(initButton);

        Scene scene = new Scene(vbox, 250, 135);
        primaryStage.setTitle("Swap Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void procurarArquivo(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar arquivo de leitura");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text File", "*.txt"));

        arquivo = fileChooser.showOpenDialog(primaryStage);
        searchButton.setText(arquivo.getName());
    }

    private ArrayList<Pagina> convertParaModelo() {
        ArrayList<Pagina> paginas = null;
        if (arquivo != null) {
            try {
                String[] operacoesArray = LeitorArquivo.lerArquivo(arquivo);
                if (operacoesArray != null) {
                    paginas = ConversorModelo.convert(operacoesArray);
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return paginas;
    }

    private void iniciarSimulacao() {
        minFrameInt = Integer.parseInt(minFrame.getText());
        maxFrameInt = Integer.parseInt(maxFrame.getText());
        resetRInt = Integer.parseInt(resetR.getText());

        SCSimulador sc = new SCSimulador(minFrameInt, maxFrameInt, resetRInt, convertParaModelo());
        this.resultGrafico.getData().add(sc.serie);
        sc.start();

        NRUSimulador nru = new NRUSimulador(minFrameInt, maxFrameInt, resetRInt, convertParaModelo());
        this.resultGrafico.getData().add(nru.serie);
        nru.start();

        BestSimulador best = new BestSimulador(minFrameInt, maxFrameInt, convertParaModelo());
        this.resultGrafico.getData().add(best.serie);
        best.start();

        FIFOSimulador fifo = new FIFOSimulador(minFrameInt, maxFrameInt, convertParaModelo());
        this.resultGrafico.getData().add(fifo.serie);
        fifo.start();

        LRUSimulador lru = new LRUSimulador(minFrameInt, maxFrameInt, convertParaModelo());
        this.resultGrafico.getData().add(lru.serie);
        lru.start();
    }

    private void initGrafico() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Frames");
        xAxis.setTickUnit(1);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Acertos");
        yAxis.setTickUnit(1);

        resultGrafico = new LineChart(xAxis, yAxis);
        resultGrafico.setTitle("Resultado Simulação de Paginamento");
        resultGrafico.setAnimated(false);
        resultGrafico.setScaleZ(20);

        Stage stage = new Stage();
        Scene scene = new Scene(resultGrafico, 800, 600);
        stage.setScene(scene);
        stage.setX(500);
        stage.setY(50);
        stage.show();
    }

    private void createTabela() {
        this.tabela = TabelaController.getInstance();
        Stage stage = new Stage();
        Scene scene = new Scene(tabela.table, 420, 600);
        stage.setScene(scene);
        stage.setX(60);
        stage.setY(50);
        stage.show();
    }
    
    private void initTabela() {
        int minframes = Integer.parseInt(this.minFrame.getText());
        int maxframes = Integer.parseInt(this.maxFrame.getText());
        tabela.initLinhas(minframes, maxframes);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
