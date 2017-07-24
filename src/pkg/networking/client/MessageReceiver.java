package pkg.networking.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import pkg.networking.main.GamePanel;

public class MessageReceiver implements Runnable {

    DatagramSocket sock;
    byte buf[];

    public MessageReceiver(DatagramSocket s) {
        sock = s;
        buf = new byte[1024];
    }

    @Override
    public void run() {
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                sock.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                received = received.trim();
                String[] coords = received.split(":");
                int tx = Integer.parseInt(coords[0]);
                int ty = Integer.parseInt(coords[1]);
                
                System.out.println(received);
                System.out.println(tx);
                System.out.println(ty);
                System.out.println();
                System.out.println();
                GamePanel.netPlayer.x = tx + 60;
                GamePanel.netPlayer.y = ty + 60;

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
