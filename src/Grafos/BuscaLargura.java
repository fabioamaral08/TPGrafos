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
    //VETORES AUXILIARES
    private int[] caminho;
    private int[] tempo;
    private int[] fila;
    
    public int[] getCaminho() {
        return caminho;
    }
    
    
    //PODE SER INTERESSANTE SABER
    public int[] getTempo() {
        return tempo;
    }

    public void execute(Grafo g, int ini) {
        
        //INICIALIZACAO DE VETORES E VARIAVEIS AUXILIARES
        Representacao rep = g.getRepresentacao();
        int numVert = rep.getNumVertices();
        this.fila = new int[numVert];
        this.caminho = new int[numVert];
        this.tempo = new int[numVert];
        int cor[] = new int[numVert];
        int topo = 1; //marca a proxima posição vaga na fila
        int atual = 0; //marca a posição do proximo a sair da fila
        fila[0] = ini; //adiviona o vertivie inicial na fila

        for (int i = 0; i < numVert; i++) {
            cor[i] = 0;
            this.caminho[i] = -1;
        }
        No aux;
        int ID;
        tempo[fila[0]] = 0;
        cor[fila[0]] = 1;
        
        while (topo != atual) { //enquanto a fila não esta vazia:
            aux = rep.getAdj(fila[atual]);
            while (aux != null) {
                ID = aux.getVertID();
                if (cor[ID] == 0) { //verifica adjacentes não visitados
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
