package TiroParabolico;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage background;
    public static BufferedImage area;
    public static BufferedImage player;
    public static BufferedImage playerMove[];
    public static BufferedImage bird;
    public static BufferedImage birdMove[];
    public static BufferedImage gameOver; 
    public static BufferedImage pause;
    public static SoundClip backSound;
    public static SoundClip gunShot; 
    public static SoundClip ahem; 

    public Assets() {
    }

    public static void init() {
        background = ImageLoader.loadImage("/images/background.png");
        area = ImageLoader.loadImage("/images/square.png");
        backSound = new SoundClip("/sounds/danse.wav");
        ahem = new SoundClip("/sounds/ahem.wav");
        gunShot = new SoundClip("/sounds/Gunshot.wav");
        gameOver = ImageLoader.loadImage("/images/gameOver.jpg");
        pause = ImageLoader.loadImage("/images/pause2.png");
   
        bird = ImageLoader.loadImage("/images/bird.png");
        SpriteSheet spriteSheet = new SpriteSheet(bird);
        
        birdMove = new BufferedImage[5];
        
        for(int i = 0; i < 5; i++){
             birdMove[i] = spriteSheet.crop(i * 183, 0, 183, 168);

        } 
        
        player = ImageLoader.loadImage("/images/player.png");
        spriteSheet = new SpriteSheet(player);
        playerMove = new BufferedImage[5];
        for(int i = 0; i < 5; i++){
            playerMove[i] = spriteSheet.crop(i * 168, 0, 168, 216);
        }
    }

}
