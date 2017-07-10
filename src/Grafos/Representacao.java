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
    
    public int getNumVertices(){
        return numVert;
    }
    public abstract void init(int numVertices,int tipo);
    public abstract void addAresta(int vIni, int vFim);
    public abstract void imprimeRepresentacao(String mensagem);
    
}