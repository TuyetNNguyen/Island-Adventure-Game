package tile;

import gamecontroller.GameController;
import gamecontroller.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Manages tiles and their layout in the game.
 */
public class TileManager {

    private static final String[] tileImages = {
            // Index 0 - 9 are not used in the Map tile
            "/tiles/grass00.png",
            "/tiles/grass00.png",
            "/tiles/grass00.png",
            "/tiles/grass00.png",
            "/tiles/grass00.png",
            "/tiles/grass00.png",
            "/tiles/grass00.png",
            "/tiles/grass00.png",
            "/tiles/grass00.png",
            "/tiles/grass00.png",

            // Keep 2 digits to keep the map tile clean and easy to navigate
            "/tiles/grass00.png",
            "/tiles/grass01.png", //11

            "/tiles/water00.png",
            "/tiles/water01.png",
            "/tiles/water02.png",
            "/tiles/water03.png",
            "/tiles/water04.png",
            "/tiles/water05.png",
            "/tiles/water06.png",
            "/tiles/water07.png",
            "/tiles/water08.png",
            "/tiles/water09.png",
            "/tiles/water10.png",
            "/tiles/water11.png",
            "/tiles/water12.png",
            "/tiles/water13.png", //25

            "/tiles/road00.png",
            "/tiles/road01.png",
            "/tiles/road02.png",
            "/tiles/road03.png",
            "/tiles/road04.png",
            "/tiles/road05.png",
            "/tiles/road06.png",
            "/tiles/road07.png",
            "/tiles/road08.png",
            "/tiles/road09.png",
            "/tiles/road10.png",
            "/tiles/road11.png",
            "/tiles/road12.png", //38

            "/tiles/earth.png",
            "/tiles/wall.png",
            "/tiles/dark_tree.png" //41 "/tiles/scary_tree.png"
    };


    private static final String tileMapFile = "/maps/worldV2.txt";

    GameController gameController;
    public Tile[] tiles;
    public int[][] mapTileNum;


    public TileManager(GameController gameController) {
        this.gameController = gameController;
        this.tiles = new Tile[tileImages.length];
        this.mapTileNum = new int[gameController.getMaxWorldCol()][gameController.getMaxWorldRow()];

        // Initialize tiles by loading their images
        initTiles();

        // Load the layout of tiles from a specified file
        loadTileLayout(tileMapFile);
    }


    private void initTiles() {
        UtilityTool utilityTool = new UtilityTool();

        for (int i = 0; i < tileImages.length; i++) {
            tiles[i] = new Tile();

            try {
                // Load the image for each tile type
                tiles[i].image = ImageIO.read(getClass().getResourceAsStream(tileImages[i]));
                tiles[i].image = utilityTool.scaleImage(tiles[i].image, gameController.getTileSize(), gameController.getTileSize());

                if (tileImages[i].contains("wall") || tileImages[i].contains("water") || tileImages[i].contains("tree")) {
                    tiles[i].isSolidTile = true;
                }

            } catch (IOException e) {
                // Print the stack trace for debugging purposes; consider logging or handling the exception appropriately
                e.printStackTrace();
            }
        }
    }


    private void loadTileLayout(String filePath) {
        try {
            // Open the input stream for reading the tile layout file
            InputStream tileLayout = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(tileLayout));

            int col = 0;
            int row = 0;
            String line;

            // Read each line of the tile layout file
            while ((line = bufferedReader.readLine()) != null && col < gameController.getMaxWorldCol() && row < gameController.getMaxWorldRow()) {
                String[] numbers = line.split(" ");

                // Parse each number in the line and populate the mapTileNum array
                for (String number : numbers) {
                    int num = Integer.parseInt(number);
                    mapTileNum[col][row] = num;
                    col++;
                }

                // Move to the next row if the current row is complete
                if (col == gameController.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            // BufferedReader and InputStream will be automatically closed

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D graphics2D) {
        int tileSize = gameController.getTileSize();
        int playerScreenX = gameController.player.screenX;
        int playerScreenY = gameController.player.screenY;
        int playerWorldX = gameController.player.worldX;
        int playerWorldY = gameController.player.worldY;

        for (int worldRow = 0; worldRow < gameController.getMaxWorldRow(); worldRow++) {
            for (int worldCol = 0; worldCol < gameController.getMaxWorldCol(); worldCol++) {
                if (worldCol >= 0 && worldCol < mapTileNum.length && worldRow >= 0 && worldRow < mapTileNum[0].length) {
                    int tile2DNum = mapTileNum[worldCol][worldRow];

                    int worldX = worldCol * tileSize;
                    int worldY = worldRow * tileSize;
                    int screenX = worldX - playerWorldX + playerScreenX;
                    int screenY = worldY - playerWorldY + playerScreenY;

                    if (worldX + tileSize > playerWorldX - playerScreenX &&
                            worldX - tileSize < playerWorldX + playerScreenX &&
                            worldY + tileSize > playerWorldY - playerScreenY &&
                            worldY - tileSize < playerWorldY + playerScreenY) {

                        // Check if the tile2DNum is within the valid range
                        if (tile2DNum >= 0 && tile2DNum < tiles.length) {
                            // Check if the image is not null before drawing
                            if (tiles[tile2DNum].image != null) {
                                graphics2D.drawImage(
                                        tiles[tile2DNum].image,
                                        screenX, screenY,
                                        null);
                            }
                        }
                    }
                }
            }
        }
    }

}
