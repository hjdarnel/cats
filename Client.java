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

    public static void main(String [] args)
    {
        String serverName = "192.168.1.37"; //EDIT THIS TO NETWORKED SERVER COMPUTER IP
        int port = 6066;
        String[] filenames = {"test1.jpg", "test2.jpg", "test3.jpg", "test4.jpg", "test5.jpg"};

        for (String x : filenames){
          try
          {
              //open socket connection
              Socket client = new Socket(serverName, port);
              //write image to BufferedImage instance
              bimg = ImageIO.read(new File(x));
              //send image to socket
              ImageIO.write(bimg,"JPG",client.getOutputStream());
              //close socket after sending image, so server knows that the image is fully transmitted
              client.close();
          } catch(IOException e) {
              e.printStackTrace();
          }
        }
    }
}
