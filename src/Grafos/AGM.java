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
public class AGM {

    private int numVert;

    public int[] execute(Grafo g, int vert) {
        this.numVert = g.getRepresentacao().getNumVertices();
        Representacao rep = g.getRepresentacao();
        return AGMPrimLista(g,vert);
    }

    public int[] AGMPrimLista(Grafo g, int vert) {
        int[] ant = new int[this.numVert];
        int[] cor = new int[this.numVert];
        int[] valor = new int[this.numVert];
        int fim = this.numVert;
        int atual = vert;
        No[] list = ((ListaAdjacencia) g.getRepresentacao()).getLista();
        No aux;
        int id;
        Representacao rep = g.getRepresentacao();
        for (int i = 0; i < this.numVert; i++) {
            ant[i] = -1;
            cor[i] = 0;
            valor[i] = Integer.MAX_VALUE;
        }
        valor[atual] = 0;
        while (fim > 0) {
            cor[atual] = 1;
            aux = list[atual];
            while (aux != null) {
                id = aux.getVertID();
                if (cor[id] == 0) {
                    if (valor[id] > aux.getPeso()) {    //O valor atual da adjacencia é maior que a adjacencia do atual com ele?
                        valor[id] = aux.getPeso();
                        ant[id] = atual;
                    }
                }
                aux = aux.getProx();
            }
            int min = Integer.MAX_VALUE;
            int proxVert = -1;
            for(int i = 0;i<this.numVert;i++){
                if(cor[i] == 0){
                    if(valor[i] < min){
                        min = valor[i];
                        proxVert = i;
                    }
                }
            }
            atual = proxVert;
            fim--;
        }

        return ant;
    }


}
