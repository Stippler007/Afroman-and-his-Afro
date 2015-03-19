package klassen.player;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import klassen.Background;
import klassen.enemys.EnemySpritzer;
import klassen.karte.GameObject;
import klassen.listener.KL;

public class Player
{
  private float x;
  private float y;
  
  public static float speedX;
  public static float speedY;
  
  private float maxLive = 100;
  private float live = maxLive;
  
  private float realoadTime=0;
  private float maxRealoadTime=0.3f;
  
  private int speed;
  private LinkedList<PlayerSpritzer> playerSpritzers;
  private LinkedList<EnemySpritzer> enemySpritzer;
  private Rectangle bounding;
  
  private boolean move=false;
  private GameObject[][] map;
  
  
  
  public Player(float x, float y, int speed, LinkedList<PlayerSpritzer> playerSpritzers, LinkedList<EnemySpritzer> enemySpritzer)
  {
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.playerSpritzers = playerSpritzers;
    this.bounding=new Rectangle((int)x,(int)y,25,25);
    this.enemySpritzer = enemySpritzer;
  }
  public void setMap(GameObject[][] map)
  {
    this.map = map;
  }
  public void update(float tslf)
  {
    move(tslf);
    
    if(x>800-bounding.width)x=800-bounding.width;
    else if(x<0)x=0;
    if(y>600-bounding.height)y=600-bounding.height;
    else if(y<0)y=0;
    
    
    if(realoadTime>maxRealoadTime)
    {
      if(KL.keys[KeyEvent.VK_UP])
      {
        playerSpritzers.add(new PlayerSpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0, -speed*3));
        realoadTime-=maxRealoadTime;
      }
      else if(KL.keys[KeyEvent.VK_DOWN])
      {
        playerSpritzers.add(new PlayerSpritzer(x+bounding.width/2-7, y+bounding.width/2-7, 0, speed*3));
        realoadTime-=maxRealoadTime;
      }
      else if(KL.keys[KeyEvent.VK_LEFT])
      {
        playerSpritzers.add(new PlayerSpritzer(x+bounding.width/2-7, y+bounding.width/2-7, -speed*3, 0));
        realoadTime-=maxRealoadTime;
      }
      else if(KL.keys[KeyEvent.VK_RIGHT])
      {
        playerSpritzers.add(new PlayerSpritzer(x+bounding.width/2-7, y+bounding.width/2-7, speed*3, 0));
        realoadTime-=maxRealoadTime;
      }
    }
    else if(realoadTime<=maxRealoadTime)
    {
      realoadTime+=tslf;
    }
    
    
    collide();
    
    if(Background.x+speedX+((400-bounding.width/2)-x)>0)
    {
      x-=speedX;
      speedX=0;
    }
    else if((Background.x+speedX)*-1+800+(x-(400-bounding.width/2))>(map.length-1)*25)
    {
      x-=speedX;
      speedX=0;
    }
    else
    {
      x=400-bounding.width/2;
    }
    if(Background.y+speedX+((300-bounding.width/2)-y)>0)
    {
      y-=speedY;
      speedY=0;
    }
    else if((Background.y+speedY)*-1+600+(y-(300-bounding.height/2))>(map[0].length-1)*25)
    {
      y-=speedY;
      speedY=0;
    }
    else
    {
      y=300-bounding.height/2;
    }
    
    bounding.x=(int)x;
    bounding.y=(int)y;
  }
  private void move(float tslf)
  {
    speedX=0;
    speedY=0;
    
    if(KL.keys[KeyEvent.VK_A])
    {
      speedX=speed*tslf;
      move=true;
    }
    if(KL.keys[KeyEvent.VK_D])
    {
      speedX=-speed*tslf;
      move=true;
    }
    if(KL.keys[KeyEvent.VK_W])
    {
      speedY=speed*tslf;
      move=true;
    }
    if(KL.keys[KeyEvent.VK_S])
    {
      speedY=-speed*tslf;
      move=true;
    }
  }
  public void collide()
  {
    if(collideEnemySpritzer())
    {
      live -= 5;
    }
    collideMap();
  }
  private void collideMap()
  {
    for (int i =(int)((Background.x/25*-1)+((x/25)-2)); i <= (int)((Background.x/25*-1)+((x/25)+2)); i++) 
    {
      for (int j = (int)((Background.y/25*-1)+((y/25-2))); j <= (int)((Background.y/25*-1)+((y/25+2))); j++) 
      {
        Rectangle help1=new Rectangle((int)(x-(speedX)),(int)(y-(speedY)),bounding.width,bounding.height);
        
        if(!(i<0)&&!(j<0)&&map[i][j]!=null&&map[i][j].isSolid()&&help1.intersects(map[i][j].getBounding()))
        {
          Rectangle help2=map[i][j].getBounding();
          rebound(help1, help2);
        }
      }
    }
      
  }
  private boolean collideEnemySpritzer()
  {
    int i=0;
    while(i<enemySpritzer.size())
    {
      Rectangle help1=enemySpritzer.get(i).getBounding();
      Rectangle help2=bounding;
      if(help1.intersects(help2))
      {
        enemySpritzer.remove(i);
        return true;
      }
      else
      {
        i++;
      }
    }
    return false;
  }
  public void rebound(Rectangle help1,Rectangle help2)
  {
    double vonlinks=help1.x+help1.width-help2.x;
    double vonoben=help1.y+help1.height-help2.y;
    double vonrechts=help2.x+help2.width-help1.x;
    double vonunten=help2.y + help2.height - help1.y;
    
    if(vonlinks<vonoben&&vonlinks<vonrechts&&vonlinks<vonunten)
    {
      speedX+=vonlinks;
    }
    else if(vonoben<vonrechts&&vonoben<vonunten)
    {
      speedY+=vonoben;
    }
    else if(vonrechts<vonunten)
    {
      speedX-=vonrechts;
    }
    else
    {
      speedY-=vonunten;
    }
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
  public float getLive()
  {
      return live;
  }
  public float getMaxLive()
  {
      return maxLive;
  }
}
