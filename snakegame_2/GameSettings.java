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
class GameSettings extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = 7675202987170119697L;
   
   /**
    * Constructs a settings panel.
    * @param preferredSize
    */
   GameSettings(Dimension preferredSize) {
      setPreferredSize(preferredSize);
      setBackground(Color.black);
      setFocusable(false);
      //test
      JButton b = new JButton("chnge");
      //b.addActionListener(ev -> );
   }
}
