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
final class GameWindow extends JFrame {
   
   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = 6373568088258070351L;

   /**
    * Window width.
    */
   static int windowWidth;

   /**
    * Window height.
    */
   static int windowHeight;

   /**
    * Constructs a window.
    */
   GameWindow() {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      windowWidth = (int) screenSize.getWidth() / 2;
      windowHeight = (int) screenSize.getHeight() / 2 + 50;
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setFocusable(false);
      setAlwaysOnTop(true);
      setUndecorated(true);
      setIconImage(new ImageIcon("img/snake_game_icon.jpg").getImage());
      add(new GameMenu(), BorderLayout.PAGE_START);
      add(new GamePanel(), BorderLayout.CENTER);
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
   }
}

