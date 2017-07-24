/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze;

import java.util.ArrayList;
import java.util.Random;
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
                aux = aux + this.cells[i][j].caminho(col);
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
}