/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiroParabolico;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author andru
 */

public class MouseManager implements MouseListener, MouseMotionListener {
    
    private boolean izquierdo;
    private boolean derecho;
    private boolean releasedMouse;
    private boolean pressedMouse;
    private int x = 0;
    private int y = 0;
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        izquierdo = true;
        releasedMouse = false;
        pressedMouse = false;
        x = e.getX();
        y = e.getY();
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        izquierdo = true;
        pressedMouse = true;
        releasedMouse = false; 
        x = e.getX();
        y = e.getY();
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        izquierdo = true;
        releasedMouse = true;
        pressedMouse = false;
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent me) { 
        izquierdo = true;
        x = me.getX();
        y = me.getY();
    }

    @Override
    public void mouseMoved(MouseEvent me) { 
    }
    //Metodos de get y set de variables
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    public boolean isDerecho() {
        return derecho;
    }

    public boolean isIzquierdo() {
        return izquierdo;
    }

    public void setDerecho(boolean derecho) {
        this.derecho = derecho;
    }

    public void setIzquierdo(boolean izquierdo) {
        this.izquierdo = izquierdo;
    }

    public boolean isReleasedMouse() {
        return releasedMouse;
    }

    public void setReleasedMouse(boolean releasedMouse) {
        this.releasedMouse = releasedMouse;
    }

    public boolean isPressedMouse() {
        return pressedMouse;
    }

    public void setPressedMouse(boolean pressedMouse) {
        this.pressedMouse = pressedMouse;
    }
  
}