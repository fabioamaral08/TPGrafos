/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Grafos;

/**
 *
 * @author Danilo Medeiros Eler
 */
public abstract class Representacao {
    protected int numVert = 0;
    protected int tipo;

    public int getTipo() {
        return tipo;
    }
    
    
    
    public int getNumVertices(){
        return numVert;
    }
    public abstract void init(int numVertices,int tipo);
    public abstract void addAresta(int vIni, int vFim,int peso);
    public abstract void imprimeRepresentacao(String mensagem);
    public abstract int getVal(int v1,int v2);
    public abstract No getAdj(int vert);
}