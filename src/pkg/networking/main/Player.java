package pkg.networking.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Player {

    private BufferedImage img;
    public int direction;
    public int x;
    public int y;
    private int speed;

    public Player() {
        this.direction = 0;
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
            direction = 0;
        }
        if (Keys.isDown(Keys.UP)) {
            this.y -= speed;
            direction = 1;
        }
        if (Keys.isDown(Keys.LEFT)) {
            this.x -= speed;
            direction = 2;
        }
        if (Keys.isDown(Keys.RIGHT)) {
            this.x += speed;
            direction = 3;
        }
    }

    public void render(Graphics g) {
        g.drawImage(img, x, y, 50, 50, null);
    }

}
