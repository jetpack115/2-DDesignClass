import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class Pixel {
   BufferedImage image;
   int width;
   int height;
   
   public Pixel() {
      try {
        // Open up picture 
        File input = new File("out.png");
        
        //Read the picture
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();

         //Print out the dimensions of the image!
         System.out.println("Width is: " + width + "pixels");
         System.out.println("Height is: " + height + "pixels");


         
         int count = 0;
         // For loop that gets the rgb number from every single pixel in the picture! Only works on PNG imagaes no jpegs for whatever reason.
         // Important to understand that we iterate through height at the first level and then through width! As we care about the top line
         // first and then the line underneath. Remember top left corner of the screen and then moce down!
         for(int i = 0; i < height; i++) {
         
            for(int j = 0; j < width; j++) {
            
               count++;

               // The image.getRGB gets us the rgb color. It does need a x axis and y axis and this is obtained thanks to our for loop!
               Color c = new Color(image.getRGB(j, i));
               System.out.println("S.No: " + count + " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue());
            }
         }

      } catch (Exception e) {}
   }
   
   static public void main(String args[]) throws Exception {
      Pixel obj = new Pixel();
   }
}