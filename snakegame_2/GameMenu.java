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
class GameMenu extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = -3457012334500469624L;
    
   /**
    * Width.
    */
   private int width;

   /**
    * Height set to 50.
    */
   private int height = 50;

   GameMenu() {
      width = (int) GameWindow.size.getWidth();
      setPreferredSize(new Dimension(width, height));
      setLayout(null);
      setBackground(Color.black);
      addExitButton();
      addSettingsButton();
      addTitle();
   }

   private void addExitButton() {
      JLabel exit = new JLabel();
      exit.setToolTipText("EXIT");
      ImageIcon exitIcon = new ImageIcon("img/exit_button.jpg");
      ImageIcon exitHoverIcon = new ImageIcon("img/exit_button_hover.jpg");
      int deltaX = height;
      Rectangle r = new Rectangle(width - deltaX, 0, width, height);
      exit.setBounds(r);
      exit.setIcon(exitIcon);
      exit.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent ev) {
            System.exit(0);
         }
         @Override
         public void mouseEntered(MouseEvent ev) {
            exit.setIcon(exitHoverIcon);
         }
         @Override
         public void mouseExited(MouseEvent ev) {
            exit.setIcon(exitIcon);
         }
      });
      add(exit);
   }

   private void addSettingsButton() {
      JLabel settings = new JLabel();
      settings.setToolTipText("SETTINGS");
      ImageIcon settingsIcon = new ImageIcon("img/settings_button.jpg");
      ImageIcon settingsHoverIcon = new ImageIcon("img/settings_button_hover.jpg");
      int deltaX = height;
      Rectangle r = new Rectangle(0, 0, deltaX, height);
      settings.setBounds(r);
      settings.setIcon(settingsIcon);
      settings.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent ev) {

         }
         @Override
         public void mouseEntered(MouseEvent ev) {
            settings.setIcon(settingsHoverIcon);
         }
         @Override
         public void mouseExited(MouseEvent ev) {
            settings.setIcon(settingsIcon);
         }
      });
      add(settings);
   }

   private void addTitle() {
      JLabel title = new JLabel();
      Font font = new Font("MONOSPACED", Font.BOLD, 50);
      title.setFont(font);
      title.setForeground(Color.white);
      String str = "Snake Game";
      FontMetrics fm = title.getFontMetrics(font);
      int strWidth = fm.stringWidth(str);
      int x = width / 2 - strWidth / 2;
      title.setText(str);
      Rectangle r = new Rectangle(x, 0, strWidth, height);
      title.setBounds(r);
      add(title);
   }
}
