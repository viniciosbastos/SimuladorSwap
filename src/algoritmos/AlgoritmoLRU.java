/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import modelo.Pagina;

/**
 *
 * @author Vinicios
 */
public class AlgoritmoLRU implements AlgoritmoSwap{
    private int numFrames;
    
    private ArrayList<Pagina> memoria;
    
    private List<Pagina> paginas;
    
    private ArrayList<Integer> pilha;
    
    public AlgoritmoLRU(int n, ArrayList<Pagina> paginas) {
        this.numFrames = n;
        this.memoria = new ArrayList();
        this.paginas = paginas;
        this.pilha = new ArrayList();
    }

    @Override
    public int simular() {
        int acertos = 0;
        int index;
        Pagina pagina;
        while (!this.paginas.isEmpty()) {
//            atualizarLU();            
            pagina = this.paginas.remove(0);
            index = procuraPagina(pagina.getId());
            if (index != -1) {
                pilha.remove(pilha.indexOf(pagina.getId()));
                acertos++;                
            } else {
                if (memoria.size() < this.numFrames) {
                    memoria.add(pagina);
                } else {
                    int key = pilha.remove(0);
                    memoria.remove(procuraPagina(key));
                    memoria.add(pagina);
                }
            }
            pilha.add(pagina.getId());
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
}
