/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class ListaAdjacencia extends Representacao {

    private No[] listaVertices;

    @Override
    public void init(int numVertices, int tipo) {
        numVert = numVertices;
        listaVertices = new No[numVertices];
        this.tipo = tipo;
    }

    public No[] getLista() {
        return this.listaVertices;
    }

    @Override
    public void addAresta(int vIni, int vFim, int peso) {
        No novoNo = new No(vFim, peso);
        novoNo.setProx(listaVertices[vIni]);
        listaVertices[vIni] = novoNo;
        if (this.tipo == 0) {
            novoNo = new No(vIni, peso);
            novoNo.setProx(listaVertices[vFim]);
            listaVertices[vFim] = novoNo;
        }
    }

    public No getAdjacentes(int vert) {
        return listaVertices[vert];
    }
    
    public int getVal(int v1, int v2){
        No aux = this.listaVertices[v1];
        while(aux != null){
            if(aux.getVertID() == v2){
                return aux.getPeso();
            }
            aux = aux.getProx();
        }
        return -1;
    }
    
    @Override
    public void imprimeRepresentacao(String mensagem) {
        System.out.println("=================================");
        System.out.println(mensagem);
        System.out.println("=================================\n");
        for (int i = 0; i < listaVertices.length; i++) {
            No aux = listaVertices[i];
            System.out.print("(((" + i + ")))-->");
            while (aux != null) {
                System.out.print("|" + aux.getVertID() + "|--> ");
                aux = aux.getProx();
            }
            System.out.println("//");
        }
    }

    @Override
    public No getAdj(int vert) {
        return this.listaVertices[vert];
    }

}
