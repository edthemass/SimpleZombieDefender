/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.items.Background;
import game.items.Barricade;
import game.items.BulletUnit;
import game.items.EnemyUnit;
import game.items.Magazin;
import game.items.PlayerUnit;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 *
 * @author eas7
 */
public class Init {

    private final double gameFactor;
    private boolean showAllBoxes = true;
    private final Dimension frameSize;
    private final Eas7Fonts fonts;
    private final Eas7Canvas canvas;
    private final Eas7Images images;
    private Magazin magazin;
    private ArrayList<Eas7Drawable> objects = new ArrayList<>();

    // TODO BULLETS KILL ENEMYS
    // TODO MAUER HAT LEBENSBALKEN
    // TODO ENEMYS GREIFEN MAUER AN
    // TODO ENEMYS TÖTEN SPIELER
    // TODO INTERFACE KILLS
    
    public Init(Eas7Canvas c, Eas7Images i, double gameFactor, Dimension frameSize) {
        this.fonts = new Eas7Fonts("OldGameFatty.ttf");
        this.canvas = c;
        this.images = i;
        this.gameFactor = gameFactor;
        this.frameSize = frameSize;

        objects.add(new Background(this));
        objects.add(new EnemyUnit(this));
        objects.add(new PlayerUnit(this));
        objects.add(magazin = new Magazin(this, 10));

        // baue Mauer
        Point2D.Double posBaricade = new Point2D.Double(10, getFrameSize().height - (80 * getGameFactor()));
        for (int y = 0; y < 2; y++) {
            System.err.println(getGameFactor());
            posBaricade.x = 10 * getGameFactor();
            posBaricade.y += 16 * getGameFactor();
            for (int x = 0; x < 29; x++) {
                objects.add(new Barricade(this, posBaricade));
                posBaricade.x += 16 * getGameFactor();
            }
        }
    }

    public void draw(Graphics2D g2d) {
        for (Eas7Drawable i : objects) {
            i.draw(g2d);
        }
    }

    public void update() {
        // Alle Objekte mit Status "useless" werden aus liste gelöscht
        try {
            for (Eas7Drawable i : objects) {
                i.update();
                if (i.isUseless()) {
                    objects.remove(i);
                    // Damit exception nicht auftritt.. einfach ein break machen!!!
                    break;
                }
                // Zeige Bounding-Boxes
                if (showAllBoxes) {
                    i.showBoundingBox(true);
                } else {
                    i.showBoundingBox(false);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.err.println(e);
        }
    }

    public void showAllBoxes(boolean b) {
        showAllBoxes = b;
    }

    public Eas7Fonts getFonts() {
        return fonts;
    }

    public Eas7Canvas getCanvas() {
        return canvas;
    }

    public Eas7Images getImages() {
        return images;
    }

    public int getGameFactor() {
        return (int) gameFactor;
    }

    public Dimension getFrameSize() {
        return frameSize;
    }

    public void addBullet(Init init, Point2D.Double position, double angle) {
        this.objects.add(new BulletUnit(init, position, angle));
    }

    public Magazin getMagazin() {
        return this.magazin;
    }

    public ArrayList<Eas7Drawable> getObjects() {
        return objects;
    }
}
