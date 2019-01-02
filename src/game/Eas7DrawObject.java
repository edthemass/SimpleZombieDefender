/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 *
 * @author Edgar Strauß
 */
public abstract class Eas7DrawObject implements Eas7Drawable {

    private Init init;
    private Image images;
    private int gameFactor, imageWidth, imageHeight, screensizeWidth, screensizeHeight;
    private Point2D.Double position;
    private double imageAngleRad = 0;
    private AffineTransform oldAT;
    private boolean useless = false;
    private boolean showBox = true;
    private Polygon polygon;

    

    public Eas7DrawObject(Init init) {
        this.init = init;
        this.gameFactor = (int) init.getGameFactor();
        this.screensizeWidth = init.getFrameSize().width;
        this.screensizeHeight = init.getFrameSize().height;
        this.position = new Point2D.Double();
        
        // Polygon hat kein Hülle
        polygon = new Polygon(/*xPoints, yPoints, nPoints*/);
        
    }

    @Override
    public void draw(Graphics2D g2d) {
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
        g2d.drawPolygon(polygon);
         // Polygon-Hülle transparent
        if (showBox) {
            g2d.setColor(new Color(0,0,0,255));
            // Polygon-Hülle  
        } else {
            g2d.setColor(new Color(0,0,0,0));
        }
        g2d.setTransform(oldAT);
    }

    @Override
    public void update() {
        
    }

    public void setImage(String str) {
        this.images = init.getImages().getImg(str);
        this.imageWidth = images.getWidth(null) * gameFactor;
        this.imageHeight = images.getHeight(null) * gameFactor;
        
        int[] xPointsDef = {0, imageWidth,imageWidth,0};
        int[] yPointsDef = {0, 0, imageHeight, imageHeight};
        setPolygon(xPointsDef, yPointsDef, 4);
    }

    public void setStartPosition(int x, int y) {
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

    @Override
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

    public void setPosition(Double x, Double y) {
        this.position.setLocation(x, y);
    }

    public double getDirection() {
        return this.imageAngleRad;
    }

    @Override
    public boolean isUseless() {
        return useless;
    }

    public void setUseless(boolean useless) {
        this.useless = useless;
    }

    public void setPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        polygon.xpoints = xPoints;
        polygon.ypoints = yPoints;
        polygon.npoints = nPoints;
    }

    @Override
    public void showBoundingBox(boolean b) {
        this.showBox = b;
    }

    @Override
    public Polygon getPolygon() {
        return this.polygon;
    }
    
    @Override
    public boolean isContains(Point2D.Double x){
        return this.polygon.contains(x);
    }
    
//    public Rectangle2D setBoundBox(double x, double y, int w, int h){
//        
//                return 
//    }

}
