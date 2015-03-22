/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Stippler
 */
public class GameMenu extends JPanel implements Runnable
{
  private JPanel paButtons;
  private JButton btNewGame;
  private JButton btLoadGame;
  private JButton btMultiplayer;
  private JButton btOptions;
  
  private JPanel paLogo; //Logo und Animationen
  private JPanel paAnimations;
  private JButton btCredits;
  
  
  private Thread animateLogo;
  
  public GameMenu() 
  {
    this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
    this.setLayout(new GridLayout(1, 0));
    
    paLogo=new JPanel();
    paButtons=new JPanel();
    
    this.add(paLogo);
    this.add(paButtons);
    
    paButtons.setLayout(new GridLayout(0,1));
    
    btNewGame=new JButton("New Game");
    btLoadGame=new JButton("Load Game");
    btMultiplayer=new JButton("Multiplayer");
    btOptions=new JButton("Play Game");
    
    btNewGame.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, Color.gray, Color.lightGray, Color.gray, Color.lightGray));
    btLoadGame.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, Color.gray, Color.lightGray, Color.gray, Color.lightGray));
    btMultiplayer.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, Color.gray, Color.lightGray, Color.gray, Color.lightGray));
    btOptions.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, Color.gray, Color.lightGray, Color.gray, Color.lightGray));
    
    btNewGame.setFont(new java.awt.Font("Tahoma", 0, 24));
    btLoadGame.setFont(new java.awt.Font("Tahoma", 0, 24));
    btMultiplayer.setFont(new java.awt.Font("Tahoma", 0, 24));
    btOptions.setFont(new java.awt.Font("Tahoma", 0, 24));
    
    paButtons.add(btNewGame);
    paButtons.add(btLoadGame);
    paButtons.add(btMultiplayer);
    paButtons.add(btOptions);
    
    
    paLogo.setLayout(new BorderLayout());
    paAnimations=new JPanel();
    btCredits=new JButton();
    btCredits.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ex_001_Hello_World/AfroMan_logo3.png")));
    btCredits.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        onBtCredits(evt);
      }
    });
    
    paLogo.add(paAnimations, java.awt.BorderLayout.CENTER);
    paLogo.add(btCredits,java.awt.BorderLayout.PAGE_START);
    
    
    
    
  }
  
  private void onBtCredits(ActionEvent event)
  {
    Graphics2D g=(Graphics2D)paAnimations.getGraphics();
    g.drawString("Afroman",20,100);
    g.drawString("By Christian, Lukas, Paul and Julian",20,120);
    
    animateLogo=new Thread(this);
    animateLogo.start();
  }
  
  @Override
  public void run() 
  {
    Graphics2D g=(Graphics2D)paAnimations.getGraphics();
    int x=0;
    int y=300;
    while(true)
    {
      g.setColor(randomColor());
      g.fillRect(x, y, 50, 50);
      x+=3;
      if(x>paAnimations.getWidth())x=-50;
      try {Thread.sleep(30);} catch (InterruptedException ex) {}
    }
  }
  private Color randomColor()
  {
    Random r=new Random();
    Color c=Color.black;
    switch(r.nextInt(7))
    {
      case 0:c=Color.RED;
        break;
      case 1:c=Color.yellow;
        break;
      case 2:c=Color.pink;
        break;
      case 3:c=Color.ORANGE;
        break;
      case 4:c=Color.BLUE;
        break;
      case 5:c=Color.GREEN;
        break;
      case 6:c=Color.cyan;
        break;
    }
    return c;
  }
}
