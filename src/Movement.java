import java.awt.event.*;
import javax.swing.*;

public class Movement {
    public static void Start() {
        JFrame frame = new JFrame("Dungeon Crawler");
        JLabel label = new JLabel("Use W/A/S/D to move", SwingConstants.CENTER);
        
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(label);
        frame.setUndecorated(true);
        frame.setVisible(true);

        frame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w' -> System.out.println('w');
                    case 'a' -> System.out.println('a');
                    case 's' -> System.out.println('s');
                    case 'd' -> System.out.println('d');
                }
            }

            public void keyReleased(KeyEvent e) {}
        });
    }
}

