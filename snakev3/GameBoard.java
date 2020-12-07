/*
 * Copyright (c) 2020, Zhihao Huang, All rights reserved.
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
 * 
 * @author Zhihao Huang
 * @since 12-06-2020
 * @version 3.0
 */
class GameBoard extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = -7122624654433386765L;
   
   static enum Direction {UP, DOWN, LEFT, RIGHT};

   private Direction direction = Direction.RIGHT;

   private final int WIDTH = GameWindow.WIDTH;

   private final int HEIGHT = GameWindow.HEIGHT;

   private final int LENGTH = 20;

   private int snakeLength;

   private int[] x, y;

   private Color[] color = {Color.white, Color.red, Color.black, Color.black};

   private Timer timer;

   private Random r = new Random();

   private Point appleLocation;

   private int speed;

   private JPanel display;

   private GamePanel panel;

   /**
    * Creates a board.
    * @param panel Panel.
    */
   GameBoard(GamePanel panel) {
      this.panel = panel;
      JButton b = new JButton();
      createTopBar();
      createDisplay();
   }

   private void createTopBar() {
      GameTopBar topBar = new GameTopBar(panel);
      add(topBar, BorderLayout.PAGE_START);
   }
   
   private void createDisplay() {
      display = new JPanel();
      display.setBackground(Color.black);
      display.setPreferredSize(new Dimension(WIDTH, HEIGHT - 50));
      add(display, BorderLayout.CENTER);
   }

   void changeDirection(Direction direction) {
      this.direction = direction;
   }

   void stopTimer() {
      System.out.println("TImer stoped");
   }

   void startTimer() {
      System.out.println("TImer started");
   }

   void changeColor(int n) {

   }

   void changeDifficulty(int speed) {

   }
}
