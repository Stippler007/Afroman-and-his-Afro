/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.player;

import klassen.karte.GameObject;

/**
 *
 * @author Stippler
 */
public class PlayerSpritzer extends Spritzer
{

  public PlayerSpritzer(float x, float y, float speedX, float speedY,GameObject map[][]) {
    super(x, y, speedX, speedY,map);
  }
  
}
