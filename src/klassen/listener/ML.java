package klassen.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import klassen.Inventory.InventoryThings;

public class ML implements MouseListener
{
  private InventoryThings iv;
  
  public ML(InventoryThings iv)
  {
      this.iv = iv;
  }
  
  @Override
  public void mouseClicked(MouseEvent e)
  {
    
  }
  @Override
  public void mousePressed(MouseEvent e)
  {
     iv.chooseThings();
  }
  @Override
  public void mouseReleased(MouseEvent e)
  {
    
  }
  @Override
  public void mouseEntered(MouseEvent e)
  {
    
  }
  @Override
  public void mouseExited(MouseEvent e)
  {
    
  }
  
}