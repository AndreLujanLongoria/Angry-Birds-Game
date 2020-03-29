package LifeOfLight;

import java.awt.Graphics;

public class Target extends Item{
    private int direction;
    private final Game game;
    private final Animation bird;
    
    private int speed;
    
    public Target(int x, int y, int direction, int width, int height, Game game){
        super(x,y, width, height);
        this.direction = direction;
        this.game = game;

        this.speed = 3;
        
        this.bird = new Animation(Assets.birdMove, 100);
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
    public void tick(){
        // Movement to left and check when collision to left
        bird.tick();
        //Pausar el juego
        if(game.getKeyManager().pause){
            this.setSpeed(0);
            //Assets.backSound.stop();
            //this.enemySelected = false;
            
        }else if(!game.getKeyManager().pause){
            this.setSpeed(3);
            //Assets.backSound.play();
            //this.enemySelected = true;
        }
    }
    
    @Override
    public void render(Graphics g){
        //Place animation of Enemy
        g.drawImage(bird.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
