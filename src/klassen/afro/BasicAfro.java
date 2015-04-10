package klassen.afro;

import klassen.ImageFactory;
import klassen.Inventory.InventoryDraw;
import klassen.Inventory.InventoryThings;
import klassen.ShopGUI;

public class BasicAfro extends Afro
{
    InventoryDraw ivd;
    InventoryThings ivt;
    
    private ShopGUI shop;
    
  public BasicAfro(float x, float y, int team, InventoryDraw ivd, InventoryThings ivt)
  {
    super(x, y, team);
    look[0]=ImageFactory.getImageFactory().getLooks("afro1");
    
    this.ivd = ivd;
    this.ivt = ivt;
  }

    @Override
    public void newShop() {
        shop = new ShopGUI(ivd);
        
        shop.addShopItem("Stone", InventoryDraw.stone, 30, 1, 4);
    }
  
}
