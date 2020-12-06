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

class GamePanel extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = -5984978183740182249L;

   GamePanel() {
      setLayout(new CardLayout());
      GameMenu menu = new GameMenu(this);
      GameSettings settings = new GameSettings(this);
      GameBoard board = new GameBoard(this);
      add(menu, "menu");
      add(settings, "settings");
      add(board, "board");
      showCard("menu");
   }

   void showCard(String card) {
      if(card.equals("menu") || card.equals("settings") || card.equals("board")) {
         CardLayout cardLayout = (CardLayout) getLayout();
         cardLayout.show(this, card);
      }
   }
}
