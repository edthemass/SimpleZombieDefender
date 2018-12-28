/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.items;

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
public class BulletUnit implements Eas7Drawable{

    private Init init;
    private Image images;
    private String imageName = "bullet.png";
    private int imageHeight, imageWidth, gameFactor, screensizeWidth, screensizeHeight, lifetime = 100;
    private double imageAngleRad;
    private AffineTransform oldAT;
    private Point2D.Double position;
    private boolean useless = false;

    public BulletUnit(Init init, Point2D.Double position, double angle, int playerWidth) {
        this.init = init;
        images = init.getImages().getImg(imageName);
        this.imageAngleRad = angle; 
        this.gameFactor = (int) init.getGameFactor();
        this.imageWidth = images.getWidth(null) * gameFactor;
        this.imageHeight = (images.getHeight(null)) * gameFactor;
        this.screensizeWidth = init.getFrameSize().width;
        this.screensizeHeight = init.getFrameSize().height;
        this.position = new Point2D.Double(position.x + (playerWidth - imageHeight), position.y);
    }

    @Override
    public void update() {
        //position.y -= 1 * gameFactor;

        position.x += (gameFactor * 5) * cos(imageAngleRad);
        position.y += (gameFactor * 5) * sin(imageAngleRad);
        
        if(lifetime < 0){
            useless = true;   
        }
        lifetime --;
        
    }

    @Override
    public void draw(Graphics2D g2d) {
        // TODO drehe Image zu Mausposition
        int cx = imageWidth / 2;
        int cy = imageHeight / 2;
        oldAT = g2d.getTransform();
        g2d.translate(cx + position.x, cy + position.y);
        g2d.rotate(imageAngleRad);
        g2d.translate(-cx, -cy);
        g2d.drawImage(
                images,
                0, // ist null
                0, // ist null
                imageWidth,
                imageHeight,
                null
        );
        // Hülle
        g2d.drawRect(
                0,
                0,
                imageWidth,
                imageHeight
        );
        g2d.setTransform(oldAT);

    }

    public boolean isUseless() {
        return useless;
    }

    
}
