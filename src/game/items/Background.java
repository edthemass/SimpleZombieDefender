/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.items;

import game.Eas7DrawObject;
import game.Init;

/**
 *
 * @author eas7.de-play
 */
public class Background extends Eas7DrawObject {

    public Background(Init init) {
        super(init);
        setImage("dirt.png");
    }
    
//    public boolean contains(){
//        return false;
//    }
}
