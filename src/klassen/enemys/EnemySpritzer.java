package klassen.enemys;

import java.awt.Rectangle;
import klassen.player.Player;

public class EnemySpritzer
{
  
  private float x;
  private float y;
  
  private float speedX;
  private float speedY;
  
  private Rectangle bounding;

  public EnemySpritzer(float x, float y, float speedX, float speedY)
  {
    this.x = x;
    this.y = y;
    this.speedX = speedX;
    this.speedY = speedY;
    this.bounding = new Rectangle((int)x, (int)y, 15, 15);
  }
  
  public void update(float tslf)
  {
    x+=speedX*tslf;
    y+=speedY*tslf;
    
    x+=Player.speedX;
    y+=Player.speedY;
    
    bounding.x=(int)x;
    bounding.y=(int)y;
  }
  public Rectangle getBounding()
  {
    return bounding;
  }
  public float getX()
  {
    return x;
  }
  public float getY()
  {
    return y;
  }
    
}
