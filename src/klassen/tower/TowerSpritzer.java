/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.tower;

import java.awt.Rectangle;
import klassen.player.Player;

/**
 *
 * @author Stippler
 */
public abstract class TowerSpritzer 
{
  protected float x;
  protected float y;
  
  protected float speedX;
  protected float speedY;
  
  protected Rectangle bounding;

  public TowerSpritzer(float x, float y,Rectangle bounding, float speedX, float speedY)
  {
    this.x = x;
    this.y = y;
    this.speedX = speedX;
    this.speedY = speedY;
    this.bounding = bounding;
  }
  public void update(float tslf)
  {
    x+=speedX*tslf;
    y+=speedY*tslf;
    
    bounding.x=(int)x;
    bounding.y=(int)y;
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
