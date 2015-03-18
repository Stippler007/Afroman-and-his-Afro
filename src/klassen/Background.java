/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import klassen.karte.GameObject;
import klassen.karte.*;
import klassen.player.Player;

/**
 *
 * @author Christian
 */
public class Background
{
  public static float x;
  public static float y;
  
  public GameObject[][] upperMap=new GameObject[100][100];
  public GameObject[][] lowerMap=new GameObject[100][100];
  
  public Background(Player player)
  {
    x=-1500;
    for (int i = 0; i < lowerMap.length; i++)
    {
      for (int j = 0; j < lowerMap[0].length; j++)
      {
        lowerMap[i][j]=new Gras();
      }
    }
    for (int i = 0; i < upperMap.length; i++)
    {
      upperMap[i][18]=new Rock();
    }
    player.setMap(upperMap);
  }
  
  public void update(float tslf)
  {
//    System.out.println(x);
    x+=Player.speedX;
    y+=Player.speedY;
    for (int i =(int)(x/25*-1); i < (int)(x/25*-1)+34; i++) 
    {
      for (int j = (int)(y/25*-1); j < (int)(y/25*-1)+26; j++) 
      {
        lowerMap[i][j].update(tslf,(int)(x+i*25),(int)(y+j*25));
        if(upperMap[i][j]!=null)upperMap[i][j].update(tslf,(int)(x+i*25),(int)(y+j*25));
      }
    }
  }
}
