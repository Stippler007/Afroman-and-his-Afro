package klassen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import klassen.Inventory.InventoryDraw;
import klassen.Inventory.InventoryThings;
import klassen.enemys.Enemy;
import klassen.enemys.EnemySpritzer;
import klassen.karte.GameObject;
import klassen.listener.KL;
import klassen.listener.ML;
import klassen.listener.MML;
import klassen.listener.MWL;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;
import klassen.tower.Tower;
import klassen.tower.TowerSpritzer;

/**
 *
 * @author Christian
 */
public class GUI extends JFrame
{
  private Canvas canvas;
  private GameMenu menu;
  
  private InventoryDraw idv;
  private InventoryThings iv;
  private MWL mwl;
  
  private float xScaling=1;
  private float yScaling=1;
  private boolean fullscreen;
  
  public GUI(Player player, 
             LinkedList<PlayerSpritzer> playerSpritzers,
             LinkedList<EnemySpritzer> enemySpritzerses, LinkedList<Enemy> enemys,
             LinkedList<Tower> towers,LinkedList<TowerSpritzer> towerSpritzers,
             Background bg , InventoryDraw idv, InventoryThings iv)
  {
    setLayout(new GridLayout());
    
    this.idv = idv;
    this.iv = iv;
    
    canvas=new Canvas(player, playerSpritzers, enemySpritzerses, enemys,towers,towerSpritzers,bg,idv, iv);
//    menu=new GameMenu();
//    canvas.setBounds(0, 0, 800, 600);
    add(canvas);
    
    mwl = new MWL(idv);
    
    addKeyListener(new KL(this));
    addMouseListener(new ML(iv));
    addMouseMotionListener(new MML(this));
    addMouseWheelListener(mwl);
  }
  public void setFullscreen()
  {
    setExtendedState(Frame.MAXIMIZED_BOTH);
    
    Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize ();
    
    xScaling = (float)screensize.width/800f;
    yScaling = (float)screensize.height/600f;
    
    setLocationRelativeTo(null);
    canvas.setScaleX(xScaling);
    canvas.setScaleY(yScaling);
    fullscreen=true;
  }
  public void setNormalscreen()
  {
    setExtendedState(Frame.NORMAL);
    xScaling = (float)getWidth()/800f;
    yScaling = (float)getHeight()/600f;
    setLocationRelativeTo(null);
    canvas.setScaleX(xScaling);
    canvas.setScaleY(yScaling);
    fullscreen=false;
  }
  public void repaintScreen()
  {
    canvas.repaint();
  }

  public boolean isFullscreen() {
    return fullscreen;
  }
  public float getScaleX()
  {
      return xScaling;
  }
  public float getScaleY()
  {
      return yScaling;
  }
  
}
