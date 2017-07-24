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

    private int[] caminho;
    private int[] tempo;
    private int[] fila;

    public int[] getCaminho() {
        return caminho;
    }

    public int[] getTempo() {
        return tempo;
    }

    public void execute(Grafo g, int ini) {
        Representacao rep = g.getRepresentacao();
        int numVert = rep.getNumVertices();
        this.fila = new int[numVert];
        this.caminho = new int[numVert];
        this.tempo = new int[numVert];
        int cor[] = new int[numVert];
        int topo = 1;
        int atual = 0;
        fila[0] = ini;

        for (int i = 0; i < numVert; i++) {
            cor[i] = 0;
            this.caminho[i] = -1;
        }
        No aux;
        int ID;
        tempo[fila[0]] = 0;
        cor[fila[0]] = 1;
        while (topo != atual) {
            aux = rep.getAdj(fila[atual]);
            while (aux != null) {
                ID = aux.getVertID();
                if (cor[ID] == 0) {
                    this.tempo[ID] = tempo[fila[atual]] + 1;
                    this.fila[topo] = ID;
                    this.caminho[ID] = fila[atual];
                    cor[ID]++;
                    topo++;
                }
                aux = aux.getProx();
            }
            atual++;
        }

    }

}
