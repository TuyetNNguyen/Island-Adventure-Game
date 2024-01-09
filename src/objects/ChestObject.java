package objects;

import gamecontroller.GameController;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ChestObject extends SuperObject{
    public ChestObject(GameController gameController) {
        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            utilityTool.scaleImage(image, gameController.getTileSize(), gameController.getTileSize());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        isSolidObject = true;
    }
}