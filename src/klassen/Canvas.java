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
import klassen.enemys.Enemy;
import klassen.enemys.EnemySpritzer;
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
  
  public Canvas(Player player, LinkedList<PlayerSpritzer> playerSpritzers, LinkedList<EnemySpritzer> enemySpritzerses, LinkedList<Enemy> enemys)
  {
    this.player = player;
    this.playerSpritzers = playerSpritzers;
    this.enemySpritzerses = enemySpritzerses;
    this.enemys = enemys;
  }
  @Override
  public void paint(Graphics g)
  {
    g.clearRect(0, 0, 800, 600);
    g.fillRect((int)player.getX(), (int)player.getY(), player.getBounding().width, player.getBounding().height);
    for (PlayerSpritzer playerSpritzer : playerSpritzers)
    {
      g.fillRect((int)playerSpritzer.getX(), (int)playerSpritzer.getY(), playerSpritzer.getBounding().width, playerSpritzer.getBounding().height);
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
  }
}
