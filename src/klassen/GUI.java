
package klassen;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
public class GUI extends JFrame
{
  private Canvas canvas;
  private GameMenu menu;
  
  private float xScaling=1;
  private float yScaling=1;
  private boolean fullscreen;
  
  public GUI(Player player, LinkedList<PlayerSpritzer> playerSpritzers, LinkedList<EnemySpritzer> enemySpritzerses, LinkedList<Enemy> enemys,Background bg)
  {
    setLayout(new GridLayout());
    canvas=new Canvas(player, playerSpritzers, enemySpritzerses, enemys,bg);
//    menu=new GameMenu();
//    canvas.setBounds(0, 0, 800, 600);
    add(canvas);
    
    addKeyListener(new KL(this));
    addMouseListener(new ML());
    addMouseMotionListener(new MML());
  }
  public void setFullscreen()
  {
    setExtendedState(Frame.MAXIMIZED_BOTH);
    xScaling = (float)getWidth()/800f;
    yScaling = (float)getHeight()/600f;
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
  
}
