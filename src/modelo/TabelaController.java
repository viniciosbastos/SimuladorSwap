/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Vinicios
 */
public class TabelaController {

    public static ObservableList<ResultadoFrame> data;

    private static TabelaController instance = null;

    public TableView<ResultadoFrame> table;

    private TabelaController() {
        data = FXCollections.observableArrayList();
        table = new TableView();
        table.setEditable(false);

        TableColumn qtdFrameCol = new TableColumn("Frames");
        qtdFrameCol.setPrefWidth(70);
        qtdFrameCol.setCellValueFactory(
                new PropertyValueFactory<ResultadoFrame, String>("qtdFrames"));

        TableColumn scCol = new TableColumn("SC");
        scCol.setPrefWidth(70);
        scCol.setCellValueFactory(
                new PropertyValueFactory<ResultadoFrame, String>("scValue"));

        TableColumn nruCol = new TableColumn("NRU");
        nruCol.setPrefWidth(70);
        nruCol.setCellValueFactory(
                new PropertyValueFactory<ResultadoFrame, String>("nruValue"));

        TableColumn bestCol = new TableColumn("BEST");
        bestCol.setPrefWidth(70);
        bestCol.setCellValueFactory(
                new PropertyValueFactory<ResultadoFrame, String>("bestValue"));

        TableColumn fifoCol = new TableColumn("FIFO");
        fifoCol.setPrefWidth(70);
        fifoCol.setCellValueFactory(
                new PropertyValueFactory<ResultadoFrame, String>("fifoValue"));

        TableColumn lruCol = new TableColumn("LRU");
        lruCol.setPrefWidth(70);
        lruCol.setCellValueFactory(
                new PropertyValueFactory<ResultadoFrame, String>("lruValue"));

        table.getColumns().addAll(qtdFrameCol, scCol, nruCol, bestCol, fifoCol, lruCol);
        table.setItems(data);
    }

    synchronized public static TabelaController getInstance() {
        if (instance == null) {
            instance = new TabelaController();
        }
        return instance;
    }

    synchronized public void editSc(Integer value, int frame) {
        int index = procurarIndex(frame);
        ResultadoFrame resultado = data.get(index);
        resultado.setScValue(value);
        table.getItems().set(index, resultado);
    }

    synchronized public void editNru(Integer value, int frame) {
        int index = procurarIndex(frame);
        ResultadoFrame resultado = data.get(index);
        resultado.setNruValue(value);
        table.getItems().set(index, resultado);
    }

    synchronized public void editBest(Integer value, int frame) {
        int index = procurarIndex(frame);
        ResultadoFrame resultado = data.get(index);
        resultado.setBestValue(value);
        table.getItems().set(index, resultado);
    }

    synchronized public void editFifo(Integer value, int frame) {
        int index = procurarIndex(frame);
        ResultadoFrame resultado = data.get(index);
        resultado.setFifoValue(value);
        table.getItems().set(index, resultado);
    }

    synchronized public void editLru(Integer value, int frame) {
        int index = procurarIndex(frame);
        ResultadoFrame resultado = data.get(index);
        resultado.setLruValue(value);
        table.getItems().set(index, resultado);
    }

    private int procurarIndex(int frame) {
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getQtdFrames() == frame) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void initLinhas(int minframes, int maxframes) {
        for (int i = minframes; i <= maxframes; i++) {
            data.add(new ResultadoFrame(i, null, null, null, null, null));
        }
    }
}
