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
class GameSettings extends JPanel {
   
   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = -5391364145155397930L;

   GameSettings(GamePanel panel) {
      setBackground(Color.green);
      addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent ev) {
            panel.showCard("board");
         }
      });
   }
}
