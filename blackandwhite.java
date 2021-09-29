import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class blackandwhite {

   BufferedImage  image;
   int width;
   int height;
   
   public blackandwhite() {
   
      try {
        // Open the dog image. 
        File input = new File("dog.jpg");
        
        // Get your dimensions. 
        image = ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight();
        
        //Standard for loop to iterate through all the pixels.
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
            
                // Get your pixel at coordinate.
                Color c = new Color(image.getRGB(j, i));

                // Use the grayscale formula found on wiki!
                int red = (int)(c.getRed() * 0.299);
                int green = (int)(c.getGreen() * 0.587);
                int blue = (int)(c.getBlue() *0.114);
                Color newColor = new Color(red + green + blue ,red + green + blue, red + green + blue);
               
                // Actually sets the pixel to the calculated new rgb we found in the formulas above.
                image.setRGB(j ,i , newColor.getRGB());
            }
         }
         
         File ouptut = new File("blackandwhiteOUT.jpg");
         ImageIO.write(image, "jpg", ouptut);
         
      } 
      catch (Exception e) 
      {
         
      }
   }
   
}