/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Vinicios
 */
public class Pagina {
    private int id;
    
    private char op;
    
    private byte M;
    
    private byte R;
    
    private byte classe;
    
    public Pagina(int p, char op) {
        this.id = p;
        this.op = op;
        this.M = 0;
        this.R = 1;
        this.classe = 2;
    }
    
    public Pagina(Pagina p) {
        this.id = p.getId();
        this.op = p.getOperacao();
        this.M = p.getM();
        this.R = p.getR();
        this.classe = p.getClasse();
    }
        
    public int getId() {
        return this.id;
    }
    
    public char getOperacao() {
        return this.op;
    }
    
    public void setR() {
        this.R = 1;
        this.classe = (byte) ((this.classe | 0x02) & 0xFF);
    }
    
    public void clearR() {
        this.R = 0;
        this.classe = (byte) ((this.classe & 0x01) & 0xFF);
    }
    
    public byte getR() {
        return this.R;
    }
    
    public void setM() {
        this.M = 1;
        this.classe = (byte) ((this.classe | 0x01) & 0xFF);
    }
    
    public void clearM() {
        this.M = 0;
        this.classe = (byte) ((this.classe & 0x02) & 0xFF);
    }
    
    public byte getM() {
        return this.M;
    }
        
    public byte getClasse() {
        return this.classe;
    }
    
    public boolean equals(Pagina other) {
        if (this.getId() == other.getId())
            return true;
        return false;
    }
    
    @Override
    public Pagina clone() {
        return new Pagina(this.id, this.op);
    }
    
    @Override
    public String toString() {
        return "" + this.getId() + this.getOperacao() + " - " + this.getClasse();
    }
}
