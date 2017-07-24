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
public class Stack {

    private No topo;

    public Stack() {
        this.topo = null;
    }

    public void add(Cell c) {
        No n = new No(c);

        n.setAnt(topo);
        this.topo = n;
    }

    public No getTopo() {
        return topo;
    }

    public No removeTopo() {
        if (this.topo != null) {
            No n = this.topo;
            this.topo = n.getAnt();
            return n;
        }
        return null;

    }

    public boolean isEmpty() {
        if (this.topo == null) {
            return true;
        }
        return false;
    }
}
