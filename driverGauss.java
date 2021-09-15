import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class driverGauss {
    public static void main (String[] args)
    {
        BufferedImage image;

        try 
        {
            // Open the picture
            File input = new File("tiny.png");
        
            // Get your sizes.
            image = ImageIO.read(input);
            int radius = 5;

            gaussianBlurr helper = new gaussianBlurr();
            double[][] weights = helper.generateWeightMatric(radius, Math.sqrt(5)); 
            BufferedImage out = helper.createGImage(image, weights, radius);
            
            File ouptut = new File("GaussianImageComplete.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }
    }
}
