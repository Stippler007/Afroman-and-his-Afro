/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.afro;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
  protected BufferedImage look[];
  
  public Afro(float x, float y) {
    this.x = x;
    this.y = y;
    look=new BufferedImage[1];
    for (int i = 0; i < look.length; i++) {
      look[i]=ImageFactory.getImageFactory().getLooks("BasicAfro"+i);
    }
  }
  
  public void update(float tslf)
  {
    x+=Player.speedX;
    y+=Player.speedY;
    
    bounding.x=(int)x;
    bounding.y=(int)y;
  }
  
  public boolean isClicked(int x,int y)
  {
    if(this.x<x&&this.x+bounding.width>x&&
       this.y<y&&this.y+bounding.height>y)
    {
      return true;
    }
    return false;
  }
}
