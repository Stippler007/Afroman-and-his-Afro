package klassen.enemys;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;

public class StandartEnemy extends Enemy implements Runnable{

    LinkedList<EnemySpritzer> enemySpritzer;
    
    public StandartEnemy(float x, float y, int speed, int speedX, int speedY, LinkedList<PlayerSpritzer> playerSpritzers, Player player, LinkedList<EnemySpritzer> enemySpritzer) {
        super(x, y, speed, playerSpritzers, player, new Rectangle((int)x, (int)y, 25, 25));
        
        super.speedX=speedX;
        super.speedY=speedY;
        setColor(Color.blue);
        setKnockback(true);
        this.enemySpritzer = enemySpritzer;
        new Thread(this).start();
    }
    
    @Override
    public void update(float tslf)
    {
        if(collidePlayerSpritzer())
        {
          live-=10;
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, speed*3,0));
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, -speed*3,0));
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0, speed*3));
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0,-speed*3));
          
        }
        
        speedX = 0;
        speedY = 0;
    
        Rectangle rect=player.getBounding();
        rect.x-=Player.speedX;
        rect.y-=Player.speedY;
        
        
        moveZiel(player.getX()+player.getBounding().width/2, player.getY()+player.getBounding().height/2,speed);
        rebound(rect);
        super.update(tslf);
    }
    
    public void run()
    {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(StandartEnemy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(live > 0)
        {
            
            
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, speed*3,0));
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, -speed*3,0));
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0, speed*3));
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0,-speed*3));
          
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                Logger.getLogger(StandartEnemy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
