package klassen.Inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class InventoryDraw {
    private int state = 0;
    
    public void paintInventory(Graphics gr)
    {
        gr.setColor(new Color(218,165,32));
        gr.fillRect(100,0, 600, 40);
        
        gr.setColor(new Color(210,180,140));
        gr.fillRect((state*30)+100,0,40,40);
        
        gr.setColor(new Color(245,245,220));
        for(int i = 0; i < (600/35);i++)
        {
            gr.fillRect(((i*30)+((i+1)*5))+100, 5, 30, 30);
        }
    }
    public void stateUp()
    {
        state++;
    }
    public void stateDown()
    {
        state--;
    }
    public void setState(int input)
    {
        state = input;
    }
}