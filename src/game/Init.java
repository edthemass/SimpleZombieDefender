/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.items.BulletUnit;
import game.items.EnemyUnit;
import game.items.Background;
import game.items.Magazin;
import game.items.PlayerUnit;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

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

//        gameWorld.update();
//        player.update();

//        for (EnemyUnit i : enemy) {
//            i.update();
//        }
        for (Eas7Drawable i : objects) {
            i.update();
        }

        // BulletUnit update
//        for (int i = 0; i < bullet.size(); i++) {
//            bullet.get(i).update();
//
//            if (bullet.get(i).isUseless()) {
//                bullet.remove(i);
//            }
//        }

//        interf.update();
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

    public void addBullet(Init init, Point2D.Double position, double angle, int playerWidth) {
        this.objects.add(new BulletUnit(init, position, angle, playerWidth));
    }

    public Magazin getMagazin() {
        return this.magazin;
    }

}
