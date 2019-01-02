/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.items;

import game.Eas7DrawObject;
import game.Init;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.KeyStroke;

/**
 *
 * @author P01004090
 */
public class PlayerUnit extends Eas7DrawObject implements MouseMotionListener, KeyListener, MouseListener {

    boolean left, right, shoot;
    private double directionX, directionY;
    private double imageAngleRad = 0;

    public PlayerUnit(Init init) {
        super(init);
        setImage("player_1.png");
        setStartPosition((getScreensizeWidth() / 2) - (getImageWidth() / 2),
                getScreensizeHeight() - getImageHeight());

        // Polygonhülle bestimmen
        // von Links-Oben
        int xPoints[] = {0, getImageWidth(),
            getImageWidth(), 0};
        int yPoints[] = {7 * getGameFactor(), 7 * getGameFactor(),
            getImageHeight(), getImageHeight()};
        int nPoints = 4;
        // Polygonhülle setzten
        setPolygon(xPoints, yPoints, nPoints);
        //
        getInit().getCanvas().addMouseMotionListener(this);
        getInit().getCanvas().addMouseListener(this);
        getInit().getCanvas().addKeyListener(this);
        // TODO ??? weiss nicht warum das nur mit dem Funktioniert und was F2 actionName bedeutet
        getInit().getCanvas().getInputMap().put(KeyStroke.getKeyStroke("F2"), "actionName");
    }

    @Override
    public void update() {
        // Bewegeung strafe(Seitwärts)
        // mit Begrenzung (10 * GameFactor) damit Player nicht an den Bildschirmrand stößt
        if ((left) && (getPosition().x > 0 + (10 * getGameFactor()))) {
            getPosition().x -= 2 * getGameFactor();
        }
        if ((right) && (getPosition().x < getScreensizeWidth() - (getImageWidth()) - (10 * getGameFactor()))) {
            getPosition().x += 2 * getGameFactor();
        }
        setImageAngleRad(imageAngleRad);
    }

    private void shoot() {
        getInit().addBullet(getInit(), getPosition(), imageAngleRad - 1.5);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // die Mittelpunkt(Peilungsposition) von bild zu Maus
        directionX = e.getX() - (getImageWidth() / 2) - getPosition().x;
        directionY = e.getY() - (getImageHeight() / 2) - getPosition().y;
        imageAngleRad = Math.atan2(directionY, directionX) + 1.5;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        // TODO Space-Taste = fun-Taste, irgend ein witziger sound
        if ((e.getKeyCode() == KeyEvent.VK_SPACE)) {
            System.err.println("Leertaste");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            getInit().showAllBoxes(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_N) {
            getInit().showAllBoxes(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Schießen
        if (e.getButton() == 1) {
            if (!getInit().getMagazin().isEmpty()) {
                shoot();
                getInit().getMagazin().emptyMag();
            }

        }
        // Nachladen
        if (e.getButton() == 3) {
            getInit().getMagazin().fillMag();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
