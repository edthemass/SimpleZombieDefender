/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.items;

import game.Eas7DrawObject;
import game.Eas7Drawable;
import game.Init;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * @author P01004090
 */
public class BulletUnit extends Eas7DrawObject {

    private boolean useless = false;
    private int lifetime = 100;

    public BulletUnit(Init init, Point2D.Double position, double angle) {
        super(init);
        setImage("bullet.png");
        setPosition(position.x, position.y);
        setImageAngleRad(angle);
    }

    @Override
    public void update() {
        //position.y -= 1 * gameFactor;

        setPosition(getPosition().x += (getGameFactor() * 5) * cos(getDirection()),
                    getPosition().y += (getGameFactor() * 5) * sin(getDirection())
        );

        if (lifetime < 0) {
            useless = true;
        }
        lifetime--;
    }

    public boolean isUseless() {
        return useless;
    }

}
