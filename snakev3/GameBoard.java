/*
 * Copyright (c) 2020, Zhihao Huang. All rights reserved.
 * 
 * 
 * 
 * 
 * 
 * 
 */
package snakev3;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * <h1>{@code GameBoard}</h1>
 * <p>This class contains the board for the game.</p>
 * @author Zhihao Huang
 * @since 12-06-2020
 * @version 3.0
 */
class GameBoard extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = -7122624654433386765L;
   
   /**
    * Four applicable directions.
    */
   static enum Direction {UP, DOWN, LEFT, RIGHT};

   /**
    * Direction.
    */
   private Direction direction;

   /**
    * Length of a single snake block.
    */
   private final int LENGTH = 20;

   /**
    * SnakeLength.
    */
   private int snakeLength;

   /**
    * The x and y coordinates.
    */
   private int[] x, y;

   /**
    * Colors of the snake, apple, background.
    */
   private Color[] color = {Color.white, Color.red};

   /**
    * Timer for animation.
    */
   private Timer timer;

   /**
    * Random number used to generate apple's location.
    */
   private Random r = new Random();

   /**
    * Apple's location.
    */
   private int[] appleLocation = new int[2];

   /**
    * The speed of the snake.
    */
   private int speed = 100;

   /**
    * Panel with card layout.
    */
   private GamePanel panel;

   /**
    * Display.
    */
   private Display display;

   /**
    * Creates a board.
    * @param panel Panel.
    */
   GameBoard(GamePanel panel) {
      this.panel = panel;
      createTopBar();
      createDisplay();
   }

   /**
    * Creates a top bar.
    */
   private void createTopBar() {
      GameTopBar topBar = new GameTopBar(panel, "Snake");
      add(topBar, BorderLayout.PAGE_START);
   }
   
   /**
    * Creates the display area.
    */
   private void createDisplay() {
      display = new Display();
      add(display, BorderLayout.CENTER);
   }

   /**
    * A class that contains the display area of the board.
    */
   private class Display extends JPanel {

      /**
       * Serial Version UID.
       */
      private static final long serialVersionUID = 1L;
      
      /**
       * Width inherited from <code>GameWindow</code>
       */
      private final int WIDTH = GameWindow.WIDTH;

      /**
       * Height reduced by 3 times the length.
       */
      private final int HEIGHT = GameWindow.HEIGHT - 3 * LENGTH;

      /**
       * If snake collides then end will be true.
       */
      private boolean end = false;

      /**
       * Creates a display area for the game.
       */
      Display() {
         setBackground(Color.black);
         setPreferredSize(new Dimension(WIDTH, HEIGHT));
         initSnake();
         addMouseListener(new MouseAdapter() {

            /**
             * Changes the cursor.
             * @param ev Mouse event.
             */
            @Override
            public void mousePressed(MouseEvent ev) {
               if(end) {
                  setCursor(new Cursor(Cursor.HAND_CURSOR));
               }
            }

            /**
             * Changes the cursor.
             * @param ev Mouse event.
             */
            @Override
            public void mouseReleased(MouseEvent ev) {
               setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            /**
             * Restarts the game only if the game ended.
             * @param ev Mouse event.
             */
            @Override
            public void mouseClicked(MouseEvent ev) {
               if(end) {         
                  restart();
               }
            }
         });
      }

      /**
       * Paints the components onto the display.
       * @param g Graphics.
       */
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         drawBorder(g);
         if(!end) {
            drawSnake(g);
            drawApple(g);
         }else {
            displayResult(g, 100, 0); // Game over.
            displayResult(g, 35, 1); // Score.
            displayResult(g, 35, 2); // Click to restart.
         }
      }

      /**
       * Draws the border which is always white.
       * @param g Graphics.
       */
      private void drawBorder(Graphics g) {
         g.setColor(Color.white);
         g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
      }

      /**
       * Displays a result.
       * @param g Graphics.
       */
      private void displayResult(Graphics g, int font, int n) {
         g.setColor(Color.lightGray);
         g.setFont(new Font("MONOSPACED", Font.PLAIN, font));
         String[] str = {"Game Over!", String.format("Score: %d", snakeLength - 2), "(Click Anyplace to Restart)"};
         FontMetrics fontMetrics = g.getFontMetrics();
         int strWidth = fontMetrics.stringWidth(str[n]);
         int ascent = fontMetrics.getAscent();
         int x1 = WIDTH / 2 - strWidth / 2;
         int oneThird = HEIGHT / 3 + ascent / 2;
         int delta = ascent * 5;
         int[] y1 = {oneThird, oneThird + delta, oneThird + 2 * delta};
         g.drawString(str[n], x1, y1[n]);
      }

      /**
       * Draws the snake.
      * @param g Graphics.
      */
      private void drawSnake(Graphics g) {
         g.setColor(color[0]);
         for(int i = 0; i < snakeLength; i++) {
            g.drawRect(x[i], y[i], LENGTH, LENGTH);
         } 
      }

      /**
       * Draws the apple.
       * @param g Graphics.
       */
      private void drawApple(Graphics g) {
         g.setColor(color[1]);
         if(replaceApple()) {
            snakeLength++;
            generateRandom();
         }
         g.fillOval(appleLocation[0], appleLocation[1], LENGTH, LENGTH);
      }

      /**
       * Checks if apple should be replaced or not.
       * @return True if apple's location matches with snake head and vice versa.
       */
      private boolean replaceApple() {
         if(x[0] == appleLocation[0] && y[0] == appleLocation[1]) {
            return true;
         }else {
            return false;
         }
      }

      /**
       * Generates a random number. 
       */
      private void generateRandom() {
         int multiplierX = WIDTH / LENGTH;
         int multiplierY = HEIGHT / LENGTH;
         do {
            appleLocation[0] = LENGTH * r.nextInt(multiplierX);
            appleLocation[1] = LENGTH * r.nextInt(multiplierY);
         }while(!appleAcceptable());
      }

      /**
       * Tests if apple's location is acceptable.
       * @return True if location is acceptable and vice versa.
       */
      private boolean appleAcceptable() {
         boolean b = true;
         for(int i = 0; i < snakeLength; i++) {
            if(x[i] == appleLocation[0] && y[i] == appleLocation[1]) {
               b = false;
            }
         }
         return b;
      }

      /**
       * Initializes the snake.
       */
      private void initSnake() {
         direction = Direction.RIGHT;
         snakeLength = 2;
         int rows = (int) HEIGHT / LENGTH;
         int cols = (int) WIDTH / LENGTH;
         int total = rows * cols; // make it sufficiently large
         x = new int[total];
         y = new int[total];
         for(int i = 0; i < snakeLength; i++) {
            x[i] = (snakeLength - i) * LENGTH;
            y[i] = LENGTH;
         }
         startAnimation();
         generateRandom();
      }

      /**
       * A class that contains the timer task.
       */
      private class Task extends TimerTask {
         @Override
         public void run() {
            if(isColliding()) { // test if snake is colliding.
               end = true;
               repaint();
               timer.cancel();
            }
            for(int i = snakeLength; i > 0; i--) {
               x[i] = x[i - 1];
               y[i] = y[i - 1];
            }
            switch (direction) {
               case UP:
                  y[0] -= LENGTH;
                  break;
               case DOWN:
                  y[0] += LENGTH;
                  break;
               case LEFT:
                  x[0] -= LENGTH;
                  break;
               case RIGHT:
                  x[0] += LENGTH;
                  break;
               default:
                  break;
            }
            repaint();
         }
      }

      /**
       * Checks if the snake is colliding.
       * @return True if the snake is colliding and vice versa.
       */
      private boolean isColliding() {
         for(int i = snakeLength; i > 0; i--) { 
            if((snakeLength >= 4) && (x[0] == x[i]) && (y[0] == y[i])) { // head runs into body
               return true;
            }
         }
         if(y[0] >= HEIGHT) { // greater than height
            return true;
         }
         if(y[0] < 0) { // less than 0
            return true;
         }
         if(x[0] >= WIDTH) { // greater than width
            return true;
         }
         if(x[0] < 0) { // less than 0;
            return true;
         }
         return false;
      }

      /**
       * Starts the timer.
       */
      private void startAnimation() {
         timer = new Timer();
         timer.schedule(new Task(), 0, speed);
      }

      /**
       * Stops the timer.
       */
      private void stopAnimation() {
         if(timer != null) {
            timer.cancel();
         }
      }
   }

   /**
    * Starts the animation.
    */
   void startAnimation() {
      display.startAnimation();
   }

   /**
    * Stops the animation.
    */
   void stopAnimation() {
      display.stopAnimation();
   }

   /**
    * Allows access to change direction from other classes.
    * @param direction Snake direction.
    */
   void changeDirection(Direction direction) {
      this.direction = direction;
   }

   /**
    * Gets the snake's current direction.
    * @return Direction.
    */
   Direction getDirection() {
      return direction;
   }

   /**
    * Restarts the game.
    */
   void restart() {
      display.end = false;
      display.initSnake();
   }

   /**
    * Changes the color of either snake, apple, or background.
    * @param n Option number.
    */
   void changeColor(int n, Color c) {
      if(n < color.length) {
         color[n] = c;
      }
   }

   /**
    * Changes the difficulty by changing the speed.
    * @param speed Snake's speed.
    */
   void changeSpeed(int speed) {
      this.speed = speed;
   }
}
