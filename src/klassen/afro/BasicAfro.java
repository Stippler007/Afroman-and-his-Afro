package klassen.afro;

import java.awt.ComponentOrientation;
import java.awt.Frame;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFrame;
import klassen.GUI;
import klassen.ImageFactory;
import klassen.Inventory.InventoryDraw;
import klassen.Inventory.InventoryThings;
import klassen.ShopGUI;

public class BasicAfro extends Afro
{
    InventoryDraw ivd;
    InventoryThings ivt;
    
    private ShopGUI shop;
    private GUI g;
    
  public BasicAfro(float x, float y, int team, InventoryDraw ivd, InventoryThings ivt, GUI g, ShopGUI shop)
  {
    super(x, y, team);
    look[0]=ImageFactory.getImageFactory().getLooks("afro1");
    
    this.ivd = ivd;
    this.ivt = ivt;
    this.g = g;
    this.shop = shop;
  }

    @Override
    public void newShop() {
        shop = new ShopGUI(ivd);
        
        shop.addShopItem("Stone", InventoryDraw.stone, 30, 1, 4);
        shop.addShopItem("Ultimative-Tower", InventoryDraw.tower1, 30, 500, 30);
    }
  
}
