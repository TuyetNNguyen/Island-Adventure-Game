package ui;

import entity.NPCGuider;
import gamecontroller.GameController;
import objects.*;

public class AssetSetter {

    GameController gameController;
    public AssetSetter(GameController gameController) {
        this.gameController = gameController;
    }

    public void setNpcGuilder() {
        gameController.npc[0] = new NPCGuider(gameController);
        gameController.npc[0].worldX = 21 * gameController.getTileSize();
        gameController.npc[0].worldY = 21 * gameController.getTileSize();
    }

    public void setObject() {

/*
        // KEY OBJECTS
        gameController.objects[0] = new KeyObject(gameController);
        gameController.objects[0].worldX = 23 * gameController.getTileSize();
        gameController.objects[0].worldY = 7 * gameController.getTileSize();

        gameController.objects[1] = new KeyObject(gameController);
        gameController.objects[1].worldX = 23 * gameController.getTileSize();
        gameController.objects[1].worldY = 40 * gameController.getTileSize();

        gameController.objects[2] = new KeyObject(gameController);
        gameController.objects[2].worldX = 38 * gameController.getTileSize();
        gameController.objects[2].worldY = 7 * gameController.getTileSize();

        // DOOR OBJECTS
        gameController.objects[3] = new DoorObject(gameController);
        gameController.objects[3].worldX = 10 * gameController.getTileSize();
        gameController.objects[3].worldY = 11 * gameController.getTileSize();

        gameController.objects[4] = new DoorObject(gameController);
        gameController.objects[4].worldX = 8 * gameController.getTileSize();
        gameController.objects[4].worldY = 28 * gameController.getTileSize();

        gameController.objects[5] = new DoorObject(gameController);
        gameController.objects[5].worldX = 12 * gameController.getTileSize();
        gameController.objects[5].worldY = 22 * gameController.getTileSize();


        // CHEST OBJECT
        gameController.objects[6] = new ChestObject(gameController);
        gameController.objects[6].worldX = 10 * gameController.getTileSize();
        gameController.objects[6].worldY = 7 * gameController.getTileSize();

        // BOOTS OBJECT
        gameController.objects[7] = new BootObject(gameController);
        gameController.objects[7].worldX = 37 * gameController.getTileSize();
        gameController.objects[7].worldY = 42 * gameController.getTileSize();


        // LANTERN OBJECT
        gameController.objects[8] = new LanternObject(gameController);
        gameController.objects[8].worldX = 38 * gameController.getTileSize();
        gameController.objects[8].worldY = 9 * gameController.getTileSize();

        // QUEEN OBJECT
        gameController.objects[9] = new QueenObject(gameController);
        gameController.objects[9].worldX = 9 * gameController.getTileSize();
        gameController.objects[9].worldY = 7 * gameController.getTileSize();

        // OUTFIT OBJECT
        gameController.objects[10] = new OutfitObject(gameController);
        gameController.objects[10].worldX = 23 * gameController.getTileSize();
        gameController.objects[10].worldY = 12 * gameController.getTileSize();



 */

    }

}