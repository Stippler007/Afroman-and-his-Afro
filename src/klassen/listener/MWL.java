package klassen.listener;

import java.awt.event.MouseWheelEvent;
import static java.awt.event.MouseWheelEvent.WHEEL_UNIT_SCROLL;
import java.awt.event.MouseWheelListener;
import klassen.Inventory.InventoryDraw;

/**
 *
 * @author lukas
 */
public class MWL implements MouseWheelListener{

    private InventoryDraw iv;
    
    public MWL(InventoryDraw iv)
    {
        this.iv = iv;
    }
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() < 0)
        {
            iv.stateDown();
        }else
        {
            iv.stateUp();
        }
    }
}
