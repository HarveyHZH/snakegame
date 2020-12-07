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

/**
 * <h1>{@code GameLauncher}</h1>
 * <p>This class contains a static method that launches the game.</p>
 * @author Zhihao Huang
 * @since 12-06-2020
 * @version 3.0
 */
public class GameLauncher {
   
   /**
    * Launches the game using the <code>invokeLater</code> method.
    */
   public static void launch() {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new GameWindow();
         }
      });
   }

   /**
    * Where the program is executed.
    * @param args Unused.
    */
   public static void main(String[] args) {
      launch();
   }
}
