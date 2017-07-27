/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze;

/**
 *
 * @author fabio
 */
public class Cell {

    private boolean[] paredes;
    /*
    0 - Cima
    1 - Direita
    2 - Baixo
    3 - Esquerda
     */

    private boolean visited;
    private int linha;
    private int coluna;

    public Cell(int x, int y) {
        this.paredes = new boolean[4];
        for (int i = 0; i < 4; i++) {
            this.paredes[i] = true;
        }
        visited = false;

        this.linha = x;
        this.coluna = y;

    }

    public int getX() {
        return linha;
    }

    public int getY() {
        return coluna;
    }

    public boolean[] getParedes() {
        return paredes;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    void derrubaParede(int i) {

        this.paredes[i] = false;
    }

    public String checkWalls(int col) {
        String aux = "";
        String ID = Integer.toString(this.linha * col + this.coluna);
        for (int i = 1; i < 3; i++) {
            if (!this.paredes[i]) {
                switch (i) {

                    case 1:
                        aux += ID + " " + Integer.toString((this.linha) * col + (this.coluna + 1)) + " " + Integer.toString(col) + "\r\n";
                        break;
                    case 2:
                        aux += ID + " " + Integer.toString((this.linha + 1) * col + this.coluna) + " " + Integer.toString(col) + "\r\n";
                        break;

                }
            }
        }

        return aux;
    }

}
