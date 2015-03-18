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

  public BasicTower(float x, float y, Rectangle bounding, int radius, float damage,
                    float maxAnimationTime,
                    LinkedList<Enemy> enemys,
                    LinkedList<TowerSpritzer> towerSpritzers) {
    super(x, y, bounding, radius, damage, maxAnimationTime, enemys,towerSpritzers);
  }

  @Override
  public void onAttack()
  {
    if(enemy!=null)
    {
      
    }
  }
}
