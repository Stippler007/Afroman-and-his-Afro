/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import klassen.Main;

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
  private JButton btClose;
  
  private JPanel paLogo; //Logo und Animationen
  private JPanel paLogo2;
  private JPanel paHeader;
  
  private JPanel paAnimations;
  private JButton btCredits;
  
  
  
  private Thread animateLogo;
  
  public GameMenu(Frame frame) 
  {
    this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
    this.setLayout(new BorderLayout());
    
    paLogo=new JPanel();
    paButtons=new JPanel();
    paHeader = new JPanel();
    
    paLogo.setBackground(Color.WHITE);
    paButtons.setBackground(Color.WHITE);
    paHeader.setBackground(Color.WHITE);
    
    this.add(paLogo, BorderLayout.CENTER);
    this.add(paButtons, BorderLayout.SOUTH);
    this.add(paHeader, BorderLayout.NORTH);
    
    
    paButtons.setLayout(new GridLayout(1,4));
    
    btNewGame=new JButton("New Game");
    btLoadGame=new JButton("Load Game");
    btMultiplayer=new JButton("Multiplayer");
    btOptions=new JButton("Options");
    
//    btNewGame.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, Color.gray, Color.lightGray, Color.gray, Color.lightGray));
//    btLoadGame.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, Color.gray, Color.lightGray, Color.gray, Color.lightGray));
//    btMultiplayer.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, Color.gray, Color.lightGray, Color.gray, Color.lightGray));
//    btOptions.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, Color.gray, Color.lightGray, Color.gray, Color.lightGray));
    
    btNewGame.setFont(new java.awt.Font("Tahoma", 0, 50));
    btLoadGame.setFont(new java.awt.Font("Tahoma", 0, 50));
    btMultiplayer.setFont(new java.awt.Font("Tahoma", 0, 50));
    btOptions.setFont(new java.awt.Font("Tahoma", 0, 50));
    
    btNewGame.setOpaque(false);
    btNewGame.setContentAreaFilled(false);
    btNewGame.setBorderPainted(false);
    btNewGame.setMargin(new Insets(1,1,30,1));
    btNewGame.setFocusPainted(false);
    
    btLoadGame.setOpaque(false);
    btLoadGame.setContentAreaFilled(false);
    btLoadGame.setBorderPainted(false);
    btLoadGame.setMargin(new Insets(1,1,30,1));
    btLoadGame.setFocusPainted(false);
    
    btMultiplayer.setOpaque(false);
    btMultiplayer.setContentAreaFilled(false);
    btMultiplayer.setBorderPainted(false);
    btMultiplayer.setMargin(new Insets(1,1,30,1));
    btMultiplayer.setFocusPainted(false);
    
    btOptions.setOpaque(false);
    btOptions.setContentAreaFilled(false);
    btOptions.setBorderPainted(false);
    btOptions.setMargin(new Insets(1,1,30,1));
    btOptions.setFocusPainted(false);
    
            
    btNewGame.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            new Main();
        }
    });
    
    paButtons.add(btNewGame);
    paButtons.add(btLoadGame);
    paButtons.add(btMultiplayer);
    paButtons.add(btOptions);
   
    paLogo.setLayout(new BorderLayout());
    try
    {
      Image icon = ImageIO.read(getClass().getResourceAsStream("/gfx/afro/logo0.png"));
      
      paLogo2 = new JPanel(){
          public void paint(Graphics g)
          {
              try {
                  BufferedImage bi = ImageIO.read(getClass().getResourceAsStream("/gfx/afro/logo0.png"));
                  g.drawImage(bi, frame.getWidth()/6, frame.getHeight()/12,frame.getWidth()-frame.getWidth()/3,frame.getHeight()-frame.getHeight()/3, null);
                  
              } catch (IOException ex) {
                  Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
      };
      
      paLogo.add(paLogo2, BorderLayout.CENTER);
    } catch (Exception ex)
    {
      Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    paHeader.setLayout(new BorderLayout());
    btClose = new JButton("Close");
    btClose.setFont(new java.awt.Font("Tahoma", 0, 30));
    btClose.setOpaque(false);
    btClose.setContentAreaFilled(false);
    btClose.setBorderPainted(false);
    btClose.setMargin(new Insets(10,1,1,10));
    btClose.setFocusPainted(false);
    
    btClose.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
       System.exit(0); 
      }
    });
    
    paHeader.add(btClose, BorderLayout.EAST);
    
    
    
    paAnimations=new JPanel();
    btCredits=new JButton("Credits");
    btCredits.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        onBtCredits(evt);
      }
    });
    
    btCredits.setFont(new java.awt.Font("Tahoma", 0, 30));
    btCredits.setOpaque(false);
    btCredits.setContentAreaFilled(false);
    btCredits.setBorderPainted(false);
    btCredits.setMargin(new Insets(10,10,1,1));
    btCredits.setFocusPainted(false);
    
    paAnimations.setBackground(Color.WHITE);
    
    paHeader.add(paAnimations, BorderLayout.CENTER);
    paHeader.add(btCredits,BorderLayout.WEST);
  }
  
  private void onBtCredits(ActionEvent event)
  {
    Graphics2D g=(Graphics2D)paAnimations.getGraphics();
    g.drawString("Afroman",20,20);
    g.drawString("By Christian, Lukas, Paul and Julian",20,45);
    
    animateLogo=new Thread(this);
    animateLogo.start();
  }
  
  @Override
  public void run() 
  {
    Graphics2D g=(Graphics2D)paAnimations.getGraphics();
    int x=250;
    int y=0;
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
