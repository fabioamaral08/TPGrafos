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

        comp.execute(gt); //reutiliza a busca em largura das componentes conexas
        this.componente = comp.getComponentes();
        this.numComp = comp.getNumComponentes();
    }
}
