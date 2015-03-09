package klassen;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import klassen.enemys.BasicEnemy;
import klassen.enemys.Enemy;
import klassen.enemys.EnemySpritzer;
import klassen.enemys.StandartEnemy;
import klassen.listener.KL;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;

public class Main
{
  public static void main(String[] args)
  {
    LinkedList<EnemySpritzer> enemySpritzerses=new LinkedList<>();
      
    LinkedList<PlayerSpritzer> playerSpritzers=new LinkedList<>();
    Player player=new Player(400-12.5f, 300-12.5f, 300, playerSpritzers, enemySpritzerses);
    
    
    LinkedList<Enemy> enemys=new LinkedList<>();
    
    GUI f=new GUI(player, playerSpritzers, enemySpritzerses, enemys); //Ich erzeuge mein GUI Objekt
    
    f.setUndecorated(true);
    f.setVisible(true);
    f.setSize(800,600);
    f.setResizable(false); 
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLocationRelativeTo(null);
    
    enemys.add(new BasicEnemy(300, 300, 20, 20, 0, playerSpritzers, player));
    enemys.add(new StandartEnemy(300, 300, 20, 20, 0, playerSpritzers, player, enemySpritzerses));
    
    Background bg=new Background();
    
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
      
      for (PlayerSpritzer playerSpritzer : playerSpritzers)
      {
        playerSpritzer.update(tslf);
      }
      for (Enemy enemy : enemys)
      {
        enemy.update(tslf);
      }
      for (EnemySpritzer enemyS : enemySpritzerses) {
        enemyS.update(tslf);
      }
      
      deleteStuff(player, enemys,playerSpritzers, enemySpritzerses);
      
      f.repaintScreen();
      try{Thread.sleep(15);} catch (InterruptedException ex){}
    }
  }
  private static void deleteStuff(Player player,LinkedList<Enemy> enemys,LinkedList<PlayerSpritzer> playerSpritzers, LinkedList<EnemySpritzer> enemySpritzers)
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
    }
    while(i<enemySpritzers.size())
    {
      EnemySpritzer ps=enemySpritzers.get(i);
      if(ps.getBounding().x>800)enemySpritzers.remove(i);
      else if(ps.getBounding().x<-ps.getBounding().width)enemySpritzers.remove(i);
      else if(ps.getBounding().y>600)enemySpritzers.remove(i);
      else if(ps.getBounding().y<-ps.getBounding().height)enemySpritzers.remove(i);
      else i++;
    }
  }
}
