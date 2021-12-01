import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;
import java.awt.*;

public class characterLocater
{
    public static void main(String[] args) throws IOException
    {
        // Converts a colored image all the way to a a sobel edge detection 
        sobelEdgeDetectionAllInOne carplates = new sobelEdgeDetectionAllInOne("C:/Users/jetpa/Desktop/2D_Design_Code/In/volvo.jpg");

        String imageUrl = "C:/Users/jetpa/Desktop/2D_Design_Code/In/volvo.jpg";
        File imageFile = new File(imageUrl);
        Image image = ImageIO.read(imageFile);
        BufferedImage bi = toBufferedImage(image);
        ITesseract instance = new Tesseract();
        instance.setDatapath("C:/Users/jetpa/Desktop/Tess4J-3.4.8-src/Tess4J/tessdata");

          BufferedImage img = ImageIO.read(imageFile);
          Graphics2D g2d = img.createGraphics();
          g2d.setColor(Color.RED);
        
          try 
          {
            File output = new File("boxed.jpg");
            ImageIO.write(bi, "jpg", output);  
          } catch (Exception e) 
          {
            //TODO: handle exception
          }

        for(Word word : instance.getWords(bi, ITessAPI.TessPageIteratorLevel.RIL_WORD))
        {
          Rectangle rect = word.getBoundingBox();

          imageFile = new File("C:/Users/jetpa/Desktop/2D_Design_Code/boxed.jpg");

          img = ImageIO.read(imageFile);
          g2d = img.createGraphics();
          g2d.setColor(Color.RED);
          g2d.drawRect((int)rect.getMinX(), (int)rect.getMinY(), (int)rect.getMaxX() - (int)rect.getMinX(), (int)rect.getMaxY() - (int)rect.getMinY());
          g2d.dispose();
          File ouptut = new File("boxed.jpg");
          ImageIO.write(img, "jpg", ouptut);

          System.out.println(rect.getMinX()+","+rect.getMaxX()+","+rect.getMinY()+","+rect.getMaxY()
                            +": "+ word.getText());
        }
        

    }

    public static BufferedImage toBufferedImage(Image img)
    {
      if (img instanceof BufferedImage)
      {
          return (BufferedImage) img;
      }

      // Create a buffered image with transparency
      BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

      // Draw the image on to the buffered image
      Graphics2D bGr = bimage.createGraphics();
      bGr.drawImage(img, 0, 0, null);
      bGr.dispose();

      // Return the buffered image
      return bimage;
    }
}
