import java.io.IOException;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class Server {
  public static void main(String[] args) {

    int counter = 1; //for output filenames

    try{
      ServerSocket serverSocket = new ServerSocket(6066);
      while(true){
        //open connetion to receive data
        Socket client = serverSocket.accept();
        //read all of the Image from the inputstream of the socket into a BufferedImage
        BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(client.getInputStream()));

        //display the buffered image in a JFrame
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);

        //for completeness, also save the image to the immediate directory. Could probably implement caching somehow.
        File outputfile = new File("transmittedimage"+counter+".jpg");
        ImageIO.write(img, "jpg", outputfile);
        counter++;

        //close the socket because there is no more data to receive
        client.close();
      }

    } catch(SocketTimeoutException st) {
        System.out.println("Socket timed out!");
    } catch (IOException ioe){
        System.out.println(ioe);
    }
  }
}
