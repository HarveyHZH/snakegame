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
 * <h1>{@code GameSettings}</h1>
 * <p>This class contains the game settings.</p>
 * @author Zhihao Huang
 * @since 12-06-2020
 * @version 3.0
 */
class GameSettings extends JPanel {
   
   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = -5391364145155397930L;

   /**
    * Game panel.
    */
   private GamePanel panel;

   /**
    * Display.
    */
   private JPanel display;

   /**
    * Creates game settings.
    * @param panel Panel.
    */
   GameSettings(GamePanel panel) {
      this.panel = panel;
      createTopBar();
      createDisplay();
   }

   /**
    * Creates a top bar.
    */
   private void createTopBar() {
      GameTopBar topBar = new GameTopBar(panel, "Settings");
      add(topBar, BorderLayout.PAGE_START);
   }
   
   /**
    * Creates the display area.
    */
   private void createDisplay() {
      display = new Display();
      add(display, BorderLayout.CENTER);
   }

   /**
    * A class that contains the display area of the board.
    */
   private class Display extends JPanel {

      /**
       * Serial version UID.
       */
      private static final long serialVersionUID = 1L;
      
      /**
       * Width inherited from <code>GameWindow</code>
       */
      private final int WIDTH = GameWindow.WIDTH;

      /**
       * Height reduced by 60.
       */
      private final int HEIGHT = GameWindow.HEIGHT - 60;

      /**
       * Options.
       */
      private String[][] option;

      /**
       * Option numbers.
       */
      private int[] optionNumber = {0, 1, 2};

      /**
       * First parts of options.
       */
      private String[] str = {"Snake Color: ", "Apple Color: ", "Difficulty: "};

      /**
       * Color names.
       */
      private String[] colorName = {"<White>", "<Red>", "<Green>", "<Blue>", "<Orange>", "<Pink>", "<Yellow>", "<Magenta>"};
      
      /**
       * Levels.
       */
      private String[] level = {"<Very Easy>", "<Easy>", "<Medium>", "<Difficult>", "<Very Difficult>"};

      /**
       * Bounds.
       */
      private Rectangle[] bound = new Rectangle[3];

      /**
       * Creates the display area.
       */
      Display() {
         setBackground(Color.black);
         setPreferredSize(new Dimension(WIDTH, HEIGHT));
         option = new String[str.length][colorName.length];
         for(int i = 0; i < str.length; i++) { // Join the strings. 
            if(i == 2) {
               for(int j = 0; j < level.length; j++) { 
                  option[i][j] = str[i] + level[j];         
               }
            }else {
               for(int j = 0; j < colorName.length; j++) {
                  option[i][j] = str[i] + colorName[j];
               }
            }
         }
         addMouseListener(new MouseAction());
      }

      /**
       * This class contains the mouse actions.
       */
      private class MouseAction extends MouseAdapter {

         /**
          * Changes the cursor.
          * @param ev Mouse event.
          */
         @Override
         public void mousePressed(MouseEvent ev) {
            Point point = new Point(ev.getX(), ev.getY());
            for(int i = 0; i < bound.length; i++) {
               if(bound[i].contains(point)) {
                  setCursor(new Cursor(Cursor.HAND_CURSOR));
               }
            }
         }

         /**
          * Changes the cursor.
          * @param ev Mouse event.
          */
         @Override
         public void mouseReleased(MouseEvent ev) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         }

         /**
          * Switches to the next option and makes the change.
          * @param ev Mouse event.
          */
         @Override
         public void mouseClicked(MouseEvent ev) {
            Point point = new Point(ev.getX(), ev.getY());
            for(int i = 0; i < bound.length; i++) {
               if(bound[i].contains(point)) {
                  if(i == 2) { // difficulty
                     if(optionNumber[i] == level.length - 1) {
                        optionNumber[i] = 0;
                     }else {
                        optionNumber[i] += 1;
                     }
                     panel.changeDifficulty(optionNumber[2]);
                  }else {
                     if(optionNumber[i] == colorName.length - 1) {
                        optionNumber[i] = 0;
                     }else {
                        optionNumber[i] += 1;
                     }
                     panel.changeSnakeColor(optionNumber[0]);
                     panel.changeAppleColor(optionNumber[1]);
                  }
               }
            }
            repaint();
         }
      }

      /**
       * Paints the components onto the display.
       */
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         drawOption(g);
      }

      /**
       * Draws the options.
       * @param g Graphics.
       */
      private void drawOption(Graphics g) {
         g.setFont(new Font("MONOSPACED", Font.PLAIN, 50));
         g.setColor(Color.lightGray);
         FontMetrics fontMetrics = g.getFontMetrics();
         int strWidth = fontMetrics.stringWidth(option[0][0]); // Use the string width of the first option to align them.
         int x = WIDTH / 2 - strWidth / 2;
         int ascent = fontMetrics.getAscent();
         int deltaY = ascent * 5;
         int[] y = {deltaY, deltaY * 2, deltaY * 3};
         for(int i = 0; i < optionNumber.length; i++) {
            int n = optionNumber[i];
            g.drawString(option[i][n], x, y[i]);
            strWidth = fontMetrics.stringWidth(option[i][n]);
            bound[i] = new Rectangle(x, y[i] - ascent / 5 * 4, strWidth, ascent);
         }
      }
   }
}
