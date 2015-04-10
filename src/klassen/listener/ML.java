package klassen.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import klassen.Inventory.InventoryThings;
import klassen.afro.Afro;

public class ML implements MouseListener
{
  private InventoryThings ivd;
  private LinkedList<Afro> afros;
  private float scaleX = 1, scaleY = 1;
  
  public ML(InventoryThings ivd, LinkedList<Afro> afros)
  {
      this.ivd = ivd;
      this.afros = afros;
  }
  
  @Override
  public void mouseClicked(MouseEvent e)
  {
    
  }
  @Override
  public void mousePressed(MouseEvent e)
  {
    ivd.chooseThings();
    
    for(Afro a : afros)
    {
        if(a.isClicked(e.getX()/scaleX, e.getY()/scaleY)) ;
    }
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
  
  public void setScale(float x, float y)
  {
      scaleX = x;
      scaleY = y;
  }
  
}