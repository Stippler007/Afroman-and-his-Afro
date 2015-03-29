package klassen;

import javax.swing.JFrame;

public class Frame extends JFrame{
    
    private boolean fullscreen = false;
    
    public Frame()
    {
        setSize(800, 500);
        setUndecorated(true);
        
        add(new GameMenu(this));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        setFullscreen();
    }
    
    public static void main(String[] args) {
        new Frame();
    }
    
    public void setFullscreen()
    {
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    
        fullscreen=true;
    }
}
