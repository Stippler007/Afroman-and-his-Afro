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
public class Rock extends GameObject
{
  private static BufferedImage look;
  
  static
  {
    look=ImageFactory.getImageFactory().getLooks("rock0");
  }

  public Rock()
  {
    solid=true;
  }
  
  @Override
  public BufferedImage getLook()
  {
    return look;
  }
  
}
