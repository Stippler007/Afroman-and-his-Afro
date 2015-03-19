package klassen.enemys;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;

public class StandartEnemy extends Enemy implements Runnable{

    LinkedList<EnemySpritzer> enemySpritzer;
    
    public StandartEnemy(float x, float y, int speed, int speedX, int speedY, LinkedList<PlayerSpritzer> playerSpritzers,LinkedList<Enemy> enemys, Player player, LinkedList<EnemySpritzer> enemySpritzer) {
        super(x, y, speed, playerSpritzers,enemys, player, new Rectangle((int)x, (int)y, 25, 25));
        
        super.speedX=speedX;
        super.speedY=speedY;
        setColor(Color.blue);
        setKnockback(true);
        this.enemySpritzer = enemySpritzer;
        
        
    }
    
    @Override
    public void update(float tslf)
    {
        if(collidePlayerSpritzer())
        {
          live-=10;
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, speed*24,0));
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, -speed*24,0));
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0, speed*24));
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0,-speed*24));
          
        }
    
        Rectangle rect=player.getBounding();
        rect.x-=Player.speedX;
        rect.y-=Player.speedY;
        rebound(rect);
        super.update(tslf);
    }
    
    public void run()
    {
        
    }
}
