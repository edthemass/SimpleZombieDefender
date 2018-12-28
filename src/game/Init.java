/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.items.Background;
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
 * @author p01004090
 */
public class Init {

    private final double gameFactor;
    private final Dimension frameSize;
    private final Eas7Fonts fonts;
    private final Eas7Canvas canvas;
    private final Eas7Images images;
    private Magazin magazin;
    private ArrayList<Eas7Drawable> objects = new ArrayList<>();

    // TODO MAUER/BARRIKADE
    // TODO BULLETS KILL ENEMYS
    // TODO MAUER HAT LEBENSBALKEN
    // TODO ENEMYS WERDEN GENERIERT ERSCHEINEN
    // TODO ENEMYS GREIFEN MAUER AN
    // TODO ENEMYS TÖTEN SPIELER
    // TODO INTERFACE KILLS
    public Init(Eas7Canvas c, Eas7Images i, double gameFactor, Dimension frameSize) {
        this.fonts = new Eas7Fonts("OldGameFatty.ttf");
        this.canvas = c;
        this.images = i;
        this.gameFactor = gameFactor;
        this.frameSize = frameSize;

//        interf = new GameInterface(this);
        objects.add(new Background(this));
        objects.add(new EnemyUnit(this));
        objects.add(new PlayerUnit(this));
        objects.add(magazin = new Magazin(this, 10));
    }

    public void draw(Graphics2D g2d) {

//        gameWorld.draw(g2d);
        for (Eas7Drawable i : objects) {
            i.draw(g2d);
            // Remove klausel nach usless Flag eingeschaltet
        }

//        interf.draw(g2d);
    }

    public void update() {
        // Alle Objekte mit Status "useless" werden aus liste gelöscht
        try {
            for (Eas7Drawable i : objects) {
                i.update();
                if (i.isUseless()) {
                    objects.remove(i);
//                  Damit exception nicht auftritt!!!
                    break;
                }
            }
        } catch (ConcurrentModificationException e) {
            System.err.println(e + "\nkann den Fehler abfangen aber nicht logisch im Code lösen");
        }
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

}
