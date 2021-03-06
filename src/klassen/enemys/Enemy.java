package klassen.enemys;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import klassen.Background;
import klassen.ImageFactory;
import klassen.karte.GameObject;
import klassen.player.Player;
import klassen.player.Spritzer;
import klassen.tower.Tower;

public abstract class Enemy
{
  protected float x;
  protected float y;
  
  protected float live=100;
  protected float maxLive=100;
  
  protected float speedX;
  protected float speedY;
  protected int speed;
  
  protected LinkedList<Spritzer> playerSpritzers;
  protected LinkedList<Enemy> enemys;
  protected Player player;
  protected Rectangle bounding;
  
  protected LinkedList<Integer> zielX=new LinkedList<>();
  protected LinkedList<Integer> zielY=new LinkedList<>();
  
  private float knockbackX;
  private float knockbackY;
  private float backKnockback=600;
  private boolean knockback=false;
  
  protected LinkedList<Tower> towers;
  
  protected static GameObject map[][];
  
  protected Color color;
  protected Status status=Status.MOVING;
  
  protected BufferedImage look[]=new BufferedImage[1];
  
  public enum Status
  {
    MOVING,ATTACKING;
  }
  
  public Enemy(float x, float y, int speed, 
          LinkedList<Spritzer> playerSpritzers,LinkedList<Enemy> enemys,
          LinkedList<Tower> towers,Player player, Rectangle bounding)
  {
    for (int i = 0; i < look.length; i++)
    {
      look[i]=ImageFactory.getImageFactory().getLooks("enemy");
    }
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.playerSpritzers = playerSpritzers;
    this.bounding = bounding;
    this.player=player;
    this.enemys=enemys;
    this.towers=towers;
    this.status=Status.MOVING;
  }
  
  public void addZiel(int x,int y)
  {
    x+=Background.x;
    y+=Background.y;
    zielX.add(x);
    zielY.add(y);
  }
  
  public static void setMap(GameObject[][] map) 
  {
    Enemy.map=map;
  }
  
  public void setColor(Color color)
  {
    this.color = color;
  }

  public void setKnockback(boolean knockback)
  {
    this.knockback = knockback;
  }
  
  public void update(float tslf)
  {
    speedX=0;
    speedY=0;
    
//    for (int zx:zielX) 
//    {
//      zx+=Player.speedX;
//    }
//    for (int zy:zielY) 
//    {
//      zy+=Player.speedY;
//    }
    
    switch(status)
    {
      case ATTACKING:
        break;
      case MOVING: updateMoving(tslf);
        break;
    }
    
    x+=speedX*tslf;
    y+=speedY*tslf;
    
    x+=Player.speedX;
    y+=Player.speedY;
    
    if(knockback)moveKnockBack(tslf);
    
    bounding.x=(int)x;
    bounding.y=(int)y;
    
    collide();
  }
  public abstract void updateAttack(float tslf);
  public void updateMoving(float tslf)
  {
    if(zielX.size()!=zielY.size())
    {
      Exception ex=new Exception("ERROR: zielX and zielY Differ");
      System.out.println(ex.getMessage());
    }
    else if(!zielX.isEmpty())
    {
      moveZiel((float)zielX.get(0),(float)zielY.get(0),speed);
      if(x<zielX.get(0)&&x>zielX.get(0)+bounding.width&&y<zielY.get(0)&&y>zielY.get(0)+bounding.height)
      {
        zielX.remove(0);
        zielY.remove(0);
      }
    }
    else
    {
      moveZiel(player.getX()+player.getBounding().width/2,
               player.getY()+player.getBounding().height/2,
               speed);
    }
  }
  
