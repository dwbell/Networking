package pkg.networking.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class NetPlayer {

    private BufferedImage img;
    private int x;
    private int y;

    public NetPlayer() {
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
