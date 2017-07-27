/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Grafos;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class Coloracao {
    private int cores[] = null;
    
    public void execute(Grafo grafo) {
        Representacao rep = grafo.getRepresentacao();
        cores = new int[rep.getNumVertices()];
        //INICIALIZA CORES ATUAIS
        for (int i=0; i<cores.length; i++){
            cores[i] = -1;
        }
        int vMaiorGrau = verticeMaiorGrau(rep);
        coloreVertice(rep, vMaiorGrau);
    }

    private void coloreVertice(Representacao rep, int vert) {
        cores[vert] = corApropriada(rep, vert);
        No adj = rep.getAdj(vert);
        while (adj != null) { //enquanto não verificou todos os adjacentes
            if (cores[adj.getVertID()] == -1) { //se o adjacente não foi colorido
                coloreVertice(rep, adj.getVertID()); //colore adjacente
            }
            adj = adj.getProx();
        }
    }
    
    private int corApropriada(Representacao rep, int vert){
        int cor = -1;
        Boolean flag = false;        
        while (!flag){
            cor++;
            No adj = rep.getAdj(vert);
            while (adj!=null && cores[adj.getVertID()] != cor){ //percorre adjacentes e compara as cores;
                adj = adj.getProx();
            }
            if (adj == null){//percorreu todas as adjacencias e não teve cor igual;
                flag = true;
            }            
        }        
        return cor;
    }

    private int verticeMaiorGrau(Representacao rep){
        int vert = 0;
        int maior = -1;
        int numVert = rep.getNumVertices();
        for (int i=0; i<numVert; i++){
            int cont = 0;
            No aux = rep.getAdj(i);
            while (aux != null){ //percorre adjacencias
                cont++;
                aux = aux.getProx();
            }
            if (cont > maior){ //compara com o maior atual
                maior = cont;
                vert = i;
            }
        }
        return vert;
    }
    //RECUPERACAO DO RESULTADO
    public int getNumCores(){
        int numComp = 0;
        int count[] = new int[cores.length];
        for (int i=0; i< count.length; i++){
            count[i] = 0;
        }

        for (int i=0; i<cores.length; i++){
            if (count[cores[i]] == 0){
                count[cores[i]]++;
            }
        }

        for (int i=0; i<count.length; i++){
            numComp = numComp + count[i];
        }

        return numComp;
    }

    public int[] getCores() {
        return cores;
    }    
}