/*
 * Copyright (c) 2020, Zhihao Huang, All rights reserved.
 * 
 * 
 * 
 * 
 * 
 * 
 */
package snakegame;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * the class that contains the game board for the snake game.
 * @author Zhihao Huang
 * @since 12-01-2020
 * @version 1.0
 */
final class GameBoard extends JPanel {

   /**
    * serial version UID.
    */
   private static final long serialVersionUID = -1133615195231152797L;

   /**
    * the direction in which the snake is going, with right as its default.
    */
   static Direction direction = Direction.RIGHT;

   /**
    * screen size.
    */
   static Dimension screenSize;

   /**
    * fixed length of one snake block.
    */
   private final int LENGTH = 20;

   /**
    * initial snake length.
    */
   private int snakeLength = 2;

   /**
    * x, y coordinates.
    */
   private int[] x, y;

   /**
    * snake color.
    */
   private static Color snakeColor = Color.white;

   /**
    * apple color.
    */
   private static Color appleColor = Color.red;

   /**
    * color used to hide text until the end.
    */
   private static Color endColor = Color.black;

   /**
    * timer for animation.
    */
   private Timer timer;

   /**
    * random number for generating apple's location.
    */
   private Random r = new Random();

   /**
    * x, y coordinates of apple.
    */
   private int appleX, appleY;

   private boolean timerIsActive;
   /**
    * constructs a game board.
    */
   GameBoard() {
      screenSize = new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
      (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
      setFocusable(true);
      setBackground(Color.black);
      setPreferredSize(screenSize);
      initSnake();
      generateRandom();
      moveSnake(100);
      addKeyListener(new KeyAction());
   }
   
   boolean getTimerState() {
      return timerIsActive;
   }
   void stopTimer() {
      timer.cancel();
      timerIsActive = false;
   }

   void startTimer(int speed) {
      timer = new Timer();
      timer.schedule(new Task(), 100, speed);
      timerIsActive = true;
   }

   /**
    * generates a random number. 
    */
    private void generateRandom() {
      
      int multiplierX = (int) screenSize.getWidth() / LENGTH;
      int multiplierY = (int) screenSize.getHeight() / LENGTH;
      do {
         appleX = LENGTH * r.nextInt(multiplierX);
         appleY = LENGTH * r.nextInt(multiplierY);
      }while(!appleAcceptable());
   }

   /**
    * test if apple's location is acceptable.
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
    * initializes the snake.
    */
   private void initSnake() {
      int rows = (int) screenSize.getHeight() / LENGTH;
      int cols = (int) screenSize.getWidth() / LENGTH;
      int total = rows * cols;
      x = new int[total];
      y = new int[total];
      for(int i = 0; i < snakeLength; i++) {
         x[i] = (snakeLength - i) * LENGTH;
         y[i] = LENGTH;
      }
   }

   /**
    * checks if the snake is colliding.
    * @return true if the snake is colliding and vice versa.
    */
   private boolean isColliding() {
      for(int i = snakeLength; i > 0; i--) { 
         if((snakeLength >= 4) && (x[0] == x[i]) && (y[0] == y[i])) { // head runs into body
            return true;
         }
      }
      if(y[0] >= screenSize.getHeight()) { // greater than screen height
         return true;
      }
      if(y[0] < 0) { // less than 0
         return true;
      }
      if(x[0] >= screenSize.getWidth()) { // greater than screen width
         return true;
      }
      if(x[0] < 0) { // less than 0;
         return true;
      }
      return false;
   }
   
   /**
    * moves the snake at a given speed.
    * @param speed snake's speed
    */
   private void moveSnake(int speed) {
      startTimer(speed);
      stopTimer();
      startTimer(speed);
   }

   /**
    * inner class that contains the timer task.
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
    * changes the direction.
    * @param d direction
    */
   static void changeDirection(Direction d) {
      direction = d;
   }

   /**
    * paints the components.
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
    * draws the border which is always white.
    * @param g graphics
    */
   private void drawBorder(Graphics g) {
      g.setColor(Color.white);
      g.drawRect(0, 0, (int) screenSize.getWidth() - 1, (int) screenSize.getHeight() - 1);
   }

   /**
    * displays the results.
    * 
    * @param g graphics
    */
   private void displayResults(Graphics g) {
      g.setColor(endColor);
      g.setFont(new Font("MONOSPACED", Font.BOLD, 50));
      String gameOver = "Game Over!";
      String score = String.format("score: %d", snakeLength - 2);
      FontMetrics fontMetrics = g.getFontMetrics();
      int width = fontMetrics.stringWidth(gameOver);
      int ascent = fontMetrics.getAscent();
      int messageX = (int) screenSize.getWidth() / 2 - width / 2;
      int messageY = (int) screenSize.getHeight() / 3 + ascent / 2;
      g.drawString(gameOver, messageX, messageY); // displays game over
      g.setFont(new Font("MONOSPACED", Font.BOLD, 25));
      fontMetrics = g.getFontMetrics();
      width = fontMetrics.stringWidth(score);
      messageX = (int) screenSize.getWidth() / 2 - width / 2;
      messageY = (int) screenSize.getHeight() / 2 + ascent / 2;
      g.drawString(score, messageX, messageY); // display score
   }

   /**
    * draws the snake.
    * @param g graphics
    */
   private void drawSnake(Graphics g) {
      g.setColor(snakeColor);
      for(int i = 0; i < snakeLength; i++) {
         g.drawRect(x[i], y[i], LENGTH, LENGTH);
      } 
   }

   /**
    * draws the apple.
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
    * checks if apple should be replaced or not.
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