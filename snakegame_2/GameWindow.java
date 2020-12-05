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
import java.awt.event.*;
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
      setFocusable(true);
      setAlwaysOnTop(true);
      setUndecorated(true);
      setIconImage(new ImageIcon("img/snake_game_icon.jpg").getImage());
      GamePanel panel = new GamePanel();
      panel.showBoard();
      GameMenu menu = new GameMenu(panel);
      add(menu, BorderLayout.PAGE_START);
      add(panel, BorderLayout.CENTER);
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
      addKeyListener(new KeyAction());
      changeLookAndFeel();
   }

   /**
    * Changes direction if desired keys are pressed.
    */
   private class KeyAction extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent ev) {
         if(ev.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
         }else if(ev.getKeyCode() == KeyEvent.VK_UP || ev.getKeyCode() == KeyEvent.VK_W) {
            if(GameBoard.direction != Direction.DOWN) {
               GameBoard.direction = Direction.UP;
            }
         }else if(ev.getKeyCode() == KeyEvent.VK_DOWN || ev.getKeyCode() == KeyEvent.VK_S) {
            if(GameBoard.direction != Direction.UP) {
               GameBoard.direction = (Direction.DOWN);
            }
         }else if(ev.getKeyCode() == KeyEvent.VK_LEFT || ev.getKeyCode() == KeyEvent.VK_A) {
            if(GameBoard.direction != Direction.RIGHT) {
               GameBoard.direction = Direction.LEFT;
            }
         }else if(ev.getKeyCode() == KeyEvent.VK_RIGHT || ev.getKeyCode() == KeyEvent.VK_D) {
            if(GameBoard.direction != Direction.LEFT) {
               GameBoard.direction = Direction.RIGHT;
            }
         }
      }
   }

   /**
    * Changes the look and feel to windows.
    */
   private void changeLookAndFeel() {
      try {
         UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
         e.printStackTrace();
      }
      SwingUtilities.updateComponentTreeUI(this);
   }
}

