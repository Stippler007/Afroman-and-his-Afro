package klassen.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import klassen.GUI;

public class MML implements MouseMotionListener
{
  public static int x;
  public static int y;
  private GUI g;
  
  public MML(GUI g)
  {
      this.g = g;
  }
  
  @Override
  public void mouseDragged(MouseEvent e)
  {
    
  }

  @Override
  public void mouseMoved(MouseEvent e)
  {
    x=(int)(e.getX()/g.getScaleX());
    y=(int)(e.getY()/g.getScaleY());
    
  }
}
