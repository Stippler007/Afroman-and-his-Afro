/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Christian
 */
public class ImageFactory
{
  private static ImageFactory imageFactory;

  private HashMap<String,BufferedImage> looks=new HashMap<String, BufferedImage>();
  private ImageFactory(){
    try 
    {
      for (int i = 0; i < 1; i++) 
      {
          looks.put("gras"+i, ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/gameObjects/gras"+i+".png")));
      }
      for (int i = 0; i < 1; i++) 
      {
          looks.put("rock"+i, ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/gameObjects/stone"+i+".png")));
      }
//      for (int i = 0; i < 1; i++) {
//        looks.put("BasicAfro"+i, ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/afro/BasicAfro"+i+".png")));
//      }
      looks.put("BasicTower", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/towers/BasicTower.png")).getSubimage(0, 0, 50, 50));
      looks.put("BasicTowerShot", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/towers/BasicTowerShot.png")));
      looks.put("player", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/player/player0.png")));
      looks.put("enemy", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/enemy/enemy.png")));
      looks.put("spritzer", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/player/spritzer.png")));
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    }
  }

  public static ImageFactory getImageFactory()
  {
    if(imageFactory==null)
    {
      imageFactory=new ImageFactory();
    }
    return imageFactory;
  }

  public BufferedImage getLooks(String str)
  {
    return looks.get(str);
  }
  
}
