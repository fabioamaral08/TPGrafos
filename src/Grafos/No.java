/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Grafos;



/**
 *
 * @author Danilo Medeiros Eler
 */
public class No {
    private int vertID;
    private No prox;
    private int peso;

    public int getPeso() {
        return peso;
    }

    
    public No(int vertID,int peso) {
        this.peso = peso;
        this.vertID = vertID;
        this.prox = null;
    }

    public int getVertID() {
        return vertID;
    }

    public void setVertID(int vertID) {
        this.vertID = vertID;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
}