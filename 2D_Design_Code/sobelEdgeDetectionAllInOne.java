import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.JFrame;

public class sobelEdgeDetectionAllInOne 
{
    private double[][] sobelKernelX;

    private double[][] sobelKernelY;

    public BufferedImage image;

    private String filePath;

    private static final int kernalSize = 3;
    
    public sobelEdgeDetectionAllInOne(String picIn)
    {
        this.filePath = picIn;
        this.readImage(picIn);
        this.toBW();
        this.toGaussianBlur();

        this.generateWeightMatrixX();
        this.generateWeightMatrixY();
        this.useSobelCompleteXY(image);
        this.saveImage();
    }


    private void saveImage() 
    {
        try 
        {
            File output = new File("sobelComplete.jpg");
            ImageIO.write(this.image, "jpg", output);
        } 
        catch (Exception e) 
        {
            //Empty for now
        }
    }


    private void toGaussianBlur() 
    {
        try 
        {
            // Open the picture
            File input = new File("C:/Users/jetpa/Desktop/2D_Design_Code/imageBlackWhite.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image = ImageIO.read(input);
            int radius = 4;

            gaussianBlurr helper = new gaussianBlurr();
            double[][] weights = helper.generateWeightMatric(radius, 2); 
            BufferedImage out = helper.createGImage(image, weights, radius);
            
            File output = new File("carplatesGaussianProcessed.jpg");
            image = ImageIO.read(output);
            ImageIO.write(out, "jpg", output);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }
    }


    // Simply reads the image put into using the filepath
    private void readImage(String picIn) 
    {
        try 
        {
            // Open the picture
            File input = new File(picIn);
        
            // Get your sizes. Not such a dynamic blur
            this.image = ImageIO.read(input);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }
    }

    private void toBW() 
    {
        grayscale bw = new grayscale();
        bw.turnGrayscale(filePath, "imageBlackWhite.jpg");
    }



    private void generateWeightMatrixX() 
    {
        // This part creates the kernal into a radius x radius matrix size
        this.sobelKernelX = new double[kernalSize][kernalSize];
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

    private void generateWeightMatrixY() 
    {
        // This part creates the kernal into a radius x radius matrix size
        this.sobelKernelY = new double[kernalSize][kernalSize];
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

    public BufferedImage useSobelX(BufferedImage source)
    {
        int width = source.getWidth();
        int height = source.getHeight();
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // Subtract radius to so you dont access unbound area
        for (int x = 0; x < width - kernalSize; x++)
        {
            for (int y = 0; y < height - kernalSize; y++)
            {

                double redskies = 0;
                double greenskies = 0;
                double blueskies = 0;

                // This for loop goes through our top row first and gets the 2-D arrays of red green and blue
                // and then prints out the results by row (see method call after for loops in this thread).
                for (int weightX = 0; weightX < sobelKernelX.length; weightX++)
                {
                    for(int weightY = 0; weightY < sobelKernelX[weightX].length; weightY++)
                    {
                        try {
                            // Where we iterate through the matrix
                            int sampleX = x + weightX - (this.sobelKernelX.length / 2);
                            int sampleY = y + weightY - (this.sobelKernelX.length / 2);

                            double currentWeight = this.sobelKernelX[weightX][weightY];

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
                // Get your weighted sum
                redskies /= kernalSize * kernalSize;
                greenskies /= kernalSize * kernalSize;
                blueskies /= kernalSize * kernalSize;
                
                redskies = Math.abs(redskies);
                greenskies = Math.abs(greenskies);
                blueskies = Math.abs(blueskies);

                //result.setRGB(x, y, new Color(this.getWeightedColorVal(red), this.getWeightedColorVal(green), this.getWeightedColorVal(blue)).getRGB());
                result.setRGB(x, y, new Color((int)redskies, (int)greenskies, (int)blueskies).getRGB());

            }
        }
        return result;
    }

    public BufferedImage useSobelY(BufferedImage source)
    {
        int width = source.getWidth();
        int height = source.getHeight();
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // Subtract radius to so you dont access unbound area
        for (int x = 0; x < width - kernalSize; x++)
        {
            for (int y = 0; y < height - kernalSize; y++)
            {

                double redskies = 0;
                double greenskies = 0;
                double blueskies = 0;

                // This for loop goes through our top row first and gets the 2-D arrays of red green and blue
                // and then prints out the results by row (see method call after for loops in this thread).
                for (int weightX = 0; weightX < this.sobelKernelY.length; weightX++)
                {
                    for(int weightY = 0; weightY < sobelKernelY[weightX].length; weightY++)
                    {
                        try {
                            // Where we iterate through the matrix
                            int sampleX = x + weightX - (this.sobelKernelY.length / 2);
                            int sampleY = y + weightY - (this.sobelKernelY.length / 2);

                            double currentWeight = this.sobelKernelY[weightX][weightY];

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
                // Get your weighted sum
                redskies /= kernalSize * kernalSize;
                greenskies /= kernalSize * kernalSize;
                blueskies /= kernalSize * kernalSize;
                
                redskies = Math.abs(redskies);
                greenskies = Math.abs(greenskies);
                blueskies = Math.abs(blueskies);

                //result.setRGB(x, y, new Color(this.getWeightedColorVal(red), this.getWeightedColorVal(green), this.getWeightedColorVal(blue)).getRGB());
                result.setRGB(x, y, new Color((int)redskies, (int)greenskies, (int)blueskies).getRGB());

            }
        }
        return result;
    }

    public void useSobelCompleteXY(BufferedImage source)
    {
        int width = source.getWidth();
        int height = source.getHeight();
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // Subtract radius to so you dont access unbound area
        for (int x = 0; x < width - kernalSize; x++)
        {
            for (int y = 0; y < height - kernalSize; y++)
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
        this.image = result;
    }
    
    public BufferedImage useSobelCompleteXYWithAngles(BufferedImage source)
    {
        int width = source.getWidth();
        int height = source.getHeight();
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // Subtract radius to so you dont access unbound area
        for (int x = 0; x < width - kernalSize; x++)
        {
            for (int y = 0; y < height - kernalSize; y++)
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
                double redTotal = Math.atan2(redskiesY, redskiesX);
                double greenTotal = Math.atan2(greenskiesY, greenskiesX);
                double blueTotal = Math.atan2(blueskiesY, blueskiesX);

                //result.setRGB(x, y, new Color(this.getWeightedColorVal(red), this.getWeightedColorVal(green), this.getWeightedColorVal(blue)).getRGB());
                try
                {
                    result.setRGB(x, y, new Color((int)redTotal, (int)greenTotal, (int)blueTotal).getRGB());
                }
              catch (Exception e) 
                {
                    System.out.println("Red = " + redTotal + "Green = " + greenTotal + "Blue = " + blueTotal);
                }

            }
        }
        return result;
    }

}