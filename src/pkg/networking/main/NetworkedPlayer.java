package pkg.networking.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class NetworkedPlayer {

    private BufferedImage img;
    public int x;
    public int y;

    public NetworkedPlayer() {
        this.x = 50;
        this.y = 50;
        init();
    }

    private void init() {
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/net_player.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        
    }

    public void render(Graphics g) {
        g.drawImage(img, x, y, 50, 50, null);
    }

}
