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
public class Transposta {

    private Grafo Transposto;                                                   // CRIA GRAFO "Transposto"

    public void execute(Grafo g) {                                              //

        Representacao Grep = g.getRepresentacao();                              //"Grep" RECEBE REPRESENTAÇÃO DO GRAFO
        Representacao GTrep = new ListaAdjacencia();                            //"GTrep" RECEBERÁ O GRAFO TRANSPOSTO DE "Grep"

        int numVert = Grep.getNumVertices();                                    //numVert RECEBE O NÚMERO DE VERTICES DE "Grep"
        Transposto = new Grafo(numVert, 1, GTrep);                              

        No aux;

        for (int i = 0; i < numVert; i++) {                                     // ENQUANTO NÃO PERCORRER TODOS OS VÉRTICES
            aux = Grep.getAdj(i);                                               // AUX RECEBE ADJACENCIA ATUAL
            while (aux != null) {                                               // ENQUANTO NÃO ACABAR ADJACÊNCIA
                this.Transposto.addAresta(aux.getVertID(), i, aux.getPeso());   // INSERE TRANSPOSTO NA REPRESENTAÇÃO
                aux = aux.getProx();                                            // PEGA PROX AUX

            }
        }

    }

    public Grafo getTransposto() {
        return Transposto;
    }

   
}
