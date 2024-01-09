package entity;

import gamecontroller.GameController;
import gamecontroller.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static entity.Entity.DIRECTION.*;
import static entity.Entity.DIRECTION.LEFT;

public class Entity {

    BufferedImage image;
    GameController gameController;


    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public Rectangle solidArea = new Rectangle(0,0, 48, 48); // create an invisible/abstract rec for collision
    public int speed;

    public boolean isCollision = false;

    public int worldX;
    public int worldY;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int actionLockCount = 0;
    String[] dialogues = new String[20];
    int dialogueIndex = 0;
    public DIRECTION direction;

    public static enum DIRECTION {
        UP, DOWN, LEFT, RIGHT
    }





    /** Map to store image arrays for different directions. */
    public static final Map<DIRECTION, BufferedImage[]> directionToImages = new HashMap<>();

    public Entity(GameController gameController){
        this.gameController = gameController;
    }

    protected void setDefaultValues() {}

    protected void loadImages() {}


    protected BufferedImage scaleImage(String imagePath) throws IOException {

        UtilityTool utilityTool = new UtilityTool();
        image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));

        return utilityTool.scaleImage(image, gameController.getTileSize(), gameController.getTileSize());
    }


    public void draw(Graphics2D graphics2D) {

        // Get the size of each tile
        int tileSize = gameController.getTileSize();

        // Get the screen coordinates and world coordinates of the player
        int playerScreenX = gameController.player.screenX;
        int playerScreenY = gameController.player.screenY;
        int playerWorldX = gameController.player.worldX;
        int playerWorldY = gameController.player.worldY;

        // Calculate the screen coordinates where the tile should be drawn
        int screenX = worldX - playerWorldX + playerScreenX; // Calculate a horizontal relative position of the tile on the screen based on the player's position
        int screenY = worldY - playerWorldY + playerScreenY; // Calculate a vertical relative position of the tile on the screen based on the player's position
        BufferedImage image = null;

        // Check if the tile is within the visible screen area (viewport) before drawing
        if (worldX + tileSize > playerWorldX - playerScreenX &&
                worldX - tileSize < playerWorldX + playerScreenX &&
                worldY + tileSize > playerWorldY - playerScreenY &&
                worldY - tileSize < playerWorldY + playerScreenY) {

            BufferedImage[] images = directionToImages.get(direction); // Retrieve the array of images corresponding to the current player direction
            image = (spriteNum == 1) ? images[0] : images[1]; // Select the current image based on the sprite number

            // Only draw the tile image at the calculated screen coordinates instead of entire tile map.
            graphics2D.drawImage(image, screenX, screenY, tileSize, tileSize, null);
        }
    }




    public void update() {
        setAction();

        isCollision = false;
        gameController.collision.checkTileCollision(this);
        gameController.collision.checkObjectCollision(this, false);
        gameController.collision.checkPlayer(this);

        // Player can move only collision is false
        if (isCollision == false) {
            adjustCoordinators(direction);
        }

        animateSprite(); // change the image every 10 frames
    }

    public void setAction() {}

    protected void adjustCoordinators(DIRECTION direction) {
        switch (direction) {
            case UP:
                worldY -= speed; // decrease y value to move up
                break;
            case DOWN:
                worldY += speed; // increase y value to move down
                break;
            case LEFT:
                worldX -= speed; // decrease x value to move left
                break;
            case RIGHT:
                worldX += speed; // increase x value to move right
                break;
        }
    }

    /**
     * Animates the player's sprite by changing the sprite number.
     */
    protected void animateSprite() {
        spriteCounter++;

        if (spriteCounter > 10) { // change the image every 10 frames
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }


    public void speak() {

      if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }

        gameController.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gameController.player.direction) {
            case UP:
                direction = DOWN;
                break;
            case DOWN:
                direction = UP;
                break;
            case LEFT:
                direction = RIGHT;
                break;
            case RIGHT:
                direction = LEFT;
                break;
        }
    }

}