/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze;

import Grafos.Grafo;
import Grafos.Representacao;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author fabio
 */
public class Lab {

    private Cell[][] cells;
    private int lin;
    private int col;

    public Lab(int l, int c) {
        this.cells = new Cell[l][c];
        this.lin = l;
        this.col = c;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                this.cells[i][j] = new Cell(i, j);

            }
        }

        generate(l * c, l, c);
    }

    public Lab(Grafo g, int col) {
        Representacao rep = g.getRepresentacao();
        int numVert = rep.getNumVertices();
        this.lin = numVert / col;
        this.col = col;
        this.cells = new Cell[this.lin][this.col];
        
        for (int i = 0; i < this.lin; i++) {
            for (int j = 0; j < this.col; j++) {
                this.cells[i][j] = new Cell(i, j);

            }
        }
        
        for (int i = 0; i < this.lin; i++) {
            for (int j = 0; j < this.col; j++) {
                Grafos.No aux = rep.getAdj(i*col+j);
                while (aux != null){
                    int ID = aux.getVertID();
                    int l = (ID)/col;
                    int c = ID - (l*col);
                    removeParede(cells[l][c],cells[i][j]);
                    aux = aux.getProx();
                }
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getLin() {
        return lin;
    }

    public int getCol() {
        return col;
    }
    

    private void generate(int numCell, int lin, int col) {
        Stack pilha = new Stack();
        int visitados = 0;
        Cell c = this.cells[0][0];
        c.setVisited(true);
        pilha.add(c);
        ArrayList<Cell> vizinhos;

        while (true) {

            vizinhos = this.getVizinhos(c, lin, col);
            if (vizinhos.isEmpty()) {
                pilha.removeTopo();
                if (pilha.isEmpty()) {
                    break;
                }
                c = pilha.getTopo().getCell();
            } else {
                Random rand = new Random();
                int viz = rand.nextInt(vizinhos.size());
                Cell prox = vizinhos.get(viz);
                pilha.add(c);
                removeParede(c, prox);
                c = prox;
                c.setVisited(true);

            }
            vizinhos.clear();
        }
    }

    private void visita(Cell c, Stack p) {

    }

    private ArrayList<Cell> getVizinhos(Cell c, int lin, int col) {
        ArrayList<Cell> vizinhos = new ArrayList();
        int i = c.getX();
        int j = c.getY();
        if (i > 0 && !this.cells[i - 1][j].isVisited()) {
            vizinhos.add(this.cells[i - 1][j]);
        }
        if (i < lin - 1 && !this.cells[i + 1][j].isVisited()) {
            vizinhos.add(this.cells[i + 1][j]);
        }
        if (j > 0 && !this.cells[i][j - 1].isVisited()) {
            vizinhos.add(this.cells[i][j - 1]);
        }
        if (j < col - 1 && !this.cells[i][j + 1].isVisited()) {
            vizinhos.add(this.cells[i][j + 1]);
        }

        return vizinhos;
    }

    private void removeParede(Cell c1, Cell c2) {
        switch (c1.getX() - c2.getX()) {
            case 1:
                c1.derrubaParede(0);

                c2.derrubaParede(2);

                break;
            case -1:

                c1.derrubaParede(2);

                c2.derrubaParede(0);
                break;
            case 0:
                switch (c1.getY() - c2.getY()) {
                    case -1:

                        c1.derrubaParede(1);

                        c2.derrubaParede(3);
                        break;
                    case 1:
                        c1.derrubaParede(3);

                        c2.derrubaParede(1);

                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Erro ao derrubar parede");
                        break;
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Erro ao derrubar parede");
                break;
        }
    }

    public String exibe() {
        String aux = "";
        for (int i = 0; i < this.lin; i++) {
            for (int j = 0; j < this.col; j++) {
                aux = aux + this.cells[i][j].checkWalls(col);
            }
        }
        return aux;
    }

    public void mostra() {
        for (int i = 0; i < this.lin; i++) {
            System.out.println();
            for (int j = 0; j < this.col; j++) {
                System.out.print((this.cells[i][j].getX()) + "," + this.cells[i][j].getY() + "  ");
            }
        }
    }

    public void gravaArq() {
        int numVert = lin * col;

        String res = exibe();
        JFileChooser dialog = new JFileChooser();
        dialog.setMultiSelectionEnabled(false);
        dialog.setDialogTitle("Save file");
        dialog.setCurrentDirectory(new File("C:\\Users\\fabio\\Desktop\\Aulas\\Grafos\\arq"));
        int result = dialog.showDialog(null, "Salvar");
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String nomeArq = dialog.getSelectedFile().getAbsolutePath();
                File arquivo2 = new File(nomeArq);//definindo o nome do local do arquivo

                arquivo2.createNewFile();
                /*ESCREVER*/
                FileWriter fileW = new FileWriter(arquivo2);//arquivo para escrita
                BufferedWriter buffW = new BufferedWriter(fileW);

                buffW.write("0\r\n" + numVert + "\r\n" + res);

                buffW.close();
            } catch (IOException ex) {
                return;
            }
        }

    }
}
