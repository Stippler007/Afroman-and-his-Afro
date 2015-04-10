/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;
import static klassen.Background.x;
import static klassen.Background.y;
import klassen.Inventory.InventoryDraw;
import klassen.Inventory.InventoryThings;
import klassen.afro.Afro;
import klassen.enemys.Enemy;
import klassen.enemys.EnemySpritzer;
import klassen.karte.GameObject;
import klassen.listener.KL;
import klassen.listener.ML;
import klassen.listener.MML;
import klassen.player.Player;
import klassen.player.Spritzer;
import klassen.tower.Tower;

/**
 *
 * @author Christian
 */
public class Canvas extends JPanel
{
  private Player player;
  private LinkedList<Spritzer> playerSpritzers;
  
  private LinkedList<EnemySpritzer> enemySpritzerses;
  private LinkedList<Enemy> enemys;
  
  private LinkedList<Afro> afros;
  
  private LinkedList<Tower> towers;
  private LinkedList<Spritzer> towerSpritzers;
  
  private InventoryDraw idv;
  private InventoryThings iv;
  
  private Background bg;
  
  private float scaleX=1;
  private float scaleY=1;

  public void setScaleX(float scaleX) {
    this.scaleX = scaleX;
  }

  public void setScaleY(float scaleY) {
    this.scaleY = scaleY;
  }
  
  public Canvas(Player player,
                LinkedList<Spritzer> playerSpritzers,
                LinkedList<EnemySpritzer> enemySpritzerses, LinkedList<Enemy> enemys,
                LinkedList<Tower> towers,LinkedList<Spritzer> towerSpritzers,LinkedList<Afro> afros,
                Background bg, InventoryDraw idv, InventoryThings iv)
  {
    this.player = player;
    this.playerSpritzers = playerSpritzers;
    this.enemySpritzerses = enemySpritzerses;
    this.towers=towers;
    this.towerSpritzers=towerSpritzers;
    this.enemys = enemys;
    this.afros=afros;
    this.bg=bg;
    this.idv = idv;
    this.iv = iv;
  }
  
  public void paint(Graphics g)
  {
    double turn;
    Graphics2D g2=(Graphics2D)g;
    g2.scale(scaleX, scaleY);
    
    for (int i =(int)(x/25*-1); i < (int)(x/25*-1)+34; i++) 
    {
      for (int j = (int)(y/25*-1); j < (int)(y/25*-1)+26; j++) 
      {
        g.drawImage(bg.lowerMap[i][j].getLook(), (int)Background.x+i*25, (int)Background.y+j*25, null);
        if(bg.upperMap[i][j]!=null)g.drawImage(bg.upperMap[i][j].getLook(), (int)Background.x+i*25, (int)Background.y+j*25, null);
      }
    }
    
    g.setColor(Color.red);
    g.fillRect((int)player.getX(), (int)player.getY()-3, player.getBounding().width, 2);
    
    g.setColor(Color.green);
    g.fillRect((int)player.getX(), (int)player.getY()-3, (int)(player.getBounding().width*(player.getLive()/player.getMaxLive())), 2);
      
    g.setColor(Color.black);
    //g.fillRect((int)player.getX(), (int)player.getY(), player.getBounding().width, player.getBounding().height);
    g.drawImage(player.getLook(), (int)player.getX(), (int)player.getY(), null);
    g.setColor(Color.gray);
    for (Spritzer playerSpritzer : playerSpritzers)
    {
      g.fillRect((int)playerSpritzer.getX(), (int)playerSpritzer.getY(), playerSpritzer.getBounding().width, playerSpritzer.getBounding().height);
    }
    for (EnemySpritzer enemyS : enemySpritzerses)
    {
      g.fillRect((int)enemyS.getX(), (int)enemyS.getY(), enemyS.getBounding().width, enemyS.getBounding().height);
    }
    for (Tower t : towers)
    {
      if(t.getX()+t.getBounding().width>0&&t.getY()+t.getBounding().height>0
         &&t.getX()<getWidth()&&t.getY()<getWidth())
      {
        turn=t.getTurn();
        g2.rotate(turn, t.getX()+t.getBounding().width/2, t.getY()+t.getBounding().height/2);
        g.drawImage(t.getLook(), (int)t.getX(), (int)t.getY(), null);
        g2.rotate(-turn, t.getX()+t.getBounding().width/2, t.getY()+t.getBounding().height/2);
      }
    }
    for (Enemy enemy : enemys)
    {
      g.setColor(Color.red);
      g.fillRect((int)enemy.getX(), (int)enemy.getY()-3, enemy.getBounding().width, 2);
      
      
      g.setColor(Color.green);
      g.fillRect((int)enemy.getX(), (int)enemy.getY()-3, (int)(enemy.getBounding().width*(enemy.getLive()/enemy.getMaxLive())), 2);
      
//      g.setColor(enemy.getColor());
//      //g.fillRect((int)enemy.getX(), (int)enemy.getY(), enemy.getBounding().width, enemy.getBounding().height);
      g2.rotate(enemy.getTurn(),enemy.getX()+enemy.getBounding().width/2,enemy.getY()+enemy.getBounding().height/2);
      g.drawImage(enemy.getLook(), (int)enemy.getX(), (int)enemy.getY(), null);
      g2.rotate(-enemy.getTurn(),enemy.getX()+enemy.getBounding().width/2,enemy.getY()+enemy.getBounding().height/2);
    }
    for (Afro afro : afros)
    {
      g.drawImage(afro.getLook(), (int)afro.getX(), (int)afro.getY(), null);
    }
    idv.paintInventory(g);
  }
}
