package klassen.Inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import klassen.ImageFactory;

public class InventoryDraw {
    private int state = 0;
    private int maxState = 8;
    public int tas[] = new int[8];          // Things at state
    public int tasn[] = new int[8];         // Things at state now
    
    public static final int stone = 1;
    public static final int tower1 = 2;
    
    private BufferedImage[] images = new BufferedImage[8];
    
    
    public void paintInventory(Graphics gr)
    {
        gr.setColor(new Color(218,165,32));
        gr.fillRect(258,0, 284, 40);
        
        gr.setColor(new Color(210,180,140));
        gr.fillRect((state*35)+258,0,40,40);
        
        gr.setColor(new Color(245,245,220));
        for(int i = 0; i < (284/35);i++)
        {
            gr.fillRect(((i*30)+((i+1)*5))+258, 5, 30, 30);
        }
        
        int digits;
        for(int i = 0; i<8; i++)
        {
            if(tasn[i] == 0)
            {
                images[i] = null;
                tas[i] = 0;
            }
            
            gr.drawImage(images[i], (i*35)+263, 5, 30, 30, null);
            
            digits = 0;
            if(tasn[i]/10 > 0) digits = 7;
            
            gr.setColor(Color.ORANGE);
            gr.drawString(""+tasn[i], (i*35)+284-digits, 32);
        }
    }
    public void stateUp()
    {
        state++;
        
        if(state > 7)
        {
            state = 0;
        }
    }
    public void stateDown()
    {
        state--;
        
        if(state < 0)
        {
            state = 7;
        }
    }
    public void setState(int input)
    {
        state = input;
    }
    public int getState()
    {
        return state;
    }
    public void setTas(int state,int thing, int value)
    {
        tas[state] = thing;
        tasn[state] = value;
        
        if(thing == stone) images[state] =ImageFactory.getImageFactory().getLooks("rock0");
        if(thing == tower1) images[state] =ImageFactory.getImageFactory().getLooks("BasicTower");
    }
}