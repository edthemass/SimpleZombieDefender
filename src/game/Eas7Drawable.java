/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Graphics2D;

/**
 *
 * @author P01004090
 */
public interface Eas7Drawable {
    public void draw(Graphics2D g2d);
    public void update();
    public boolean isUseless();
}
