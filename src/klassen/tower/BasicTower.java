/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.tower;

import java.awt.Rectangle;
import java.util.LinkedList;
import klassen.enemys.Enemy;
import klassen.player.PlayerSpritzer;
import klassen.player.Spritzer;

/**
 *
 * @author Stippler
 */
public class BasicTower extends Tower
{

  public BasicTower(float x, float y, int radius, float damage,
                    LinkedList<Enemy> enemys,
                    LinkedList<Spritzer> spritzers) {
    super(x, y, new Rectangle((int)x,(int)y,50,50), radius, damage, 1, enemys,spritzers);
  }
  
  @Override
  public void onAttack()
  {
    if(enemy!=null)
    {
      float speedX = enemy.getX()+enemy.getBounding().width/2 - (x+bounding.width/2);
      float speedY = enemy.getY()+enemy.getBounding().height/2 - (y+bounding.height/2);

      float help = (float)Math.sqrt(speedX*speedX+speedY*speedY);

      speedX/=help;
      speedY/=help;
      
      speedX*=500;
      speedY*=500;
      
      spritzers.add(new PlayerSpritzer(x+bounding.width/2, y+bounding.height/2, 30, 30));
    }
  }
}
