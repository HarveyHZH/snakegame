package snakegame;

import java.awt.*;
import javax.swing.*;
public class GamePanel extends JPanel {
   
   private CardLayout cardLayout;

   GamePanel() {
      cardLayout = new CardLayout();
      setLayout(cardLayout);
      add(new GameBoard(), "BOARD");
      add(new GameMenu(), "MENU");
      showMenu();
   }

   void showBoard() {
      cardLayout.show(this, "BOARD");
   }

   void showMenu() {
      cardLayout.show(this, "MENU");
   }
}

