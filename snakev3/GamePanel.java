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
   GamePanel() {
      setLayout(new CardLayout());
      GameMenu menu = new GameMenu(this);
      GameSettings settings = new GameSettings(this);
      board = new GameBoard(this);
      add(menu, "menu");
      add(settings, "settings");
      add(board, "board");
      showCard("menu");
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
