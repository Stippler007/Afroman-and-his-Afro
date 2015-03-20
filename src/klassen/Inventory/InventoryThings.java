/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.Inventory;

import java.util.LinkedList;
import klassen.Background;
import klassen.karte.GameObject;
import klassen.karte.Rock;
import klassen.listener.MML;
import klassen.tower.Tower;

/**
 *
 * @author lukas
 */
public class InventoryThings {
    
    private GameObject[][] map;
    
    private InventoryDraw ivd;
    private LinkedList<Tower> tower;
    
    public InventoryThings(InventoryDraw ivd, LinkedList<Tower> tower)
    {
        this.ivd = ivd;
        this.tower = tower;
    }
    public void setMap(GameObject[][] map)
    {
        this.map = map;
    }
    public void chooseThings()
    {
        int x = ((MML.x-(int)Background.x));
        int y = ((MML.y-(int)Background.y));
        
        if(ivd.tas[ivd.getState()] != 0 && ivd.tasn[ivd.getState()] != 0)
        {
            if(map[x/25][y/25] == null && ivd.tas[ivd.getState()] == ivd.stone)
            {
                map[x/25][y/25] = new Rock();
                ivd.tasn[ivd.getState()]--;
            }
//            if(ivd.tas[ivd.getState()] == ivd.)
        }
        
        
    }
    
}
