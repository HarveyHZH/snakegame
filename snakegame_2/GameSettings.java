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
class GameSettings extends JPanel {

   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = 7675202987170119697L;
   
   /**
    * Game board.
    */
   private GameBoard board;

   /**
    * Color names.
    */
   private String[] colorNames = {"white", "red", "green", "blue", "orange", "pink", "yellow", "magenta"};
   /**
    * Some color options.
    */
   private Color[] colors = {Color.white, Color.red, Color.green, Color.blue, Color.orange, Color.pink, Color.yellow, Color.magenta};

   /**
    * Font.
    */
   private Font font = new Font("MONOSPACED", Font.BOLD, 25);

   /**
    * Constructs a settings panel.
    * @param preferredSize size.
    */
   GameSettings(Dimension preferredSize, GameBoard board) {
      setLayout(new GridLayout(0, 2));
      setPreferredSize(preferredSize);
      setBackground(Color.white);
      setFocusable(false);
      this.board = board;
      addPlaceHolder();
      addColorChanger("Snake Color:", true);
      addColorChanger("Apple Color:", false);
      addDifficultyChanger();
      addPlaceHolder();
   }

/**
    * Adds an empty label to create space.
    */
   private void addPlaceHolder() {
      JLabel l1 = new JLabel();
      JLabel l2 = new JLabel();
      add(l1);
      add(l2);
   }

   /**
    * Add the option to change the colors of snake and apple.
    * 
    * @param name snake color or apple color.
    * @param isSnake true if changing snake's color and vise versa.
    */
   private void addColorChanger(String name, boolean isSnake) {
      JLabel label = new JLabel(name);
      label.setFont(font);
      add(label);
      JComboBox<String> box = new JComboBox<>(colorNames);
      box.setFont(font);
      box.setSelectedIndex(0); // white
      box.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ev) {
            if(isSnake) {
               board.changeSnakeColor(colors[box.getSelectedIndex()]);
            }else {
               board.changeAppleColor(colors[box.getSelectedIndex()]);
            }
         }
      });
      add(box);
   }

   /**
    * Adds the option to change the difficulties.
    */
   private void addDifficultyChanger() {
      JLabel label = new JLabel("Difficulty");
      label.setFont(font);
      add(label);
      String[] dif = {"extremely easy", "easy", "medium", "difficult", "extremely difficult"};
      int[] speeds = {300, 200, 100, 50, 25};
      JComboBox<String> box = new JComboBox<>(dif);
      box.setFont(font);
      box.setSelectedIndex(2); // medium
      box.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ev) {
            board.changeDifficulty(speeds[box.getSelectedIndex()]);
         }
      });
      add(box);
   }
}
