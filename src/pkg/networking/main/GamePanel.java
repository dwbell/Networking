package pkg.networking.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    //Dimensions
    public static final int W = 512;
    public static final int H = 512;
    public static final int SCALE = 1;

    //Game Loop
    private Thread thread;
    private boolean running;
    private final int FPS = 60;
    private final int TARGET_TIME = 1000 / FPS;

    //Render
    private BufferedImage image;
    private Graphics2D g;

    //Players
    public static Player player;
    public static NetworkedPlayer netPlayer;

    public GamePanel() {
        setPreferredSize(new Dimension(W * SCALE, H * SCALE));
        setFocusable(true);
        requestFocus();

        player = new Player();
        netPlayer = new NetworkedPlayer();
    }

    //Ready to display
    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            addKeyListener(this);
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        //Initialize
        running = true;
        image = new BufferedImage(W, H, 1);
        g = (Graphics2D) image.getGraphics();

        //Timer
        long start;
        long elapsed;
        long wait;

        while (running) {
            start = System.nanoTime();

            update();
            render();

            elapsed = System.nanoTime() - start;

            wait = TARGET_TIME - elapsed / 1000000;
            if (wait < 0) {
                wait = TARGET_TIME;
            }

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        Keys.update();
        player.update();
        netPlayer.update();

    }

    public void render() {
        //g
        g.clearRect(0, 0, W, H);
        player.render(g);
        netPlayer.render(g);

        //g2
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, W * SCALE, H * SCALE, null);
        g2.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Keys.keySet(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Keys.keySet(e.getKeyCode(), false);
    }

}
