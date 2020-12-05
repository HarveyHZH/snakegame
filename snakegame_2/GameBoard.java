/*
 * Copyright (c) 2020, Zhihao Huang, All rights reserved.
 * 
 * 
 * 
 * 
 * 
 * 
 */
package snakegame_2;

import java.awt.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * @author Zhihao Huang
 * @since 12-04-2020
 * @version 2.0
 */
class GameBoard extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = 3933211839401963206L;
   
   /**
    * Width.
    */
   private int width;

   /**
    * Height.
    */
   private int height;

   /**
    * The direction in which the snake is going, with right as its default.
    */
   static Direction direction = Direction.RIGHT;
 
   /**
    * Fixed length of one snake block.
    */
   private final int LENGTH = 20;

   /**
    * Initial snake length.
    */
   private int snakeLength = 2;

   /**
    * x, y coordinates.
    */
   private int[] x, y;

   /**
    * Snake color.
    */
   private Color snakeColor = Color.white;

   /**
    * Apple color.
    */
   private Color appleColor = Color.red;

   /**
    * Color used to hide text until the end.
    */
   private Color endColor = Color.black;

   /**
    * Timer for animation.
    */
   private Timer timer;

   /**
    * Random number for generating apple's location.
    */
   private Random r = new Random();

   /**
    * x, y coordinates of apple.
    */
   private int appleX, appleY;
 
   /**
    * The speed at which the snake moves.
    */
   private int speed = 100;

   /**
    * Constructs a game board.
    * @param preferredSize
    */
   GameBoard(Dimension preferredSize) {
      width = (int) preferredSize.getWidth();
      height = (int) preferredSize.getHeight();
      setPreferredSize(preferredSize);
      setBackground(Color.red);
      setFocusable(false);
      setBackground(Color.black);
      initSnake();
      generateRandom();
   }

   /**
    * Initializes the snake.
    */
   private void initSnake() {
      int rows = (int) height / LENGTH;
      int cols = (int) width / LENGTH;
      int total = rows * cols; // make it sufficiently large
      x = new int[total];
      y = new int[total];
      for(int i = 0; i < snakeLength; i++) {
         x[i] = (snakeLength - i) * LENGTH;
         y[i] = LENGTH;
      }
   }

   /**
    * Generates a random number. 
    */
   private void generateRandom() {
      
      int multiplierX = width / LENGTH;
      int multiplierY = height / LENGTH;
      do {
         appleX = LENGTH * r.nextInt(multiplierX);
         appleY = LENGTH * r.nextInt(multiplierY);
      }while(!appleAcceptable());
   }

   /**
    * Tests if apple's location is acceptable.
    * @return true if location is acceptable and vice versa.
    */
   private boolean appleAcceptable() {
      boolean b = true;
      for(int i = 0; i < snakeLength; i++) {
         if(x[i] == appleX && y[i] == appleY) {
            b = false;
         }
      }
      return b;
   }

   /**
    * Stops the timer.
    */
   void stopTimer() {
      timer.cancel();
   }

   /**
    * Starts the timer.
    */
   void startTimer() {
      timer = new Timer();
      timer.schedule(new Task(), 0, speed);
   }

   /**
    * Inner class that contains the timer task.
    */
   private class Task extends TimerTask {
      @Override
      public void run() {
         if(isColliding()) { // test if snake is colliding.
            snakeColor = Color.black;
            appleColor = Color.black;
            endColor = Color.white;
            appleX = 0;
            appleY = 0;
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
    * @return true if the snake is colliding and vice versa.
    */
    private boolean isColliding() {
      for(int i = snakeLength; i > 0; i--) { 
         if((snakeLength >= 4) && (x[0] == x[i]) && (y[0] == y[i])) { // head runs into body
            return true;
         }
      }
      if(y[0] >= height) { // greater than height
         return true;
      }
      if(y[0] < 0) { // less than 0
         return true;
      }
      if(x[0] >= width) { // greater than width
         return true;
      }
      if(x[0] < 0) { // less than 0;
         return true;
      }
      return false;
   }

   /**
    * Paints the components.
    * @param g graphics
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
    * @param g graphics
    */
   private void drawBorder(Graphics g) {
      g.setColor(Color.white);
      g.drawRect(0, 0, width - 1, height - 1);
   }

   /**
    * Displays the results.
    * @param g graphics
    */
   private void displayResults(Graphics g) {
      g.setColor(endColor);
      g.setFont(new Font("MONOSPACED", Font.BOLD, 50));
      String gameOver = "Game Over!";
      String score = String.format("score: %d", snakeLength - 2);
      FontMetrics fontMetrics = g.getFontMetrics();
      int strWidth = fontMetrics.stringWidth(gameOver);
      int ascent = fontMetrics.getAscent();
      int messageX = width / 2 - strWidth / 2;
      int messageY = height / 3 + ascent / 2;
      g.drawString(gameOver, messageX, messageY); // displays game over
      g.setFont(new Font("MONOSPACED", Font.BOLD, 25));
      fontMetrics = g.getFontMetrics();
      strWidth = fontMetrics.stringWidth(score);
      messageX = width / 2 - strWidth / 2;
      messageY = height / 2 + ascent / 2;
      g.drawString(score, messageX, messageY); // display score
   }

   /**
    * Draws the snake.
    * @param g graphics
    */
   private void drawSnake(Graphics g) {
      g.setColor(snakeColor);
      for(int i = 0; i < snakeLength; i++) {
         g.drawRect(x[i], y[i], LENGTH, LENGTH);
      } 
   }

   /**
    * Draws the apple.
    * @param g graphics
    */
   private void drawApple(Graphics g) {
      g.setColor(appleColor);
      if(replaceApple()) {
         snakeLength++;
         generateRandom();
      }
      g.fillOval(appleX, appleY, LENGTH, LENGTH);
   }

   /**
    * Checks if apple should be replaced or not.
    * @return true if apple's location matches with snake head and vice versa.
    */
   private boolean replaceApple() {
      if(x[0] == appleX && y[0] == appleY) {
         return true;
      }else {
         return false;
      }
   }
}
