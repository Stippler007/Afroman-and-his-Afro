/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.tower;

import java.awt.Rectangle;
import klassen.ImageFactory;

/**
 *
 * @author Stippler
 */
public class BasicTowerSpritzer extends TowerSpritzer
{

  public BasicTowerSpritzer(float x, float y, float speedX, float speedY) 
  {
    super(x, y, new Rectangle((int)x,(int)y,25,25), speedX, speedY);
  }
  
}
