package ui;
import gamecontroller.GameController;

import javax.swing.*;
import java.awt.*;

public class Window {

    public Window(int width, int height, String title, GameController gameController) {
        // Set the borders for the frame
        JFrame window = new JFrame(title);
        window.setPreferredSize(new Dimension(width, height));

        // Ensure the window cannot be resized beyond/smaller than the specified max/min width and height
        window.setMaximumSize(new Dimension(width, height));
        window.setMinimumSize(new Dimension(width, height));

        //window.getContentPane().setBackground(Color.BLACK);  // Set the background color of the JFrame's content pane
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when hit on exit button
        window.setResizable(false); // Make the window non-resizable
        window.setLocationRelativeTo(null); // Center the window on the screen when run the game
        window.add(gameController); // Add the game components to the frame
        window.pack();
        window.setVisible(true); // Make the window visible}
    }
}
    /*
    public Window(GameController gameController, GamePanel gamePanel) {
        JFrame window = new JFrame(title);

        // Set layout for GamePanel
        gamePanel.setLayout(null); // Set null layout initially
        gamePanel.setPreferredSize(new Dimension(gamePanel.screenWidth, gamePanel.screenHeight));
        gamePanel.setBackground(Color.black);
        gamePanel.setDoubleBuffered(true);

        window.getContentPane().add(gamePanel); // Add GamePanel to the window
        gamePanel.add(gameController); // Add GameController components on top of GamePanel

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when hit on exit button
        window.setResizable(false); // Make the window non-resizable
        window.pack();
        window.setLocationRelativeTo(null); // Center the window on the screen when run the game
        window.setVisible(true); // Make the window visible
    }

     */