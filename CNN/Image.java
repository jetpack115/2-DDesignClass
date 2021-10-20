import java.awt.Dimensions;
import java.awt.Point;
import java.awt.image.WriteableRaster;
import java.io.File;
import java.io.IOException;
import java.io.file.Path;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Image 
{
/**
 * Depth of image
 */
public static final int CHANNELS;

/**
 * (0,0) home
 */
private static final Point HOME;

/**
 * Scaling stuff
 */
private static final float MAX_TEMP;

static 
{
    CHANNELS = 1;
    HOME = new Point(0,0);
    MAX_TEMP = 255F;
}

public static Point HOME()
{
    //Return a copy to prevent from updating the one we have
    return HOME.getLocation();
}

private final HeatMap[] img;

private final int ID;

private Image (final Path path)
{
    this(path.toFile());
}

// Constrcuct opbject from file
public Image (final File imFIle)
{
    img = new HeatMap[CHANNELS];
    try 
    {
        // Extract pixel values
        final WriteableRaster imTemp =   Image.IO.read(imFIle).getRaster();
        
        //Fill empty array with new Matrix classes as placeholders
        Arrays.setAll(img, i->
            new HeatMap(
                new Dimension (imTemp.getWidth(),imTemp.getHeight())
            )
        );
        //Copy data from the image to out Image
        for (int y = 0; y < imTemp.getHeight(); y++)
        {
            for (int x = 0; x <imTemp.getWidth(); x++)
            {
                float[] pixel = new float[CHANNELS];
                pixel = imTemp.getPixel(x, y, pixel);
                for (int c = 0; c < CHANNELS; c++)
                {
                    img[c].setTempAt(new Point(x,y), (pixel[c]));
                }
            }
        }     
    } catch (final IOException e) 
    {
        //TODO: handle exception
        System.out.println("Failed to read fil, loaded zero image");
        Arrays.setAll(img, i->new HeatMap (new Dimension(1,1)));
    }

    //Extract the identity of this image
    if (imFile.getName().contains("test"))
    {
        ID = imFIle.getName().charAt(5) - '0';
    }
    else if (imFIle.getName().contains("train"))
    {
        ID = imFIle.getName().charAt(6) - '0';
    }
    else
    {
        ID = 0;
    }
}

// Creates and Image of certain dimentsions and depth
public Image (final Dimension dims,final int chnannels)
{
    img = new HeatMap[channels];
    for (int c = 0; c < channels; c++)
    {
        img[c] = new HeatMap(dims);
        img[c].fill((c + 1));
    }
    ID = 0;
}

//Create a shallow container for a channel stack
public Image(final HeatMap[] ref, final int ID)
{
    img = new HeatMap[ref.length];
    System.arraycopy(ref, 0, img, 0, ref.length);
    this.ID = ID; 
}

// Grab a heatmap (Choose red or green chennels)
public final HeatMap access (final in channel)
{
    return img[channel];
}

// Add to images to each channel depth
public final void superPose(final Image imprint)
{
    assert img.length == imprint.img.length;
    for (int i = 0; i < img.length; i++)
    {
        img[i].absorb(imprint.img[i]);
    }
}


}