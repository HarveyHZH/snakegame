/*
 * Copyright (c) 2020, Zhihao Huang, All rights reserved.
 * 
 * 
 * 
 * 
 * 
 * 
 */
package snakegame;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

/**
 * the class that contains the window for the game board.
 * @author Zhihao Huang
 * @since 12-01-2020
 * @version 1.0
 */
final class GameWindow extends JFrame {

   /**
    * serial version UID.
    */
   private static final long serialVersionUID = 3465274245522474230L;

   /**
    * constructs a window.
    */
   GameWindow() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setFocusable(false);
      setAlwaysOnTop(true);
      setIconImage(new ImageIcon("img/snake_game_icon.jpg").getImage());
      setUndecorated(true);
      GamePanel gp = new GamePanel();
      JMenuBar mb = new JMenuBar();
      JMenuItem mi = new JMenuItem("asdf");
      GameBoard gb = new GameBoard();
      mi.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ev) {
            if(gb.getTimerState()) {
               gb.stopTimer();
            }else {
               gb.startTimer(100);
            }
         }
      });
      mb.add(mi);
      add(mb, BorderLayout.PAGE_START);
      add(gb, BorderLayout.CENTER);
      addMouseListener(new MouseAction());
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
   }

   // !!!!!
   // to be removed.
   // !!!!!
   /**
    * inner class that contains mouse actions.
    */
   private class MouseAction extends MouseAdapter {
      @Deprecated
      public void mouseClicked(MouseEvent ev) {
         if(ev.getClickCount() == 2) {
            System.exit(0);
         }
      }
   }
}
