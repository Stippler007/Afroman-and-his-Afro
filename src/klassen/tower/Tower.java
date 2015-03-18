/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.tower;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import klassen.enemys.Enemy;
import klassen.player.Player;

/**
 *
 * @author Stippler
 */
public abstract class Tower
{
  protected float x;
  protected float y;
  
  protected Rectangle bounding;
  protected int radius;
  protected BufferedImage look[];
  
  protected Enemy enemy;
  protected LinkedList<Enemy> enemys;
  protected float damage;
  
  protected float animationTime=0;
  protected float maxAnimationTime;
  
  public Tower(float x, float y, Rectangle bounding,
               int radius,float damage,float maxAnimationTime,LinkedList<Enemy> enemys)
  {
    this.x = x;
    this.y = y;
    this.enemys=enemys;
    this.bounding = bounding;
    this.damage=damage;
  }
  
  public void update(float tslf)
  {
    if(animationTime<maxAnimationTime)
    {
      animationTime+=tslf;
      onAttack();
    }
    else
    {
      animationTime-=maxAnimationTime;
    }
    
    x+=Player.speedX;
    y+=Player.speedY;
  }
  public void onAttack()
  {
    
  }
  private LinkedList<Enemy> getPossibleEnemys()
  {
    LinkedList<Enemy> enemys=new LinkedList<>();
    if(!this.enemys.isEmpty())
    {
      for (Enemy e : this.enemys)
      {
        float enemyX=e.getX()+e.getBounding().width/2;
        float enemyY=e.getY()+e.getBounding().height/2;
        enemyX-=(x+bounding.width/2);
        enemyY-=(y+bounding.height/2);
        
        float distance = (float)Math.sqrt(enemyX*enemyX+enemyY*enemyY);
        
        if(distance<radius)
        {
          enemys.add(e);
        }
      }
    }
    return enemys;
  }
  protected Enemy getLowestLiveEnemy()
  {
    LinkedList<Enemy> enemys=getPossibleEnemys();
    if(!enemys.isEmpty())
    {
      Enemy enemy=enemys.get(0);
      for (Enemy e : enemys) 
      {
        if(e.getLive()<enemy.getLive())
        {
          enemy=e;
        }
      }
      return enemy;
    }
    return null;
  }
  protected Enemy getHighestLiveEnemy()
  {
    LinkedList<Enemy> enemys=getPossibleEnemys();
    if(!enemys.isEmpty())
    {
      Enemy enemy=enemys.get(0);
      for (Enemy e : enemys) 
      {
        if(e.getLive()<enemy.getLive())
        {
          enemy=e;
        }
      }
      return enemy;
    }
    return null;
  }
  protected Enemy getNearestEnemy()
  {
    LinkedList<Enemy> enemys=getPossibleEnemys();
    if(!enemys.isEmpty())
    {
      Enemy enemy=enemys.get(0);
      float enemyX=enemys.get(0).getX()+enemys.get(0).getBounding().width/2;
      float enemyY=enemys.get(0).getY()+enemys.get(0).getBounding().height/2;
      enemyX-=(x+bounding.width/2);
      enemyY-=(y+bounding.height/2);
      float minDistance=(float)Math.sqrt(enemyX*enemyX+enemyY*enemyY);
      
      for (int i=1;i<0;i++) 
      {
        Enemy e=enemys.get(i);
        enemyX=e.getX()+e.getBounding().width/2;
        enemyY=e.getY()+e.getBounding().height/2;
        enemyX-=(x+bounding.width/2);
        enemyY-=(y+bounding.height/2);
        
        float distance = (float)Math.sqrt(enemyX*enemyX+enemyY*enemyY);
        if(distance<minDistance)
        {
          minDistance=distance;
          enemy=e;
        }
      }
      return enemy;
    }
    return null;
  }
  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }
}