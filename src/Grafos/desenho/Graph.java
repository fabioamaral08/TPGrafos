/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.desenho;

import Grafos.Grafo;
import Grafos.No;
import Grafos.Representacao;
import Grafos.desenho.color.ColorScale;
import Grafos.desenho.color.GrayScale;
import Grafos.desenho.color.RainbowScale;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class Graph extends Object implements Cloneable {

    protected ArrayList<Vertex> vertex = new ArrayList<Vertex>();
    protected ArrayList<Edge> edges = new ArrayList<Edge>();
    protected boolean Oriented;
    protected boolean app;
    protected int tipo;
    protected int[] pos;

    public void setApp(boolean app) {
        this.app = app;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Graph(int nVert, int tipo) {
        this.tipo = tipo;
        RainbowScale cS = new RainbowScale();
        //GrayScale cS = new GrayScale();
        int colorStep = 255 / nVert;
        for (int i = 0; i < nVert; i++) {
            Vertex v = new Vertex();
            v.setID(i);
            v.setColor(cS.getColor(i * colorStep));
            // v.setColor(Color.RED);
//            if (i % 2 == 0){
//                v.setSelected(false);
//            }
            this.vertex.add(v);

        }
        if (tipo == 1) {
            this.Oriented = true;
        } else {
            this.Oriented = false;
        }
        computeCircledPosition(150);
        //computeLinePosition();
    }

    public Graph(int[] ordem, ArrayList<Edge> arestas) {
        RainbowScale cS = new RainbowScale();
        this.pos = new int[ordem.length];
        int colorStep = 255 / ordem.length;
        for (int i = 0; i < ordem.length; i++) {
            pos[ordem[i]] = i;
            Vertex novo = new Vertex();
            novo.setID(ordem[i]);
            novo.setColor(cS.getColor(i * colorStep));
            this.vertex.add(novo);
        }

        for (Edge e : arestas) {
            Vertex v1 = e.getSource();
            Vertex v2 = e.getTarget();
            int cont = 0;
            for (Vertex v : this.vertex) {
                if (v.getID() == v1.getID()) {
                    v1 = v;
                    cont++;
                }

                if (v.getID() == v2.getID()) {
                    v2 = v;
                    cont++;
                }
                if (cont == 2) {
                    break;
                }
            }
            Edge nova = new Edge(v1, v2, e.getPeso(), true);
            this.edges.add(nova);
        }

        computeLinePosition();
    }

    public Graph(Grafo g) {
        Representacao rep = g.getRepresentacao();
        int numVert = rep.getNumVertices();
        this.vertex = new ArrayList();
        this.edges = new ArrayList();
        boolean dir;
        if (rep.getTipo() == 1) {
            dir = true;
        } else {
            dir = false;
        }
        RainbowScale cS = new RainbowScale();
        //GrayScale cS = new GrayScale();
        int colorStep = 255 / numVert;
        for (int i = 0; i < numVert; i++) {
            Vertex v = new Vertex();
            v.setID(i);
            v.setColor(cS.getColor(i * colorStep));

            this.vertex.add(v);

        }

        for (int i = 0; i < numVert; i++) {
            No aux = rep.getAdj(i);
            while (aux != null) {
                edges.add(new Edge(vertex.get(i), vertex.get(aux.getVertID()), aux.getPeso(), dir));
                aux = aux.getProx();
            }

        }
        if (tipo == 1) {
            this.Oriented = true;
        } else {
            this.Oriented = false;
        }

        computeCircledPosition(150);
    }

    public void addVertex(Vertex v) {
        this.vertex.add(v);
    }

    public void addEdge(Edge e) {
        this.edges.add(e);
    }

    public void computeCircledPosition(int ray) {
        int nVert = this.vertex.size();
        int step = 360 / nVert;
        int deslocX = 100 + ray;
        int deslocY = 100 + ray;
        for (int i = 0; i < nVert; i++) {
            double ang = i * step;
            ang = ang * Math.PI / 180;//necessario em radianos
            float X = (float) Math.cos(ang);
            float Y = (float) Math.sin(ang);
            X = X * ray + deslocX;
            Y = Y * ray + deslocY;
            this.vertex.get(i).setX(X);
            this.vertex.get(i).setY(Y);
        }
    }

    public void computeLinePosition() {
        int nVert = this.vertex.size();
        int step = 100;
        int deslocX = 50;
        int deslocY = 100;
        for (int i = 0; i < nVert; i++) {
            double pos = i * step;

            float X = (float) pos + deslocX;
            float Y = (float) 200;

            this.vertex.get(i).setX(X);
            this.vertex.get(i).setY(Y);
        }
    }

    public void computeMazePosition(int col) {
        this.app = true;
        int nVert = this.vertex.size();
        int step = 50;
        int deslocX = 50;
        int deslocY = 50;
        int count = 0;
        double pos = 50;
        for (int i = 0; i < nVert; i++) {
            pos += step;

            float X = (float) pos + deslocX;
            float Y = (float) deslocY;

            Vertex v = this.vertex.get(i);
            v.setX(X);
            v.setY(Y);
            v.setColor(Color.BLACK);
            count++;
            if (count >= col) {
                count = 0;
                deslocY += 50;
                pos = 50;
            }
        }
        for (Edge e : edges) {
            e.setDirected(false);
        }
    }

    public boolean isOriented() {
        return Oriented;
    }

    public ArrayList<Vertex> getVertex() {
        return this.vertex;
    }

    public void draw(java.awt.Graphics2D g2) {
        //Draw each edges of the graph
        for (Edge edge : edges) {
            edge.draw(g2, !this.app);
        }
        //Draw each vertice of the graph
        for (Vertex v : this.vertex) {
            v.draw(g2, !this.app);
        }
    }

    public void drawTopo(java.awt.Graphics2D g2) {
        //Draw each edges of the graph
        int inter = 1;
        for (Edge edge : edges) {
            edge.drawTopo(g2, inter);
            inter *= (-1);
        }
        //Draw each vertice of the graph
        for (Vertex v : this.vertex) {
            v.draw(g2, !app);
        }
    }

    public java.awt.Dimension getSize() {
        if (this.vertex.size() > 0) {
            float maxX = vertex.get(0).getX();
            float minX = vertex.get(0).getX();
            float maxY = vertex.get(0).getY();
            float minY = vertex.get(0).getY();

            //Encontra o maior e menor valores para X e Y
            for (Vertex v : this.vertex) {
                if (maxX < v.getX()) {
                    maxX = v.getX();
                } else if (minX > v.getX()) {
                    minX = v.getX();
                }

                if (maxY < v.getY()) {
                    maxY = v.getY();
                } else if (minY > v.getY()) {
                    minY = v.getY();
                }
            }

            int w = (int) (maxX + (this.vertex.get(0).getRay() * 5)) + 350;
            int h = (int) (maxY + (this.vertex.get(0).getRay() * 5));

            return new java.awt.Dimension(w, h);
        } else {
            return new java.awt.Dimension(0, 0);
        }
    }

    public Graph clone() {
        Graph g = new Graph(this.vertex.size(), this.tipo);
        g.edges = (ArrayList) this.edges.clone();
        g.vertex = (ArrayList) this.vertex.clone();
        g.Oriented = this.Oriented;
        g.tipo = this.tipo;
        return g;
    }

    public void buscaLargura(int[] caminho) {
        for (int i = 0; i < caminho.length; i++) {
            if (caminho[i] != -1) {
                Edge a = new Edge(this.vertex.get(caminho[i]), this.vertex.get(i), 0, true);
                a.setSelected(true);
                this.edges.add(a);
            }
        }
    }

    public void caminho(int[] caminho, int vi, int vf) {
        int aux = vf;
        Vertex v1 = this.vertex.get(vi);
        Vertex v2 = this.vertex.get(vf);
        for (Vertex v : vertex) {
            v.setColor(Color.BLACK);
        }
        v1.setColor(Color.GREEN);
        v2.setColor(Color.RED);

        while (caminho[aux] != -1) {
            Edge a = new Edge(this.vertex.get(caminho[aux]), this.vertex.get(aux), 0, true);
            a.setSelected(true);
            this.edges.add(a);
            aux = caminho[aux];

        }
    }

    public void agm(int[] ant){
        for (int i = 0; i < ant.length; i++) {
            if (ant[i] != -1) {
                Edge a = new Edge(this.vertex.get(ant[i]), this.vertex.get(i), 0, false);
                a.setSelected(true);
                this.edges.add(a);
            }
        }
    }
}
