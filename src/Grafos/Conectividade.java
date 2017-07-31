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
public class Conectividade {

    private int[] cor;
    private int[] componente;
    private int numComp;

    public int[] getCor() {
        return cor;
    }

    public int[] getComponente() {
        return componente;
    }

    public int getNumComp() {
        return numComp;
    }

    public void execute(Grafo g) {

        Topologia topsort = new Topologia();    //reutiliza o código que ordena por tempo de chegada
        topsort.execute(g);

        int[] ordem = topsort.getOrdem();   //Vetor com a ordem da busca

        Transposta transp = new Transposta(); //reutiliza a função transposta

        transp.execute(g);
        Grafo gt = transp.getTransposto();

        ComponentesConexas comp = new ComponentesConexas();

       Representacao rep = gt.getRepresentacao();
        this.cor = new int[rep.getNumVertices()];
        this.componente = new int[cor.length];
        
        
        numComp = 0;
        for(int i = 0;i<cor.length;i++){
            if(cor[ordem[i]] == 0){
                visita(ordem[i],rep);
                numComp++;
            }
        }
    }

    private void visita(int i, Representacao  rep) {
        this.cor[i] = 1;
        this.componente[i] = this.numComp;
        
        No aux = rep.getAdj(i);
        int ID;
        while(aux != null){
            ID = aux.getVertID();
            if(cor[ID] == 0){
                visita(ID,rep);
            }
            aux = aux.getProx();
        }
    }
}
