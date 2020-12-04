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
class GameBoard extends JPanel {

   /**
    *
    */
   private static final long serialVersionUID = 3933211839401963206L;
   
   GameBoard(Dimension preferredSize) {
      setPreferredSize(preferredSize);
      setBackground(Color.red);
   }
}
