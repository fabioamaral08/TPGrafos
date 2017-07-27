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
    private int[] ant; //recuperação da arvore

    public int[] getAnt() {
        return ant;
    }

    public void execute(Grafo g, int vert) {
        Representacao rep = g.getRepresentacao();
        this.numVert = rep.getNumVertices();
        AGMPrim(g,vert, rep);
    }

    public void AGMPrim(Grafo g, int vert,Representacao rep) {
       
        //INICIALIZAÇÃO DOS VETORES E VARIAVEIS
        ant = new int[this.numVert];
        int[] cor = new int[this.numVert];
        int[] valor = new int[this.numVert];
        int fim = this.numVert;
        int atual = vert;
        No aux; //
        int id;
        //VALORES INICIAIS DOS VETORES
        for (int i = 0; i < this.numVert; i++) {
            ant[i] = -1;
            cor[i] = 0;
            valor[i] = Integer.MAX_VALUE;
        }
        valor[atual] = 0;
        
        while (fim > 0) {
            cor[atual] = 1;
            aux = rep.getAdj(atual);
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
            //PROCURA O CORTE LEVE
            for(int i = 0;i<this.numVert;i++){
                if(cor[i] == 0){
                    if(valor[i] < min){
                        min = valor[i];
                        proxVert = i;
                    }
                }
            }
            atual = proxVert;
            fim--; //decrementa o numero de vertices que não pertencem a solução
        }

        
    }


}
