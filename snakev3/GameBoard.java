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
class GameBoard extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = -7122624654433386765L;
   
   /**
    * Creates a board.
    * @param panel
    */
   GameBoard(GamePanel panel) {
      setBackground(Color.red);
      addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent ev) {
            panel.showCard("menu");
         }
      });
   }

   void stopTimer() {
      System.out.println("TImer stoped");
   }

   void startTimer() {
      System.out.println("TImer started");
   }
}
