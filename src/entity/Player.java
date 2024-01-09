package entity;

import gamecontroller.GameController;
import gamecontroller.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static entity.Entity.DIRECTION.*;


/**
 * The Player class represents the player entity in the game.
 * It extends the Entity class and includes methods for updating and drawing the player on the screen.
 */
public class Player extends Entity {

    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    private boolean isGirlOutfit = true;
    public int playerLife = 3;
    private boolean invincible = false;
    //private int keyNums = 0;

    private static final Map<DIRECTION, BufferedImage[]> directionToImages = new HashMap<>(); // Map to store image arrays for player different directions.



    /**
     * Player constructor.
     *
     * @param gameController The GameController managing the game state.
     * @param keyHandler     The KeyHandler handling keyboard input.
     */
    public Player(GameController gameController, KeyHandler keyHandler) {
        super(gameController);
        this.keyHandler = keyHandler; // Key input

        // Camera system, keeps the screenX and Y positions always be placed on Player object
        this.screenX = gameController.getScreenWidth()/2 - (gameController.getTileSize()/2);
        this.screenY = gameController.getScreenHeight()/2 - (gameController.getTileSize()/2);

        loadImages();

        solidArea = new Rectangle(); // Player collision area. Note we could do rec(0,0,tile, tile) if full player size
        solidArea.x = 6;
        solidArea.y = 10;
        solidAreaDefaultX = solidArea.x ;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 35;
        solidArea.height = 35;

        setDefaultValues(); // Player default position
    }



    /**
     * Sets default values for the player, such as initial position and speed.
     */
    @Override
    public void setDefaultValues() {
        worldX = gameController.getTileSize() * 23; // starting position at center
        worldY = gameController.getTileSize() * 21;
        speed = 4;
        direction = DOWN;
    }

    @Override
    public void loadImages() {
        try {
            // Load and scale player images for different directions based on the outfit state.
            String outfitPrefix = isGirlOutfit ? "girl_" : "boy_";

            directionToImages.put(UP, new BufferedImage[]{
                    scaleImage("/player/girl_up_1"),
                    scaleImage("/player/girl_up_2"),
                    scaleImage("/player/boy_up_1"),
                    scaleImage("/player/boy_up_2")}
            );
            directionToImages.put(DOWN, new BufferedImage[]{
                    scaleImage("/player/girl_down_1"),
                    scaleImage("/player/girl_down_2"),
                    scaleImage("/player/boy_down_1"),
                    scaleImage("/player/boy_down_2")}
            );
            directionToImages.put(LEFT, new BufferedImage[]{
                    scaleImage("/player/girl_left_1"),
                    scaleImage("/player/girl_left_2"),
                    scaleImage("/player/boy_left_1"),
                    scaleImage("/player/boy_left_2")}
            );
            directionToImages.put(RIGHT, new BufferedImage[]{
                    scaleImage("/player/girl_right_1"),
                    scaleImage("/player/girl_right_2"),
                    scaleImage("/player/boy_right_1"),
                    scaleImage("/player/boy_right_2")}
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Updates the player's position based on keyboard input and animates the player's sprite.
     */
    @Override
    public void update() {

        if (keyHandler.isUpPressed || keyHandler.isDownPressed || keyHandler.isLeftPressed || keyHandler.isRightPressed) {

            // First check key input and set direction
            if (keyHandler.isUpPressed) {
                direction = UP;
            } else if (keyHandler.isDownPressed) {
                direction = DOWN;
            } else if (keyHandler.isLeftPressed) {
                direction = LEFT;
            } else if (keyHandler.isRightPressed) {
                direction = RIGHT;
            }

            // Then check Tile collision
            isCollision = false;
            gameController.collision.checkTileCollision(this);

            // Then check Object collision
            int objectIndex = gameController.collision.checkObjectCollision(this, true);
            pickUpObject(objectIndex);

            // Then check NPC guider collision
            int npcIndex = gameController.collision.checkEntity(this, gameController.npc);
            interfaceNPC(npcIndex);

            // Player can move only collision is false
            if (isCollision == false) {
                adjustCoordinators(direction);
            }

            animateSprite(); // change the image every 10 frames

        }
    }



    public void pickUpObject(int index){

        if(index != 999) { // 999 is just a random index number that not in the array, indicates no objects were touched


 /*

  String objectName = gameController.objects[index].name;
 switch(objectName) {
                case "Key":
                    gameController.playSoundEffect(8); // sound index 1 or 8
                    keyNums++;
                    gameController.objects[index] = null;
                    gameController.ui.showMessage("You got a key");
                    break;
                case "Door":
                    if(keyNums > 0) {
                        gameController.playSoundEffect(9); // sound index 3 or 9
                        gameController.objects[index] = null;
                        keyNums--;
                        gameController.ui.showMessage("You opened the door.");
                    } else {
                        gameController.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots":
                    gameController.playSoundEffect(2);
                    speed += 2;
                    gameController.objects[index] = null;
                    gameController.ui.showMessage("Speed up!");
                    break;
                case "Lantern":
                    gameController.playSoundEffect(2);
                    speed -= 2;
                    gameController.objects[index] = null;
                    gameController.ui.showMessage("You're slow down.");
                    break;
                case "Outfit":
                    handleOutfitObject(index);
                    isRedOutfit = false;
                    break;
                case "Chest":
                    gameController.ui.isGameOver = true;
                    gameController.stopMusic();
                    gameController.playSoundEffect(4);
                    gameController.objects[index] = null;
                    gameController.ui.showMessage("You found the queen!");
                    break;
            }


  */
        }

    }


    public void interfaceNPC(int index) {

        if (index != 999) {
            if (gameController.keyHandler.isEnterPressed) {
                gameController.gameState = gameController.dialogueState;
                gameController.npc[index].speak();

            }
        }
        gameController.keyHandler.isEnterPressed = false;
    }


    private void handleOutfitObject(int index) {
        if (!gameController.objects[index].isCollected) {
            gameController.playSoundEffect(2); // sound index for collecting outfit
            gameController.objects[index].isCollected = true;
            gameController.ui.showMessage("You changed your outfit!");

            // Toggle outfit state
            isGirlOutfit = !isGirlOutfit;

            // Reload player images based on the new outfit state
            loadImages();
        }
    }






@Override
public void draw(Graphics2D graphics2D) {

        BufferedImage[] images = directionToImages.get(direction); // Retrieve the array of images corresponding to the current player direction
        BufferedImage image = (spriteNum == 1) ? images[0] : images[1]; // Select the current image based on the sprite number

        graphics2D.drawImage(image, screenX, screenY, null);

/*
        // Set the font to bold
        Font originalFont = graphics2D.getFont();
        Font boldFont = new Font(originalFont.getName(), Font.BOLD, originalFont.getSize());
        graphics2D.setFont(boldFont);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.drawString("Yo, big meow is heading " + direction + " chooochooo", screenX, screenY - 5);
        graphics2D.setFont(originalFont); // Reset the font to the original after drawing the string


 */
    }



    public boolean isGirlOutfit() {
        return isGirlOutfit;
    }

}