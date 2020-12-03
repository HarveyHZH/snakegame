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

import javax.swing.*;

/**
 * public class that executes the game.
 * @author Zhihao Huang
 * @since 12-02-2020
 * @version 1.0
 */
public final class GameLauncher {
   
   /**
    * empty constructor.
    */
   GameLauncher() {}

   /**
    * a public static method that executes the game.
    */
    public static void launch() {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new GameWindow();
         }
      });
   }
}
