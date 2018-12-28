package game.items;

import game.Eas7DrawObject;
import game.Eas7Drawable;
import game.Init;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author P01004090
 */
public class EnemyUnit extends Eas7DrawObject {

    
    private Init init;

    public EnemyUnit(Init init) {
        super(init);
        setImage("player_1.png");
        Random ran = new Random();
        // TODO y-Startposition random machen
        setStartPosition(ran.nextInt(getScreensizeWidth() - getImageWidth()), -20);
    }

    @Override
    public void update() {
        
        setPosition(getPosition().x,getPosition().y += 0.5 * getGameFactor());
    }
}
