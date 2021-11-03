import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class kernalToMinimizeDriver 
{
    public static void main (String[] args)
    {
        long start = System.currentTimeMillis();
        BufferedImage image;

        grayscale bw = new grayscale();
        bw.turnGrayscale("C:/Users/jetpa/Desktop/New folder/2-DDesignClass-main/2-DDesignClass-main/gtacharacter.jpg", "gtacharacterbw.jpg");

        try 
        {
            // Open the picture
            File input = new File("C:/Users/jetpa/Desktop/New folder/2-DDesignClass-main/2-DDesignClass-main/gtacharacter.jpg");
        
            // Get your sizes.
            image = ImageIO.read(input);
            int radius = 9;

            //Split the image into three channels
            channelSplitter splitIt = new channelSplitter(image);

            
            useKernaltoMinimizePic ms = new useKernaltoMinimizePic(radius);
            //ms.generateGaussianKernal(3);
            BufferedImage out = ms.passKernal(image);
            
            ms.generateKernalSharpen();
            File output = new File("characterSmaller.jpg");
            ImageIO.write(out, "jpg", output);
            int amountofIterations = 1;
            
            while (out.getHeight() >= 10)
            {
                image = ImageIO.read(output);
                out = ms.passKernal(image);
                output = new File("characterSmaller.jpg");
                ImageIO.write(out, "jpg", output);
                amountofIterations++;
            }

            System.out.println("Amount of iterations through algorithm: " + amountofIterations);
            //ms.data.toString();
            
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
