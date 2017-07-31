package pkg.networking.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashSet;
import pkg.networking.main.GamePanel;

public class MessageReceiver implements Runnable {

    private DatagramSocket sock;
    private byte buf[];
    private HashSet<String> existingClients;
    private int numClients = 0;

    public MessageReceiver(DatagramSocket s) {
        sock = s;
        buf = new byte[1024];
        existingClients = new HashSet();

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
                String id = coords[0];
                int tx = Integer.parseInt(coords[1]);
                int ty = Integer.parseInt(coords[2]);

                if (!existingClients.contains(id)) {
                    existingClients.add(id);
                    numClients++;
                }
                
                //Loop through and gather client info
                System.out.println(received);
                GamePanel.netPlayer.x = tx + 60;
                GamePanel.netPlayer.y = ty + 60;

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
