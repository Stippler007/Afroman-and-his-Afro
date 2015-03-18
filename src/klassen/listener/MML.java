/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Christian
 */
public class MML implements MouseMotionListener
{
  public static int x;
  public static int y;
  @Override
  public void mouseDragged(MouseEvent e)
  {
    
  }

  @Override
  public void mouseMoved(MouseEvent e)
  {
    x=e.getX();
    y=e.getY();
  }
}
