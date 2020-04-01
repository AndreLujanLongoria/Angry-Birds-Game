package TiroParabolico;

import java.awt.Graphics;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Player extends Item{

    private int direction;
    private final Game game;
    private final Animation player;
    private int speed;
    
    private int xCoordinateStart;
    private int yCoordinateStart;
    private int xCoordinateEnd;
    private int yCoordinateEnd;
    private boolean startMovement;
    private double startingTime;
    
    private boolean touchedFloor;
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        
        this.speed = 3;
        this.xCoordinateStart = 0;
        this.yCoordinateStart = 0;
        this.xCoordinateEnd = 0;
        this.yCoordinateEnd = 0;
        this.startMovement = false;
        this.touchedFloor = false; 

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

    public boolean isStartMovement() {
        return startMovement;
    }

    public void setStartMovement(boolean startMovement) {
        this.startMovement = startMovement;
    }

    public boolean isTouchedFloor() {
        return touchedFloor;
    }

    public void setTouchedFloor(boolean touchedFloor) {
        this.touchedFloor = touchedFloor;
    }

    @Override
    public void tick() {
        
        // Moving player with mouse
       if(this.draggedCollision(game.getMouseManager().getX(), game.getMouseManager().getY())){
        if(game.getMouseManager().isIzquierdo()){
            setX(game.getMouseManager().getX() -50);
            setY(game.getMouseManager().getY() - 50);
            game.getMouseManager().setIzquierdo(false);
        }
        
        // If mouse pressed store the starting point to get the difference
        // and get the speed
        if(game.getMouseManager().isPressedMouse()){
            System.out.println("Entre Pressed");
            // Store initial coordinates
            this.xCoordinateStart = game.getMouseManager().getX();
            System.out.println(this.xCoordinateStart);
            this.yCoordinateStart = game.getMouseManager().getY();
            
            game.getMouseManager().setPressedMouse(false);
        }
        
        // When finished dragging we start the movement
        if(game.getMouseManager().isReleasedMouse()){
            System.out.println("Entre");
            game.getMouseManager().setReleasedMouse(false);
            // Set X and Y according to movement
            this.xCoordinateEnd = game.getMouseManager().getX();
            System.out.println(this.xCoordinateEnd);
            if(this.xCoordinateEnd < 0){
                this.xCoordinateEnd = 0;
            }
            this.yCoordinateEnd = game.getMouseManager().getY();
            this.startingTime = System.currentTimeMillis();
            //Start Movement
            this.startMovement = true; 
        }
        
        // Place other method to render one by one until collided
        // game.
        //System.out.println("Tick jugador");
        if(this.startMovement){
            
            // Get the difference between starting point
            double xDifference = abs(this.xCoordinateEnd - this.xCoordinateStart);
            double yDifference = abs(this.yCoordinateEnd - this.yCoordinateStart);
            int yStartingPoint = this.yCoordinateEnd;
            
            // Set time
            //one pixel is one meter, velocity is m/s
            double time = (System.currentTimeMillis() - this.startingTime)*0.001;
            
            // FOR ANY ANGLE
            /*
            // Change X position x = velocityx * time
            setX((int)(this.xCoordinateEnd + xDifference*time));
            System.out.println(this.x);
          
            // Change Y position y = velocityY *time - .5 * gravity * time^2
            // Remember the movement in Y is positive going down
            setY((int)(yStartingPoint + -1*yDifference*time + 0.5*9.81*pow(time,2.0)));
            System.out.println("Y");
            System.out.println(this.y);
            
            player.tick();
            
            */
            
            
            // In Case of collision
            /*
            if (getX() + 60 >= game.getWidth()) {
                // reset to starting point 
                setX(0);
                this.startMovement = false;
            }
            */
            
            //For an angle of 45 we use
            setX((int)(this.xCoordinateEnd + xDifference*time*Math.cos(45)));
            setY((int)(yStartingPoint + -1*xDifference*Math.sin(45)*time + 0.5*9.81*pow(time,2.0)));
            player.tick();
            game.getMouseManager().setX(this.getX());
            game.getMouseManager().setY(this.getY());
        }
       }
        
        // reset x position and y position if colision and stop animation
        if (getX() + 60 >= game.getWidth()) {
            setX(100);
            setY(game.getHeight()/2 - this.getHeight()/2);
            this.startMovement = false;
            this.setTouchedFloor(true);
            game.beep();
        }
        else if (getX() <= -30) {
            setX(-30);
        }
        if (getY() + 80 >= game.getHeight()) {
            setX(100);
            setY(game.getHeight()/2 - this.getHeight()/2);
            this.startMovement = false;
            this.setTouchedFloor(true);
            game.beep();
        }
        else if (getY() <= -20) {
            setX(100);
            setY(game.getHeight()/2 - this.getHeight()/2);
            this.startMovement = false;
            this.setTouchedFloor(true);
            game.beep();
        }
        
        if(game.getKeyManager().pause){
            this.setSpeed(0);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(player.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }

}
