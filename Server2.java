import java.io.IOException;
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
    try{
      ServerSocket serverSocket = new ServerSocket(6066);
      while(true){
        Socket client = serverSocket.accept();

        BufferedImage img=ImageIO.read(ImageIO.createImageInputStream(client.getInputStream()));
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);

        client.close();
      }

    } catch(SocketTimeoutException st) {
        System.out.println("Socket timed out!");
    } catch (IOException ioe){
        System.out.println(ioe);
    }
  }
}