  protected void moveZiel(float zielX,float zielY,int speed)
  {
    float speedX = (zielX) - (x+bounding.width/2);
    float speedY = (zielY) - (y+bounding.height/2);
    
    float help = (float)Math.sqrt(speedX*speedX+speedY*speedY);
    
    speedX/=help;
    speedY/=help;
    
    speedX*=speed;
    speedY*=speed;
    
    this.speedX+=speedX;
    this.speedY+=speedY;
  }
  private void moveKnockBack(float tslf)
  {
    if(knockbackX!=0)
    {
      if(knockbackX>0)
      {
        knockbackX-=backKnockback*tslf;
        if(knockbackX<0)knockbackX=0;
      }
      else{
        knockbackX+=backKnockback*tslf;
        if(knockbackX>0)knockbackX=0;
      }
    }
    
    if(knockbackY!=0)
    {
      if(knockbackY<0)
      {
        knockbackY+=backKnockback*tslf;
        if(knockbackY>0)knockbackY=0;
      }
      else{
        knockbackY-=backKnockback*tslf;
        if(knockbackY<0)knockbackY=0;
      }
    }
    x+=knockbackX*tslf;
    y+=knockbackY*tslf;
  }
  private void knockBack(Rectangle rect)
  {
    if(rect.intersects(bounding))
    {
      
      int nachrechts=(int)(rect.x+rect.width)-bounding.x;
      int nachlinks=(int)(bounding.x+bounding.width)-rect.x;
      int nachunten=(int)(rect.y+rect.height)-bounding.y;
      int nachoben=(int)(bounding.y+bounding.height)-rect.y;
      
      
      if(nachrechts<nachlinks&&nachrechts<nachoben&&nachrechts<nachunten)
      {
        x+=nachrechts;
        knockbackX=300;
      }
      else if(nachlinks<nachoben&&nachlinks<nachunten)
      {
        x-=nachlinks;
        knockbackX=-300;
      }
      else if(nachoben<nachunten)
      {
        y-=nachoben;
        knockbackY=-300;
      }
      else if(nachoben>nachunten)
      {
        y+=nachunten;
        knockbackY=300;
      }
//      int vonunten=(int) ;
    }
  }
  protected void collide()
  {
    collisionEnemy();
    rebound(player.getBounding());
    for (int i =(int)((Background.x/25*-1)+((x/25)-2)); i <= (int)((Background.x/25*-1)+((x/25)+2)); i++) 
    {
      for (int j = (int)((Background.y/25*-1)+((y/25-2))); j <= (int)((Background.y/25*-1)+((y/25+2))); j++) 
      {
        if(!(i<0)&&!(j<0)&&!(i>map.length-1)&&!(j>map[0].length-1)&&map[i][j]!=null&&map[i][j].isSolid())
        {
          Rectangle help2=map[i][j].getBounding();
          rebound(help2);
        }
      }
    }
    for (Tower t : towers) 
    {
      if(t.getBounding().intersects(bounding))
      {
        rebound(t.getBounding());
      }
    }
  }
  protected boolean collidePlayerSpritzer()
  {
    int i=0;
    while(i<playerSpritzers.size())
    {
      Rectangle help1=playerSpritzers.get(i).getBounding();
      Rectangle help2=bounding;
      if(help1.intersects(help2))
      {
        if(knockback)knockBack(help1);
        playerSpritzers.remove(i);
        return true;
      }
      else
      {
        i++;
      }
    }
    return false;
  }
  protected void collisionEnemy()
  {
    int i=0;
    while(i<enemys.size())
    {
      Enemy e=enemys.get(i);
      if(e.getBounding().intersects(bounding))
      {
        rebound(e.getBounding());
      }
      i++;
    }
  }
  protected void rebound(Rectangle rect)
  {
    if(bounding.intersects(rect))
    {
      int nachrechts=(int)(rect.x+rect.width)-bounding.x;
      int nachlinks=(int)(bounding.x+bounding.width)-rect.x;
      int nachunten=(int)(rect.y+rect.height)-bounding.y;
      int nachoben=(int)(bounding.y+bounding.height)-rect.y;
      
      if(nachrechts<nachlinks&&nachrechts<nachoben&&nachrechts<nachunten)
      {
        x+=nachrechts;
      }
      else if(nachlinks<nachoben&&nachlinks<nachunten)
      {
        x-=nachlinks;
      }
      else if(nachoben<nachunten)
      {
        y-=nachoben;
      }
      else if(nachoben>nachunten)
      {
        y+=nachunten;
      }
    }
    bounding.x=(int)x;
    bounding.y=(int)y;
  }

  public float getLive()
  {
    return live;
  }
  public double getTurn()
  {
    double a=speedX;
    double b=speedY;

    double turn=Math.atan(b/a);
    if(a<0){
      turn+=Math.PI;
    }
    return turn; 
  }
  public float getMaxLive()
  {
    return maxLive;
  }
  
  public Color getColor()
  {
    return color;
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
  public BufferedImage getLook() 
  {
    return look[0];
  }
}
