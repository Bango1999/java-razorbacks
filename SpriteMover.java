
import java.util.ArrayList;
import java.util.Iterator;

public class SpriteMover implements Runnable {
	
	private Controller controller;
	private View view;
	
	SpriteMover(Controller c, View v) {
		controller = c;
		view = v;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			ArrayList<Sprite> sprites = controller.getModel().getSpriteList();
			
			synchronized(sprites) {
				for (Sprite s : sprites) {
		             		s.move();
		         	}
			
			
				for(int i = 0; i < sprites.size(); i++) {
		    		    for(int j = 0; j < sprites.size(); j++) {
		    			 if(sprites.get(i).overlaps(sprites.get(j))) {
		    			     if((sprites.get(i) instanceof Razorback) && (sprites.get(j) instanceof Opponent)) {
		    				sprites.get(j).hit();
		    			     } else if ((sprites.get(i) instanceof Opponent) && (sprites.get(j) instanceof Razorback)) {
		    				 sprites.get(i).hit();
		    			     }
		    			 }
		    		    }
		    		}
			}

				synchronized(sprites) {
				    Iterator<Sprite> iter = sprites.iterator();
		    		 	while (iter.hasNext()) {
				            if (iter.next().shouldRemove()) {
						iter.remove();
					    }
					}
				}
			
			view.repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				System.out.println("Tried to sleep, I'm too wired!");
			}
		}
	}
	
}

