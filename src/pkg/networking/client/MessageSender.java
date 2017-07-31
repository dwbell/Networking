package pkg.networking.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import pkg.networking.main.*;

public class MessageSender implements Runnable {

    public final static int PORT = 4445;
    private DatagramSocket sock;
    private String hostname;

    public MessageSender(DatagramSocket s, String h) {
        sock = s;
        hostname = h;
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

    @Override
    public void run() {
        boolean connected = false;
        do {
            try {
                sendMessage("GREETINGS");
                connected = true;
            } catch (Exception e) {

            }
        } while (!connected);

        while (true) {
            try {
                //May need to be fixed
                Thread.sleep(10);
                String nx = Integer.toString(GamePanel.player.x);
                String ny = Integer.toString(GamePanel.player.y);
                String send = nx + ":" + ny;
                System.out.println(send);
                sendMessage(send);

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
