import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

// Class that randomizes the rgb spectrum of a picture so that it messes up the image.
public class makepictureWhiter {
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

                // Allows us to create a border around the image in black since it must not be an edge
                // or else it will defulat the pixel to black!
                if (i != 0 && i != height && j != 0 && j != width) {
                    red = (int)(c.getRed() + 100);
                    green = (int)(c.getGreen() + 100);
                    blue = (int)(c.getBlue() + 100);
                    if (red > 255)
                    {
                        red = 255;
                    }
                    if (green > 255)
                    {
                        green = 255;
                    }
                    if (blue > 255)
                    {
                        blue = 255;
                    }
                }
                Color newColor = new Color(red, green, blue);

                image.setRGB(j ,i , newColor.getRGB());
                System.out.println("Reading... Currently on ");
            }
        }
        // Output the new random picture.
        File ouptut = new File("WhiterPicture.jpg");
        ImageIO.write(image, "jpg", ouptut);

        }
        catch (Exception e) {}
    }

}
