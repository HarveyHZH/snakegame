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
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * <h1>{@code GameMenu}</h1>
 * <p>This class contains the menu for the game.</p>
 * @author Zhihao Huang
 * @since 12-06-2020
 * @version 3.0
 */
class GameMenu extends JPanel {
   
   /**
    * Serial version UID.
    */
   private static final long serialVersionUID = -7360267867103699188L;

   /**
    * The width inherited from the <code>GameWindow</code> class.
    */
   private final int WIDTH = (int) GameWindow.WIDTH;

   /**
    * The height inherited from the <code>GameWindow</code> class.
    */
   private final int HEIGHT = (int) GameWindow.HEIGHT;

   /**
    * The panel that stores different cards.
    */
   private GamePanel panel;

   /**
    * Color of title, start button, settings button, and exit button.
    */
   private Color color[] = {new Color(150, 0, 0), Color.lightGray, Color.lightGray, Color.lightGray};

   /**
    * The bounds of title.
    */
   private Rectangle[] bound = new Rectangle[4];

   /**
    * Timer for changing colors.
    */
   private Timer timer;

   /**
    * Creates a menu.
    */
   GameMenu(GamePanel panel) {
      this.panel = panel;
      setBackground(Color.black);
      addMouseListener(new MouseAction());
      alterColor(20);
   }

   /**
    * Uses a timer to alter the colors.
    * @param speed Speed of timer.
    */
   private void alterColor(int speed) {
      timer = new Timer();
      timer.schedule(new TimerTask() {
         int red = 150; // Sets the range from 0 to 150.
         int green = 0;
         int blue = 0;
         @Override
         public void run() { // Alter colors.
            if(red > 0 && blue == 0) {      
               red--;
               green++;
            }
            if(green > 0 && red == 0) {
               green--;
               blue++;
            }
            if(blue > 0 && green == 0) {
               red++;
               blue--;
            }
            color[0] = new Color(red, green, blue);
            repaint();
         }
      }, 0, speed);
   }

   /**
    * This class contains the mouse actions.
    */
   private class MouseAction extends MouseAdapter {

      /**
       * Changes the colors to white when mouse is pressed.
       * @param ev Mouse event.
       */
      @Override
      public void mousePressed(MouseEvent ev) {
         Point point = ev.getPoint(); 
         for(int i = 1; i < 4; i++) {
            if(bound[i].contains(point)) {
               color[i] = Color.white;
               repaint();
               break;
            }
         }   
      }

      /**
       * Reverts the colors when mouse is released.
       * @param ev Mouse Event.
       */
      @Override
      public void mouseReleased(MouseEvent ev) {
         for(int i = 1; i < 4; i++) {
            color[i] = Color.lightGray;
         }
         repaint();
      }

      /**
       * Shows a different card or exit the program when mouse is clicked.
       * @param ev Mouse event.
       */
      @Override
      public void mouseClicked(MouseEvent ev) {
         Point point = ev.getPoint();
         if(bound[1].contains(point)) { // start
            panel.showCard("board");
         }else if(bound[2].contains(point)) { // settings
            panel.showCard("settings");
         }else if(bound[3].contains(point)) { // exit
            System.exit(0);
         }
      }
   }

   /**
    * Paints the components to the menu.
    * @param g Graphics.
    */
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      placeComponents(g, 250, "Snake", 0);
      placeComponents(g, 50, "<Start>", 1);
      placeComponents(g, 50, "<Settings>", 2);
      placeComponents(g, 50, "<Exit>", 3);
   }

   /**
    * Places the components onto the menu.
    * @param g Graphics.
    */
   private void placeComponents(Graphics g, int fontSize, String str, int rect) {
      g.setColor(color[rect]);
      g.setFont(new Font("MONOSPACED", Font.PLAIN, fontSize));
      FontMetrics fm = g.getFontMetrics();
      int strWidth = fm.stringWidth(str);
      int ascent = fm.getAscent();
      int x = WIDTH / 2 - strWidth / 2;
      int h = HEIGHT / 2;
      int delta = ascent * 2;
      double y[] = {h, h + 1.5 * delta, h + 2.5 * delta, h + 3.5 * delta};
      bound[rect] = new Rectangle(x, (int) y[rect] - ascent / 5 * 4, strWidth, ascent);
      if(rect == 0) { // Title is special.
         g.fillRoundRect(x, (int) y[rect] - ascent / 5 * 4, strWidth, ascent, ascent / 5, ascent / 5);
         g.setColor(Color.black);
      }
      g.drawString(str, x, (int) y[rect]);
   }
}
