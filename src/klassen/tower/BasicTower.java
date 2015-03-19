/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.tower;

import java.awt.Rectangle;
import java.util.LinkedList;
import klassen.enemys.Enemy;
import klassen.player.Spritzer;

/**
 *
 * @author Stippler
 */
public class BasicTower extends Tower
{

  public BasicTower(float x, float y, int radius, float damage,
                    LinkedList<Enemy> enemys,
                    LinkedList<Spritzer> towerSpritzers) {
    super(x, y, new Rectangle((int)x,(int)y,50,50), radius, damage, 1, enemys,towerSpritzers);
  }

  @Override
  public void onAttack()
  {
    if(enemy!=null)
    {
      towerSpritzers.add(new BasicTowerSpritzer((int)x, (int)y, 30, 30));
    }
  }
}
