
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.util.Random;

abstract class Sprite
{
    private int x;
    private int y;
    private int size;
    private int w;
    private int h;
    private int xSlope;
    private int ySlope;
    private int afterHit;
    private Boolean isHit;
    private Image image;
    private static Random rand;

    public Sprite(int xIn, int yIn, int width, int height, String imagePath, int imageSize) {
        if (rand == null) {
            rand = new Random();
        }
        size = imageSize;
        setImage(imagePath);
        x = xIn;
        y = yIn;
        w = width;
        h = height;
        afterHit = 0;
        isHit = false;
        xSlope = rand.nextInt(11) - 5;
        ySlope = rand.nextInt(11) - 5;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSize() { return size; }
    public void setSize(int s) { size = s; }
    public void setX(int xIn) { x = xIn; }
    public void setY(int yIn) { y = yIn; }
    
    public void setImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ioe) {
            System.out.println("Unable to load image file.");
        }
    }
    public Image getImage() { return image; }
    
    public boolean overlaps(Sprite s) {
    	
    	//Get corners of current sprite
    	
    	
    	//do some bounds checking
    	if ((x <= s.x && s.x <= x + size) && (y <= s.y && s.y <= y + size))
    		return true;
    	else if ((s.x <= x && x <= s.x + size) && (s.y <= y && y <= s.y + s.size))
    		return true;
    	else if ((x <= s.x && s.x <= x + size) && (s.y <= y && y <= s.y + s.size))
    		return true;
    	else if ((s.x <= x && x <= s.x + size) && (y <= s.y && s.y <= y + size))
    		return true;
    	else
    		return false;
    }
    
    public void hit() {
    	isHit = true;
    	setImage("gravestone.jpg");
    	xSlope = ySlope = 0;
    }
    
    public void update(Graphics g) {
    	if (isHit)
    		afterHit++;
        g.drawImage(getImage(), x, y, getSize(), getSize(), null);
    }
    
    public boolean shouldRemove() {
    	if (afterHit >= 20)
    		return true;
    	else
    		return false;
    }
    
    public void move() {
        // Move the Sprite
        int x = getX() + xSlope;
        int y = getY() + ySlope;
        if (x < 0) x = w;
        if (x > w) x = 0;
        if (y < 0) y = h;
        if (y > h) y = 0;
        setX(x);
        setY(y);
    }

}
