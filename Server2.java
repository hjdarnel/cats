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

public class Server2 {
  public static void main(String[] args) {
    int counter = 1;
    try{
      ServerSocket serverSocket = new ServerSocket(6066);
      while(true){
        Socket client = serverSocket.accept();

        BufferedImage img=ImageIO.read(ImageIO.createImageInputStream(client.getInputStream()));
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);

        File outputfile = new File("image"+counter+".jpg");
        ImageIO.write(img, "jpg", outputfile);
        counter++;

        client.close();
      }

    } catch(SocketTimeoutException st) {
        System.out.println("Socket timed out!");
    } catch (IOException ioe){
        System.out.println(ioe);
    }
  }
}
