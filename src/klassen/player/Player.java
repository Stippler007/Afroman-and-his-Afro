/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.player;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import klassen.Background;
import klassen.enemys.EnemySpritzer;
import klassen.listener.KL;

/**
 *
 * @author Christian
 */
public class Player
{
  private float x;
  private float y;
  
  public static float speedX;
  public static float speedY;
  
  private float maxLive = 100;
  private float live = maxLive;
  
  private float realoadTime=0;
  private float maxRealoadTime=0.3f;
  
  private int speed;
  private LinkedList<PlayerSpritzer> playerSpritzers;
  private LinkedList<EnemySpritzer> enemySpritzer;
  private Rectangle bounding;
  
  private boolean move=false;
  
  public Player(float x, float y, int speed, LinkedList<PlayerSpritzer> playerSpritzers, LinkedList<EnemySpritzer> enemySpritzer)
  {
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.playerSpritzers = playerSpritzers;
    this.bounding=new Rectangle((int)x,(int)y,25,25);
    this.enemySpritzer = enemySpritzer;
  }
  public void update(float tslf)
  {
    move(tslf);
    
    if(x>800-bounding.width)x=800-bounding.width;
    else if(x<0)x=0;
    if(y>600-bounding.height)y=600-bounding.height;
    else if(y<0)y=0;
    
    
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
    
    if(collideEnemySpritzer())
    {
        live -= 5;
    }
  }
  private void move(float tslf)
  {
    speedX=0;
    speedY=0;
    
    if(KL.keys[KeyEvent.VK_A])
    {
      speedX=speed*tslf;
      move=true;
    }
    if(KL.keys[KeyEvent.VK_D])
    {
      speedX=-speed*tslf;
      move=true;
    }
    if(KL.keys[KeyEvent.VK_W])
    {
      speedY=speed*tslf;
      move=true;
    }
    if(KL.keys[KeyEvent.VK_S])
    {
      speedY=-speed*tslf;
      move=true;
    }
    
    if(Background.x+speedX<0)
    {
      x+=speedX;
      speedX=0;
    }
  }
  private boolean collideEnemySpritzer()
  {
    int i=0;
    while(i<enemySpritzer.size())
    {
      Rectangle help1=enemySpritzer.get(i).getBounding();
      Rectangle help2=bounding;
      if(help1.intersects(help2))
      {
        enemySpritzer.remove(i);
        return true;
      }
      else
      {
        i++;
      }
    }
    return false;
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
        speedX+=nachrechts;
      }
      else if(nachlinks<nachoben&&nachlinks<nachunten)
      {
        speedX-=nachlinks;
      }
      else if(nachoben<nachunten)
      {
        speedY-=nachoben;
      }
      else if(nachoben>nachunten)
      {
        speedY+=nachunten;
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
  public float getLive()
  {
      return live;
  }
  public float getMaxLive()
  {
      return maxLive;
  }
}
