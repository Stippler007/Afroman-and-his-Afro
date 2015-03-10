/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.enemys;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;

/**
 *
 * @author Christian
 */
public abstract class Enemy
{
  protected float x;
  protected float y;
  
  protected float live=100;
  protected float maxLive=100;
  
  protected float speedX;
  protected float speedY;
  
  protected int speed;
  protected LinkedList<PlayerSpritzer> playerSpritzers;
  protected Player player;
  protected Rectangle bounding;
  
  private float knockbackX;
  private float knockbackY;
  private float backKnockback=600;
  private boolean knockback=false;
  
  protected Color color;
  
  public Enemy(float x, float y, int speed, LinkedList<PlayerSpritzer> playerSpritzers,Player player, Rectangle bounding)
  {
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.playerSpritzers = playerSpritzers;
    this.bounding = bounding;
    this.player=player;
  }

  public void setColor(Color color)
  {
    this.color = color;
  }

  public void setKnockback(boolean knockback)
  {
    this.knockback = knockback;
  }
  
  public void update(float tslf)
  {
    x+=speedX*tslf;
    y+=speedY*tslf;
    
    x+=Player.speedX;
    y+=Player.speedY;
    
    
    if(knockback)moveKnockBack(tslf);
    
    bounding.x=(int)x;
    bounding.y=(int)y;
  }
  private void moveKnockBack(float tslf)
  {
    if(knockbackX!=0)
    {
      if(knockbackX>0)
      {
        knockbackX-=backKnockback*tslf;
        if(knockbackX<0)knockbackX=0;
      }
      else{
        knockbackX+=backKnockback*tslf;
        if(knockbackX>0)knockbackX=0;
      }
    }
    
    if(knockbackY!=0)
    {
      if(knockbackY<0)
      {
        knockbackY+=backKnockback*tslf;
        if(knockbackY>0)knockbackY=0;
      }
      else{
        knockbackY-=backKnockback*tslf;
        if(knockbackY<0)knockbackY=0;
      }
    }
    x+=knockbackX*tslf;
    y+=knockbackY*tslf;
  }
  private void knockBack(Rectangle rect)
  {
    if(rect.intersects(bounding))
    {
      
      int nachrechts=(int)(rect.x+rect.width)-bounding.x;
      int nachlinks=(int)(bounding.x+bounding.width)-rect.x;
      int nachunten=(int)(rect.y+rect.height)-bounding.y;
      int nachoben=(int)(bounding.y+bounding.height)-rect.y;
      
      
      if(nachrechts<nachlinks&&nachrechts<nachoben&&nachrechts<nachunten)
      {
        x+=nachrechts;
        knockbackX=300;
      }
      else if(nachlinks<nachoben&&nachlinks<nachunten)
      {
        x-=nachlinks;
        knockbackX=-300;
      }
      else if(nachoben<nachunten)
      {
        y-=nachoben;
        knockbackY=-300;
      }
      else if(nachoben>nachunten)
      {
        y+=nachunten;
        knockbackY=300;
      }
//      int vonunten=(int) ;
    }
  }
  protected boolean collidePlayerSpritzer()
  {
    int i=0;
    while(i<playerSpritzers.size())
    {
      Rectangle help1=playerSpritzers.get(i).getBounding();
      Rectangle help2=bounding;
      if(help1.intersects(help2))
      {
        if(knockback)knockBack(help1);
        playerSpritzers.remove(i);
        return true;
      }
      else
      {
        i++;
      }
    }
    return false;
  }
  protected void rebound(Rectangle rect)
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

  public float getLive()
  {
    return live;
  }

  public float getMaxLive()
  {
    return maxLive;
  }
  
  public Color getColor()
  {
    return color;
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
