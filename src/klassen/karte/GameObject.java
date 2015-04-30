/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.karte;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author Christian
 */
public abstract class GameObject implements Serializable
{
  
  protected boolean solid;
  protected boolean onFire;
  protected Rectangle bounding;
  
  protected float maxLive=-1;
  protected float live=-1;
  
  {
    bounding=new Rectangle(0,0,25,25);
  }
  
  public GameObject(float maxLive,float live)
  {
    this.maxLive=maxLive;
    this.live=live;
  }
  
  public GameObject()
  {
    
  }

  public void setLive(float live)
  {
    this.live = live;
  }
  
  
  
  public void update(float tslf,float x,float y)
  {
    bounding.x=(int)x;
    bounding.y=(int)y;
  }
  
  public abstract BufferedImage getLook();

  public float getLive()
  {
    return live;
  }
  
  public boolean isSolid()
  {
    return solid;
  }
  public Rectangle getBounding()
  {
    return bounding;
  }
}
