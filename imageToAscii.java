import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;

//used this string from The coding train'Ñ@#W$9876543210?!abc;:+=-,._ '
//rescaling the input image to lower dimension is needed to give optimum result
//setting the font size 1 in notepad is helpful in viewing the image
public class imageToAscii {
  public static void main(String args[]) throws Exception {
    BufferedImage img = null;
    File f = null;

    // read image
    try {
      f = new File("sample.jpg");
      img = ImageIO.read(f);
    } catch (IOException e) {
      System.out.println(e);
    }

    // get image width and height
    int width = img.getWidth();
    int height = img.getHeight();

    //output file
    String name = "output.txt";
    PrintWriter outFile = new PrintWriter(new FileWriter(name));

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        // Creating a Color object from pixel value
        Color c = new Color(img.getRGB(y, x));
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        int grayscale = (red + green + blue)/3;
        Color newColor = new Color(grayscale, grayscale,grayscale);
        //getting the particular ascii character
        char ch=getascii(grayscale);
        //writing ch into file
        outFile.write(ch);
        img.setRGB(y, x, newColor.getRGB());
      }
      //all pixels are read from a row
      outFile.write("\n");
    }
    outFile.flush();
    outFile.close();
  }

  public static char getascii(int grayscale) {
    String str = "Ñ@#W$9876543210?!abc;:+=-,._ ";
    int len = str.length();
    //this calculation is done with respect to dark mode
    //which means the black pixels will get space character which has no brightness
    //you can change this for light mode by removing str.lenght()-1
    int pos=str.length()-1-(int)(((len-1)*grayscale)/255);
    char ch= str.charAt(pos);
    return ch;
  }
}