package LifeOfLight;

import java.awt.Graphics;

public class Player extends Item{

    private int direction;
    private final Game game;
    private final Animation player;
    private String keyboardDir;
    private int speed;
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        
        this.speed = 3; 
        
        this.player = new Animation(Assets.playerMove, 100);
    }

    public int getDirection() {
        return direction;
    }


    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void tick() {
        // moving player depending on flags
        if (game.getKeyManager().up) {
           setY(getY() - speed);
           keyboardDir = "up"; 
        }
        if (game.getKeyManager().down) {
           setY(getY() + speed);
           keyboardDir = "down";
        }
        if (game.getKeyManager().left) {
           setX(getX() - speed); 
           keyboardDir = "left";
           player.tick();
        }
        if (game.getKeyManager().right) {
           setX(getX() + speed);
           //game.beep();
           keyboardDir = "right";
           this.player.tick();
        }
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }
        else if (getY() <= -20) {
            setY(-20);
        }
        if(game.getKeyManager().pause){
            this.setSpeed(0);

        }else if(!game.getKeyManager().pause){
            this.setSpeed(3);
        }
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(player.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }

}
