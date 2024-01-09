package objects;

import gamecontroller.GameController;

import javax.imageio.ImageIO;
import java.io.IOException;

public class QueenObject extends SuperObject{
    public QueenObject(GameController gameController) {
        name = "Queen";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/princess.png"));
            utilityTool.scaleImage(image, gameController.getTileSize(), gameController.getTileSize());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        isSolidObject = true;
    }
}