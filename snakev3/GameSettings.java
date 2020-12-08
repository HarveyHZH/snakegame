/*
 * Copyright (c) 2020, Zhihao Huang. All rights reserved.
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

   private final int WIDTH = GameWindow.WIDTH;

   private final int HEIGHT = GameWindow.HEIGHT;

   private GamePanel panel;

   private JPanel display;

   GameSettings(GamePanel panel) {
      this.panel = panel;
      createTopBar();
      createDisplay();
   }

   private void createTopBar() {
      GameTopBar topBar = new GameTopBar(panel, "Settings");
      add(topBar, BorderLayout.PAGE_START);
   }
   
   private void createDisplay() {
      display = new JPanel();
      display.setBackground(Color.black);
      display.setPreferredSize(new Dimension(WIDTH, HEIGHT - 50));
      add(display, BorderLayout.CENTER);
   }
}
