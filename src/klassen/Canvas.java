/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JPanel;
import static klassen.Background.x;
import static klassen.Background.y;
import klassen.Inventory.InventoryDraw;
import klassen.enemys.Enemy;
import klassen.enemys.EnemySpritzer;
import klassen.listener.KL;
import klassen.listener.ML;
import klassen.listener.MML;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;

/**
 *
 * @author Christian
 */
public class Canvas extends JPanel
{
  private Player player;
  private LinkedList<PlayerSpritzer> playerSpritzers;
  
  private LinkedList<EnemySpritzer> enemySpritzerses;
  private LinkedList<Enemy> enemys;
  
  private InventoryDraw iv;
  
  private Background bg;
  
  private float scaleX=1;
  private float scaleY=1;

  public void setScaleX(float scaleX) {
    this.scaleX = scaleX;
  }

  public void setScaleY(float scaleY) {
    this.scaleY = scaleY;
  }
  
  public Canvas(Player player, LinkedList<PlayerSpritzer> playerSpritzers, LinkedList<EnemySpritzer> enemySpritzerses, LinkedList<Enemy> enemys,Background bg)
  {
    this.player = player;
    this.playerSpritzers = playerSpritzers;
    this.enemySpritzerses = enemySpritzerses;
    this.enemys = enemys;
    this.bg=bg;
    
    iv = new InventoryDraw();
  }
  @Override
  public void paint(Graphics g)
  {
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
    g.fillRect((int)player.getX(), (int)player.getY(), player.getBounding().width, player.getBounding().height);
    g.setColor(Color.gray);
    for (PlayerSpritzer playerSpritzer : playerSpritzers)
    {
      g.fillRect((int)playerSpritzer.getX(), (int)playerSpritzer.getY(), playerSpritzer.getBounding().width, playerSpritzer.getBounding().height);
    }
    for (EnemySpritzer enemyS : enemySpritzerses)
    {
      g.fillRect((int)enemyS.getX(), (int)enemyS.getY(), enemyS.getBounding().width, enemyS.getBounding().height);
    }
    for (Enemy enemy : enemys)
    {
      g.setColor(Color.red);
      g.fillRect((int)enemy.getX(), (int)enemy.getY()-3, enemy.getBounding().width, 2);
      
      g.setColor(Color.green);
      g.fillRect((int)enemy.getX(), (int)enemy.getY()-3, (int)(enemy.getBounding().width*(enemy.getLive()/enemy.getMaxLive())), 2);
      
      g.setColor(enemy.getColor());
      g.fillRect((int)enemy.getX(), (int)enemy.getY(), enemy.getBounding().width, enemy.getBounding().height);
    }
     
    iv.paintInventory(g);
  }
}
