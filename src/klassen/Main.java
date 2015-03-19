package klassen;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import klassen.Inventory.InventoryDraw;
import klassen.Inventory.InventoryThings;
import klassen.enemys.BasicEnemy;
import klassen.enemys.Enemy;
import klassen.enemys.EnemySpritzer;
import klassen.enemys.StandartEnemy;
import klassen.listener.KL;
import klassen.player.Player;
import static klassen.player.Player.speedX;
import static klassen.player.Player.speedY;
import klassen.player.PlayerSpritzer;
import klassen.tower.BasicTower;
import klassen.tower.Tower;
import klassen.tower.TowerSpritzer;

public class Main
{
  public static void main(String[] args)
  {
    LinkedList<Enemy> enemys=new LinkedList<>();
    LinkedList<EnemySpritzer> enemySpritzerses=new LinkedList<>();
    
    LinkedList<Tower> towers=new LinkedList<>();
    LinkedList<TowerSpritzer> towerSpritzers=new LinkedList<>();
    
    
    LinkedList<PlayerSpritzer> playerSpritzers=new LinkedList<>();
    Player player=new Player(400-12.5f, 300-12.5f, 300, playerSpritzers, enemySpritzerses,towers);
    
    InventoryDraw ivd = new InventoryDraw();
    InventoryThings iv = new InventoryThings(ivd);
    
    Background bg=new Background(player, iv);
    
    GUI f=new GUI(player, playerSpritzers, enemySpritzerses, enemys,towers,towerSpritzers,bg, ivd, iv); //Ich erzeuge mein GUI Objekt
    
    f.setUndecorated(true);
    f.setVisible(true);
    f.setSize(800,600);
    f.setResizable(false); 
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLocationRelativeTo(null);
//    f.setFullscreen();
    
//    enemys.add(new StandartEnemy(300, 300, 20, 0, 0, playerSpritzers,enemys, player, enemySpritzerses));
//    enemys.add(new BasicEnemy(300, 300, 30, 20, 0, playerSpritzers, enemys, player));
//    enemys.add(new BasicEnemy(300, 300, 30, 20, 0, playerSpritzers, enemys, player));
    
    towers.add(new BasicTower(100, 100, 300, 40, 3, enemys, towerSpritzers));
    
    long lastFrame=System.currentTimeMillis();
    while(true)
    {   
      long thisFrame=System.currentTimeMillis();
      float tslf=(float)(thisFrame-lastFrame)/1000;
      lastFrame=thisFrame;
      
      if(KL.keys[KeyEvent.VK_ESCAPE])
      {
        System.exit(0);
      }
      
      player.update(tslf);
      
      bg.update(tslf);
      for (PlayerSpritzer playerSpritzer : playerSpritzers)
      {
        playerSpritzer.update(tslf);
      }
      for (Enemy enemy : enemys)
      {
        enemy.update(tslf);
      }
      for (EnemySpritzer enemy : enemySpritzerses) 
      {
        enemy.update(tslf);
      }
      for (TowerSpritzer ts : towerSpritzers) 
      {
        ts.update(tslf);
      }
      for (Tower t : towers) 
      {
        t.update(tslf);
      }
      
      deleteStuff(player, enemys,playerSpritzers, enemySpritzerses,bg);
      
      f.repaintScreen();
      try{Thread.sleep(15);} catch (InterruptedException ex){}
    }
  }
  private static void deleteStuff(Player player,LinkedList<Enemy> enemys,LinkedList<PlayerSpritzer> playerSpritzers, LinkedList<EnemySpritzer> enemySpritzers,Background bg)
  {
    int i=0;
    if(player.getLive() <= 0)
    {
        System.exit(0);
    }
    while(i<enemys.size())
    {
      if(enemys.get(i).getLive()<=0)
      {
        enemys.remove(i);
      }
      else
      {
        i++;
      }
    }
    i=0;
    while(i<playerSpritzers.size())
    {
      PlayerSpritzer ps=playerSpritzers.get(i);
      if(ps.getBounding().x>800)playerSpritzers.remove(i);
      else if(ps.getBounding().x<-ps.getBounding().width)playerSpritzers.remove(i);
      else if(ps.getBounding().y>600)playerSpritzers.remove(i);
      else if(ps.getBounding().y<-ps.getBounding().height)playerSpritzers.remove(i);
      else i++;
      
      for (int j =(int)(Background.x/25*-1); i <= (int)(Background.x/25*-1); i++) 
      {
        for (int k = (int)(Background.y/25*-1); j <= (int)(Background.y/25*-1); j++) 
        {
          if(!(j<0)&&!(j<0)&&bg.upperMap[j][k]!=null&&bg.upperMap[j][k].isSolid())
          {
            
          }
        }
      }
    }
    while(i<enemySpritzers.size())
    {
//      EnemySpritzer ps=enemySpritzers.get(i);
//      if(ps.getBounding().x>800)enemySpritzers.remove(i);
//      else if(ps.getBounding().x<-ps.getBounding().width)enemySpritzers.remove(i);
//      else if(ps.getBounding().y>600)enemySpritzers.remove(i);
//      else if(ps.getBounding().y<-ps.getBounding().height)enemySpritzers.remove(i);
//      else i++;
      
        if(enemySpritzers.get(i).getX() < Background.x) {
            enemySpritzers.remove(i);
        }
        else if(enemySpritzers.get(i).getX() > 99*25) {
            enemySpritzers.remove(i);
        }
        else if(enemySpritzers.get(i).getY() < Background.y) {
            enemySpritzers.remove(i);
        }
        else if(enemySpritzers.get(i).getY() > 99*25) {
            enemySpritzers.remove(i);
        }
        else i++;
        
    }
    
  }
}
