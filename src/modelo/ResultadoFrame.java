/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Vinicios
 */
public class ResultadoFrame {    
    private final SimpleObjectProperty<Integer> qtdFrames;
    private final SimpleObjectProperty<Integer> scValue;
    private final SimpleObjectProperty<Integer> nruValue;
    private final SimpleObjectProperty<Integer> bestValue;
    private final SimpleObjectProperty<Integer> fifoValue;
    private final SimpleObjectProperty<Integer> lruValue;
    
    public ResultadoFrame(Integer frame, Integer sc, Integer nru, Integer best, Integer fifo, Integer lru) {
        this.qtdFrames = new SimpleObjectProperty(frame);
        this.scValue = new SimpleObjectProperty(sc);
        this.nruValue = new SimpleObjectProperty(nru);
        this.bestValue = new SimpleObjectProperty(best);
        this.fifoValue = new SimpleObjectProperty(fifo);
        this.lruValue = new SimpleObjectProperty(lru);
    }
    
    public void setQtdFrames(Integer f){
        this.qtdFrames.set(f);
    }
    public void setScValue(Integer sc){
        this.scValue.set(sc);
    }
    public void setNruValue(Integer nr){
        this.nruValue.set(nr);
    }
    public void setBestValue(Integer b){
        this.bestValue.set(b);
    }
    public void setFifoValue(Integer f){
        this.fifoValue.set(f);
    }
    public void setLruValue(Integer l){
        this.lruValue.set(l);
    }

    public Integer getQtdFrames(){
        return this.qtdFrames.get();
    }
    public Integer getScValue(){
        return this.scValue.get();
    }
    public Integer getNruValue(){
        return this.nruValue.get();
    }
    public Integer getBestValue(){
        return this.bestValue.get();
    }
    public Integer getFifoValue(){
        return this.fifoValue.get();
    }
    public Integer getLruValue(){
        return this.lruValue.get();
    }
}
