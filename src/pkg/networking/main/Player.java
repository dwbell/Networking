package pkg.networking.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Player {

    private BufferedImage img;
    public int x;
    public int y;
    private int speed;

    public Player() {
        this.x = 10;
        this.y = 10;
        this.speed = 3;
        init();
    }

    private void init() {
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/player.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (Keys.isDown(Keys.DOWN)) {
            this.y += speed;
        }
        if (Keys.isDown(Keys.UP)) {
            this.y -= speed;
        }
        if (Keys.isDown(Keys.LEFT)) {
            this.x -= speed;
        }
        if (Keys.isDown(Keys.RIGHT)) {
            this.x += speed;
        }
        updateNetwork();
    }

    public void render(Graphics g) {
        g.drawImage(img, x, y, 50, 50, null);
    }

    public void updateNetwork() {
        
    }
}
