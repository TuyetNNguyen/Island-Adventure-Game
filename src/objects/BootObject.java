package objects;

import gamecontroller.GameController;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BootObject extends SuperObject{
    public BootObject(GameController gameController) {
        name = "Boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            utilityTool.scaleImage(image, gameController.getTileSize(), gameController.getTileSize());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}