package pkg.networking.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import pkg.networking.main.GamePanel;

public class Sender extends Network {

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
        String nx = Integer.toString(GamePanel.player.x);
        String ny = Integer.toString(GamePanel.player.y);
        String send = nx + ":" + ny;
        sendMessage(send);
    }
}
