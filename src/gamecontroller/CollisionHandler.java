package gamecontroller;

import entity.Entity;
import objects.SuperObject;

import java.awt.*;

public class CollisionHandler {
    private final GameController gameController;

    public CollisionHandler(GameController gameController) {
        this.gameController = gameController;
    }


    public void checkTileCollision(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gameController.getTileSize();
        int entityRightCol = entityRightWorldX / gameController.getTileSize();
        int entityTopRow = entityTopWorldY / gameController.getTileSize();
        int entityBottomRow = entityBottomWorldY / gameController.getTileSize();

        // right and left shoulder of player character, ignore the head
        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case UP:
                entityTopRow = (entityTopWorldY - entity.speed) / gameController.getTileSize();
                tileNum1 = gameController.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gameController.tileManager.mapTileNum[entityRightCol][entityTopRow];

                if (gameController.tileManager.tiles[tileNum1].isSolidTile == true ||
                        gameController.tileManager.tiles[tileNum2].isSolidTile == true) {
                    entity.isCollision = true;
                }

                break;
            case DOWN:
                entityBottomRow = (entityBottomWorldY - entity.speed) / gameController.getTileSize();
                tileNum1 = gameController.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gameController.tileManager.mapTileNum[entityRightCol][entityBottomRow];

                if (gameController.tileManager.tiles[tileNum1].isSolidTile == true ||
                        gameController.tileManager.tiles[tileNum2].isSolidTile == true) {
                    entity.isCollision = true;
                }
                break;
            case LEFT:
                entityLeftCol = (entityLeftWorldX - entity.speed) / gameController.getTileSize();
                tileNum1 = gameController.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gameController.tileManager.mapTileNum[entityRightCol][entityBottomRow];

                if (gameController.tileManager.tiles[tileNum1].isSolidTile == true ||
                        gameController.tileManager.tiles[tileNum2].isSolidTile == true) {
                    entity.isCollision = true;
                }

                break;
            case RIGHT:
                entityRightCol = (entityRightWorldX - entity.speed) / gameController.getTileSize();
                tileNum1 = gameController.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gameController.tileManager.mapTileNum[entityRightCol][entityBottomRow];

                if (gameController.tileManager.tiles[tileNum1].isSolidTile == true ||
                        gameController.tileManager.tiles[tileNum2].isSolidTile == true) {
                    entity.isCollision = true;
                }

                break;
        }
    }


    public int checkObjectCollision(Entity entity, boolean isPlayer) {
        int index = 999;

        for (int i = 0; i < gameController.objects.length; i++) {
            if (gameController.objects[i] != null) {
                Rectangle entitySolidArea = new Rectangle(entity.worldX + entity.solidArea.x, entity.worldY + entity.solidArea.y,
                        entity.solidArea.width, entity.solidArea.height);

                Rectangle objectSolidArea = new Rectangle(gameController.objects[i].worldX + gameController.objects[i].solidArea.x,
                        gameController.objects[i].worldY + gameController.objects[i].solidArea.y,
                        gameController.objects[i].solidArea.width, gameController.objects[i].solidArea.height);

                switch (entity.direction) {
                    case UP:
                        entitySolidArea.y -= entity.speed;
                        break;
                    case DOWN:
                        entitySolidArea.y += entity.speed;
                        break;
                    case LEFT:
                        entitySolidArea.x -= entity.speed;
                        break;
                    case RIGHT:
                        entitySolidArea.x += entity.speed;
                        break;
                }

                if (entitySolidArea.intersects(objectSolidArea)) {
                    if (gameController.objects[i].isSolidObject) {
                        entity.isCollision = true;
                    }

                    if (isPlayer) {
                        index = i;
                    }
                }

                // Reset positions to default after collision check
                entitySolidArea.setLocation(entity.solidAreaDefaultX, entity.solidAreaDefaultY);
                objectSolidArea.setLocation(gameController.objects[i].solidAreaDefaultX,
                        gameController.objects[i].solidAreaDefaultY);
            }
        }

        return index;
    }


    public int checkEntity(Entity entity, Entity[] targets) {
        int index = 999;

        for (int i = 0; i < targets.length; i++) {
            Entity target = targets[i];
            if (target != null) {
                Rectangle entitySolidArea = new Rectangle(entity.worldX + entity.solidArea.x, entity.worldY + entity.solidArea.y,
                        entity.solidArea.width, entity.solidArea.height);

                Rectangle targetSolidArea = new Rectangle(target.worldX + target.solidArea.x, target.worldY + target.solidArea.y,
                        target.solidArea.width, target.solidArea.height);

                switch (entity.direction) {
                    case UP:
                        entitySolidArea.y -= entity.speed;
                        break;
                    case DOWN:
                        entitySolidArea.y += entity.speed;
                        break;
                    case LEFT:
                        entitySolidArea.x -= entity.speed;
                        break;
                    case RIGHT:
                        entitySolidArea.x += entity.speed;
                        break;
                }

                if (entitySolidArea.intersects(targetSolidArea)) {
                    entity.isCollision = true;
                    index = i;
                }

                // Reset positions to default after collision check
                entitySolidArea.setLocation(entity.solidAreaDefaultX, entity.solidAreaDefaultY);
                targetSolidArea.setLocation(target.solidAreaDefaultX, target.solidAreaDefaultY);
            }
        }

        return index;
    }



    public void checkPlayer(Entity entity) {
        Rectangle entitySolidArea = new Rectangle(entity.worldX + entity.solidArea.x, entity.worldY + entity.solidArea.y,
                entity.solidArea.width, entity.solidArea.height);

        Rectangle playerSolidArea = new Rectangle(gameController.player.worldX + gameController.player.solidArea.x,
                gameController.player.worldY + gameController.player.solidArea.y,
                gameController.player.solidArea.width, gameController.player.solidArea.height);

        switch (entity.direction) {
            case UP:
                entitySolidArea.y -= entity.speed;
                break;
            case DOWN:
                entitySolidArea.y += entity.speed;
                break;
            case LEFT:
                entitySolidArea.x -= entity.speed;
                break;
            case RIGHT:
                entitySolidArea.x += entity.speed;
                break;
        }

        if (entitySolidArea.intersects(playerSolidArea)) {
            entity.isCollision = true;
        }

        // Reset positions to default after collision check
        entitySolidArea.setLocation(entity.solidAreaDefaultX, entity.solidAreaDefaultY);
        playerSolidArea.setLocation(gameController.player.solidAreaDefaultX, gameController.player.solidAreaDefaultY);
    }

}





    /*

    public void checkPlayer(Entity entity) {

        // Get entity's solid area position (player)
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // Get the object's solid area position (key, door, chest)
        gameController.player.solidArea.x = gameController.player.worldX + gameController.player.solidArea.x;
        gameController.player.solidArea.y = gameController.player.worldY + gameController.player.solidArea.y;

        switch (entity.direction) {
            case UP:

                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gameController.player.solidArea)) {
                    entity.isCollision = true;
                }
                break;

            case DOWN:

                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gameController.player.solidArea)) {
                    entity.isCollision = true;
                }
                break;

            case LEFT:

                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gameController.player.solidArea)) {
                    entity.isCollision = true;
                }

                break;
            case RIGHT:

                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gameController.player.solidArea)) {
                    entity.isCollision = true;
                }
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gameController.player.solidArea.x = gameController.player.solidAreaDefaultX;
        gameController.player.solidArea.y = gameController.player.solidAreaDefaultY;

    }

     */

