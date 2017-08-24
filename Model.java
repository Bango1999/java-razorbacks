import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class Model
{
    private ArrayList<Sprite> sprites;
    private int width;
    private int height;
    Random r;

    Model(int w, int h) throws IOException {
        sprites = new ArrayList<Sprite>();
        width = w;
        height = h;
        r = new Random();
        initialize();
    }
    
    public ArrayList<Sprite> getSpriteList() { return sprites; }
    
    public void initialize() {
        for (int i=0; i<50; i++) {
            int newX = r.nextInt(width-50);
            int newY = r.nextInt(height-50);
            if (i%2==0) {
                sprites.add(new Razorback(newX, newY, width, height));
            } else {
                sprites.add(new Opponent(newX, newY, width, height));
            }
        }
    }

    public void update(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.update(g);
        }
    }

    public void forward() {
        for (Sprite s : sprites) {
            s.move();
        }
    }
}
