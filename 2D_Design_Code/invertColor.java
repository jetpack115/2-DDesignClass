import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.JFrame;

public class invertColor {
   
    
    private String fileIn;

    public invertColor(String imageName)
    {
        this.fileIn = imageName;
    }
    public void invert(String nameOut)
    {
        try {
            // Open the dog image. 
            File input = new File(this.fileIn);
            
            // Get your dimensions. 
            BufferedImage img = ImageIO.read(input);
            int width = img.getWidth();
            int height = img.getHeight();
            
            //Standard for loop to iterate through all the pixels.
            for(int i = 0; i < height; i++) 
            {
                for(int j = 0; j < width; j++) 
                {
                
                    // Get your pixel at coordinate.
                    Color c = new Color(img.getRGB(j, i));
                    int red = 0;
                    int green = 0;
                    int blue = 0;

                    if (c.getRed() < 20 && c.getBlue() < 20 && c.getGreen() < 20)
                    {
                        red = 255;
                        green = 255;
                        blue = 255;
                    }
                    Color newColor = new Color(red, green, blue);
                   
                    // Actually sets the pixel to the calculated new rgb we found in the formulas above.
                    img.setRGB(j ,i , newColor.getRGB());
                }
             }
             File output = new File(nameOut);
             ImageIO.write(img, "jpg", output);
             
          } 
          catch (Exception e) 
          {
            // Nothing here 
          }
    }
}
