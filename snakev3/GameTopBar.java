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
import java.awt.event.*;
import javax.swing.*;

/**
 * <h1>{@code GameTopBar}</h1>
 * <p>This class contains the top bar used for switching between the menu, board, and settings.</p>
 * @author Zhihao Huang
 * @since 12-06-2020
 * @version 3.0
 */
class GameTopBar extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = 1719486781735283624L;
   
   /**
    * Width inherited from <code>GameWindow</code>.
    */
   private final int WIDTH = GameWindow.WIDTH;

   /**
    * Height set to 50.
    */
   private final int HEIGHT = 50;

   /**
    * Panel with card layout.
    */
   private GamePanel panel;

   /**
    * Constructs a top bar.
    * @param panel Panel for showing cards.
    */
   GameTopBar(GamePanel panel) {
      this.panel = panel;
      setPreferredSize(new Dimension(WIDTH, HEIGHT));
      setBackground(Color.lightGray);
      setLayout(null);
      addLabel(true);
      addLabel(false);
   }

   /**
    * Adds either an exit label or a menu label.
    * @param isExit True when adding exit label.
    */
   private void addLabel(boolean isExit) {
      JLabel label = new JLabel();
      ImageIcon icon;
      ImageIcon iconHover;
      if(isExit) { // Load exit labels.
         icon = new ImageIcon("icons/exit_button.jpg");
         iconHover = new ImageIcon("icons/exit_button_hover.jpg");
         label.setToolTipText("Exit");
      }else { // Load menu labels.
         icon = new ImageIcon("icons/menu_button.jpg");
         iconHover = new ImageIcon("icons/menu_button_hover.jpg");
         label.setToolTipText("Menu");
      }
      label.setIcon(icon);
      label.addMouseListener(new MouseAdapter() {

         /**
          * Changes the icon when hovered.
          * @param ev Mouse event.
          */
         @Override
         public void mouseEntered(MouseEvent ev) { 
            label.setIcon(iconHover);
         }

         /**
          * Reverts the icon when exited.
          * @param ev Mouse event.
          */
         @Override
         public void mouseExited(MouseEvent ev) { 
            label.setIcon(icon);
         }

         /**
          * Performs actions according to conditions.
          * @param ev Mouse event.
          */
         @Override
         public void mouseClicked(MouseEvent ev) { 
            if(isExit) {
               System.exit(0); 
            }else {
               panel.showCard("menu");
            }
         }

         /**
          * Changes cursor to hand.
          * @param ev Mouse event.
          */
         @Override
         public void mousePressed(MouseEvent ev) {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
         }

         /**
          * Resets cursor to default.
          * @param ev Mouse event.
          */
         @Override
         public void mouseReleased(MouseEvent ev) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         }
      });
      Rectangle r;
      if(isExit) {
         r = new Rectangle(WIDTH - HEIGHT, 0, HEIGHT, HEIGHT);
      }else {
         r = new Rectangle(0, 0, HEIGHT, HEIGHT);
      }
      label.setBounds(r);
      add(label);
   }

   /**
    * Paints components onto the bar.
    * @param g Graphics.
    */
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      drawTitle(g);
   }

   /**
    * Draws the title.
    * @param g Graphics.
    */
   private void drawTitle(Graphics g) {
      g.setColor(Color.black);
      g.setFont(new Font("MONOSPACED", Font.PLAIN, 70));
      FontMetrics fm = g.getFontMetrics();
      String title = "Snake";
      int strWidth = fm.stringWidth(title);
      int ascent = fm.getAscent();
      int x = WIDTH / 2 - strWidth / 2;
      int y = HEIGHT / 2 + ascent / 3;
      g.drawString(title, x, y);
   }
}
