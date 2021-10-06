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
        bw.turnGrayscale("carplates.jpg", "carplatesBW.jpg");

        //Step Two: Use low form of Gaussian Blur to make edges more visible to computer
        BufferedImage image;
        try 
        {
            // Open the picture
            File input = new File("carplatesBW.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image = ImageIO.read(input);
            int radius = 4;

            gaussianBlurr helper = new gaussianBlurr();
            double[][] weights = helper.generateWeightMatric(radius, 2); 
            BufferedImage out = helper.createGImage(image, weights, radius);
            
            File ouptut = new File("carplatesGaussianProcessed.jpg");
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
            File inputs = new File("carplatesGaussianProcessed.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image2 = ImageIO.read(inputs);

            edge = new SobelEdgeDetector();
            BufferedImage out = edge.useSobelX(image2);
            
            File ouptut = new File("carplatesSobelX.jpg");
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
            File inputs = new File("carplatesGaussianProcessed.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image3 = ImageIO.read(inputs);

            edge.useSobelY(image3);
            BufferedImage out = edge.useSobelY(image3);
            
            File ouptut = new File("carplatesSobelY.jpg");
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
            File inputs = new File("carplatesGaussianProcessed.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image4 = ImageIO.read(inputs);

            BufferedImage out = edge.useSobelCompleteXY(image4);
            
            File ouptut = new File("carplatesSobelCompleteXY.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }

        // Step Five: Combo both X and Y edges into one image using arcTan
        /*BufferedImage image5;
        try 
        {
            // Open the picture
            File inputs = new File("carplatesGaussianProcessed.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image5 = ImageIO.read(inputs);

            BufferedImage out = edge.useSobelCompleteXYWithAngles(image5);
            
            File ouptut = new File("gtacharacterSobelCompleteXYARCTAN.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }*/        

        // Histogram playthrough
        new Processor("carplatesSobelCompleteXY.jpg")
        .rotatedNearestNeighbor(1)
        .crop(10,10,300,300)
        .translateNearestNeighbor(50,50)
        .save("doneplates.png");

        new Processor("carplatesSobelCompleteXY.jpg")
        .histogram()
        .save("histogramplates.png");



        // Playing around with intensity of guassian blur and result of amount of edges
        // Radius 4
        whitetoRed gaussianRadius4 = new whitetoRed("carplatesSobelCompleteXY.jpg");
        gaussianRadius4.convertRedtoWhite("carPlatesgaussian4.jpg");
        System.out.println("Amount of pixels found to be an edge: " + gaussianRadius4.getEdges());



        // Radius 9
        BufferedImage image9;
        try 
        {
            // Open the picture
            File input = new File("carplatesBW.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image = ImageIO.read(input);
            int radius = 9;

            gaussianBlurr helper = new gaussianBlurr();
            double[][] weights = helper.generateWeightMatric(radius, 3); 
            BufferedImage out = helper.createGImage(image, weights, radius);
            
            File ouptut = new File("carplatesGaussianProcessedradius9.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }
        SobelEdgeDetector edge9 = new SobelEdgeDetector();
        try 
        {
            // Open the picture
            File inputs = new File("carplatesGaussianProcessedradius9.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image9 = ImageIO.read(inputs);

            BufferedImage out = edge9.useSobelCompleteXY(image9);
            
            File ouptut = new File("carplatesSobelCompleteXYRadius9.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }
        
        // Radius 9
        whitetoRed gaussianRadius9 = new whitetoRed("carplatesSobelCompleteXYRadius9.jpg");
        gaussianRadius9.convertRedtoWhite("carPlatesgaussian9.jpg");
        System.out.println("Amount of pixels found to be an edge: " + gaussianRadius9.getEdges());

        

        // Playing around with intensity of guassian blur and result of amount of edges
        // Radius 25

        BufferedImage image25;
        try 
        {
            // Open the picture
            File input = new File("carplatesBW.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image = ImageIO.read(input);
            int radius = 25;

            gaussianBlurr helper = new gaussianBlurr();
            double[][] weights = helper.generateWeightMatric(radius, 5); 
            BufferedImage out = helper.createGImage(image, weights, radius);
            
            File ouptut = new File("carplatesGaussianProcessedradius25.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }
        SobelEdgeDetector edge25 = new SobelEdgeDetector();
        try 
        {
            // Open the picture
            File inputs = new File("carplatesGaussianProcessedradius25.jpg");
        
            // Get your sizes. Not such a dynamic blur
            image25 = ImageIO.read(inputs);

            BufferedImage out = edge9.useSobelCompleteXY(image25);
            
            File ouptut = new File("carplatesSobelCompleteXYRadius25.jpg");
            ImageIO.write(out, "jpg", ouptut);
        }
        catch (IOException e)
        {
            System.out.println("Complete.");
        }
        
        // Radius 9
        whitetoRed gaussianRadius25 = new whitetoRed("carplatesSobelCompleteXYRadius25.jpg");
        gaussianRadius25.convertRedtoWhite("carPlatesgaussian25.jpg");
        System.out.println("Amount of pixels found to be an edge: " + gaussianRadius25.getEdges());
        



        
    }    
}
