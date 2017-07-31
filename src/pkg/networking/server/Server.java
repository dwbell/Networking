package pkg.networking.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread {

    public final static int PORT = 4445;
    private final static int BUFFER = 1024;

    private DatagramSocket socket;
    private ArrayList<InetAddress> clientAddresses;
    private ArrayList<Integer> clientPorts;
    private HashSet<String> existingClients;

    public Server() throws IOException {
        socket = new DatagramSocket(PORT);
        clientAddresses = new ArrayList();
        clientPorts = new ArrayList();
        existingClients = new HashSet();
    }

    //1) Recieves a message from a SINGLE client
    //2) Takes the message and forwards it to ALL OTHER clients
    public void run() {
        byte[] buf = new byte[BUFFER];
        while (true) {
            try {
                Arrays.fill(buf, (byte) 0);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String content = new String(buf, buf.length);

                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                String id = clientAddress.toString() + "," + clientPort;
                if (!existingClients.contains(id)) {
                    existingClients.add(id);
                    clientPorts.add(clientPort);
                    clientAddresses.add(clientAddress);
                }

                content = id + ":" + content;
                byte[] data = (content).getBytes();
                for (int i = 0; i < clientAddresses.size(); i++) {
                    if (clientPort != clientPorts.get(i)) {
                        InetAddress cl = clientAddresses.get(i);
                        int cp = clientPorts.get(i);
                        packet = new DatagramPacket(data, data.length, cl, cp);
                        System.out.println(cl + ":" + cp);
                        socket.send(packet);
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public static void main(String args[]) throws Exception {
        Server s = new Server();
        s.start();
    }
}
