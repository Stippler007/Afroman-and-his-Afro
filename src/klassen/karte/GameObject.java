/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.karte;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Christian
 */
public abstract class GameObject
{
  
  protected boolean solid;
  protected boolean onFire;
  protected Rectangle bounding;
  
  
  public GameObject()
  {
    bounding=new Rectangle(0,0,25,25);
  }
  public void update(float tslf,float x,float y)
  {
    bounding.x=(int)x;
    bounding.y=(int)y;
  }
  
  public abstract BufferedImage getLook();
  
  public boolean isSolid()
  {
    return solid;
  }
  public Rectangle getBounding()
  {
    return bounding;
  }
}
