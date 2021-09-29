import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class grayscale 
{
    private BufferedImage image;
    private int width;
    private int height;
    
    public grayscale()
    {
    }


    public void turnGrayscale(String nameIn, String nameOut)
    {
        try {
            // Open the dog image. 
            File input = new File(nameIn);
            
            // Get your dimensions. 
            this.image = ImageIO.read(input);
            this.width = image.getWidth();
            this.height = image.getHeight();
            
            //Standard for loop to iterate through all the pixels.
            for(int i = 0; i < this.height; i++) 
            {
                for(int j = 0; j < this.width; j++) 
                {
                
                    // Get your pixel at coordinate.
                    Color c = new Color(this.image.getRGB(j, i));
    
                    // Use the grayscale formula found on wiki!
                    int red = (int)(c.getRed() * 0.299);
                    int green = (int)(c.getGreen() * 0.587);
                    int blue = (int)(c.getBlue() * 0.114);
                    Color newColor = new Color(red + green + blue ,red + green + blue, red + green + blue);
                   
                    // Actually sets the pixel to the calculated new rgb we found in the formulas above.
                    this.image.setRGB(j ,i , newColor.getRGB());
                }
             }
             
             File ouptut = new File(nameOut);
             ImageIO.write(this.image, "jpg", ouptut);
             
          } 
          catch (Exception e) 
          {
            // Nothing here 
          }
    }


}
