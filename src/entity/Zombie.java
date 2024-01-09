package entity;

import gamecontroller.GameController;
import gamecontroller.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Zombie extends Entity {

    private final GameController gameController;
    UtilityTool utilityTool = new UtilityTool();
    private int speed = 5;
    private int lives = 1;

    public BufferedImage image;


    public Zombie(GameController gameController) {
        super(gameController);
        this.gameController = gameController;
        loadZombieImages();
        setRandomPosition();
    }


    public void loadZombieImages() {

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/frog.gif"));
            utilityTool.scaleImage(image, gameController.getTileSize(), gameController.getTileSize());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    private void setRandomPosition() {
        Random random = new Random();
        worldX = random.nextInt(gameController.getMaxWorldCol()) * gameController.getTileSize();
        worldY = random.nextInt(gameController.getMaxWorldRow()) * gameController.getTileSize();
    }

    public void update() {
        moveRandomly();
        // checkPlayerCollision();
    }

    private void moveRandomly() {
        Random random = new Random();
        int direction = random.nextInt(4); // 0: up, 1: down, 2: left, 3: right

        switch (direction) {
            case 0:
                worldY -= speed;
                break;
            case 1:
                worldY += speed;
                break;
            case 2:
                worldX -= speed;
                break;
            case 3:
                worldX += speed;
                break;
        }
    }


    /*
    private void checkPlayerCollision() {
        Rectangle zombieSolidArea = new Rectangle(worldX, worldY, gameController.getTileSize(), gameController.getTileSize());
        Rectangle playerSolidArea = gameController.player.playerSolidArea;

        if (zombieSolidArea.intersects(playerSolidArea)) {
            // Zombie hit player, deduct a life
            if (!gameController.player.isInvincible()) {
                gameController.player.deductLife();
                //gameController.player.setInvincible(true); // Implement invincibility frames if needed
                setRandomPosition(); // Reset zombie position
            }
        }
    }


     */


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

        // Check if the tile is within the visible screen area (viewport) before drawing
        if (worldX + tileSize > playerWorldX - playerScreenX &&
                worldX - tileSize < playerWorldX + playerScreenX &&
                worldY + tileSize > playerWorldY - playerScreenY &&
                worldY - tileSize < playerWorldY + playerScreenY) {

            // Only draw the tile image at the calculated screen coordinates instead of entire tile map.
            graphics2D.drawImage(image, screenX, screenY, tileSize, tileSize, null);
        }

    }


}
