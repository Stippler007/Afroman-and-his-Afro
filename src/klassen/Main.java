package klassen;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import klassen.Inventory.InventoryDraw;
import klassen.Inventory.InventoryThings;
import klassen.afro.Afro;
import klassen.afro.BasicAfro;
import klassen.enemys.BasicEnemy;
import klassen.enemys.Enemy;
import klassen.enemys.EnemySpritzer;
import klassen.karte.GameObject;
import klassen.listener.KL;
import klassen.player.Player;
import static klassen.player.Player.speedX;
import static klassen.player.Player.speedY;
import klassen.player.Spritzer;
import klassen.tower.BasicTower;
import klassen.tower.Tower;

public class Main implements Runnable
{
    Player player;
    Background bg;
    LinkedList<Spritzer> spritzers;
    LinkedList<Enemy> enemys;
    LinkedList<EnemySpritzer> enemySpritzerses;
    LinkedList<Tower> towers;
    GUI f;
    LinkedList<Afro> afros;
    ShopGUI shop;
    
  public Main()
  {
    afros=new LinkedList<Afro>();
    
    enemys=new LinkedList<>();
    enemySpritzerses=new LinkedList<>();
    
    towers=new LinkedList<>();
    spritzers=new LinkedList<>();
    
    player=new Player(400-12.5f, 300-12.5f, 300, spritzers, enemySpritzerses,towers);
    
    InventoryDraw ivd = new InventoryDraw();
    InventoryThings iv = new InventoryThings(ivd, towers, enemys, spritzers);
    
    bg=new Background(player, enemys,iv);

    // GameMenu gm = new GameMenu(new Frame());
    f=new GUI(player, spritzers, enemySpritzerses, enemys,towers,spritzers,afros,bg,ivd, iv); //Ich erzeuge mein GUI Objekt
    
    f.setUndecorated(true);
    f.setVisible(true);
    f.setSize(800,600);
    f.setResizable(false); 
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLocationRelativeTo(null);
    f.setFullscreen();
    
    enemys.add(new BasicEnemy(300, 300, 30, 20, 0, spritzers, enemys, towers, player));
    enemys.add(new BasicEnemy(300, 300, 30, 20, 0, spritzers, enemys, towers, player));
    
    afros.add(new BasicAfro(400, 400, 0, ivd, iv, f, shop));
    
//    towers.add(new BasicTower(100, 100, 300, 40, enemys, spritzers));
    
        new Thread(this).start();
    
    }
  
  public void run()
  {
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
      for (Spritzer spritzer : spritzers)
      {
        spritzer.update(tslf);
      }
      for (Enemy enemy : enemys)
      {
        enemy.update(tslf);
      }
      for (EnemySpritzer enemy : enemySpritzerses) 
      {
        enemy.update(tslf);
      }
      for (Tower t : towers) 
      {
        t.update(tslf);
      }
      for (Afro afro : afros)
      {
        afro.update(tslf);
      }
      
      deleteStuff(player, enemys,spritzers, enemySpritzerses,bg);
      
      f.repaintScreen();
      try{Thread.sleep(15);} catch (InterruptedException ex){}
    }
    }
  private static void deleteStuff(Player player,LinkedList<Enemy> enemys,LinkedList<Spritzer> playerSpritzers, LinkedList<EnemySpritzer> enemySpritzers,Background bg)
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
    while(i<playerSpritzers.size()){
      Spritzer ps=playerSpritzers.get(i);
      if(ps.getBounding().x>Background.x+bg.lowerMap.length*25)playerSpritzers.remove(i);
      else if(ps.getBounding().x<Background.x-ps.getBounding().width)playerSpritzers.remove(i);
      else if(ps.getBounding().y>Background.y+bg.lowerMap[0].length*25)playerSpritzers.remove(i);
      else if(ps.getBounding().y<Background.y-ps.getBounding().height)playerSpritzers.remove(i);
      else if(!ps.isAlive())
      {
        playerSpritzers.remove(i);
      }
      else i++;
      
      
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
    public static void main(String[] args) {
        new Main();
    }
}