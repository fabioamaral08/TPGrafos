/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

/**
 *
 * @author fabio
 */
public class BuscaLargura {
    private int[][] caminho;
    private int[] tempo;

    public int[][] getCaminho() {
        return caminho;
    }

    public int[] getTempo() {
        return tempo;
    }
    
    
    
    public void execute(Grafo g, int ini, int fim) {
        Representacao rep = g.getRepresentacao();
        int numVert = rep.getNumVertices();
        

    }

    public void buscaLarguraLista(int ini, int numVert, No[] list) {
        caminho = new int[numVert][2];
        tempo = new int[numVert];
        int[] fila = new int[numVert];
        int topo, prox, vertAux, ant;
        No aux;

        fila[0] = ini;
        topo = 0;
        prox = 1;
        caminho[ini][0] = 1;
        caminho[ini][0] = -1;
        tempo[0] = 0;

        while (topo != prox) {
            ant = fila[topo];
            aux = list[fila[topo]].getProx();
            while (aux != null) {
                vertAux = aux.getVertID();
                if (caminho[vertAux][0] == 0) {
                    fila[prox] = vertAux;
                    prox++;
                    caminho[vertAux][0] = 1;
                    caminho[vertAux][1] = ant;
                    tempo[vertAux] = tempo[ant] + 1;
                }
                aux = aux.getProx();
            }
            topo++;
        }

    }
}
