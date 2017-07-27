/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import java.util.ArrayList;

/**
 *
 * @author fabio
 */
public class Caminho {

    private int[] ant;
    private ArrayList<Integer> vertices;
    private int[] valor;

    public int[] getAnt() {
        return ant;
    }
    

    public void execute(Grafo g, int ini) {
        Representacao rep = g.getRepresentacao();
        int numVert = rep.getNumVertices();

        this.ant = new int[numVert];
        this.vertices = new ArrayList();
        this.valor = new int[numVert];

        for (int i = 0; i < numVert; i++) {
            this.valor[i] = Integer.MAX_VALUE;
            this.ant[i] = -1;
            vertices.add(i);
        }

        int[] cor = new int[numVert];
        No aux;
        this.valor[ini] = 0;
        int atual;
        while (!this.vertices.isEmpty()) {
            atual = encontraMenor();
            aux = rep.getAdj(atual);
            cor[atual] = 1;
            while (aux != null) {
                int ID = aux.getVertID();
                if (cor[ID] == 0) {
                    relaxa(atual, ID, aux.getPeso());
                }
                aux = aux.getProx();
            }
        }
    }

    private int encontraMenor() {
        int menor = Integer.MAX_VALUE;
        int index = 0;
        int count = 0;

        for (Integer i : vertices) {
            if (valor[i] < menor) {
                index = count;
                menor = valor[i];
            }
            count++;
        }
        int vert = vertices.get(index);
        vertices.remove(index);
        return vert;

    }

    private void relaxa(int atual, int outro, int peso) {
        if (valor[atual] == Integer.MAX_VALUE) {
            return;
        }
        if (valor[outro] > (valor[atual] + peso)) {
            valor[outro] = valor[atual] + peso;
            ant[outro] = atual;
        }

    }
}
