import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.lowagie.text.Rectangle;

import java.util.List;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.Word;

public class characterDetector 
{
    public static void main(String[] args)
    {
        // Converts a colored image all the way to a a sobel edge detection 
        sobelEdgeDetectionAllInOne carplates = new sobelEdgeDetectionAllInOne("C:/Users/jetpa/Desktop/New folder/2-DDesignClass-main/2-DDesignClass-main/carplates.jpg");
        BufferedImage regions = carplates.image;
        Tesseract tess = new Tesseract();
        try 
        {
            tess.setDatapath("C:/Users/jetpa/Desktop/Tess4J-3.4.8-src/Tess4J/tessdata");

            String text = tess.doOCR(new File("C:/Users/jetpa/Desktop/New folder/2-DDesignClass-main/sobelComplete.jpg"));
            String[] wordList = text.split(" ");
            /*for (int i = 0; i < wordList.length; i++)
            {
                Word yes = new Word(arg0, arg1, arg2)
                wordList[i];
            }*/
            System.out.print(text);

            /*File imageFile = new File("C:/Users/jetpa/Desktop/Tess4J-3.4.8-src/Tess4J/tessdata", "C:/Users/jetpa/Desktop/New folder/2-DDesignClass-main/sobelComplete.jpg");
            BufferedImage bi = ImageIO.read(imageFile);
            int level = TessPageIteratorLevel.RIL_SYMBOL;
            logger.info("PageIteratorLevel: " + Utils.getConstantName(level, TessPageIteratorLevel.class));
            List<Rectangle> result = instance.getSegmentedRegions(bi, level);
            for (int i = 0; i < result.size(); i++) {
                Rectangle rect = result.get(i);
                logger.info(String.format("Box[%d]: x=%d, y=%d, w=%d, h=%d", i, rect.x, rect.y, rect.width, rect.height));
            }
        
            assertTrue(result.size() > 0);*/
           
        }
        catch (TesseractException e) 
        {
            e.printStackTrace();
        }



    }
}
