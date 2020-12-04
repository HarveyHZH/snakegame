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

import javax.swing.*;

/**
 * @author Zhihao Huang
 * @since 12-04-2020
 * @version 2.0
 */
public final class GameLauncher {
   
   /**
    * Empty constructor that is not accessible for other classes.
    */
   GameLauncher() {}
   
   /**
    * Calls the constructor of the Window class using <code>invokeLater</code> for thread safety.
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
    * Executes the program.
    * @param args Unused.
    */
   public static void main(String[] args) {
      launch();
   }
}
