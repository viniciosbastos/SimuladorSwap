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
public class AlgoritmoOtimo implements AlgoritmoSwap{
    private int numFrames;
    
    private ArrayList<Pagina> memoria;
    
    private List<Pagina> paginas;
    
    public AlgoritmoOtimo(int n, ArrayList<Pagina> paginas) {
        this.numFrames = n;
        this.memoria = new ArrayList();
        this.paginas = paginas;
    }

    @Override
    public int simular() {
        int acertos = 0;
        
        Pagina pagina;
        int index;
        while (!this.paginas.isEmpty()) {
            pagina = this.paginas.remove(0);
            index = procuraPagina(pagina.getId());
            if (index != -1) {
                acertos++;                
            } else {
                if (memoria.size() < this.numFrames) {
                    memoria.add(pagina);
                } else {
                    int key = procurarUltimo();
                    memoria.remove(key);
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
    
    private int procurarUltimo() {
        int key = 0;
        int maior = 0;
        int count;
        Pagina pagina;
        for (int i = 0; i < memoria.size(); i++) {
            pagina = memoria.get(i);
            count = 0;
            for (Pagina p : this.paginas) {
                if (!pagina.equals(p))
                    count++;
                else
                    break;
            }
            if (count > maior) {
                maior = count;
                key = i;
            }
        }
        return key;
    }
}
