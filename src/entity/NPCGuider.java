package entity;

import gamecontroller.GameController;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static entity.Entity.DIRECTION.*;

public class NPCGuider extends Entity {


    public NPCGuider(GameController gameController) {
        super(gameController);

        loadImages();
        setDialogue();
        setDefaultValues();
    }


    @Override
    public void setDefaultValues() {
        direction = UP;
        speed = 1;
    }

    @Override
    public void loadImages() {


        try {
            directionToImages.put(UP, new BufferedImage[]{
                    scaleImage("/npc/oldman_up_1"),
                    scaleImage("/npc/oldman_up_2")}
            );
            directionToImages.put(DOWN, new BufferedImage[]{
                    scaleImage("/npc/oldman_down_1"),
                    scaleImage("/npc/oldman_down_2")}
            );
            directionToImages.put(LEFT, new BufferedImage[]{
                    scaleImage("/npc/oldman_left_1"),
                    scaleImage("/npc/oldman_left_2")}
            );
            directionToImages.put(RIGHT, new BufferedImage[]{
                    scaleImage("/npc/oldman_right_1"),
                    scaleImage("/npc/oldman_right_2")}
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void setAction() {

        actionLockCount++;

        if(actionLockCount == 120) { // Change the frame every 2 seconds
            Random random = new Random();
            int i = random.nextInt(100);

            if (i <= 25) {
                direction = UP;
            } else if (i > 25 && i <= 50) {
                direction = DOWN;
            } else if (i > 50 && i <= 75) {
                direction = LEFT;
            } else {
                direction = RIGHT;
            }

            actionLockCount = 0;
        }
    }



    public void setDialogue() {

        dialogues[0] = "Hello there,";
        dialogues[1] = "I assume you're here to find the treasure, \n be aware it could cost your life, \n but sure it's a big reward if you found it.";
        dialogues[2] = "Good luck with the adventure!";
        dialogues[3] = "Sorry I have nothing else to share!";
    }


}
