/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.items;

import game.Eas7DrawObject;
import game.Init;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author P01004090
 */
public class Barricade extends Eas7DrawObject{

    boolean ckracked = false;
    
    public Barricade(Init init, Point2D.Double position) {
        super(init);
        setPosition(position.x, position.y);
    }

    @Override
    public void update() {
        if(!ckracked){
            setImage("mauer_1.png");
        } else {
            setImage("mauer_2.png");
        }
    }
    
    
    

}
