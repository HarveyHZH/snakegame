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

class GameMenu extends JPanel {
   
   /**
    *
    */
   private static final long serialVersionUID = -7360267867103699188L;

   GameMenu(GamePanel panel) {
      setBackground(Color.blue);
      addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent ev) {
            panel.showCard("settings");
         }
      });
   }
}
