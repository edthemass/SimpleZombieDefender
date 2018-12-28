/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.items;

import game.Eas7DrawObject;
import game.Init;
import java.awt.Graphics2D;

/**
 *
 * @author P01004090
 */
public class Magazin extends Eas7DrawObject {

    private int max, loaded, reloadDelay = 60, delay;
    private boolean empty = false, reloadProgress = false;

    // TODO Animation LadeDelay, oder reicht Sound????
    public Magazin(Init init, int max) {
        super(init);
        this.max = max;
        // beim start soll das Magazin voll sein
        this.loaded = max;
        setImage("bullet1.png");
    }

    @Override
    public void draw(Graphics2D g2d) {

        int xPos = 0;
        for (int i = 0; i < loaded; i++) {
            g2d.drawImage(
                    getImages(),
                    xPos, // ist null
                    0, // ist null
                    getImageWidth(),
                    getImageHeight(),
                    null
            );
            xPos += getImageWidth();
        }
    }

    @Override
    public void update() {
        delay();
    }

    private void delay() {
        if (reloadProgress) {
            if (delay > 0) {
                delay--;
                // damit beim Nachladevorgang kein Schuß abgegeben werden kann
                empty = true;
            } else {
                reload();
                // damit beim Nachladevorgang kein Schuß abgegeben werden kann
                empty = false;
                reloadProgress = false;
            }
        }
    }

    private void reload() {
        this.empty = false;
        this.loaded = this.max;
    }

    public void fillMag() {
        delay = reloadDelay;
        reloadProgress = true;
        // TODO Nachlade Sound
    }

    public void emptyMag() {
        this.loaded--;
        if (this.loaded < 1) {
            this.empty = true;
        }
    }

    public boolean isEmpty() {
        return empty;
    }
}
