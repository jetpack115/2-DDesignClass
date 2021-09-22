import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.util.Random;

public class meanBlurr {
    
    
    /**
     * Makes a matrix/kernel with 1 in each value.
     * @param radius width and height of the kernel/matrix being created.
     * @return weight - 2-D array that will hold the one values needed for calculation.
     */
    public double[][] generateWeightMatrix(int radius) 
    {
        double[][] weights = new double[radius][radius];
        for (int i = 0; i < weights.length; i++)
        {
            for (int j = 0; j < weights[i].length; j++)
            {
                weights[i][j] = 1;
            }
        }

        return weights;
    }
    /**
     * Creates a random matirx with random values between 1 and 255.
     * @param radius width and height of the kernal.
     * @return weight 2-D array of random kernal.
     */
    public double[][] generateRandomMatrix(int radius) 
    {
        Random rand = new Random();
        double[][] weights = new double[radius][radius];
        double sum = 0;
        for (int i = 0; i < weights.length; i++)
        {
            for (int j = 0; j < weights[i].length; j++)
            {
                weights[i][j] = 1 + rand.nextInt(255);
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

    public BufferedImage createmImage(BufferedImage source, double weights[][], int radius)
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

                            // The weight of the position in the matrix
                            double currentWeight = weights[weightX][weightY];

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
                            // Leave this empty...
                        }
                    }
                }
                //Be sure to divide the values you added by the radius squared. This is tto find the average
                redskies /= (radius * radius);
                greenskies /= (radius * radius);
                blueskies /= (radius * radius);

                //result.setRGB(x, y, new Color(this.getWeightedColorVal(red), this.getWeightedColorVal(green), this.getWeightedColorVal(blue)).getRGB());
                result.setRGB(x, y, new Color((int)redskies, (int)greenskies, (int)blueskies).getRGB());





            }
        }
        return result;
    }





    public BufferedImage createrandomImage(BufferedImage source, double weights[][], int radius)
    {
        int width = source.getWidth();
        int height = source.getHeight();
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // Subtract radius to so you dont access unbound area
        for (int x = 0; x < width - radius; x++)
        {
            for (int y = 0; y < height - radius; y++)
            {
                double red[][] = new double[radius][radius];
                double green[][] = new double[radius][radius]; 
                double blue[][] = new double[radius][radius]; 

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

                            // The weight of the position in the matrix
                            double currentWeight = weights[weightX][weightY];

                            // Samples the color at our calculated values of the matrix
                            Color sampledColor = new Color(source.getRGB(sampleX, sampleY));
                            
            
                            red[weightX][weightY] = currentWeight * sampledColor.getRed();
                            green[weightX][weightY] = currentWeight * sampledColor.getGreen();
                            blue[weightX][weightY] = currentWeight * sampledColor.getBlue();
                            


                        }
                        //Catches our out of bounds 
                        catch (Exception e) 
                        {
                            // Leave this empty...
                        }
                    }
                }
                //Be sure to divide the values you added by the radius squared. This is tto find the average



                result.setRGB(x, y, new Color(this.getWeightedColorVal(red), this.getWeightedColorVal(green), this.getWeightedColorVal(blue)).getRGB());
                





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
        summation /= 10;
        return (int) summation;
    }





}

