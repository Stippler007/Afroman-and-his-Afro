/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.player;

import java.awt.image.BufferedImage;
import klassen.ImageFactory;

/**
 *
 * @author Stippler
 */
public class PlayerSpritzer extends Spritzer
{

  public PlayerSpritzer(float x, float y, float speedX, float speedY) {
    super(x, y, speedX, speedY);
    look=new BufferedImage[1];
    look[0]=ImageFactory.getImageFactory().getLooks("player");
  }
  
}
