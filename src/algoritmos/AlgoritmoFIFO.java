/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import modelo.Pagina;

/**
 *
 * @author Vinicios
 */
public class AlgoritmoFIFO implements AlgoritmoSwap{
    private int numFrames;
    
    private ArrayList<Pagina> memoria;
    
    private List<Pagina> paginas;
    
    private int[] pilhaPagina;
    
    public AlgoritmoFIFO(int n, ArrayList<Pagina> paginas) {
        this.numFrames = n;
        this.memoria = new ArrayList();
        this.paginas = paginas;
        this.pilhaPagina = new int[n];
    }

    @Override
    public int simular() {
        int acertos = 0;
        int index;
        Pagina pagina;
        while (!this.paginas.isEmpty()) {
            pagina = this.paginas.remove(0);
            index = procuraPagina(pagina.getId());
            if (index != -1) {
                acertos++;                
            } else {
                if (memoria.size() < this.numFrames) {
                    memoria.add(pagina);
                } else {
                    memoria.remove(0);
                    memoria.add(pagina);
                }
            }
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
