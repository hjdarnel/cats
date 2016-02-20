import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import java.io.*;
import javax.imageio.ImageIO;

public class Client {
    Image newimg;
    static BufferedImage bimg;
    byte[] bytes;

    public static void main(String [] args)
    {
        String serverName = "localhost";
        int port = 6066;
        String[] filenames = {"test1.jpg", "test2.jpg", "test3.jpg", "test4.jpg", "test.jpg"};

        for (String x : filenames){
          try
          {
              Socket client = new Socket(serverName, port);
              bimg = ImageIO.read(new File(x));

              ImageIO.write(bimg,"JPG",client.getOutputStream());

              client.close();
          } catch(IOException e) {
              e.printStackTrace();
          }
        }
    }
}
