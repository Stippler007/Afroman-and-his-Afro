package klassen.enemys;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;

public class BasicEnemy extends Enemy
{
  public BasicEnemy(float x, float y, int speed, int speedX, int speedY,LinkedList<PlayerSpritzer> playerSpritzers,Player player)
  {
    super(x, y, speed, playerSpritzers, player, new Rectangle((int)x, (int)y, 25, 25));
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
    
    Rectangle rect=player.getBounding();
    rect.x-=Player.speedX;
    rect.y-=Player.speedY;
    rebound(rect);
    super.update(tslf);
  }
}