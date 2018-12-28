package game.items;

import game.Eas7Drawable;
import game.Init;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author P01004090
 */
public class EnemyUnit implements Eas7Drawable{

    private Init init;
    private Image images;
    private int gameFactor, imageWidth, imageHeight, screensizeWidth, screensizeHeight;
    private String imageName = "player_2.png";
    private Point2D.Double position;

    public EnemyUnit(Init init) {
        
        images = init.getImages().getImg(imageName);
        this.init = init;
        this.gameFactor = (int) init.getGameFactor();
        this.imageWidth = images.getWidth(null) * gameFactor;
        this.imageHeight = images.getHeight(null) * gameFactor;
        this.screensizeWidth = init.getFrameSize().width;
        this.screensizeHeight = init.getFrameSize().height;
        this.position = new Point2D.Double();
        
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                images,
                (int)position.x, // ist null
                (int)position.y, // ist null
                imageWidth,
                imageHeight,
                null
        );
        g2d.drawRect(
                (int)position.x,
                (int)position.y,
                imageWidth,
                imageHeight
        );
    }

    @Override
    public void update() {
        position.y += 0.5 * gameFactor;
    }
}
