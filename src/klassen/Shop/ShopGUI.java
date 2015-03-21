package klassen.Shop;

import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author lukas
 */
public class ShopGUI extends JPanel implements AdjustmentListener{
    
    private JFrame frame;
    private JScrollBar scrollBar;
    
    public ShopGUI()
    {
        frame = new JFrame("Shop");
        frame.setSize(400,400);
        
        scrollBar = new JScrollBar();
        scrollBar.addAdjustmentListener(this);
        scrollBar.setValue(2);
        scrollBar.setMaximum(20);
        
//        System.out.println(scrollBar.getMaximum());
        
        this.setLayout(new BorderLayout());
        this.add(scrollBar, BorderLayout.EAST);
        
        frame.add(this);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    public void setFrameVisible(boolean visible)
    {
        frame.setVisible(visible);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        System.out.println(e.getValue());
    }
    
}
