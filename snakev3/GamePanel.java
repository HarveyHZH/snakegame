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
import javax.swing.*;

/**
 * <h1>{@code GamePanel}</h1>
 * <p>This class contains the menu, board, and settings for the game.</p>
 * @author Zhihao Huang
 * @since 12-06-2020
 * @version 3.0
 */
class GamePanel extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = -5984978183740182249L;

   /**
    * Board.
    */
   private GameBoard board;

   /**
    * Menu.
    */
   private GameMenu menu;

   /**
    * Settings.
    */
   private GameSettings settings;

   /**
    * Will be true once board is shown once.
    */
   private boolean boardShown = false;

   /**
    * Colors for the snake and apple.
    */
   private Color[] color = {Color.white, Color.red, Color.green, Color.blue, Color.orange, Color.pink, Color.yellow, Color.magenta};
   
   /**
    * Creates a panel with card layout.
    */
   GamePanel() {
      setLayout(new CardLayout());
      menu = new GameMenu(this);
      settings = new GameSettings(this);
      board = new GameBoard(this);
      this.setFocusable(true);
      this.addKeyListener(new KeyAction());
      add(menu, "menu");
      add(settings, "settings");
      add(board, "board");
      showCard("menu");
   }

   /**
    * This class contains the key actions.
    */
   private class KeyAction extends KeyAdapter {

      /**
       * Performs different actions according to conditions.
       * @param ev Key event.
       */
      @Override
      public void keyPressed(KeyEvent ev) {
         if(ev.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
         } 
         if(menu.isShowing()) { // Only works when menu is shown.
            if(ev.getKeyCode() == KeyEvent.VK_SPACE) {
               showCard("board");
            }
         }else if(board.isShowing()) { // Only works when board is shown.
            if(ev.getKeyCode() == KeyEvent.VK_UP || ev.getKeyCode() == KeyEvent.VK_W) {
               if(board.getDirection() != GameBoard.Direction.DOWN) {
                  board.changeDirection(GameBoard.Direction.UP);
               }
            }else if(ev.getKeyCode() == KeyEvent.VK_DOWN || ev.getKeyCode() == KeyEvent.VK_S) {
               if(board.getDirection() != GameBoard.Direction.UP) {
                  board.changeDirection(GameBoard.Direction.DOWN);
               }
            }else if(ev.getKeyCode() == KeyEvent.VK_LEFT || ev.getKeyCode() == KeyEvent.VK_A) {
               if(board.getDirection() != GameBoard.Direction.RIGHT) {
                  board.changeDirection(GameBoard.Direction.LEFT);
               }
            }else if(ev.getKeyCode() == KeyEvent.VK_RIGHT || ev.getKeyCode() == KeyEvent.VK_D) {
               if(board.getDirection() != GameBoard.Direction.LEFT) {
                  board.changeDirection(GameBoard.Direction.RIGHT);
               }
            }
         }
      }
   }

   /**
    * Shows the designated card.
    * @param card Name of card.
    */
   void showCard(String card) {
      if(card.equals("menu") || card.equals("settings") || card.equals("board")) {
         if(!card.equals("board")) {
            board.stopAnimation();
         }else {
            boardShown = true;
            board.startAnimation();
         }
         CardLayout cardLayout = (CardLayout) getLayout();
         cardLayout.show(this, card);
      }else {
         System.err.printf("error: %s is not applicable to showCard(String card).", card);
      }
   }

   /**
    * Test if game is active.
    * @return True if game is active and vice versa.
    */
   boolean gameActive() {
      return boardShown;
   }

   /**
    * Changes the snake color according to the option number.
    * @param n Option.
    */
   void changeSnakeColor(int n) {
      board.changeColor(0, color[n]);
   }

   /**
    * Changes the apple color according to the option number.
    * @param n Option.
    */
   void changeAppleColor(int n) {
      board.changeColor(1, color[n]);
   }

   /**
    * Changes the difficulty according to the level.
    * @param level Level.
    */
   void changeDifficulty(int level) {
      int[] speed = {300, 200, 100, 50, 25};
      board.changeSpeed(speed[level]);
   }
}

