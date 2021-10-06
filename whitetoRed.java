import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class whitetoRed {
    BufferedImage image;
    int width;
    int height;
    private int numberofEdges;


    public whitetoRed(String picIn)
    {
        numberofEdges = 0;
        try 
        {
            BufferedImage bufferedImage = ImageIO.read(new File(picIn));
            this.image = bufferedImage;
        } 
        catch (IOException ex) {
            ex.printStackTrace();
          }
    }

    public void convertRedtoWhite(String fileOut)
    {
        try {
             // Get your sizes.
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
                    // Changes White to red!
                    if (red >= 15 && green >= 15 && blue >= 15 )
                    {
                        // Color is in the range of black so change it!
                        red = 255;
                        green = 0;
                        blue = 0;
                        this.numberofEdges++;
                    }
        
                    
                
                    Color newColor = new Color(red, green, blue);
        
                    image.setRGB(j ,i , newColor.getRGB());
                }
            }
            // Output the new random picture.
            File ouptut = new File(fileOut);
            ImageIO.write(image, "jpg", ouptut);
        
            }
            catch (Exception e) {}
        }

        public int getEdges()
        {
            return this.numberofEdges;
        }
    }




