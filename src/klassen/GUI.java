
package klassen;

import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import klassen.enemys.Enemy;
import klassen.enemys.EnemySpritzer;
import klassen.listener.KL;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;

/**
 *
 * @author Christian
 */
public class GUI extends JFrame
{
  private Canvas canvas;
  
  public GUI(Player player, LinkedList<PlayerSpritzer> playerSpritzers, LinkedList<EnemySpritzer> enemySpritzerses, LinkedList<Enemy> enemys,Background bg)
  {
    canvas=new Canvas(player, playerSpritzers, enemySpritzerses, enemys,bg);
    canvas.setBounds(0, 0, 800, 600);
    addKeyListener(new KL());
    add(canvas);
  }
  public void repaintScreen()
  {
    canvas.repaint();
  }
}
