/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.Inventory;

import klassen.Background;
import klassen.karte.GameObject;
import klassen.karte.Rock;
import klassen.listener.MML;

/**
 *
 * @author lukas
 */
public class InventoryThings {
    
    private GameObject[][] map;
    private InventoryDraw ivd;
    
    public InventoryThings(InventoryDraw ivd)
    {
        this.ivd = ivd;
    }
    public void setMap(GameObject[][] map)
    {
        this.map = map;
    }
    public void chooseThings()
    {
        int x = ((MML.x-(int)Background.x)/25);
        int y = ((MML.y-(int)Background.y)/25);
        
        if(ivd.tas[ivd.getState()] != 0 && ivd.tasn[ivd.getState()] != 0 && ivd.tas[ivd.getState()] == ivd.stone)
        {
            if(map[x][y] == null) map[x][y] = new Rock();
            ivd.tasn[ivd.getState()]--;
        }
    }
    
}
