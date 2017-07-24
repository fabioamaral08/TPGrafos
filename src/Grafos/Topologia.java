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
public class Topologia {

    private int[] ordem;
    private int topo;
    private int[] cor;

    public int[] getOrdem() {
        return ordem;
    }

    public int getTopo() {
        return topo;
    }
    
    
    
    public void execute(Grafo g) {
        buscaProf(g.getRepresentacao());

    }

    private void buscaProf(Representacao rep) {
        int numVert = rep.getNumVertices();
        this.ordem = new int[numVert];
        this.topo = numVert -1;
        this.cor = new int[numVert];

        for (int i = 1; i < numVert; i++) {
            this.cor[i] = 0;
        }
        
        for(int i = 0; i< numVert; i++){
            if(this.cor[i] == 0){
                visita(i,rep);
            }
        }

    }

    private void visita(int i, Representacao rep) {
        this.cor[i] = 1;
        No aux = rep.getAdj(i);
        int id;
        while(aux != null){
            id = aux.getVertID();
            if(cor[id] == 0){
                visita(id,rep);
            }
            
            aux = aux.getProx();
        }
        this.ordem[this.topo--] = i;
    }
}
