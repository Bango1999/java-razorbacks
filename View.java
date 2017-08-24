import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.ArrayList;

public class View extends JFrame {
    private Controller controller;

    private class MyPanel extends JPanel {

        MyPanel(Controller c) {
            addMouseListener(c);
        }

        public void paintComponent(Graphics g) {
            controller.update(g);
            revalidate();
        }
    }


    public View(Controller c) throws Exception{
        controller = c;
        setTitle("Assignment 4");
        setSize(1200, 800);
        getContentPane().add(new MyPanel(c));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

