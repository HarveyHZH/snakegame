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

class GameBoard extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = -7122624654433386765L;
   
   GameBoard(GamePanel panel) {
      setBackground(Color.red);
      addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent ev) {
            panel.showCard("menu");
         }
      });
   }
}
