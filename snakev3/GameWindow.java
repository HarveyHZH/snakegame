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
 * <h1>{@code GameWindow}</h1>
 * <p>This class contains the window for the game.</p>
 * @author Zhihao Huang
 * @since 12-06-2020
 * @version 3.0
 */
class GameWindow extends JFrame {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = 55544047277394877L;

   /**
    * Fixed window size.
    */
   static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();

   /**
    * Window width.
    */
   static final int WIDTH = (int) SIZE.getWidth();

   /**
    * Window height.
    */
   static final int HEIGHT = (int) SIZE.getHeight();
   
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
      setFocusable(false);
   }
}
