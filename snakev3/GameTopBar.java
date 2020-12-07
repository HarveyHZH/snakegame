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
class GameTopBar extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = 1719486781735283624L;
   
   private final int WIDTH = GameWindow.WIDTH;

   private final int HEIGHT = 50;

   GamePanel panel;

   GameTopBar(GamePanel panel) {
      this.panel = panel;
      setPreferredSize(new Dimension(WIDTH, HEIGHT));
      setBackground(Color.white);
      addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent ev) {
            panel.showCard("menu");
         }
      });
   }
}
