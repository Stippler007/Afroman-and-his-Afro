/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import klassen.enemys.BasicEnemy;
import klassen.enemys.Enemy;
import klassen.enemys.EnemySpritzer;
import klassen.listener.KL;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;

/**
 *
 * @author Christian
 */
public class Main
{
  public static void main(String[] args)
  {
    LinkedList<PlayerSpritzer> playerSpritzers=new LinkedList<>();
    Player player=new Player(100, 100, 300, playerSpritzers);
    
    LinkedList<EnemySpritzer> enemySpritzerses=new LinkedList<>();
    LinkedList<Enemy> enemys=new LinkedList<>();
    
    GUI f=new GUI(player, playerSpritzers, enemySpritzerses, enemys); //Ich erzeuge mein GUI Objekt
    
    f.setUndecorated(true); // Ich haue die "Titelleiste" weg
    f.setVisible(true); // Ich hab keine Ahnung wie ich das erklären soll...
    f.setSize(800,600); // setzte die Größe 800/600
    f.setResizable(false); // sage man soll es weder vergrößern noch verkleiner können (unnötig da die ränder sowiso nicht angezeigt werden) 
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // setzte was passieren soll sobald man das rote x drückt (ebenfalls unnötig)
    f.setLocationRelativeTo(null); // lasse das ganze in der mitte vom bildschirm anzeigen (ansonsten klebt es links oben was uncool ist)
    
    enemys.add(new BasicEnemy(300, 300, 20, 20, 0, playerSpritzers, player));
    
    
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
      
      deleteStuff(enemys,playerSpritzers);
      
      f.repaintScreen();
      try{Thread.sleep(15);} catch (InterruptedException ex){}
    }
  }
  private static void deleteStuff(LinkedList<Enemy> enemys,LinkedList<PlayerSpritzer> playerSpritzers)
  {
    int i=0;
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
  }
}
