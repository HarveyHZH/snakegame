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
import javax.swing.*;

/**
 * 
 * @author Zhihao Huang
 * @since 12-06-2020
 * @version 3.0
 */
class GamePanel extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = -5984978183740182249L;

   private GameBoard board;

   private GameMenu menu;

   private GameSettings settings;
   
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

   private class KeyAction extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent ev) {
         if(menu.isShowing()) {
            if(ev.getKeyCode() == KeyEvent.VK_SPACE) {
               showCard("board");
            }else if(ev.getKeyCode() == KeyEvent.VK_ESCAPE) {
               System.exit(0);
            }
         }else if(board.isShowing()) {
            if(ev.getKeyCode() == KeyEvent.VK_UP || ev.getKeyCode() == KeyEvent.VK_W) {
               board.changeDirection(GameBoard.Direction.UP);
            }else if(ev.getKeyCode() == KeyEvent.VK_DOWN || ev.getKeyCode() == KeyEvent.VK_S) {
               board.changeDirection(GameBoard.Direction.DOWN);
            }else if(ev.getKeyCode() == KeyEvent.VK_LEFT || ev.getKeyCode() == KeyEvent.VK_A) {
               board.changeDirection(GameBoard.Direction.RIGHT);
            }else if(ev.getKeyCode() == KeyEvent.VK_RIGHT || ev.getKeyCode() == KeyEvent.VK_D) {
               board.changeDirection(GameBoard.Direction.RIGHT);
            }
         }else if(settings.isShowing()) {

         }
      }
   }

   void showCard(String card) {
      if(card.equals("menu") || card.equals("settings") || card.equals("board")) {
         if(!card.equals("board")) {
            board.stopTimer();
         }else {
            board.startTimer();
         }
         CardLayout cardLayout = (CardLayout) getLayout();
         cardLayout.show(this, card);
      }else {
         System.err.printf("error: %s is not applicable to showCard(String card).", card);
      }
   }
}
