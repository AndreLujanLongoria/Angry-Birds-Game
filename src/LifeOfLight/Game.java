package LifeOfLight;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public class Game implements Runnable {
    private BufferStrategy bs;   
    private Graphics g;            
    private Display display;  
    private Thread thread;      
    private boolean running; 
    
    String title;                
    private final int width;       
    private final int height;           
    private Player player;  
    private Target target;
    private final KeyManager keyManager;
    private final MouseManager mouseManager;
    private int countLifes = 1; 

    private int lifes;
    private int points;
    private boolean notEnded;
    private final ReadandWrite saveFile;
    private int counterTouchedLimits;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        saveFile = new ReadandWrite();
        counterTouchedLimits = 0; 
        
        this.points = 0;
        //this.lifes = (int) (Math.random() * (7-5 +1)) + 5;
        this.lifes = 5;
        notEnded = true; 
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLifes() {
        return lifes;
    }

    public int getPoints() {
        return points;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isNotEnded() {
        return notEnded;
    }

    public void setNotEnded(boolean notEnded) {
        this.notEnded = notEnded;
    }

    public int getCounterTouchedLimits() {
        return counterTouchedLimits;
    }

    public void setCounterTouchedLimits(int counterTouchedLimits) {
        this.counterTouchedLimits = counterTouchedLimits;
    }
    
    
    
    
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         player = new Player(0, getHeight() - 100, 1, 100, 100, this);   
         target = new Target(getWidth() -200, getHeight() - 200, 1, 100, 100, this);
         
         display.getJframe().addKeyListener(keyManager);
         display.getJframe().addMouseListener(mouseManager);
         display.getCanvas().addMouseListener(mouseManager);
         display.getCanvas().addMouseMotionListener(mouseManager);
         display.getJframe().addMouseMotionListener(mouseManager);
                 
         Assets.backSound.setLooping(true);
         Assets.backSound.play();
    }
    
    @Override
    public void run() {
        init();
        int fps = 50;
        double timeTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while(notEnded){
            while (running) {
                now = System.nanoTime();
                delta += (now - lastTime) / timeTick;
                lastTime = now;

                if (delta >= 1) {
                    tick();
                    render();
                    delta --;
                }
            }
        }
        stop();
    }
    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }
    
   
    public void beep() {
        Assets.gunShot.play();
    }
    public Player getPlayer() {
        return player;
    }
   
    private void tick() {
        keyManager.tick();
        player.tick();
        target.tick();
        
        if(this.getLifes() == 0){
            this.setRunning(false);
            render();
        }
        // Check Collision with enemy
        if(player.isTouchedFloor()){
            this.setCounterTouchedLimits(this.getCounterTouchedLimits() +1);
            System.out.println("COUNTER");
            System.out.println(this.getCounterTouchedLimits());
            if(this.getCounterTouchedLimits() % 4 == 3){
                this.setLifes(lifes-1);
                System.out.println("ENTRE");
                this.setCounterTouchedLimits(this.getCounterTouchedLimits() +1);
               
                
            }
             // Restart touched floor
            player.setTouchedFloor(false);
        }
        
        
        
        
    }
    
    private void render() {
        bs = display.getCanvas().getBufferStrategy();

        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();
            
            g.drawImage(Assets.background, 0, 0, width, height, null);
            g.drawImage(Assets.area, 0, 0, 200, height, null);
            
            g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
            g.setColor(Color.red);
           
            //Place the lifes and points 
            g.drawString("Lifes: ", getWidth() -100, 25);
            g.drawString(String.valueOf(getLifes()), getWidth() -60, 25);
            
            //g.drawString(String.valueOf("1"), getWidth() -60, 25);
            g.drawString("Points: ", getWidth() -100, 45);
            g.drawString(String.valueOf(getPoints()), getWidth() -40, 45);
            //g.drawString(String.valueOf("2"), getWidth() -60, 45);
                 
            player.render(g);
            
            target.render(g);
            
            

            if(this.getKeyManager().pause){
                g.drawImage(Assets.pause, getWidth()/2 -150, getHeight()/2 -150, 300, 300, null);
            }
            
            // Save the game using G key if there is not stop
            if(this.getKeyManager().save && !this.getKeyManager().pause){
                System.out.println("Entered Save");
                // send data of lifes and points
                ReadandWrite.Save("gamestatus.txt", this.getLifes(), this.getPoints());
                this.getKeyManager().setSave(false);
            }
            
            if(this.getKeyManager().load && !this.getKeyManager().pause){
                System.out.println("Entered Save");
                // send data of lifes and points
                int data[] = {0,0};
               
                ReadandWrite.Load("gamestatus.txt", data);
                this.getKeyManager().setLoad(false);
                //Place logic to retrieve values and rewrite lifes and scores
                this.setLifes(data[0]);
                this.setPoints(data[1]);           
            }
            
            // End of the game
            if(!this.isRunning()){
                g.drawImage(Assets.gameOver, 0, 0, 800, 500, null);
                Assets.backSound.stop();
            }
            
            bs.show();
            g.dispose();
            
            
        }
    }

    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }

}
