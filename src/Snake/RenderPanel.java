/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
Shenendehowa High School
  
     File: $(name).java
     Date: $(date)
     Purpose:
     Author: Yicheng Huang
     Secret Sauce Code: 4

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JPanel;

public class RenderPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
          Snake snake = Snake.snake;
        g.setColor(Color.WHITE);
       
        g.fillRect(0, 0, snake.width, snake.height);
        g.setColor(Color.BLACK);

      
        for (int i = 0; i < snake.width / Snake.scale; i++) {
            g.drawLine(i * Snake.scale, 0, i * Snake.scale, snake.height);
        }
        for (int i = 0; i < snake.height / Snake.scale; i++) {
            g.drawLine(0, i * snake.scale, snake.width, i * snake.scale);

        }
   
        for (Point point : snake.snakeParts) {
                 g.setColor(Color.BLACK);
            g.fillRect(point.x, point.y, Snake.scale, Snake.scale);
            g.setColor(Color.GREEN);
            g.fillRect(point.x+2, point.y+2, Snake.scale-4, Snake.scale-4);
        }
        g.setColor(Color.BLACK);

        g.fillRect(snake.head.x, snake.head.y, Snake.scale, Snake.scale);
           g.setColor(Color.GREEN);
        g.fillRect(snake.head.x+2, snake.head.y+2, Snake.scale-4, Snake.scale-4);
  
          g.setColor(Color.RED);
          g.fillOval(snake.cherry.x, snake.cherry.y, Snake.scale, Snake.scale);
       // g.fillRect(snake.cherry.x, snake.cherry.y, Snake.scale, Snake.scale);
        g.setColor(Color.GREEN);
        g.drawArc(snake.cherry.x+snake.scale/2, snake.cherry.y-snake.scale/4, snake.scale/2, snake.scale/2, 180, -90);

        g.setColor(Color.BLACK);
        String string = "Score: " + snake.score + " Points";
        String string2 = "\n" + "Length: " + snake.taillength;
        g.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        g.drawString(string, 345, 20);
        g.drawString(string2, 365, 40);
        if (snake.over) {
            g.setFont(new Font("Arial", Font.CENTER_BASELINE, 36));
            g.setColor(Color.RED);
            g.drawString("Game Over", 300, 320);
            g.drawString("Press Space To Restart", 200, 360);
        }
    }

}



