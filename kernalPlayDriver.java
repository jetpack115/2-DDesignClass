import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class kernalPlayDriver 
{
    public static void main (String[] args)
    {
        long start = System.currentTimeMillis();
        BufferedImage image;

        try 
        {
            // Open the picture
            File input = new File("C:/Users/jetpa/Desktop/New folder/2-DDesignClass-main/2-DDesignClass-main/gtacharacter.jpg");
        
            // Get your sizes.
            image = ImageIO.read(input);
            int radius = 9;

            kernalPlay kp = new kernalPlay();
            double[][] weights = kp.generateRandomMatrix(radius); 
            BufferedImage out = kp.createrandomImage(image, weights, radius);
            
            File ouptut = new File("squared.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }

        long elapsedTimeMillis = System.currentTimeMillis()-start;
        float elapsedTimeSec = elapsedTimeMillis/1000F;
        System.out.println("The program took: " + elapsedTimeSec + " seconds!");
    } 
}
