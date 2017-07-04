/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import java.util.List;
import modelo.Pagina;

/**
 *
 * @author Vinicios
 */
public class AlgoritmoNRU implements AlgoritmoSwap{
    private int numFrames;
    
    private ArrayList<Pagina> memoria;
    
    private ArrayList<Pagina> paginas;
    
    private int reset;
    private int contagem;
    
        
    public AlgoritmoNRU(int n, ArrayList<Pagina> paginas, int reset) {
        this.numFrames = n;
        this.memoria = new ArrayList();
        this.paginas = (ArrayList<Pagina>) paginas.clone();
        this.reset = reset;
        this.contagem = 0;
    }

    @Override
    public int simular() {
        int acertos = 0;
        int faltas = 0;
        int index = 0;
        Pagina pagina;
        
        while (!this.paginas.isEmpty()) {
            if (this.contagem == this.reset) {
                resetR();
                this.contagem = 0;
            }

            pagina = this.paginas.remove(0);
            index = procuraPagina(pagina.getId());
            if (index != -1) {
                Pagina p = memoria.get(index);
                p.setR();
                
                if (pagina.getOperacao()== 'W') {
                    p.setM();
                } 
                acertos += 1;
            } else {
                pagina.setR();
                if (pagina.getOperacao() == 'W') 
                    pagina.setM();
                faltas += 1;
                if (memoria.size() < this.numFrames) {
                    memoria.add(pagina);
                } else {
                    int i = procurarMenorClasse();
                    memoria.remove(i);
                    memoria.add(pagina);
                }
            }
            this.contagem++;
        }
        
        
        return acertos;
    }
    
    private int procuraPagina(int id) {        
        for (int i = 0; i < memoria.size(); i++) {
            if (memoria.get(i).getId() == id)
                return i;
        }        
        return -1;
    }
    
    private void resetR() {
        for (Pagina p : memoria) {
            p.clearR();
        }
    }
    
    private int procurarMenorClasse() {
        int index = 0;
        int menor = 4;
        
        for (int i = 0; i < memoria.size(); i++) {
            Pagina p = memoria.get(i);
            if (p.getClasse() < menor) {
                index = i;
                menor = p.getClasse();
            }
        }
        return index;
    }
}
