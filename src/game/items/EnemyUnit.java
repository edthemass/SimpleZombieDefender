package game.items;

import game.Eas7DrawObject;
import game.Eas7Drawable;
import game.Init;
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

    private boolean blocked = false;

    public EnemyUnit(Init init) {
        super(init);
        setImage("player_1.png");
        Random ran = new Random();
        // TODO y-Startposition random machen
        setStartPosition(ran.nextInt(getScreensizeWidth() - getImageWidth()), -20);
    }

    // TODO Probleme bei Mauer-Stop
    @Override
    public void update() {

        for (Eas7Drawable object : getInit().getObjects()) {

        }
        
        if (blocked) {
            setPosition(getPosition().x, getPosition().y);
        } else {
            setPosition(getPosition().x, getPosition().y += 0.5 * getGameFactor());
        }
    }

//    public void check(){
//        
//    }
}
