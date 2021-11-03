import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class pool 
{
    
    private final int RADIUS = 2;
    
    private double[][] weight;


    public pool()
    {
        this.generatePoolingKernal();
    }


    public void generatePoolingKernal()
    {
        this.weight = new double[RADIUS][RADIUS];
        for (int i = 0; i < weight.length; i++)
        {
            for (int j = 0; j < weight[i].length; j++)
            {
                weight[i][j] = 1;
            }
        }
    }

    public BufferedImage passKernal(BufferedImage source)
    {
        
        //Check that we are divisble by our final radiues

        
        
        // This piece ensures we are starting at the image from a point where the kernal
        // is not out of bounds.
        int starterxVal = RADIUS / 2;
        int starteryVal = RADIUS / 2;
        
        int width = source.getWidth() - starterxVal;
        int height = source.getHeight() - starteryVal;


        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        
        // Subtract radius to so you dont access unbound area
        for (int x = starterxVal; x < width - RADIUS; x += RADIUS)
        {
            for (int y = starteryVal; y < height - RADIUS; y += RADIUS)
            {
                double redskies = 0;
                double greenskies = 0;
                double blueskies = 0;

                // This for loop goes through our top row first and gets the 2-D arrays of red green and blue
                // and then prints out the results by row (see method call after for loops in this thread).
                for (int weightX = 0; weightX < this.weight.length; weightX++)
                {
                    for (int weightY = 0; weightY < this.weight[weightX].length; weightY++)
                    {
                        try {
                            // Where we iterate through the matrix
                            int sampleX = x + weightX - (this.weight.length / 2);
                            int sampleY = y + weightY - (this.weight.length / 2);

                            // The weight of the position in the matrix
                            double currentWeight = this.weight[weightX][weightY];

                            // Samples the color at our calculated values of the matrix
                            Color sampledColor = new Color(source.getRGB(sampleX, sampleY));
                            
                            /*
                            red[weightX][weightY] = currentWeight * sampledColor.getRed();
                            green[weightX][weightY] = currentWeight * sampledColor.getGreen();
                            blue[weightX][weightY] = currentWeight * sampledColor.getBlue();
                            */
                            // This formula is simply just 1 times the color and then we add it 
                            redskies += currentWeight * sampledColor.getRed();
                            greenskies += currentWeight * sampledColor.getGreen();
                            blueskies += currentWeight * sampledColor.getBlue();

                        }
                        //Catches our out of bounds 
                        catch (Exception e) 
                        {
                            System.out.println("Out of bounds");
                        }
                    }
                }

                redskies /= (RADIUS * RADIUS);
                greenskies /= (RADIUS * RADIUS);
                blueskies /= (RADIUS * RADIUS);


                //result.setRGB(x, y, new Color(this.getWeightedColorVal(red), this.getWeightedColorVal(green), this.getWeightedColorVal(blue)).getRGB());
                result.setRGB(x, y, new Color((int)redskies, (int)greenskies, (int)blueskies).getRGB());






            }
        }
        return result;
    }
}
