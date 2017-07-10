/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Grafos;

/**
 *isso
 * @author Danilo Medeiros Eler
 */
public class Grafo {
    private int numVertices;
    private Representacao representacao;
    private static boolean ponderado = false;
    
    public Grafo(int numVert,int tipo, Representacao representacao){
        this.numVertices = numVert;
        this.representacao = representacao;
        this.representacao.init(numVert,tipo);
    }

    public void addAresta(int vIni, int vFim){
        representacao.addAresta(vIni, vFim);
    }

    public Representacao getRepresentacao(){
        return representacao;
    }

    public void imprimeRepresentacao(String mensagem){
        representacao.imprimeRepresentacao(mensagem);
    }
    
    public static boolean isPonderado(){
        return ponderado;
    }
    
    public static void setPonderado(boolean pond){
        ponderado = pond;
    }
    
    public void nada(){}
    
}
