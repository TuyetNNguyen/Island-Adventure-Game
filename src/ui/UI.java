package ui;

import gamecontroller.GameController;
import objects.KeyObject;
import objects.OutfitObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    private static final int BASE_FONT_SIZE = 40;
    private static final int LARGE_FONT_SIZE = 80;
    private static final int SMALL_FONT_SIZE = 25;

    private GameController gameController;
    private Graphics2D graphics2D;

    private Font baseFont;
    private Font largeFont;

    private BufferedImage lifeImage;
    private boolean isMessageOn = false;
    private String message = "";
    private int messageCounter = 0;
    public boolean isGameOver = false;
    public String currentDialogue = "";




    public UI(GameController gameController) {
        this.gameController = gameController;

        baseFont = new Font("Arial", Font.PLAIN, BASE_FONT_SIZE);
        largeFont = new Font("Arial", Font.BOLD, LARGE_FONT_SIZE);

        OutfitObject outfitObject = new OutfitObject(gameController);
        lifeImage = outfitObject.image;
    }


    public void showMessage(String text) {
        message = text;
        isMessageOn = true;
    }


    public void draw(Graphics2D graphics2D) {

        this.graphics2D = graphics2D;

        graphics2D.setFont(baseFont);
        graphics2D.setColor(Color.white);

        // PLAY STATE
        if (gameController.gameState == gameController.pauseState) {

        }
        // PAUSE STATE
        if (gameController.gameState == gameController.pauseState) {
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if (gameController.gameState == gameController.dialogueState) {
            drawDialogueScreen();
        }
    }



    public void drawDialogueScreen() {

        int tileSize = gameController.getTileSize();

        //WINDOW
        int x = tileSize*2;
        int y = tileSize/2;
        int width = gameController.getScreenWidth() - (tileSize*5);
        int height = tileSize*4;
        drawSubWindow(x,y,width,height);

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, SMALL_FONT_SIZE));
        x += tileSize;
        y += tileSize;

        for(String line : currentDialogue.split("\n")) {
            graphics2D.drawString(line, x, y);
            y += 40;
        }
    }


    public void drawSubWindow(int x, int y, int width, int height){

        Color color = new Color(0,0,0, 200);
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(x,y,width, height, 35, 35);

        color = new Color(255,255,255);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x+5, y+5, width-10, height-10, 25,25);
    }

    public void drawPauseScreen() {

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gameController.getScreenHeight() / 2;

        graphics2D.drawString(text, x, y);
    }

    public int getXForCenteredText(String text) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = gameController.getScreenWidth() / 2 - length / 2;
        return x;
    }

}

   /*
    public void draw(Graphics2D graphics2D) {
        int tileSize = gameController.getTileSize();

        if (isGameOver) {
            drawGameOverScreen(graphics2D, tileSize);
        } else {
            drawGameScreen(graphics2D, tileSize);
        }
    }


    private void drawGameOverScreen(Graphics2D graphics2D, int tileSize) {
        String text;
        int textLength;
        int x;
        int y;

        gameController.setFont(largeFont);
        graphics2D.setColor(Color.yellow);

        text = "Congratulations";
        textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        x = gameController.getScreenWidth() / 2 - textLength / 2;
        y = gameController.getScreenHeight() / 2 + (tileSize * 2);
        graphics2D.drawString(text, x, y);

        gameController.setFont(baseFont);
        graphics2D.setColor(Color.white);

        text = "You found the treasure";
        textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        x = gameController.getScreenWidth() / 2 - textLength / 2;
        y = gameController.getScreenHeight() / 2 - (tileSize * 3);
        graphics2D.drawString(text, x, y);

        text = "Your time is : " + decimalFormat.format(playTime);
        textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        x = gameController.getScreenWidth() / 2 - textLength / 2;
        y = gameController.getScreenHeight() / 2 + (tileSize * 4);
        graphics2D.drawString(text, x, y);
    }


    private void drawGameScreen(Graphics2D graphics2D, int tileSize) {
        gameController.setFont(baseFont);
        graphics2D.setColor(Color.white);
        graphics2D.drawImage(lifeImage, tileSize / 2, tileSize / 2, tileSize, tileSize, null);
        graphics2D.drawString("x " + gameController.player.playerLife, 74, 65);


        graphics2D.drawImage(keyImage, tileSize / 2, (tileSize / 2) + 50, tileSize, tileSize, null);
        graphics2D.drawString("x " + gameController.player.getKeyNums(), 74, 100);


        // TIME
        playTime += (double) 1 / 60;
        graphics2D.drawString("Time: " + decimalFormat.format(playTime), tileSize * 11, 65);

        // Message
        if (isMessageOn) {
            graphics2D.setFont(graphics2D.getFont().deriveFont(SMALL_FONT_SIZE));
            graphics2D.drawString(message, tileSize / 2, tileSize * 5);

            messageCounter++;
            if (messageCounter > 120) { // 60 FPS => the text disappears after 2 seconds
                messageCounter = 0;
                isMessageOn = false;
            }
        }
    }



 }

    */