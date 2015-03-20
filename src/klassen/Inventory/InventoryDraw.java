package klassen.Inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import klassen.karte.GameObject;
import klassen.karte.Rock;

public class InventoryDraw {
    private int state = 0;
    private int maxState = 8;
    public int tas[] = new int[8];          // Things at state
    public int tasn[] = new int[8];         // Things at state now
    
    public static final int stone = 0;
    
    protected ArrayList<GameObject> otherMapThings;
    
    public InventoryDraw(ArrayList<GameObject> otherMapThings)
    {
        this.otherMapThings = otherMapThings;
        
        setTas(0,stone,20);
    }
    
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
        
        otherMapThings.add(new Rock());
        otherMapThings.get(otherMapThings.size()-1).update(0, ((state*30)+((state+1)*5))+258, 5);
    }
}