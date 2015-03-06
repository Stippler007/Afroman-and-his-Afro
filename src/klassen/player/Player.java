/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.player;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import klassen.listener.KL;

/**
 *
 * @author Christian
 */
public class Player
{
  private float x;
  private float y;
  
  private float realoadTime=0;
  private float maxRealoadTime=0.3f;
  
  private int speed;
  private LinkedList<PlayerSpritzer> playerSpritzers;
  private Rectangle bounding;

  public Player(float x, float y, int speed, LinkedList<PlayerSpritzer> playerSpritzers)
  {
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.playerSpritzers = playerSpritzers;
    this.bounding=new Rectangle((int)x,(int)y,25,25);
  }
  public void update(float tslf)
  {
    if(KL.keys[KeyEvent.VK_W])
    {
      y-=speed*tslf;
    }
    if(KL.keys[KeyEvent.VK_S])
    {
      y+=speed*tslf;
    }
    if(KL.keys[KeyEvent.VK_A])
    {
      x-=speed*tslf;
    }
    if(KL.keys[KeyEvent.VK_D])
    {
      x+=speed*tslf;
    }
    if(x>800)x=-bounding.width;
    else if(x<-bounding.width)x=800;
    if(y>600)y=-bounding.height;
    else if(y<-bounding.height)y=600;
    
    
    if(realoadTime>maxRealoadTime)
    {
      if(KL.keys[KeyEvent.VK_UP])
      {
        playerSpritzers.add(new PlayerSpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0, -speed*3));
        realoadTime-=maxRealoadTime;
      }
      else if(KL.keys[KeyEvent.VK_DOWN])
      {
        playerSpritzers.add(new PlayerSpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0, speed*3));
        realoadTime-=maxRealoadTime;
      }
      else if(KL.keys[KeyEvent.VK_LEFT])
      {
        playerSpritzers.add(new PlayerSpritzer(x+bounding.width/2-7, y+bounding.width/2-7, -speed*3, 0));
        realoadTime-=maxRealoadTime;
      }
      else if(KL.keys[KeyEvent.VK_RIGHT])
      {
        playerSpritzers.add(new PlayerSpritzer(x+bounding.width/2-7, y+bounding.width/2-7, speed*3, 0));
        realoadTime-=maxRealoadTime;
      }
    }
    else if(realoadTime<=maxRealoadTime)
    {
      realoadTime+=tslf;
    }
    
    bounding.x=(int)x;
    bounding.y=(int)y;
  }
  public void rebound(Rectangle rect)
  {
    if(bounding.intersects(rect))
    {
      int nachrechts=(int)(rect.x+rect.width)-bounding.x;
      int nachlinks=(int)(bounding.x+bounding.width)-rect.x;
      int nachunten=(int)(rect.y+rect.height)-bounding.y;
      int nachoben=(int)(bounding.y+bounding.height)-rect.y;
      
      if(nachrechts<nachlinks&&nachrechts<nachoben&&nachrechts<nachunten)
      {
        x+=nachrechts;
      }
      else if(nachlinks<nachoben&&nachlinks<nachunten)
      {
        x-=nachlinks;
      }
      else if(nachoben<nachunten)
      {
        y-=nachoben;
      }
      else if(nachoben>nachunten)
      {
        y+=nachunten;
      }
    }
  }
  public Rectangle getBounding()
  {
    return bounding;
  }
  public float getX()
  {
    return x;
  }
  public float getY()
  {
    return y;
  }
}
