package objects;

import gamecontroller.GameController;
import gamecontroller.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean isSolidObject = false;
    public int worldX;
    public int worldY;
    public Rectangle solidArea;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    UtilityTool utilityTool = new UtilityTool();
    public boolean isCollected;


    public SuperObject() {
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x ;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 48;
        solidArea.height = 48;
        isCollected = false;
    }

    public void draw(Graphics2D graphics2D, GameController gameController) {

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