/*

    public int checkObjectCollision(Entity entity, boolean isPlayer) {
        int index = 999;

        for (int i = 0; i < gameController.objects.length; i++) {
            if (gameController.objects[i] != null) {

                // Get entity's solid area position (player)
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the object's solid area position (key, door, chest)
                gameController.objects[i].objectSolidArea.x = gameController.objects[i].worldX + gameController.objects[i].objectSolidArea.x;
                gameController.objects[i].objectSolidArea.y = gameController.objects[i].worldY + gameController.objects[i].objectSolidArea.y;

                switch (entity.direction) {
                    case UP:
                        entity.solidArea.y -= entity.speed;

                        if (entity.solidArea.intersects(gameController.objects[i].objectSolidArea)) {
                            if (gameController.objects[i].isSolidObject) {
                                entity.isCollision = true;
                            }

                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case DOWN:
                        entity.solidArea.y += entity.speed;

                        if (entity.solidArea.intersects(gameController.objects[i].objectSolidArea)) {
                            if (gameController.objects[i].isSolidObject) {
                                entity.isCollision = true;
                            }

                            if (isPlayer) {
                                index = i;
                            }
                        }

                        break;
                    case LEFT:
                        entity.solidArea.x -= entity.speed;

                        if (entity.solidArea.intersects(gameController.objects[i].objectSolidArea)) {
                            if (gameController.objects[i].isSolidObject) {
                                entity.isCollision = true;
                            }

                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case RIGHT:
                        entity.solidArea.x += entity.speed;

                        if (entity.solidArea.intersects(gameController.objects[i].objectSolidArea)) {
                            if (gameController.objects[i].isSolidObject) {
                                entity.isCollision = true;
                            }

                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gameController.objects[i].objectSolidArea.x = gameController.objects[i].objectSolidAreaDefaultX;
                gameController.objects[i].objectSolidArea.y = gameController.objects[i].objectSolidAreaDefaultY;
            }
        }

        return index;
    }

 */


/*
    // NPC GUIDER OR NPC MONSTER
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {

                // Get entity's solid area position (player)
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the object's solid area position (key, door, chest)
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case UP:

                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.isCollision = true;
                            index = i;
                        }
                        break;

                    case DOWN:

                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.isCollision = true;
                            index = i;
                        }
                        break;

                    case LEFT:

                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.isCollision = true;
                            index = i;
                        }

                        break;
                    case RIGHT:

                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.isCollision = true;
                            index = i;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }

        return index;
    }
    */