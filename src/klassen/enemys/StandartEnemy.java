package klassen.enemys;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;

public class StandartEnemy extends Enemy{

    public StandartEnemy(float x, float y, int speed, LinkedList<PlayerSpritzer> playerSpritzers, Player player, Rectangle bounding) {
        super(x, y, speed, playerSpritzers, player, bounding);
        
        super.speedX=speedX;
        super.speedY=speedY;
        setColor(Color.blue);
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
