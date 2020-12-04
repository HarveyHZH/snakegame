   package snakegame;
   import java.awt.event.*;
   /**
    * interface that contains the key actions.
    */
    class KeyAction extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent ev) {
         if(ev.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
         }else if(ev.getKeyCode() == KeyEvent.VK_UP || ev.getKeyCode() == KeyEvent.VK_W) {
            if(GameBoard.direction != Direction.DOWN) {
               GameBoard.changeDirection(Direction.UP);
            }
         }else if(ev.getKeyCode() == KeyEvent.VK_DOWN || ev.getKeyCode() == KeyEvent.VK_S) {
            if(GameBoard.direction != Direction.UP) {
               GameBoard.changeDirection(Direction.DOWN);
            }
         }else if(ev.getKeyCode() == KeyEvent.VK_LEFT || ev.getKeyCode() == KeyEvent.VK_A) {
            if(GameBoard.direction != Direction.RIGHT) {
               GameBoard.changeDirection(Direction.LEFT);
            }
         }else if(ev.getKeyCode() == KeyEvent.VK_RIGHT || ev.getKeyCode() == KeyEvent.VK_D) {
            if(GameBoard.direction != Direction.LEFT) {
               GameBoard.changeDirection(Direction.RIGHT);
            }
         }
      }
   }