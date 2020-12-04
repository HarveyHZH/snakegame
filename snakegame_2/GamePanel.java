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
   
   GamePanel() {
      setPreferredSize(new Dimension((int) GameWindow.size.getWidth(), (int) GameWindow.size.getHeight() - 50));
      setBackground(Color.black);
   }

}
