package net.tbs.cmd.assets.gfx;

import java.awt.image.BufferedImage;

//crops spritesheets into separate images

public class ImageSheet {
	private BufferedImage sprite;
	
	public ImageSheet(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sprite.getSubimage(x, y, width, height);
	}
}