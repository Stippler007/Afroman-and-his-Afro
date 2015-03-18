/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.karte;

import java.awt.image.BufferedImage;
import klassen.ImageFactory;

/**
 *
 * @author Christian
 */
public class Gras extends GameObject
{
  private static BufferedImage look; 

  static
  {
    look=ImageFactory.getImageFactory().getLooks("gras0");
  }

  @Override
  public BufferedImage getLook()
  {
    return look;
  }
  
}
