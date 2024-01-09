package gamecontroller;

import entity.Entity;
import entity.NPCGuider;
import entity.Player;
import objects.SuperObject;
import tile.TileManager;
import ui.AssetSetter;
import ui.UI;
import ui.Window;

import javax.swing.*;
import java.awt.*;

public class GameController extends JPanel implements Runnable{
    final String title = "WongWong Hunts Treasure";

    // TILE SIZE SETTING
    final int originalTileSize = 16; // 16x16 the original size of each tile. Keep it small so that it can be flexible to upscale
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48X48, the actual size of each tile


    // Set the total number of tiles in horizontal and vertical
    final int maxScreenCol = 16; // 16 tiles horizontally
    final int maxScreenRow = 12; // 12 titles vertically


    // SCREEN SETTING
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels


    // CAMERA SETTING
    final int maxWorldCol = 50;
    final int maxWorldRow = 50;



    // SYSTEM
    private boolean isRunning = false;
    public KeyHandler keyHandler = new KeyHandler(this);
    SoundHandler backgroundMusic = new SoundHandler();
    SoundHandler soundEffect = new SoundHandler();
    public TileManager tileManager = new TileManager(this);
    public CollisionHandler collision = new CollisionHandler(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Thread thread;


    // ENTITY AND OBJECTS
    public Player player = new Player(this, keyHandler);
    public SuperObject[] objects = new SuperObject[20]; // Create an object array
    public Entity[] npc = new Entity[10];



    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;



    // FRAMES PER SECOND SETTING (FPS)
    int FPS = 60; // Draw 60 frames per second
    int secondInNanos = 1000000000;
    int nanosToMillis = 1000000;




    /**
     * GameController constructor
     */
    public GameController() {
        new Window(screenWidth, screenHeight, title,this);
        //this.setBackground(Color.BLACK); // Set the background color of the panel
        init();
        start();
    }

    private void init() {
        this.setDoubleBuffered(true); // reduce flickering and improve the rendering of graphics
        this.setFocusable(true); // Request focus to enable keyboard input for the panel
        this.addKeyListener(keyHandler);

        assetSetter.setObject(); // Set objects

        assetSetter.setNpcGuilder(); // Set Npc Guiders

        playMusic(6); // Set background music (index 0 or 6)
        stopMusic();
        gameState = playState;

    }


    /**
     * The run method is the game loop that updates game logic and draw frames
     * It's invoked when start a thread until the game is stopped, triggering the cleanup process.
     */
    // Game loop
    @Override
    public void run() {

        double drawInterval = secondInNanos/FPS; // draw a frame in every 0.0166 sec
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (thread != null) {
            // 1st update the game info
            updateAllComponents();
            // 2nd draw the screen with the updated info
            repaint(); // Swing will call paintComponent() at the appropriate time in the event dispatch thread.

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/ nanosToMillis;

                long remainingTimeMillis = Math.max(0, (long) remainingTime);
                Thread.sleep(remainingTimeMillis); // pause the game loop until the sleep time is over
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * update logic such as the position of characters, checking for collisions,
     * handling user input, and other game-related logic
     */
    public void updateAllComponents() {
        if (gameState == playState) {

            // PLAYER
            player.update();

            // NPC GUIDER
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {

        }
    }


    /**
     * draw method is responsible for rendering the current state of the game on the screen
     * @param graphics the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics); // Jpanel is the parent class

        Graphics2D graphics2D = (Graphics2D) graphics;

        // TILE
        tileManager.draw(graphics2D); // must draw the tiles first since it's layering

        // OBJECT
        for (int i = 0; i < objects.length; i++) {
            if(objects[i] != null) {
                objects[i].draw(graphics2D, this);
            }
        }


        // NPC GUIDER
        for(int i = 0; i < npc.length; i++){
            if(npc[i] != null) {
                npc[i].draw(graphics2D);
            }
        }

        // PLAYER
        player.draw(graphics2D);

        // UI
        ui.draw(graphics2D);

        graphics2D.dispose(); // Dispose of the graphics context to release system resources to save memories
    }


    public void playMusic(int index) {
        backgroundMusic.setFile(index);
        backgroundMusic.play();
        backgroundMusic.loop();
    }

    public void stopMusic(){
        backgroundMusic.stop();
    }

    public void playSoundEffect(int index){
        soundEffect.setFile(index);
        soundEffect.play();
    }


    /**
     * This method initiates a new thread that runs the game loop.
     * It ensures that multiple threads are not started concurrently.
     */
    private synchronized void start() {
        if (isRunning) return;

        // Create a new thread for the game and start it
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }


    /**
     * Attempt to join (wait for) the game thread to finish its execution
     * ensuring that one thread doesn't proceed until another has finished
     */
    private synchronized void stop() {
        if (!isRunning) return;

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        isRunning = false;
    }



    public int getTileSize() {
        return tileSize;
    }


    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

}