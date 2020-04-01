package TiroParabolico;

import java.awt.Graphics;

public class Target extends Item{
    private int direction;
    private final Game game;
    private final Animation bird;
    
    private int speed;
    /**
     * 
     * @param x
     * @param y
     * @param direction
     * @param width
     * @param height
     * @param game 
     */
    public Target(int x, int y, int direction, int width, int height, Game game){
        super(x,y, width, height);
        this.direction = direction;
        this.game = game;

        this.speed = 3;
        
        this.bird = new Animation(Assets.birdMove, 100);
    }
    /**
     * 
     * @return 
     */
    public int getDirection() {
        return direction;
    }
    /**
     * 
     * @param direction 
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }
    /**
     * 
     * @return 
     */
    public int getSpeed() {
        return speed;
    }
    /**
     * 
     * @param speed 
     */
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
            
        }else if(!game.getKeyManager().pause){
            this.setSpeed(3);
        }
    }
    
    @Override
    public void render(Graphics g){
        //Place animation of Enemy
        g.drawImage(bird.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
