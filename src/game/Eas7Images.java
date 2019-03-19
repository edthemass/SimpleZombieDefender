/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author eas7
 */
public final class Eas7Images {

    private File directory;
    private File[] fileList;
    private final String PATH = "src\\game\\images\\";
    private final ArrayList<Eas7ImageObject> source = new ArrayList<>();    
    
    public Eas7Images() {
        try {
            // Bekommt Directory zu gewiesen
            directory = new File(PATH);
            // Alles aus Directory kommt in Array
            fileList = directory.listFiles();
            // Iteriert über Array 
            for (File fileList1 : fileList) {
                // Übergibt alles (Datei-Name, Pfad, Image) in Image-Objekt
                source.add(new Eas7ImageObject(fileList1.getName(), PATH, ImageIO.read(fileList1.getAbsoluteFile())));
            }
        } catch (IOException ex) {
            Logger.getLogger(Eas7Images.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Image getImg(String name){
        int i;
        for(i = 0; i < source.size(); i++){
            if(source.get(i).getNameFile().equals(name)){
                break;
            }
        }
        return source.get(i).getImage();
    }
}
