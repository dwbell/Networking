package pkg.networking.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread {

    public final int PORT = 4445;
    private final int BUFFER = 256;

    private DatagramSocket socket;
    private HashMap<String, ClientInfo> clients;

    public Server() throws IOException {
        socket = new DatagramSocket(PORT);
        clients = new HashMap();
    }

    public void run() {
        byte[] buf = new byte[BUFFER];
        while (true) {
            try {
                //Receive message from client
                Arrays.fill(buf, (byte) 0);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String content = new String(buf, buf.length);

                //Pull temporary values for conditional checks
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                String id = clientAddress.toString() + "," + clientPort;

                //Ensure we are only adding new clients
                if (!clients.containsKey(id)) {
                    clients.put(id, new ClientInfo(clientAddress, clientPort));
                }

                //Send out to all OTHER clients
                content = id + ":" + content;
                byte[] data = (content).getBytes();
                for (ClientInfo ci : clients.values()) {
                    if (ci.getPort() != clientPort) {
                        System.out.println(ci.getAddress() + ":" + ci.getPort());
                        packet = new DatagramPacket(data, data.length, ci.getAddress(), ci.getPort());
                        socket.send(packet);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws Exception {
        Server s = new Server();
        s.start();
    }
}
