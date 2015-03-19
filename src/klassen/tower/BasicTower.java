/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.tower;

import java.awt.Rectangle;
import java.util.LinkedList;
import klassen.enemys.Enemy;

/**
 *
 * @author Stippler
 */
public class BasicTower extends Tower
{
  public final static int width=50;
  public final static int height=50;
  
  
  public BasicTower(float x, float y, int radius, float damage,
                    float maxAnimationTime,
                    LinkedList<Enemy> enemys,
                    LinkedList<TowerSpritzer> towerSpritzers) {
    super(x, y, new Rectangle((int)x,(int)y,width,height), radius, damage, maxAnimationTime, enemys,towerSpritzers);
  }

  @Override
  public void onAttack()
  {
    if(enemy!=null)
    {
      
      towerSpritzers.add(new BasicTowerSpritzer((int)x, (int)y, 0, 0));
    }
  }
}
