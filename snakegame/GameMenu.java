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
import java.awt.event.*;
import javax.swing.*;

/**
 * the class that contains the menu for the snake game.
 * @author Zhihao Huang
 * @since 12-04-2020
 * @version 2.0
 */
final class GameMenu extends JPanel {

   /**
    * serial version UID.
    */
   private static final long serialVersionUID = -3736395722789867586L;
   
   /**
    * screen width.
    */
   private int screenWidth = (int) GameBoard.screenSize.getWidth();
   
   /**
    * screen height;
    */
   private int screenHeight = (int) GameBoard.screenSize.getHeight();

   GameMenu() {
      setBackground(Color.black);
      setPreferredSize(new Dimension(screenWidth, screenHeight));
      setFocusable(false);
   }

   /*@Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.white);
      g.drawRect(0, 0, screenWidth - 1, screenHeight - 1);
   }*/
}
