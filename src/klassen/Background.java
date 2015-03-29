/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import java.util.LinkedList;
import klassen.Inventory.InventoryThings;
import klassen.enemys.Enemy;
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
  
  public Background(Player player, LinkedList<Enemy> enemys,InventoryThings it)
  {
    x=-1500;
    for (int i = 0; i < lowerMap.length; i++)
    {
      for (int j = 0; j < lowerMap[0].length; j++)
      {
        lowerMap[i][j]=new Gras();
      }
    }
    for (int i = 1; i < upperMap.length-2; i++)
    {
      upperMap[0][i]=new Rock();
    }
    for (int i = 1; i < upperMap.length; i++)
    {
      upperMap[i][1]=new Rock();
    }
    for (int i = 0; i < upperMap.length; i++)
    {
      upperMap[i][upperMap.length-3]=new Rock();
    }
    for (int i = 1; i < upperMap.length-2; i++)
    {
      upperMap[upperMap.length-2][i]=new Rock();
    }
    player.setMap(upperMap);
    it.setMap(upperMap);
    Enemy.setMap(upperMap);
  }
  public void shareMap()
  {
    
  }
  public void update(float tslf)
  {
//    System.out.println(x);
    if(x+Player.speedX < 0 && x+Player.speedX < (lowerMap.length-1)*25)
    {
        x+=Player.speedX;
    }else
    {
        x = 0;
        Player.speedX = 0;
    }
    if(y+Player.speedY < 0 && y+Player.speedY < (lowerMap[0].length-1)*25)
    {
        y+=Player.speedY;
    }else
    {
        y = 0;
        Player.speedY = 0;
    }
    for (int i =(int)(x/25*-1); i < (int)(x/25*-1)+34; i++) 
    {
      for (int j = (int)(y/25*-1); j < (int)(y/25*-1)+26; j++) 
      {
        lowerMap[i][j].update(tslf,(int)(x+i*25),(int)(y+j*25));
        
      }
    }
    for(int i = 0; i < 100; i++)
    {
        for (int j = 0; j < 100; j++) {
            if(upperMap[i][j]!=null)upperMap[i][j].update(tslf,(int)(x+i*25),(int)(y+j*25));;
            
        }
    }
  }
}
