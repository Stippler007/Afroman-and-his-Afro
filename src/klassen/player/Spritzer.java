/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.player;

import java.awt.Rectangle;
import klassen.karte.GameObject;

/**
 *
 * @author Christian
 */
public abstract class Spritzer
{
  private float x;
  private float y;
  
  private float speedX=0;
  private float speedY=0;
  
  private Rectangle bounding;
  private GameObject map[][];
  
  public Spritzer(float x, float y, float speedX, float speedY,GameObject map[][])
  {
    this.x = x;
    this.y = y;
    this.speedX = speedX;
    this.speedY = speedY;
    this.bounding = new Rectangle((int)x, (int)y, 15, 15);
    this.map=map;
  }
  
  public void update(float tslf)
  {
    x+=Player.speedX;
    y+=Player.speedY;
    
    x+=speedX*tslf;
    y+=speedY*tslf;
    
    bounding.x=(int)x;
    bounding.y=(int)y;
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
