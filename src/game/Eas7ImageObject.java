/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Image;

/**
 *
 * @author P01004090
 */
public class Eas7ImageObject {
    
    private Image image;
    private String namePath, nameFile;

    public Eas7ImageObject(String name, String path, Image image) {
        this.nameFile = name;
        this.namePath = path;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getNamePath() {
        return namePath;
    }

    public void setNamePath(String namePath) {
        this.namePath = namePath;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
    
    
}
