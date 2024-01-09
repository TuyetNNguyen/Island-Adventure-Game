package objects;

import gamecontroller.GameController;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DoorObject extends SuperObject{

    public DoorObject(GameController gameController) {

        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            utilityTool.scaleImage(image, gameController.getTileSize(), gameController.getTileSize());
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        isSolidObject = true;
    }
}