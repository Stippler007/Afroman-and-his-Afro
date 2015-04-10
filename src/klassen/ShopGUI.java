package klassen;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import klassen.ImageFactory;
import klassen.Inventory.InventoryDraw;

/**
 *
 * @author lukas
 */
public class ShopGUI extends JPanel implements AdjustmentListener, ActionListener{
    
    private JFrame frame;
    private JScrollBar scrollBar;
    private int money = 4000;
    private JLabel moneyTxt;
    private JPanel panel;
    
    private int toTheTop = 0;
    
    private InventoryDraw ivd;
    
    private LinkedList<Integer> strongness = new LinkedList<>();
    private LinkedList<Integer> radius = new LinkedList<>();
    private LinkedList<JPanel> mainPanel = new LinkedList<>();
    private LinkedList<JLabel> pictures = new LinkedList<>();
    private LinkedList<JPanel> underPanel = new LinkedList<>();
    private LinkedList<JButton> btn = new LinkedList<>();
    private LinkedList<Integer> state = new LinkedList<>();
    private LinkedList<Integer> price = new LinkedList<>();
    
    public ShopGUI(InventoryDraw ivd)
    {
        this.ivd = ivd;
        frame = new JFrame("Shop");
        frame.setSize(400,400);
        frame.setLayout(new BorderLayout());
        
        scrollBar = new JScrollBar(JScrollBar.VERTICAL,0,200,0,1000);
        scrollBar.addAdjustmentListener(this);
        
        moneyTxt = new JLabel("Money: "+  money);
        moneyTxt.setHorizontalAlignment(JLabel.CENTER);
        moneyTxt.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(210,180,140)));
        frame.add(moneyTxt,BorderLayout.NORTH);
        
        this.setLayout(null);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollBar, BorderLayout.EAST);
        
        panel.add(this, BorderLayout.CENTER);
        
        frame.add(panel);
        
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
    }
    public void setFrameVisible(boolean visible)
    {
        frame.setVisible(visible);
    }
    public void addShopItem(String name, int state, int strongness, int radius, int price)
    {
        if(state == InventoryDraw.stone) pictures.add(new JLabel(new ImageIcon(ImageFactory.getImageFactory().getLooks("rock0"))));
        else if(state == InventoryDraw.tower1) pictures.add(new JLabel(new ImageIcon(ImageFactory.getImageFactory().getLooks("BasicTower"))));
        else return;
        
        this.state.add(state);
        this.strongness.add(strongness);
        this.radius.add(radius);
        this.price.add(price);
        
        mainPanel.add(new JPanel());
        underPanel.add(new JPanel());
        
        underPanel.get(underPanel.size()-1).setLayout(new GridLayout(2,1));
        underPanel.get(underPanel.size()-1).add(new JLabel("Damage: " + strongness));
        underPanel.get(underPanel.size()-1).add(new JLabel("Radius: " + strongness));
        
        btn.add(new JButton(price+"Y"));
        btn.get(btn.size()-1).addActionListener(this);
        
        mainPanel.get(mainPanel.size()-1).setLayout(new GridLayout(1,3));
        mainPanel.get(mainPanel.size()-1).setBorder(BorderFactory.createTitledBorder(name));
        mainPanel.get(mainPanel.size()-1).add(pictures.get(pictures.size()-1));
        mainPanel.get(mainPanel.size()-1).add(underPanel.get(underPanel.size()-1));
        mainPanel.get(mainPanel.size()-1).add(btn.get(btn.size()-1));
        
        drawThings();
    }
    
    public void drawThings()
    {
        moneyTxt.setText("Money: " + money);
        
        for (int i = 0; i < mainPanel.size(); i++) {
            
            mainPanel.get(i).setBounds(0,(i*100)-toTheTop,getWidth(), 100);
            
            if(price.get(i) > money) btn.get(i).setEnabled(false);
            else btn.get(i).setEnabled(true);
            
            this.add(mainPanel.get(i));
            
        }
        
        scrollBar.setVisibleAmount(1000-((100*mainPanel.size())-(getHeight())));
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        toTheTop = e.getValue();
        drawThings();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for (int i = 0; i < mainPanel.size(); i++) {
            
            if(btn.get(i) == e.getSource())
            {
                money -= price.get(i);
                
                for (int j = 0; j < 8; j++) {
                    
                    if(ivd.tas[j] == 0)
                    {
                        ivd.setTas(j, state.get(i), 1);
                        break;
                    }else if(ivd.tas[j] == state.get(i))
                    {
                        ivd.tasn[j]++;
                        break;
                    }
                    
                }
                
                drawThings();
            }
            
        }
        
    }
    
}
