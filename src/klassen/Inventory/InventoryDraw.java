package klassen.Inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class InventoryDraw {
    private int state = 0;
    private int maxState = 8;
    
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
        
        if(state < 1)
        {
            state = 7;
        }
    }
    public void setState(int input)
    {
        state = input;
    }
}