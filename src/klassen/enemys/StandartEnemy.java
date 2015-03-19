package klassen.enemys;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;
import klassen.tower.Tower;

public class StandartEnemy extends Enemy implements Runnable{

    LinkedList<EnemySpritzer> enemySpritzer;
    
    public StandartEnemy(float x, float y, int speed, int speedX, int speedY, 
                         LinkedList<PlayerSpritzer> playerSpritzers,LinkedList<Enemy> enemys, 
                         LinkedList<Tower> towers, Player player, LinkedList<EnemySpritzer> enemySpritzer) {
        super(x, y, speed, playerSpritzers,enemys, towers,player, new Rectangle((int)x, (int)y, 25, 25));
        
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
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, speed*24,0));
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, -speed*24,0));
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0, speed*24));
          enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0,-speed*24));
          
        }
    
        
        
        speedX = 0;
        speedY = 0;
        
        moveZiel(player.getX()+player.getBounding().width/2, player.getY()+player.getBounding().height/2,speed*12);
        
        super.update(tslf);
        collide();
    }
    
    public void run()
    {
        
        while(live > 0)
        {
            enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, speed*24,0));
            enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, -speed*24,0));
            enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0, speed*24));
            enemySpritzer.add(new EnemySpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0,-speed*24));
          
            
            try {
                Thread.sleep(3500);
            } catch (InterruptedException ex) {
                Logger.getLogger(StandartEnemy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
