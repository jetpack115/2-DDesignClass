import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class driverGauss {
    public static void main (String[] args)
    {
        long start = System.currentTimeMillis();
        BufferedImage image;

        try 
        {
            // Open the picture
            File input = new File("gtacharacter.jpg");
        
            // Get your sizes.
            image = ImageIO.read(input);
            int radius = 9;

            gaussianBlurr helper = new gaussianBlurr();
            double[][] weights = helper.generateWeightMatric(radius, 3); 
            BufferedImage out = helper.createGImage(image, weights, radius);
            
            File ouptut = new File("GaussianImageComplete.jpg");
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
