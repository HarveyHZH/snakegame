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
 */
class GameWindow extends JFrame {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = 55544047277394877L;

   /**
    * Window Size.
    */
   static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();

   /**
    * Constructs a window.
    */
   GameWindow() {
      setPreferredSize(SIZE);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setAlwaysOnTop(true);
      setUndecorated(true);
      GamePanel panel = new GamePanel();
      add(panel);
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
   }
}
