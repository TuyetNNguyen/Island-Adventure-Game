package objects;

import gamecontroller.GameController;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LanternObject extends SuperObject{
    public LanternObject(GameController gameController) {
        name = "Lantern";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/lantern.png"));
            utilityTool.scaleImage(image, gameController.getTileSize(), gameController.getTileSize());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}