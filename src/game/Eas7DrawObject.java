/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 *
 * @author Edgar Strauß
 */
public abstract class Eas7DrawObject implements Eas7Drawable{
    
    private Init init;
    private Image images;
    private int gameFactor, imageWidth, imageHeight, screensizeWidth, screensizeHeight;
    private Point2D.Double position;
    private double directionX, directionY;
    private double imageAngleRad = 0;
    private AffineTransform oldAT;
    private boolean useless = false;

    public Eas7DrawObject(Init init) {
        this.init = init;
        this.gameFactor = (int) init.getGameFactor();
        this.screensizeWidth = init.getFrameSize().width;
        this.screensizeHeight = init.getFrameSize().height;   
        this.position = new Point2D.Double();
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        int cx = imageWidth / 2;
        int cy = imageHeight / 2;
        oldAT = g2d.getTransform();
        g2d.translate(cx+position.x, cy+position.y);
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

    @Override
    public void update() {}
    
    public void setImage(String str){
        this.images = init.getImages().getImg(str);
        this.imageWidth = images.getWidth(null) * gameFactor;
        this.imageHeight = images.getHeight(null) * gameFactor;
    }
    
    public void setStartPosition(int x, int y){
        this.position.x = x;
        this.position.y = y;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getScreensizeWidth() {
        return screensizeWidth;
    }

    public int getScreensizeHeight() {
        return screensizeHeight;
    }

    public Init getInit() {
        return init;
    }

    public Point2D.Double getPosition() {
        return position;
    }

    public int getGameFactor() {
        return gameFactor;
    }

    public void setImageAngleRad(double imageAngleRad) {
        this.imageAngleRad = imageAngleRad;
    }

    public Image getImages() {
        return images;
    }
}
