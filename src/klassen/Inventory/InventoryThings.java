/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.Inventory;

import java.awt.Rectangle;
import java.util.LinkedList;
import klassen.Background;
import klassen.ShopGUI;
import klassen.enemys.Enemy;
import klassen.karte.GameObject;
import klassen.karte.Rock;
import klassen.listener.MML;
import klassen.player.Spritzer;
import klassen.tower.BasicTower;
import klassen.tower.Tower;

/**
 *
 * @author lukas
 */
public class InventoryThings {

    private GameObject[][] map;

    private InventoryDraw ivd;
    private LinkedList<Tower> tower;
    private LinkedList<Enemy> enemy;
    private LinkedList<Spritzer> spritzer;
    private ShopGUI shop;
    
    public InventoryThings(InventoryDraw ivd, LinkedList<Tower> tower, LinkedList<Enemy> enemy, LinkedList<Spritzer> spritzer) {
        this.ivd = ivd;
        this.tower = tower;
        this.enemy = enemy;
        this.spritzer = spritzer;
    }

    public void setMap(GameObject[][] map) {
        this.map = map;
    }

    public void chooseThings() {
        int x = ((MML.x - (int) Background.x));
        int y = ((MML.y - (int) Background.y));

        Rectangle help;
        if(ivd.tas[ivd.getState()] < 0)
        {
            help = new Rectangle(MML.x,MML.y,50,50);
        }else help = new Rectangle(MML.x,MML.y,25,25);
        
        boolean weiter = true;
        for (Tower t : tower) {
            if (t.getBounding().intersects(help)) 
            {
                weiter = false;
            }
        }
        if(ivd.tas[ivd.getState()] == 0 || ivd.tasn[ivd.getState()] == 0 || map[x / 25][y / 25] != null)
        {
            weiter = false;
        }
        if(weiter)
        {
            if (ivd.tas[ivd.getState()] == InventoryDraw.stone) {
                map[x / 25][y / 25] = new Rock();
                ivd.tasn[ivd.getState()]--;
            }
            if (ivd.tas[ivd.getState()] == InventoryDraw.tower1) {
                tower.add(new BasicTower(MML.x,MML.y,300,40,enemy, spritzer));
                ivd.tasn[ivd.getState()]--;
            }
        }
        if(ivd.getState() == 7)
        {
            shop = new ShopGUI(ivd);
        }
    }

}
