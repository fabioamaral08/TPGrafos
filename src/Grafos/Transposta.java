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
    
    private Grafo Transposto;
    
    
    public void execute(Grafo g){       
        
        Representacao Grep = g.getRepresentacao();        
        Representacao GTrep = new ListaAdjacencia();
        
        int numVert = Grep.getNumVertices();
        GTrep.init(numVert, 1);
        No aux;
        
        for(int i = 0; i < numVert; i++){                                       // ENQUANTO NÃO PERCORRER TODOS OS VÉRTICES
            aux = Grep.getAdj(i);                                               // AUX RECEBE ADJACENCIA ATUAL
            while(aux != null){                                                 // ENQUANTO NÃO ACABAR ADJACÊNCIA
                GTrep.addAresta(aux.getVertID(), i, aux.getPeso());             // INSERE TRANSPOSTO NA REPRESENTAÇÃO
                aux = aux.getProx();
            }
        }
       
    }

    public Grafo getTransposto() {
        return Transposto;
    }
    
    //lol
    
}
