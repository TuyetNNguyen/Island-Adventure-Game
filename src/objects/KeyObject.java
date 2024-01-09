package objects;

import gamecontroller.GameController;

import javax.imageio.ImageIO;
import java.io.IOException;

public class KeyObject extends SuperObject{

    public KeyObject(GameController gameController) {
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            utilityTool.scaleImage(image, gameController.getTileSize(), gameController.getTileSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}