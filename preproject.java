import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class preproject extends JPanel {

   public void paint(Graphics g) {
      Image img = createImageWithText();
      g.drawImage(img, 400,400,this);
   }

   private Image createImageWithText() {
    // This right here is what actually builds the image. The black square is 400 x 400 in size!  
    BufferedImage bufferedImage = new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);
      Graphics g = bufferedImage.getGraphics();

      g.drawString("My name is Jose", 20,20);
      g.drawString("And this is me playing with images", 20,40);
      g.drawString("This is on the (20, 60) axis, where", 20,60);
      g.drawString("The 20 means the x axis and 60 means the y axis", 20,80);
      g.drawString("All of it starts at the top left corner where the coordinates are (0,0)", 20,100);

      // I can play around and draw on wierd points too where things get covered up and characters just overlap.
      g.drawString("REEEEEEEEEEEEEEEEE", 20,20);
      g.drawString("My name is Jose", 20,20);
      g.drawString("My name is Josesasdklfkalsdhjfk,asdjfklasdjkfljsdalfj", 20,20);
    
      // And I can also leave a dot in each corner of my workable space.
      //Ask the teacher?!?!?!?!?
      g.drawString("AAA", 0,0);
      g.drawString("AAA", 400, 0);
      g.drawString("AAA", 0, 400);
      g.drawString("AAA", 400,400);
      
      return bufferedImage;
   }
   
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.getContentPane().add(new preproject());

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(50, 50);
      frame.setVisible(true);
   }
}