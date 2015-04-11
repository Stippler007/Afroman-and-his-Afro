/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.afro;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.plaf.basic.BasicTextUI;
import klassen.ImageFactory;
import klassen.player.Player;

/**
 *
 * @author Stippler
 */
public abstract class Afro 
{
  protected float x;
  protected float y;
  
  protected Rectangle bounding;
  protected BufferedImage look[]=new BufferedImage[1];
  
  public int team;
  
  public Afro(float x, float y,int team) {
    this.x = x;
    this.y = y;
    this.team=team;
    look=new BufferedImage[1];
    look[0]=ImageFactory.getImageFactory().getLooks("afro1");
    bounding=new Rectangle((int)x, (int)y, look[0].getWidth(), look[0].getHeight());
  }
  
  public void update(float tslf)
  {
    x+=Player.speedX;
    y+=Player.speedY;
    
    bounding.x=(int)x;
    bounding.y=(int)y;
  }
  
  public boolean isClicked(float x2,float y2)
  {
    
      int x = (int) x2;
      int y = (int) y2;
      
    if(this.x<x&&this.x+bounding.width>x&&
       this.y<y&&this.y+bounding.height>y)
    {
      return true;
    }
    return false;
  }

  public BufferedImage getLook()
  {
    return look[0];
  }

  public float getX()
  {
    return x;
  }

  public float getY()
  {
    return y;
  }
  public abstract void newShop();
}
