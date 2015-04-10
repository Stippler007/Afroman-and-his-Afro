/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.player;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import klassen.Background;
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
  
  private boolean alive=true;
  protected float damage=10;
  
  protected BufferedImage look[]=new BufferedImage[1];
  
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
    
    collisionMap();
  }
  private void collisionMap()
  {
    for (int i =(int)((Background.x/25*-1)+((x/25)-2)); i <= (int)((Background.x/25*-1)+((x/25)+2)); i++) 
    {
      for (int j = (int)((Background.y/25*-1)+((y/25-2))); j <= (int)((Background.y/25*-1)+((y/25+2))); j++) 
      {
//        map[i][j].update(x, x, y);
        //TODO ausbessern
        if(!(i<0)&&!(j<0)&&!(i>map.length-1)&&!(j>map[0].length-1)&&map[i][j]!=null&&map[i][j].isSolid()&&map[i][j].getBounding().intersects(bounding))
        {
          alive=false;
          map[i][j].setLive(map[i][j].getLive()-damage);
          if(map[i][j].getLive()<=0)map[i][j]=null;
        }
      }
    }
  }
  public Rectangle getBounding()
  {
    return bounding;
  }
  public boolean isAlive()
  {
    return alive;
  }
  public float getX()
  {
    return x;
  }
  public float getY()
  {
    return y;
  }
  public BufferedImage getLook() 
  {
    return look[0];
  }
}
