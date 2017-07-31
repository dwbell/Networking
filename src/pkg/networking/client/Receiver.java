package pkg.networking.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import pkg.networking.main.GamePanel;
import pkg.networking.main.NetPlayer;

public class Receiver extends Network implements Runnable {

    private byte buf[];

    public Receiver(DatagramSocket sock) {
        super();
        this.sock = sock;
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
                String id = coords[0];
                int tx = Integer.parseInt(coords[1]);
                int ty = Integer.parseInt(coords[2]);

                if (!GamePanel.netPlayers.containsKey(id)) {
                    GamePanel.netPlayers.put(id, new NetPlayer());
                }

                //ONLY RECEIVING ONE AT A TIME....
                GamePanel.netPlayers.get(id).setX(tx);
                GamePanel.netPlayers.get(id).setY(ty);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
