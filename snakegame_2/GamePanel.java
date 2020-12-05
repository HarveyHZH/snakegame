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
import javax.swing.*;

/**
 * @author Zhihao Huang
 * @since 12-04-2020
 * @version 2.0
 */
class GamePanel extends JPanel {

   /**
    *
    */
   private static final long serialVersionUID = -6087019656836491039L;
   
   /**
    * Width.
    */
   private int width = GameWindow.windowWidth;

   /**
    * Height.
    */
   private int height = GameWindow.windowHeight - 50;

   /**
    * Game board.
    */
   private GameBoard board;

   /**
    * Game settings.
    */
   private GameSettings settings;

   /**
    * Constructs a panel that contains the board and settings in card layout.
    */
   GamePanel() {
      //setPreferredSize(new Dimension(width, height));
      //setBackground(Color.black);
      setLayout(new CardLayout());
      setFocusable(false);
      board = new GameBoard(new Dimension(width, height));
      settings = new GameSettings(new Dimension(width, height), board);
      add(board, "board");
      add(settings, "settings");
   }

   /**
    * Shows the game board.
    */
   void showBoard() {
      CardLayout cardLayout = (CardLayout) this.getLayout();
      board.startTimer();
      cardLayout.show(this, "board");
   }

   /**
    * Shows the settings.
    */
   void showSettings() {
      CardLayout cardLayout = (CardLayout) this.getLayout();
      board.stopTimer();
      cardLayout.show(this, "settings");
   }
}
