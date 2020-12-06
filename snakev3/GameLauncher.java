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

import javax.swing.*;

public class GameLauncher {
   
   public static void launch() {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new GameWindow();
         }
      });
   }

   public static void main(String[] args) {
      launch();
   }
}
