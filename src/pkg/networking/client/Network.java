package pkg.networking.client;

import java.io.IOException;
import java.net.DatagramSocket;

public abstract class Network {

    protected String hostname;
    protected final int PORT = 4445;
    protected DatagramSocket sock;
   

    public Network() {
        this.hostname = "localhost";
        
    }

    public Network(String hostname) throws IOException {
        this.hostname = hostname;
        
    }
}
