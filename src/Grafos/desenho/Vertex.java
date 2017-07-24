/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.desenho;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class Vertex {

    private float x;
    private float y;
    private int ray = 12;
    private Boolean selected = true;
    private Color color = Color.RED;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void draw(java.awt.Graphics2D g2, boolean texto) {
        if (this.selected) {
            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
            g2.setStroke(new java.awt.BasicStroke(3.0f));
        } else { //not selected and there is not a global vertex selected
            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.2f));
            g2.setStroke(new java.awt.BasicStroke(1.5f));
        }

        g2.setColor(this.color);
        g2.fillOval(((int) this.x) - this.getRay(), ((int) this.y)
                - this.getRay(), this.getRay() * 2, this.getRay() * 2);

        g2.setColor(Color.BLACK);
        g2.drawOval(((int) this.x) - this.getRay(), ((int) this.y)
                - this.getRay(), this.getRay() * 2, this.getRay() * 2);

        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));

        if (texto) {
            this.drawText(g2, new Point((int) this.x - 4, (int) this.y + 4), Integer.toString(ID));
        }

    }

    public float getX() {
        return x;
    }

    public void setX(float X) {
        this.x = X;
    }

    public float getY() {
        return y;
    }

    public void setY(float Y) {
        this.y = Y;
    }

    public int getRay() {
        return ray;
    }

    public void setRay(int ray) {
        this.ray = ray;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean flag) {
        this.selected = flag;
    }

    private void drawText(Graphics2D g2, Point s, String text) {
        g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
        if (this.color.equals(Color.BLACK)) {
            g2.setColor(Color.WHITE);
        } else {
            g2.setColor(Color.BLACK);
        }
        // g2.rotate(Math.acos(cos), pc.x, pc.y);

        g2.drawString(text, s.x, s.y);

        // g2.rotate(-Math.acos(cos), pc.x, pc.y);
    }
}
