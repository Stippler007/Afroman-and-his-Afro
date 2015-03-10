/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import klassen.karte.GameObjects;
import klassen.karte.Gras;
import klassen.player.Player;

/**
 *
 * @author Christian
 */
public class Background
{
  public static float x;
  public static float y;
  
  GameObjects[][] map=new GameObjects[100][100];
  
  public Background()
  {
    for (int i = 0; i < map.length; i++)
    {
      for (int j = 0; j < map[0].length; j++)
      {
        map[i][j]=new Gras();
      }
    }
  }
  
  public void update(float tslf)
  {
    x+=Player.speedX;
    y+=Player.speedY;
    for (int i =(int)(x/25*-1); i < (int)(x/25*-1)+34; i++) 
    {
      for (int j = (int)(y/25*-1); j < (int)(y/25*-1)+26; j++) 
      {
        map[i][j].update(tslf,(int)(x+i*25),(int)(y+j*25));
      }
    }
  }
}
