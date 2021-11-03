import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
/**
 * This class simply uses a kernal over an image until the picture is exactly one pixel
 * @author jlcorrales
 */
public class useKernaltoMinimizePic 
{

    private double[][] weight;

    private int radius;

    public ArrayList<Double> data;

    private boolean averaged;

    private double[][] sobelKernelX;

    private double[][] sobelKernelY;

    /**
     * Basic contructor of the class
     */
    public useKernaltoMinimizePic(int radius)
    {
        this.radius = radius;
        this.generateKernal(radius);
        this.data = new ArrayList<>();
        this.averaged = false;
    }

    /**
     * Method to genearate our kenral of size radius, keeps the same calue of the image just makes it smaller
     * @param radius size of lenght and width of the kernal in question
     */
    private void generateKernal(int radius) 
    {
        this.weight = new double[radius][radius];
        for (int i = 0; i < weight.length; i++)
        {
            for (int j = 0; j < weight[i].length; j++)
            {
                if (i == 1 && j == 1)
                {
                    weight[i][j] = 0;
                }
                
                else
                {
                    weight[i][j] = 1;
                }
            }
        }
    }


    public void generateKernalSharpen()
    {
        this.weight = new double[radius][radius];
        for (int i = 0; i < weight.length; i++)
        {
            for (int j = 0; j < weight[i].length; j++)
            {
                if (i == 0 || i == 2)
                {
                    if (j == 1)
                    {
                        weight[i][j] = 1;
                    }
                    else
                    {
                        weight[i][j] = 0;
                    }
                }
                
                else
                {
                    if (j == 1)
                    {
                        weight[i][j] = 0;
                    }
                    else
                    {
                        weight[i][j] = 1;
                    }
                }
            }
        }
    }

    /**
     * Creates a kernal for use in a gaussian blur
     * @param variance the variance you wish to input into the guassain equation
     */
    public void generateGaussianKernal(double variance)
    {
        this.weight = new double[radius][radius];
        double sum = 0;
        for (int i = 0; i < weight.length; i++)
        {
            for (int j = 0; j < weight[i].length; j++)
            {
                weight[i][j] = this.gModel(i - radius / 2, j - radius / 2, variance);
                sum += weight[i][j];
                
            }
        }
        
        for (int i = 0; i < weight.length; i++)
        {
            for (int j = 0; j < weight[i].length; j++)
            {
                weight[i][j] /= sum;
            }
        }
        this.averaged = true;
        
    }

    private double gModel(double x, double y, double variance)
    {
        return (1 / (2 * Math.PI * Math.pow(variance, 2) * 
            Math.exp(-(Math.pow(x, 2)) + Math.pow(y, 2)) / (2 * Math.pow(variance, 2))));
    }

    public void generateSobelKernals()
    {
        this.generateWeightMatrixX();
        this.generateWeightMatrixY();
    }



    private void generateWeightMatrixY() 
    {
                // This part creates the kernal into a radius x radius matrix size
                this.sobelKernelY = new double[this.radius][this.radius];
                double sum = 0;
        
                for (int i = 0; i < sobelKernelY.length; i++)
                {
                    // For top row
                    if (i == 0)
                    {
                        int helper = -1;
                        for (int j = 0; j < sobelKernelY[i].length; j++)
                        {
                            this.sobelKernelY[i][j] = helper;
                            if (helper > -2)
                            {
                                helper += -1;
                            }
                            else
                            {
                                helper += 1;
                            }
                            sum += this.sobelKernelY[i][j];
                        }
                    }
                    // For middle row
                    else if(i == 1)
                    {
                        int helper = 0;
                        for (int j = 0; j < sobelKernelY[i].length; j++)
                        {
                            this.sobelKernelY[i][j] = helper;
                            sum += this.sobelKernelY[i][j];
                        }
                    }
                    else
                    {
                        int helper = 1;
                        for (int j = 0; j < sobelKernelY[i].length; j++)
                        {
                            this.sobelKernelY[i][j] = helper;
                            if (helper < 2)
                            {
                                helper += 1;
                            }
                            else
                            {
                                helper -= 1;
                            }
                            sum += this.sobelKernelY[i][j];
                        }
                    }
                }
    }

    private void generateWeightMatrixX() 
    {
        // This part creates the kernal into a radius x radius matrix size
        this.sobelKernelX = new double[this.radius][this.radius];
        double sum = 0;

        for (int i = 0; i < sobelKernelX.length; i++)
        {
            // For top row
            if (i == 0 || i == 2)
            {
                int helper = -1;
                for (int j = 0; j < sobelKernelX[i].length; j++)
                {
                    this.sobelKernelX[i][j] = helper;
                    helper += 1;
                    sum += this.sobelKernelX[i][j];
                }
            }
            // For middle row
            else if(i == 1)
            {
                int helper = -2;
                for (int j = 0; j < sobelKernelX[i].length; j++)
                {
                    this.sobelKernelX[i][j] = helper;
                    sum += this.sobelKernelX[i][j];
                    helper += 2;
                }
            }

        }
    }

