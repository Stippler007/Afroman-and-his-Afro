
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.enemys;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import klassen.player.Player;
import klassen.player.Spritzer;
import klassen.tower.Tower;

/**
 *
 * @author Christian
 */
public class BasicEnemy extends Enemy
{
  public BasicEnemy(float x, float y, int speed, int speedX, int speedY,
                    LinkedList<Spritzer> playerSpritzers,LinkedList<Enemy> enemys,
                    LinkedList<Tower> towers,Player player)
  {
    super(x, y, speed, playerSpritzers,enemys,towers, player, new Rectangle((int)x, (int)y, 25, 25));
    super.speedX=speedX;
    super.speedY=speedY;
    setColor(Color.RED);
    setKnockback(true);
  }

  @Override
  public void update(float tslf)
  {
    if(collidePlayerSpritzer())
    {
      live-=10;
    }
    speedX=0;
    speedY=0;
    
    moveZiel(player.getX()+player.getBounding().width/2, player.getY()+player.getBounding().height/2,speed);
    
    super.update(tslf);
    collide();
  }
}
