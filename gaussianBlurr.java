import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class gaussianBlurr {
    
    public double[][] generateWeightMatric(int radius, double variance) 
    {
        double[][] weights = new double[radius][radius];
        double sum = 0;
        for (int i = 0; i < weights.length; i++)
        {
            for (int j = 0; j < weights[i].length; j++)
            {
                weights[i][j] = this.gModel(i - radius / 2, j - radius / 2, variance);
                sum += weights[i][j];
                
            }
        }
        
        for (int i = 0; i < weights.length; i++)
        {
            for (int j = 0; j < weights[i].length; j++)
            {
                weights[i][j] /= sum;
            }
        }
        
        return weights;
    }

    public BufferedImage createGImage(BufferedImage source, double weights[][], int radius)
    {
        int width = source.getWidth();
        int height = source.getHeight();
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // Subtract radius to so you dont access unbound area
        for (int x = 0; x < width - radius; x++)
        {
            for (int y = 0; y < height - radius; y++)
            {

                double redskies = 0;
                double greenskies = 0;
                double blueskies = 0;

                // This for loop goes through our top row first and gets the 2-D arrays of red green and blue
                // and then prints out the results by row (see method call after for loops in this thread).
                for (int weightX = 0; weightX < weights.length; weightX++)
                {
                    for(int weightY = 0; weightY < weights[weightX].length; weightY++)
                    {
                        try {
                            // Where we iterate through the matrix
                            int sampleX = x + weightX - (weights.length / 2);
                            int sampleY = y + weightY - (weights.length / 2);

                            double currentWeight = weights[weightX][weightY];

                            // Samples the color at our calculated values of the matrix
                            Color sampledColor = new Color(source.getRGB(sampleX, sampleY));
                            
                            /*
                            red[weightX][weightY] = currentWeight * sampledColor.getRed();
                            green[weightX][weightY] = currentWeight * sampledColor.getGreen();
                            blue[weightX][weightY] = currentWeight * sampledColor.getBlue();
                            */

                            redskies += currentWeight * sampledColor.getRed();
                            greenskies += currentWeight * sampledColor.getGreen();
                            blueskies += currentWeight * sampledColor.getBlue();

                        }
                        //Catches our out of bounds 
                        catch (Exception e) 
                        {
                            // Removing this piece makes it soooooo quick!
                        }
                    }
                }
                //result.setRGB(x, y, new Color(this.getWeightedColorVal(red), this.getWeightedColorVal(green), this.getWeightedColorVal(blue)).getRGB());
                result.setRGB(x, y, new Color((int)redskies, (int)greenskies, (int)blueskies).getRGB());





            }
        }
        return result;
    }

    private int getWeightedColorVal(double[][] weightedColor)
    {
        double summation = 0;

        for (int i = 0; i < weightedColor.length; i++)
        {
            for (int j = 0; j < weightedColor[i].length; j++)
            {
                summation += weightedColor[i][j];
            }
        }
        return (int) summation;
    }


    
    public double gModel(double x, double y, double variance)
    {
        return (1 / (2 * Math.PI * Math.pow(variance, 2) * 
            Math.exp(-(Math.pow(x, 2)) + Math.pow(y, 2)) / (2 * Math.pow(variance, 2))));
    }

}
