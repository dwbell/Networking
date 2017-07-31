package pkg.networking.main;

import javax.swing.JFrame;

public class Game {

    public static void main(String[] args) throws Exception{
        //JFrame
        JFrame window = new JFrame("Networking");
        window.add(new GamePanel());
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
