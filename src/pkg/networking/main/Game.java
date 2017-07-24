package pkg.networking.main;

import java.net.DatagramSocket;
import javax.swing.JFrame;
import pkg.networking.client.*;

public class Game {

    public static void main(String[] args) throws Exception{
        //JFrame
        JFrame window = new JFrame("Networking");
        window.add(new GamePanel());
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Networking 
        String host = "localhost";
        DatagramSocket socket = new DatagramSocket();
        MessageReceiver r = new MessageReceiver(socket);
        MessageSender s = new MessageSender(socket, host);
        Thread rt = new Thread(r);
        Thread st = new Thread(s);
        rt.start();
        st.start();

    }
}
