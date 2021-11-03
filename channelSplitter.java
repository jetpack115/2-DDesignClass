import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class channelSplitter 
{
    
    private BufferedImage redChannel;

    private BufferedImage greenChannel;

    private BufferedImage blueChannel;

    
    
    public channelSplitter(BufferedImage source)
    {
        this.splitChannels(source);
    }

    private void splitChannels(BufferedImage source)
    {
        
        this.redChannel = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.greenChannel = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.blueChannel = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int i = 0; i < source.getHeight(); i++) 
        {
            for(int j = 0; j < source.getWidth(); j++) 
            {
                Color c = new Color (source.getRGB(j, i));
    
                int red = (int)(c.getRed());
                int green = (int)(c.getGreen());
                int blue = (int)(c.getBlue());
    
                Color redColor = new Color(red, 0, 0);
                Color greenColor = new Color(0, green, 0);
                Color blueColor = new Color(0, 0, blue);
    
                this.redChannel.setRGB(j ,i , redColor.getRGB());
                this.greenChannel.setRGB(j ,i , greenColor.getRGB());
                this.blueChannel.setRGB(j ,i , blueColor.getRGB());
            }
        }

        File output1 = new File("red.jpg");
        File output2 = new File("green.jpg");
        File output3 = new File("blue.jpg");

        try 
        {
            ImageIO.write(this.redChannel, "jpg", output1);
            ImageIO.write(this.greenChannel, "jpg", output2);
            ImageIO.write(this.blueChannel, "jpg", output3);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }


    }




}