    public BufferedImage passKernal(BufferedImage source)
    {
        // This piece ensures we are starting at the image from a point where the kernal
        // is not out of bounds.
        int starterxVal = this.radius / 2;
        int starteryVal = this.radius / 2;
        
        int width = source.getWidth() - starterxVal;
        int height = source.getHeight() - starteryVal;


        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        
        // Subtract radius to so you dont access unbound area
        for (int x = starterxVal; x < width - radius; x++)
        {
            for (int y = starteryVal; y < height - radius; y++)
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
                //Be sure to divide the values you added by the radius squared. This is to find the average
                if (!averaged)
                {
                    redskies /= (radius * radius);
                    greenskies /= (radius * radius);
                    blueskies /= (radius * radius);
                }

                //result.setRGB(x, y, new Color(this.getWeightedColorVal(red), this.getWeightedColorVal(green), this.getWeightedColorVal(blue)).getRGB());
                result.setRGB(x, y, new Color((int)redskies, (int)greenskies, (int)blueskies).getRGB());
                this.data.add(redskies);
                this.data.add(greenskies);
                this.data.add(blueskies);






            }
        }
        return result;
    }


    public BufferedImage useSobelCompleteXY(BufferedImage source)
    {
        int width = source.getWidth();
        int height = source.getHeight();
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // Subtract radius to so you dont access unbound area
        for (int x = 0; x < width - radius; x++)
        {
            for (int y = 0; y < height - radius; y++)
            {
                //Goes through X axis
                double redskiesX = 0;
                double greenskiesX = 0;
                double blueskiesX = 0;
                //Goes through Y axis
                double redskiesY = 0;
                double greenskiesY = 0;
                double blueskiesY = 0;

                // This for loop goes through our top row first and gets the 2-D arrays of red green and blue
                // and then prints out the results by row (see method call after for loops in this thread).
                for (int weightX = 0; weightX < this.sobelKernelY.length; weightX++)
                {
                    for(int weightY = 0; weightY < sobelKernelY[weightX].length; weightY++)
                    {
                        try {
                            // FOR THE Y VALUES
                            int sampleX = x + weightX - (this.sobelKernelY.length / 2);
                            int sampleY = y + weightY - (this.sobelKernelY.length / 2);

                            double currentWeightY = this.sobelKernelY[weightX][weightY];

                            // Samples the color at our calculated values of the matrix
                            Color sampledColorY = new Color(source.getRGB(sampleX, sampleY));

                            redskiesY += currentWeightY * sampledColorY.getRed();
                            greenskiesY += currentWeightY * sampledColorY.getGreen();
                            blueskiesY += currentWeightY * sampledColorY.getBlue();



                            // FOR THE X VALUES
                            int sampleXX = x + weightX - (this.sobelKernelX.length / 2);
                            int sampleYY = y + weightY - (this.sobelKernelX.length / 2);

                            double currentWeightX = this.sobelKernelX[weightX][weightY];

                            // Samples the color at our calculated values of the matrix
                            Color sampledColorX = new Color(source.getRGB(sampleXX, sampleYY));

                            redskiesX += currentWeightX * sampledColorX.getRed();
                            greenskiesX += currentWeightX * sampledColorX.getGreen();
                            blueskiesX += currentWeightX * sampledColorX.getBlue();

                        }
                        //Catches our out of bounds 
                        catch (Exception e) 
                        {
                            // Removing this piece makes it soooooo quick!
                        }
                    }
                }
                // Get your weighted sum for Y
                redskiesY /= kernalSize * kernalSize;
                greenskiesY /= kernalSize * kernalSize;
                blueskiesY /= kernalSize * kernalSize;

                // Get your weighted sum for X
                redskiesX /= kernalSize * kernalSize;
                greenskiesX /= kernalSize * kernalSize;
                blueskiesX /= kernalSize * kernalSize;                

                // Find hyptenuse of x and y to get a combo picture of both edge finds
                double redTotal = Math.sqrt((Math.pow(redskiesX, 2) + Math.pow(redskiesY, 2)));
                double greenTotal = Math.sqrt((Math.pow(greenskiesX, 2) + Math.pow(greenskiesY, 2)));
                double blueTotal = Math.sqrt((Math.pow(blueskiesX, 2) + Math.pow(blueskiesY, 2)));

                //result.setRGB(x, y, new Color(this.getWeightedColorVal(red), this.getWeightedColorVal(green), this.getWeightedColorVal(blue)).getRGB());
                result.setRGB(x, y, new Color((int)redTotal, (int)greenTotal, (int)blueTotal).getRGB());

            }
        }
        return result;
    }



}
