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
    * 
    */
   GamePanel() {
      setPreferredSize(new Dimension(width, height));
      setBackground(Color.black);
   }
}
