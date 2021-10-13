import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Scanner;
import java.util.Random;
public class masterDriverImageProcessing {
    public static void main (String[] args)
    {
        //This class simply facilitates using what I have thus far into one msimple to us
        //program. It deiplays a menu of sorts to let the user do whatever they want from the following options
        //1. Make the image black and white
        //2. Change the black colors in an image to green.
        //3. Apply a level of guassian blurr - be sure to include a submenu that lets the user determine the radius
        //of the blur and to give an estimate as to how long it will take before agreeing!
        //4. Make the piucture appear whiter (just raises rgb values a bit)
        //5. Apply a mean blur to the image.
        //6. Destroy the image - randomizes the regb values as clutter the image data to an unretrivable value.
        //7. Apply a sobel edge detector to the image. Let the user know that to get meanigful results it must be in b&w
        //8  Turn white colors to red in an image (useful for after applying the sobel algorithm)
        Scanner input = new Scanner(System.in);
        String fileName = "";

        int current = 0;
        boolean contiuneLoop = true;
        boolean firstIteration = true;


        System.out.println("Welcome to the simple image processing program!\nPlease first load up an image!\nEnter"
        + " the name of the image that you will be using including the file type (ie .jpg., .jpg, etc). Make sure it is in the same subdirectory as the program!");
        while (true)
        {
            try 
            {
                // Make sure to check if it ends in a valid data type!
                fileName = input.next();
                break;
            } catch (Exception e) {
                System.out.println("That is not a valid string please try again!");
            }
        }
        System.out.println("Thank you!");

        while(contiuneLoop)
        {
            System.out.println("Choose from the following:\n1) Make the image black and white\n2)Guassian blur"
            + "\n3) Change the black colors in an image to green\n4) Make the piucture appear whiter\n5) Apply a mean blur\n6) Destroy the image"
            + "\n7) Apply the sobel edge detector\n8) Turn white colors to red in an image \n9) Exit");
            
            current = input.nextInt();
            // Black and white
            if (current == 1)
            {
                if (firstIteration)
                {
                    grayscale bw = new grayscale();
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    // Check for data type too
                    bw.turnGrayscale(fileName, fileOut);
                    System.out.println("Your image has been created!");
                }
                else
                {
                    System.out.println("What would you like the input of the file to be?");
                    String fileIn = input.next();
                    grayscale bw = new grayscale();
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    // Check for data type too
                    bw.turnGrayscale(fileIn, fileOut);
                    System.out.println("Your image has been created!");
                }
            }
            // Guassian Blurr
            if (current == 2)
            {
                if (firstIteration)
                {
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    BufferedImage image;
                    try 
                    {
                        // Open the picture
                        File in = new File(fileName);
                    
                        // Get your sizes. Not such a dynamic blur
                        image = ImageIO.read(in);
                        int radius = 4;
                        System.out.println("How big would you like the radius of the kernal used in the blur? The bigger the slower, but the blur more intense!");
                        radius = input.nextInt();
                        gaussianBlurr helper = new gaussianBlurr();
                        double[][] weights = helper.generateWeightMatric(radius, Math.sqrt(radius)); 
                        BufferedImage out = helper.createGImage(image, weights, radius);
                        
                        File ouptut = new File(fileOut);
                        ImageIO.write(out, "jpg", ouptut);
                    }
                    catch (IOException e)
                    {
                        System.out.println("Your image has been created!");
                    }
                }
                else
                {
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    System.out.println("What would you like the input of the file to be?");
                    String fileIn = input.next();
                    BufferedImage image;
                    try 
                    {
                        // Open the picture
                        File in = new File(fileIn);
                    
                        // Get your sizes. Not such a dynamic blur
                        image = ImageIO.read(in);
                        int radius = 4;
                        System.out.println("How big would you like the radius of the kernal used in the blur? The bigger the slower, but the blur more intense!");
                        radius = input.nextInt();
                        gaussianBlurr helper = new gaussianBlurr();
                        double[][] weights = helper.generateWeightMatric(radius, Math.sqrt(radius)); 
                        BufferedImage out = helper.createGImage(image, weights, radius);
                        
                        File ouptut = new File(fileOut);
                        ImageIO.write(out, "jpg", ouptut);
                    }
                    catch (IOException e)
                    {
                        System.out.println("Your image has been created!");
                    }
                }
            }
            //Change the black colors in an image to green
            if (current == 3)
            {
                if (firstIteration)
                {
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    BufferedImage image;
                    int width;
                    int height;
            
                    try {
                    // Open the picture
                    File in = new File(fileName);
                    
                    // Get your sizes.
                    image = ImageIO.read(in);
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
                            // Changes black to green!
                            if (red <= 40 && green <= 40 && blue <= 40 )
                            {
                                // Color is in the range of black so change it!
                                red = 4;
                                green = 255;
                                blue = 0;
                            }
            
                            
                        
                            Color newColor = new Color(red, green, blue);
            
                            image.setRGB(j ,i , newColor.getRGB());
                            System.out.println("Reading... Currently on ");
                        }
                    }
                    // Output the new random picture.
                    File ouptut = new File(fileOut);
                    ImageIO.write(image, "jpg", ouptut);
            
                    }
                    catch (Exception e) {}
                    System.out.println("Your image has been created!");
                }
                else
                {
                    System.out.println("What would you like the input of the file to be?");
                    String fileIn = input.next();
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    BufferedImage image;
                    int width;
                    int height;
            
                    try {
                    // Open the picture
                    File in = new File(fileIn);
                    
                    // Get your sizes.
                    image = ImageIO.read(in);
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
                            // Changes black to green!
                            if (red <= 40 && green <= 40 && blue <= 40 )
                            {
                                // Color is in the range of black so change it!
                                red = 4;
                                green = 255;
                                blue = 0;
                            }
            
                            
                        
                            Color newColor = new Color(red, green, blue);
            
                            image.setRGB(j ,i , newColor.getRGB());
                            System.out.println("Reading... Currently on ");
                        }
                    }
                    // Output the new random picture.
                    File ouptut = new File(fileOut);
                    ImageIO.write(image, "jpg", ouptut);
            
                    }
                    catch (Exception e) {}
                    System.out.println("Your image has been created!");

                }
            }
            //Make pic whiter
            if (current == 4)
            {
                if (firstIteration)
                {
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    BufferedImage image;
                    int width;
                    int height;
            
                    try {
                    // Open the picture
                    File in = new File(fileName);
                    
                    // Get your sizes.
                    image = ImageIO.read(in);
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
            
                            // Allows us to create a border around the image in black since it must not be an edge
                            // or else it will defulat the pixel to black!
                            if (i != 0 && i != height && j != 0 && j != width) {
                                red = (int)(c.getRed() + 100);
                                green = (int)(c.getGreen() + 100);
                                blue = (int)(c.getBlue() + 100);
                                if (red > 255)
                                {
                                    red = 255;
                                }
                                if (green > 255)
                                {
                                    green = 255;
                                }
                                if (blue > 255)
                                {
                                    blue = 255;
                                }
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
                    System.out.println("Your image has been created!");
                }
                else
                {
                    System.out.println("What would you like the input of the file to be?");
                    String fileIn = input.next();
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    BufferedImage image;
                    int width;
                    int height;
            
                    try {
                    // Open the picture
                    File in = new File(fileIn);
                    
                    // Get your sizes.
                    image = ImageIO.read(in);
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
            
                            // Allows us to create a border around the image in black since it must not be an edge
                            // or else it will defulat the pixel to black!
                            if (i != 0 && i != height && j != 0 && j != width) {
                                red = (int)(c.getRed() + 100);
                                green = (int)(c.getGreen() + 100);
                                blue = (int)(c.getBlue() + 100);
                                if (red > 255)
                                {
                                    red = 255;
                                }
                                if (green > 255)
                                {
                                    green = 255;
                                }
                                if (blue > 255)
                                {
                                    blue = 255;
                                }
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
                    System.out.println("Your image has been created!");
                }
            }
            //5. Apply a mean blur to the image.
            if (current == 5)
            {
                if (firstIteration)
                {
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    BufferedImage image;
            
                    try 
                    {
                        // Open the picture
                        File in = new File(fileName);
                    
                        // Get your sizes.
                        image = ImageIO.read(in);
                        int radius = 9;
            
                        meanBlurr blury = new meanBlurr();
                        double[][] weights = blury.generateRandomMatrix(radius); 
                        BufferedImage out = blury.createrandomImage(image, weights, radius);
                        
                        File ouptut = new File(fileOut);
                        ImageIO.write(out, "jpg", ouptut);
                    }
                    catch (IOException e)
                    {

                    }
                    System.out.println("Your image has been created!");
                }
                else
                {
                    System.out.println("What would you like the input of the file to be?");
                    String fileIn = input.next();
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    BufferedImage image;
            
                    try 
                    {
                        // Open the picture
                        File in = new File(fileIn);
                    
                        // Get your sizes.
                        image = ImageIO.read(in);
                        int radius = 9;
            
                        meanBlurr blury = new meanBlurr();
                        double[][] weights = blury.generateRandomMatrix(radius); 
                        BufferedImage out = blury.createrandomImage(image, weights, radius);
                        
                        File ouptut = new File(fileOut);
                        ImageIO.write(out, "jpg", ouptut);
                    }
                    catch (IOException e)
                    {

                    }
                    System.out.println("Your image has been created!");

                }
            }
            //randomizes the rgb values
            if (current == 6)
            {
                if (firstIteration)
                {
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    BufferedImage image;
                    int width;
                    int height;
                    Random rand = new Random();
            
                    try {
                    // Open the picture
                    File in = new File(fileName);
                    image = ImageIO.read(in);
                    
                    // Get your sizes.
                    width = image.getWidth();
                    height = image.getHeight();
            
                    for(int i = 0; i < height; i++) 
                    {
                        for(int j = 0; j < width; j++) 
                        {
                            //Color c = new Color (image.getRGB(i, j));
            
                            // Get your random numbers between 0 and 255
                            int red = rand.nextInt(256);
                            int green = rand.nextInt(256);
                            int blue = rand.nextInt(256);
            
                            Color newColor = new Color(red, green, blue);
            
                            image.setRGB(j ,i , newColor.getRGB());
                        }
                    }
                    // Output the new random picture.
                    File ouptut = new File(fileOut);
                    ImageIO.write(image, "jpg", ouptut);
            
                    }
                    catch (Exception e) {}
                    System.out.println("Your image has been created!");
                }
                else
                {
                    System.out.println("What would you like the input of the file to be?");
                    String fileIn = input.next();
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    BufferedImage image;
                    int width;
                    int height;
                    Random rand = new Random();
            
                    try {
                    // Open the picture
                    File in = new File(fileIn);
                    image = ImageIO.read(in);
                    
                    // Get your sizes.
                    width = image.getWidth();
                    height = image.getHeight();
            
                    for(int i = 0; i < height; i++) 
                    {
                        for(int j = 0; j < width; j++) 
                        {
                            //Color c = new Color (image.getRGB(i, j));
            
                            // Get your random numbers between 0 and 255
                            int red = rand.nextInt(256);
                            int green = rand.nextInt(256);
                            int blue = rand.nextInt(256);
            
                            Color newColor = new Color(red, green, blue);
            
                            image.setRGB(j ,i , newColor.getRGB());
                        }
                    }
                    // Output the new random picture.
                    File ouptut = new File(fileOut);
                    ImageIO.write(image, "jpg", ouptut);
            
                    }
                    catch (Exception e) {}
                    System.out.println("Your image has been created!");
                }
            }
            //Sobel standalone
            if (current == 7)
            {
                if (firstIteration)
                {

                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    System.out.println("Your image has been created!");
                    SobelEdgeDetector edge = new SobelEdgeDetector();
                    BufferedImage image4;
                    try 
                    {
                        // Open the picture
                        File inputs = new File(fileName);
                    
                        // Get your sizes. Not such a dynamic blur
                        image4 = ImageIO.read(inputs);
            
                        BufferedImage out = edge.useSobelCompleteXY(image4);
                        
                        File ouptut = new File(fileOut);
                        ImageIO.write(out, "jpg", ouptut);
                    }
                    catch (IOException e)
                    {
                        System.out.println("Your image has been created!");
                    }
                }
                else
                {
                    System.out.println("What would you like the input of the file to be?");
                    String fileIn = input.next();
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    SobelEdgeDetector edge = new SobelEdgeDetector();
                    BufferedImage image4;
                    try 
                    {
                        // Open the picture
                        File inputs = new File(fileIn);
                    
                        // Get your sizes. Not such a dynamic blur
                        image4 = ImageIO.read(inputs);
            
                        BufferedImage out = edge.useSobelCompleteXY(image4);
                        
                        File ouptut = new File(fileOut);
                        ImageIO.write(out, "jpg", ouptut);
                    }
                    catch (IOException e)
                    {
                        System.out.println("Your image has been created!");
                    }
                }
            }
            // Turn white colors to red in an image
            if (current == 8)
            {
                if (firstIteration)
                {
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    whitetoRed helper = new whitetoRed(fileName);
                    helper.convertRedtoWhite(fileOut);
                    System.out.println("Your image has been created!");
                }
                else
                {
                    System.out.println("What would you like the input of the file to be?");
                    String fileIn = input.next();
                    System.out.println("What would you like the output of the file to be?");
                    String fileOut = input.next();
                    whitetoRed helper = new whitetoRed(fileIn);
                    helper.convertRedtoWhite(fileOut);
                    System.out.println("Your image has been created!");
                }
            }
            if (current == 9)
            {
                //Exit the program
                contiuneLoop = false;
            }
        }
        input.close();
        System.out.println("Thank you for using the image processing program. Have a great day!");
        
    }
}

