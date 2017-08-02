package pkg.networking.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import pkg.networking.main.GamePanel;

public class Sender {

    private final int PORT = 4445;
    private final String hostname = "localhost";
    private DatagramSocket sock;

    public Sender(DatagramSocket sock) {
        super();
        this.sock = sock;
    }

    private void sendMessage(String s) {
        byte buf[] = s.getBytes();
        try {
            InetAddress address = InetAddress.getByName(hostname);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
            sock.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Update single players values to server
    //~60 times per second
    public void update() {
        String dir = Integer.toString(GamePanel.player.direction);
        String nx = Integer.toString(GamePanel.player.x);
        String ny = Integer.toString(GamePanel.player.y);
        String send = dir + ":" + nx + ":" + ny;
        sendMessage(send);
    }
}
