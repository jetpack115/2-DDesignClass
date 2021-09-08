import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.util.Random;

// Class that randomizes the rgb spectrum of a picture so that it messes up the image.
public class randomizer {
    public static void main (String[] args)
    {
        BufferedImage image;
        int width;
        int height;
        Random rand = new Random();

        try {
        // Open the picture
        File input = new File("tiny.png");
        image = ImageIO.read(input);
        
        // Get your sizes.
        width = image.getWidth();
        height = image.getHeight();

        for(int i = 0; i < height; i++) 
        {
            for(int j = 0; j < width; j++) 
            {
                //Color c = new Color (image.getRGB(i, j));

                // Get your random numbers between 0 and 255
                int red = rand.nextInt(256);
                int green = rand.nextInt(256);
                int blue = rand.nextInt(256);

                Color newColor = new Color(red, green, blue);

                image.setRGB(j ,i , newColor.getRGB());
            }
        }
        // Output the new random picture.
        File ouptut = new File("FruitLoops.jpg");
        ImageIO.write(image, "jpg", ouptut);

        }
        catch (Exception e) {}
    }

}
