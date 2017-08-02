package pkg.networking.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import pkg.networking.main.GamePanel;
import pkg.networking.main.NetPlayer;

public class Receiver implements Runnable {

    private byte buf[];
    private final int BUFFER = 256;
    private DatagramSocket sock;

    public Receiver(DatagramSocket sock) {
        super();
        this.sock = sock;
        buf = new byte[BUFFER];
    }

    @Override
    public void run() {
        while (true) {
            try {
                //Receive a packet
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                sock.receive(packet);

                //Receive string and dissect it id:x:y
                String received = new String(packet.getData(), 0, packet.getLength());
                received = received.trim();
                String[] coords = received.split(":");
                String id = coords[0];
                int dir = Integer.parseInt(coords[1]);
                int tx = Integer.parseInt(coords[2]);
                int ty = Integer.parseInt(coords[3]);

                //Only add if it's new, checked by address and port
                if (!GamePanel.netPlayers.containsKey(id)) {
                    GamePanel.netPlayers.put(id, new NetPlayer(dir,tx,ty));
                }

                //Set respected id to new x,y position
                GamePanel.netPlayers.get(id).setDir(dir);
                GamePanel.netPlayers.get(id).setX(tx);
                GamePanel.netPlayers.get(id).setY(ty);

                System.out.println(dir + ":" + tx + "," + ty);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
