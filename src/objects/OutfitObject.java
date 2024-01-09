package objects;

import gamecontroller.GameController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OutfitObject extends SuperObject{
    public OutfitObject(GameController gameController) {
        super();
        name = "Outfit";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/outfit.png"));
            utilityTool.scaleImage(image, gameController.getTileSize(), gameController.getTileSize());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D graphics2D, GameController gameController) {
        // Draw the outfit object only if it hasn't been collected yet
        if (!isCollected) {
            super.draw(graphics2D, gameController);
        }
    }

}