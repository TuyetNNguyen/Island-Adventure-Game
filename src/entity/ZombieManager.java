package entity;

import gamecontroller.GameController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ZombieManager {

    private final GameController gameController;
    private List<Zombie> zombies;

    public ZombieManager(GameController gameController, int numberOfZombies) {
        this.gameController = gameController;
        initializeZombies(numberOfZombies);
    }

    private void initializeZombies(int numberOfZombies) {
        zombies = new ArrayList<>();

        for (int i = 0; i < numberOfZombies; i++) {
            zombies.add(new Zombie(gameController));
        }
    }

    public void updateAllZombies() {
        for (Zombie zombie : zombies) {
            zombie.update();
        }
    }

    public void drawAllZombies(Graphics2D graphics2D) {
        for (Zombie zombie : zombies) {
            zombie.draw(graphics2D);
        }
    }


}
