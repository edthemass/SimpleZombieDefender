/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

/**
 *
 * @author edthemass
 */
public class Eas7Canvas extends JComponent implements Runnable {

    private static final long MAX_LOOP_TIME = 16;// 1 SEK / 60  = FPS
    private final Thread thread;
    private Init init;

    public Eas7Canvas(double gameFactor, Dimension frameSize) {
        // TODO DIMENSION IN IMAGES ÜBERGEBEN
        init = new Init(this, new Eas7Images(), gameFactor, frameSize);
        thread = new Thread(this);
        // Wahrscheinlich mit einer Abfrage abfangen
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        init.draw(g2d);
    }

    public void update() {
        init.update();
    }

    @Override
    public void run() {
        long newTimestamp, oldTimestamp;
        while (true) {
            oldTimestamp = System.currentTimeMillis();
            update();
            newTimestamp = System.currentTimeMillis();
            if (newTimestamp - oldTimestamp > MAX_LOOP_TIME) {
                System.out.println("late");
                continue;
            }
            render();
            newTimestamp = System.currentTimeMillis();
            // wenn zeit unterschritten wird thread wartet
            if (newTimestamp - oldTimestamp <= MAX_LOOP_TIME) {
                try {
                    Thread.sleep(MAX_LOOP_TIME - (newTimestamp - oldTimestamp));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void render() {
        repaint();
    }

    public Init getInit() {
        return init;
    } 
}
