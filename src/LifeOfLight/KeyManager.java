package LifeOfLight;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    
    public boolean up;     
    public boolean down;  
    public boolean left;    
    public boolean right; 
    
    public boolean pause;
    public boolean save;
    public boolean load;

    private final boolean keys[];  // to store all the flags for every key

    public void setSave(boolean save) {
        keys[KeyEvent.VK_S] = save;
    }

    public void setLoad(boolean load) {
        keys[KeyEvent.VK_L] = load;
    }
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        //keys[e.getKeyCode()] = true;
        if(e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_L){
            
            //keys[e.getKeyCode()] = !keys[e.getKeyCode()];
        } else {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        // Place a condition specially for  Key P 
        if(e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_L){
            keys[e.getKeyCode()] = !keys[e.getKeyCode()];
        } else {
            keys[e.getKeyCode()] = false;
        }
    }

    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        pause = keys[KeyEvent.VK_P];
        save = keys[KeyEvent.VK_S];
        load = keys[KeyEvent.VK_L];
    }
}
