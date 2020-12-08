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
    * Width inherited from <code>GameWindow</code>.
    */
   private final int WIDTH = GameWindow.WIDTH;

   /**
    * Height inherited from <code>GameWindow</code>.
    */
   private final int HEIGHT = GameWindow.HEIGHT;

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
    * Colors of the snake, apple, background, and end.
    */
   private Color[] color = {Color.white, Color.red, Color.black, Color.black};

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
      System.out.println(HEIGHT);
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
       * Height reduced by 62.
       */
      private final int HEIGHT = GameWindow.HEIGHT - 62;

      /**
       * Creates a display area for the game.
       */
      Display() {
         setBackground(Color.black);
         setPreferredSize(new Dimension(WIDTH, HEIGHT));
         initSnake();
         generateRandom();
      }

      /**
       * Paints the components onto the display.
       * @param g Graphics.
       */
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         displayResults(g);
         drawSnake(g);
         drawApple(g);
         drawBorder(g);
      }

      /**
       * Draws the border which is always white.
       * @param g Graphics.
       */
      private void drawBorder(Graphics g) {
         g.setColor(Color.white);
         g.drawRect(0, 0, WIDTH - 1, HEIGHT);
      }

      /**
       * Displays the results.
       * @param g Graphics.
       */
      private void displayResults(Graphics g) {
         g.setColor(color[3]);
         g.setFont(new Font("MONOSPACED", Font.BOLD, 50));
         String gameOver = "Game Over!";
         String score = String.format("score: %d", snakeLength - 2);
         FontMetrics fontMetrics = g.getFontMetrics();
         int strWidth = fontMetrics.stringWidth(gameOver);
         int ascent = fontMetrics.getAscent();
         int messageX = WIDTH / 2 - strWidth / 2;
         int messageY = HEIGHT / 3 + ascent / 2;
         g.drawString(gameOver, messageX, messageY); // displays game over
         g.setFont(new Font("MONOSPACED", Font.BOLD, 25));
         fontMetrics = g.getFontMetrics();
         strWidth = fontMetrics.stringWidth(score);
         messageX = WIDTH / 2 - strWidth / 2;
         messageY = HEIGHT / 2 + ascent / 2;
         g.drawString(score, messageX, messageY); // display score
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
      }

      /**
       * A class that contains the timer task.
       */
      private class Task extends TimerTask {
         @Override
         public void run() {
            if(isColliding()) { // test if snake is colliding.
               color[0] = color[2];
               color[1] = color[2];
               color[3] = Color.white;
               appleLocation[0] = 0;
               appleLocation[1] = 0;
               for(int i = 0; i < x.length; i++) {
                  x[i] = 0;
               }
               for(int i = 0; i < y.length; i++) {
                  y[i] = 0;
               }
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
      void startAnimation() {
         timer = new Timer();
         timer.schedule(new Task(), 0, speed);
      }

      /**
       * Stops the timer.
       */
      void stopAnimation() {
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
      display.initSnake();
   }

   /**
    * Changes the color of either snake, apple, or background.
    * @param n Option number.
    */
   void changeColor(int n, Color c) {
      color[n] = c;
   }

   /**
    * Changes the difficulty by changing the speed.
    * @param speed Snake's speed.
    */
   void changeDifficulty(int speed) {

   }
}
