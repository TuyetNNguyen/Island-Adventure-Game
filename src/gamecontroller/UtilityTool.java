package gamecontroller;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * UtilityTool class provides utility methods for image manipulation.
 */
public class UtilityTool {

    /**
     * Scales the given BufferedImage to the specified width and height.
     *
     * @param original The original BufferedImage to be scaled.
     * @param width    The desired width of the scaled image.
     * @param height   The desired height of the scaled image.
     * @return A new BufferedImage with the specified width and height.
     */
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {

        // Create a new BufferedImage with the specified width, height, and type of the original image.
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());

        // Create a Graphics2D object for drawing on the new image.
        Graphics2D graphics2D = scaledImage.createGraphics();

        // Draw the original image onto the new image with the specified width and height.
        graphics2D.drawImage(original, 0, 0, width, height, null);

        // Dispose of the Graphics2D object to free up system resources.
        graphics2D.dispose();

        // Return the scaled BufferedImage.
        return scaledImage;
    }
}