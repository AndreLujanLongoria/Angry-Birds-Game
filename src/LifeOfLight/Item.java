package LifeOfLight;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Item {
    protected int x;        
    protected int y;        
    protected int width;
    protected int height;

    public Item(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


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
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * To update positions of the item for every tick
     */
    public abstract void tick(); //Son abstractos no tienen codigo

    public abstract void render(Graphics g);
    
    public boolean collision (Object o){ //Ally
        boolean bStatus = false;  
        if(o instanceof Item ){
            Rectangle rThis = new Rectangle(getX()-10, getY(),getWidth()*2, getHeight()-90);  //x y width height
            Item i = (Item)o;
            Rectangle rOther = new Rectangle(i.getX()+50, i.getY()+100,1, 1);
            bStatus = rThis.intersects(rOther);
        }
        return bStatus;
    }
    
    public boolean collisionRight(Object o){ //Enemy
        boolean bStatus = false; // assuming a collision 
        if(o instanceof Item ){
            // Or use this
            Rectangle rThis = new Rectangle(getX(), getY(),getWidth(), getHeight());
            Item i = (Item)o;
            Rectangle rOther = new Rectangle(i.getX(), i.getY(),i.getWidth(), i.getHeight());
            //For optimization use Rectangle rOther new (((Item o).getX(), ....))
            
            bStatus = rThis.intersects(rOther);
        }
        return bStatus;
        
    }

}
