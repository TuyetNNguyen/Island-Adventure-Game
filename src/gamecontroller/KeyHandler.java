package gamecontroller;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyHandler implements KeyListener {

    GameController gameController;
    public boolean isUpPressed;
    public boolean isDownPressed;
    public boolean isLeftPressed;
    public boolean isRightPressed;
    public boolean isEnterPressed;
    public boolean isPausePressed;
    private Set<Integer> pressedKeys = new HashSet<>();

    public KeyHandler(GameController gameController){
        this.gameController = gameController;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // PLAY STATE
        if(gameController.gameState == gameController.playState){
            handleKeyEvent(e.getKeyCode(), true);
        }

        // PAUSE STATE
        else if(gameController.gameState == gameController.pauseState){
            if (isPausePressed) {
                isPausePressed = false;
                gameController.gameState = gameController.playState;
             }
        }

        // DIALOGUE STATE
        else if(gameController.gameState == gameController.dialogueState){
            if(code == KeyEvent.VK_ENTER) {
                gameController.gameState = gameController.playState;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        handleKeyEvent(e.getKeyCode(), false);
    }

    private void handleKeyEvent(int keyCode, boolean isPressed) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                isUpPressed = isPressed;
                break;
            case KeyEvent.VK_S:
                isDownPressed = isPressed;
                break;
            case KeyEvent.VK_A:
                isLeftPressed = isPressed;
                break;
            case KeyEvent.VK_D:
                isRightPressed = isPressed;
                break;
            case KeyEvent.VK_P:
                isPausePressed = false;
                gameController.gameState = gameController.pauseState;
                break;
            case KeyEvent.VK_ENTER:
                isEnterPressed = isPressed;
                break;


        }
    }

     /*

    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gameController.gameState == gameController.playState ||
                gameController.gameState == gameController.pauseState ||
                gameController.gameState == gameController.dialogueState) {
            handleKeyEvent(code, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        handleKeyEvent(e.getKeyCode(), false);
    }

    private void handleKeyEvent(int keyCode, boolean isPressed) {

        pressedKeys.clear(); // Clear the set to handle only the keys that are currently pressed

        // Add the currently pressed keys to the set
        if (isPressed) {
            pressedKeys.add(keyCode);
        }

        isUpPressed = pressedKeys.contains(KeyEvent.VK_W);
        isDownPressed = pressedKeys.contains(KeyEvent.VK_S);
        isLeftPressed = pressedKeys.contains(KeyEvent.VK_A);
        isRightPressed = pressedKeys.contains(KeyEvent.VK_D);
        isPausePressed = pressedKeys.contains(KeyEvent.VK_P);
        isEnterPressed = pressedKeys.contains(KeyEvent.VK_ENTER);

        // Handle state transitions
        if (gameController.gameState != gameController.playState) {
            gameController.gameState = gameController.playState;
        }
    }

     */

}

