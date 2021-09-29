import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SobelEdgeDetectorDriver 
{
    public static void main(String[] args) 
    {
    
        //Step One: Process the image into black and white

        grayscale bw = new grayscale();
        bw.turnGrayscale("gtacharacter.jpg", "gtacharacterBW.jpg");

        //Step Two: Use low form of Gaussian Blur to make edges more visible to computer
        BufferedImage image;
        try 
        {
            // Open the picture
            File input = new File("gtacharacterBW.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image = ImageIO.read(input);
            int radius = 4;

            gaussianBlurr helper = new gaussianBlurr();
            double[][] weights = helper.generateWeightMatric(radius, 2); 
            BufferedImage out = helper.createGImage(image, weights, radius);
            
            File ouptut = new File("gtacharacterGaussianProcessed.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }
        SobelEdgeDetector edge = new SobelEdgeDetector();
        // Step ThreeA: use the Sobel Edge detector algorithm to highlight edges in x direction
        BufferedImage image2;
        try 
        {
            // Open the picture
            File inputs = new File("gtacharacterGaussianProcessed.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image2 = ImageIO.read(inputs);

            edge = new SobelEdgeDetector();
            BufferedImage out = edge.useSobelX(image2);
            
            File ouptut = new File("gtacharacterSobelX.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }

        // Step ThreeB: use the Sobel Edge detector algorithm to highlight edges in Y direction
        BufferedImage image3;
        try 
        {
            // Open the picture
            File inputs = new File("gtacharacterGaussianProcessed.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image3 = ImageIO.read(inputs);

            edge.useSobelY(image3);
            BufferedImage out = edge.useSobelY(image3);
            
            File ouptut = new File("gtacharacterSobelY.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }


        // Step Four: Combo both X and Y edges into one image (FInd hypotenuse of to get this!)
        BufferedImage image4;
        try 
        {
            // Open the picture
            File inputs = new File("gtacharacterGaussianProcessed.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image4 = ImageIO.read(inputs);

            BufferedImage out = edge.useSobelCompleteXY(image4);
            
            File ouptut = new File("gtacharacterSobelCompleteXY.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }

        // Step Five: Combo both X and Y edges into one image using arcTan
        BufferedImage image5;
        try 
        {
            // Open the picture
            File inputs = new File("gtacharacterGaussianProcessed.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image5 = ImageIO.read(inputs);

            BufferedImage out = edge.useSobelCompleteXYWithAngles(image5);
            
            File ouptut = new File("gtacharacterSobelCompleteXYARCTAN.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }        

        

        



        
    }    
}
