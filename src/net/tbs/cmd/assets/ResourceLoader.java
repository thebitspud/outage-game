package net.tbs.cmd.assets;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

//the class that allows loading resources from within a jar file

public class ResourceLoader {
    public BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ResourceLoader.class.getResource("/textures/" + path + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
    }
    
    //loading the game display icons

    private ArrayList<Image> icons;
    
    public void loadIcons(JFrame f) { 	
    	icons = new ArrayList<Image>();

    	icons.add(loadImage("icons/icon 16x"));
    	icons.add(loadImage("icons/icon 32x"));
    	icons.add(loadImage("icons/icon 64x"));
    	
    	f.setIconImages(icons);
    }
}