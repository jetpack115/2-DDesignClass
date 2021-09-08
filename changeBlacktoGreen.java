import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

// Class that detects black pixels and changes them to neon green
public class changeBlacktoGreen {
    public static void main (String[] args)
    {
        BufferedImage image;
        int width;
        int height;

        try {
        // Open the picture
        File input = new File("tiny.png");
        
        // Get your sizes.
        image = ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight();

        for(int i = 0; i < height; i++) 
        {
            for(int j = 0; j < width; j++) 
            {
                Color c = new Color (image.getRGB(j, i));

                int red = 0;
                int green = 0;
                int blue = 0;

                // Get your rgb values
                red = (int)(c.getRed());
                green = (int)(c.getGreen());
                blue = (int)(c.getBlue());
                // Changes black to green!
                if (red <= 40 && green <= 40 && blue <= 40 )
                {
                    // Color is in the range of black so change it!
                    red = 4;
                    green = 255;
                    blue = 0;
                }

                
            
                Color newColor = new Color(red, green, blue);

                image.setRGB(j ,i , newColor.getRGB());
                System.out.println("Reading... Currently on ");
            }
        }
        // Output the new random picture.
        File ouptut = new File("BlackisNowGreen.jpg");
        ImageIO.write(image, "jpg", ouptut);

        }
        catch (Exception e) {}
    }

}
