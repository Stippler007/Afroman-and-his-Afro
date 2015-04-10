/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.afro;

import klassen.ImageFactory;

/**
 *
 * @author Christian
 */
public class BasicAfro extends Afro
{
  
  public BasicAfro(float x, float y, int team)
  {
    super(x, y, team);
    look[0]=ImageFactory.getImageFactory().getLooks("afro1");
  }
  
  
  
}
