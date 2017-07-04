/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import modelo.Pagina;

/**
 *
 * @author Vinicios
 */
public class AlgoritmoSegundaChance implements AlgoritmoSwap {

    private int numFrames;

    private ArrayList<Pagina> memoria;

    private ArrayList<Pagina> paginas;
    private int reset;
    private int contagem;

    public AlgoritmoSegundaChance(int n, ArrayList<Pagina> paginas, int reset) {
        this.numFrames = n;
        this.memoria = new ArrayList();
        this.paginas = paginas;
        this.reset = reset;
        this.contagem = 0;
    }

    @Override
    public int simular() {
        int acertos = 0;
        int index;
        Pagina pagina;
        while (!this.paginas.isEmpty()) {
            if (this.contagem == this.reset) {
                resetR();
                this.contagem = 0;
            }

            pagina = this.paginas.remove(0);

            index = procuraPagina(pagina.getId());

            if (index != -1) {
                memoria.get(index).setR();
                acertos++;
            } else if (memoria.size() < this.numFrames) {
                memoria.add(pagina);
            } else {
                remover();
                memoria.add(pagina);
            }
            this.contagem++;
        }
        return acertos;
    }

    private void resetR() {
        for (Pagina p : memoria) {
            p.clearR();
        }
    }

    private int procuraPagina(int id) {
        for (int i = 0; i < memoria.size(); i++) {
            if (memoria.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void remover() {
        boolean flag = false;
        Pagina p;
        while (flag == false) {
            p = memoria.remove(0);
            if (p.getClasse() == 2) {
                p.clearR();
                memoria.add(p);
            } else {
                flag = true;
            }
        }
    }

}
