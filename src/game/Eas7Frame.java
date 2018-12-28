/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author eas7.de-play
 */
public class Eas7Frame {
    
    private JFrame frame;
    private Dimension frameSize;
    
    public Eas7Frame() {
        // GAME ROOT PANEL SIZE
        // 1920 / 4 = 480
        // 1080 / 4 = 270
        
        // TODO Funktion die Bildschirmgrösse ermittelt
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setFrameSize();
                
        frame.setSize(frameSize);
        
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //Kein Rahmen
        frame.setUndecorated(true);
        //frame.setLocation(200, 300);
        frame.setResizable(false);
        
        // TODO nicht frameSize übereben sondern Factor
        frame.add(new Eas7Canvas(getGameFrameFactor(), frameSize));
        frame.setVisible(true);    
    }
    
    private double getGameFrameFactor(){
        return (frameSize.getWidth() / 480);
    }
    
    private void setFrameSize(){
        // Für Systemen mit mehrere Monitoren
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        
        // zum Testen von eigenen Auflösungen
        frameSize = new Dimension(480 , 270);
        
        // richtet nach Monitor
//        frameSize = new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
    }
    
}
