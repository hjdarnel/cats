import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Server extends Thread {
    private ServerSocket serverSocket;
    Socket server;

    public Server(int port) throws IOException, ClassNotFoundException, Exception
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(180000);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                server = serverSocket.accept();
                BufferedImage img=ImageIO.read(ImageIO.createImageInputStream(server.getInputStream()));
                JFrame frame = new JFrame();
                frame.getContentPane().add(new JLabel(new ImageIcon(img)));
                frame.pack();
                frame.setVisible(true);
            }
            catch(SocketTimeoutException st)
            {
                System.out.println("Socket timed out!");
                break;
            }
            catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }
    }

    public static void main(String [] args) throws IOException, ClassNotFoundException, Exception
    {
        Thread t = new Server(6066);
        t.start();
    }
}