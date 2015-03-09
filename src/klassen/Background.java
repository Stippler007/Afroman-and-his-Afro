/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import klassen.karte.GameObjects;
import klassen.karte.Gras;

/**
 *
 * @author Christian
 */
public class Background
{
  
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
  
}
