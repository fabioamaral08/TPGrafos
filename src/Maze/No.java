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
public class No {

    private Cell cell;
    private No ant;

    public No(Cell c) {
        this.cell = c;
        this.ant = null;
    }

    public Cell getCell() {

        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }

}
