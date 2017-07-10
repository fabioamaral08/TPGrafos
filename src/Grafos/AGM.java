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
    
    public void execute(Grafo g){
        Representacao rep = g.getRepresentacao();
    }
        public Grafo AGMPrimLista(Grafo g) {
        int[] valores = new int[this.numVert];
        No[] list = g.getRepresentacao()
        int fim, u, v, min, ind;
        limpaAux();     //Limpa cor/Antecessor
        No aux;
        for (int i = 0; i < this.numVert; i++) { //Inicializa vertice com o maximo (simulando infinito)
            valores[i] = Integer.MAX_VALUE;
        }
        u = 0;  //vertice inicial
        valores[u] = 0;
        this.corAnt[u][0] = 1;
        this.corAnt[u][1] = -1;
        fim = this.numVert - 1; // condição de parada

        while (fim >= 0) { //cada iteração visita um vetice. Quando todos foram visitados o loop termina.
            aux = this.list[u].getProx();
            while (aux != null) {   //Percorre as adjacências
                v = aux.getVertice();
                if (this.corAnt[v][0] == 0) {   //verifica se esse ja foi adicionado na solução;
                    if (valores[v] > aux.getValor()) {  //Se não foi adicionado, verificar se o vertice atual oferece um custo menor do que o custo anterior desse vertice
                        valores[v] = aux.getValor();    //Se o custo for melhor, atualizado o custo desse vertice
                        this.corAnt[v][1] = u;          //E atualiza o vertice que gera esse custo menor
                    }
                }
                aux = aux.getProx();
            }
            this.corAnt[u][0] = 2;//Vertice como pertencete a solução
            min = Integer.MAX_VALUE;
            for (int i = 0; i < this.numVert; i++) {//verifica quais dos vertices que ainda não foram adicionados a solução tem o menor custo associado
                if (this.corAnt[i][0] == 0 && valores[i] < min) {
                    u = i;
                    min = valores[i];
                }
            }//Ao final do loop, a variavel u tem o vertice que será adicionado no conjunto de soluções

            fim--; //decrementa o numero de vertices que faltam adicionar
        }
        return retArv(this.corAnt, this.numVert); //função que cria arvore a partir dos antecessores de um vertice;
    }
    }
}
