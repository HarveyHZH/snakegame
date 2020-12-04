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
import java.util.*;
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
   private int width = GameWindow.windowWidth;

   /**
    * Height set to 50.
    */
   private int height = 50;

   /**
    * Settings displayed set to false.
    */
   private boolean settingsDisplayed = false;

   /**
    * Random number.
    */
   private Random random;

   /**
    * Game panel.
    */
   private GamePanel panel;

   /**
    * Constructs a menu.
    */
   GameMenu(GamePanel panel) {
      random = new Random();
      this.panel = panel;
      setPreferredSize(new Dimension(width, height));
      setLayout(null);
      setFocusable(false);
      setBackground(Color.black);
      addExitButton();
      addSettingsButton();
      addTitle();
   }

   /**
    * Adds an exit button to the menu.
    */
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
         public void mousePressed(MouseEvent ev) { // exit program
            System.exit(0); 
         }
         @Override
         public void mouseEntered(MouseEvent ev) { 
            exit.setIcon(exitHoverIcon);
            exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
         }
         @Override
         public void mouseExited(MouseEvent ev) {
            exit.setIcon(exitIcon);
            exit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         }
      });
      add(exit);
   }

   /**
    * Adds a settings button to the menu.
    */
   private void addSettingsButton() {
      JLabel settings = new JLabel();
      ImageIcon settingsIcon = new ImageIcon("img/settings_button.jpg");
      ImageIcon settingsHoverIcon = new ImageIcon("img/settings_button_hover.jpg");
      ImageIcon backIcon = new ImageIcon("img/back_button.jpg");
      ImageIcon backHoverIcon = new ImageIcon("img/back_button_hover.jpg");
      int deltaX = height;
      Rectangle r = new Rectangle(0, 0, deltaX, height);
      settings.setBounds(r); 
      settings.setIcon(settingsIcon);
      settings.setToolTipText("SETTINGS");
      settings.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent ev) {
            if(settingsDisplayed) { // show board
               settings.setIcon(backHoverIcon);
               settings.setToolTipText("BACK");
               panel.showBoard();
            }else { // show settings
               settings.setIcon(settingsHoverIcon);
               settings.setToolTipText("SETTINGS");
               panel.showSettings();
            }
            settingsDisplayed = settingsDisplayed ? false : true; // change condition
         }
         @Override
         public void mouseEntered(MouseEvent ev) {
            settings.setCursor(new Cursor(Cursor.HAND_CURSOR));
            if(settingsDisplayed) {
               settings.setIcon(backHoverIcon);
            }else {
               settings.setIcon(settingsHoverIcon);
            }
         }
         @Override
         public void mouseExited(MouseEvent ev) {
            settings.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            if(settingsDisplayed) {
               settings.setIcon(backIcon);
            }else {
               settings.setIcon(settingsIcon);
            }
         }
      });
      add(settings);
   }

   /**
    * Adds the title "Snake Game".
    */
   private void addTitle() {
      JLabel title = new JLabel();
      Font font = new Font("MONOSPACED", Font.BOLD, 50);
      title.setFont(font);
      title.setForeground(Color.white);
      String str = "Snake"; // title text
      FontMetrics fm = title.getFontMetrics(font);
      int strWidth = fm.stringWidth(str); // get the text width
      int x = width / 2 - strWidth / 2;
      title.setText(str);
      Rectangle r = new Rectangle(x, 0, strWidth, height);
      title.setBounds(r);
      title.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent ev) { // generate a random color.
            title.setCursor(new Cursor(Cursor.HAND_CURSOR));
            title.setForeground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
         }
         @Override
         public void mouseExited(MouseEvent ev) {
            title.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            title.setForeground(Color.white);
         }
      });
      add(title);
   }
}
