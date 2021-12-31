/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
Shenendehowa High School
  
     File: $(name).java
     Date: $(date)
     Purpose:
     Author: Yicheng Huang
     Secret Sauce Code: 4

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
package Snake;

import com.sun.org.apache.xpath.internal.patterns.NodeTest;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {

    public JFrame jframe;

    public static Snake snake;

    public RenderPanel renderPanel;

    public Timer timer = new Timer(1, this);

    public ArrayList<Point> snakeParts = new ArrayList<Point>();
public int height=650, width=800; 
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    public int ticks = 0, direction = DOWN, score, taillength;
    public static int scale = 20;
    public boolean over = false, paused;
    public Point head, cherry;
    public Random random;

    public Snake() {

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(806, 669);
        jframe.setLocationRelativeTo(null);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // jframe.setLocation(dim.height/2- jframe.getWidth()/2 , dim.width/2 - jframe.getHeight()/2);
        startGame();
    }

    public void startGame() {
        over = false;
        paused = false;
        random = new Random();
        score = 0;
        taillength = 5;
        direction = random.nextInt(4);
        snakeParts.clear();
        
        head = new Point(scale * (random.nextInt(width/scale-10)+5), scale * (random.nextInt(height/scale-10)+5));
        
        cherry = new Point(scale * random.nextInt(width/scale), scale * random.nextInt(height/scale));


        for (int i = 0; i < taillength; i++) {
            snakeParts.add(new Point(head.x, head.y));
        }
        timer.start();

    }

    public static void main(String[] args) {
        snake = new Snake();
    }

    public boolean noTailAt(int x, int y) {
        for (Point point : snakeParts) {
            if (point.equals(new Point(x, y))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        renderPanel.repaint();
        ticks++;
        if (ticks % 30 == 0 && head != null && !over && !paused) { //controls how fast snake is moving
            snakeParts.add(new Point(head.x, head.y));
            if (direction == DOWN) {
                if (head.y < renderPanel.getHeight() - scale && !noTailAt(head.x, head.y + scale)) {
                    head = new Point(head.x, head.y + scale);
                } else {
                    over = true;
                }

            }
            if (direction == UP) {
                if (head.y > 0 && !noTailAt(head.x, head.y - scale)) {
                    head = (new Point(head.x, head.y - scale));
                } else {
                    over = true;
                }
            }
            if (direction == LEFT) {
                if (head.x > 0 && !noTailAt(head.x - scale, head.y)) {
                    head = (new Point(head.x - scale, head.y));
                } else {
                    over = true;
                }
            }
            if (direction == RIGHT) {
                if (head.x < renderPanel.getWidth() - scale && !noTailAt(head.x + scale, head.y)) {
                    head = (new Point(head.x + scale, head.y));
                } else {
                    over = true;
                }
            }

           
            if (snakeParts.size() > taillength) {
                snakeParts.remove(0);
            }
            if (cherry != null) {

                if (head.x == cherry.x && head.y == cherry.y) {
                    score += 10;
                    taillength++;
                     cherry.setLocation(scale * random.nextInt(width/scale), scale * random.nextInt(height/scale));
                  
                }
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {
      
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int i = ke.getKeyCode();
        if (i == KeyEvent.VK_UP && direction != DOWN) {
            direction = UP;
        }
        else  if (i == KeyEvent.VK_DOWN && direction != UP) {
            direction = DOWN;
        }
       else if (i == KeyEvent.VK_LEFT && direction != RIGHT) {
            direction = LEFT;
        }
      else  if (i == KeyEvent.VK_RIGHT && direction != LEFT) {
            direction = RIGHT;
        }
        if (i == KeyEvent.VK_SPACE) {
            if (over) {
                startGame();
            } else {
                paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
      
    }
}



