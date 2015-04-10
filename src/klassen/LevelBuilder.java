/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package klassen;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.FieldPosition;
import java.text.ParsePosition;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import klassen.karte.GameObject;
import klassen.karte.Gras;
import klassen.karte.Rock;

/**
 *
 * @author Julian
 */
public class LevelBuilder extends JFrame{
 
    private int width = 30;
    private int height = 30;
    
    public LevelBuilder() {
        JPanel controls = new JPanel();
        JButton btNewMap = new JButton("New Map");
        
        controls.setLayout(new GridLayout(1, 5, 10, 10));
        controls.add(new JLabel("Width: "+width));
        controls.add(new JLabel("Height: "+height));
        controls.add(btNewMap);
        
        btNewMap.setAction(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DlgNewMap dlg = new DlgNewMap(LevelBuilder.this);
                dlg.setVisible(true);
                
                if(dlg.isReady()) {
                    width = dlg.getWidth();
                    height = dlg.getHeight();
                    onCreateMap(dlg.getGround());
                }
            }
        });
        
        this.setLayout(new BorderLayout(5, 5));
        this.setSize(800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(controls, BorderLayout.NORTH);
        this.add(new LevelGrid(), BorderLayout.CENTER);
    }
    
    private void onCreateMap(GameObject ground) {
        System.out.println(ground.toString());
    }
    
    public static void main(String[] args) {
        LevelBuilder builder = new LevelBuilder();
        builder.setVisible(true);
    }
}

class LevelGrid extends JPanel {
    
    private GameObject[][] map;
    
    public LevelGrid() {
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }
}

class DlgNewMap extends JDialog {
    
    private JSpinner spWidth, spHeight;
    private JComboBox<GameObject> cbGround;
    private boolean ready = false;
    
    public DlgNewMap(JFrame owner) {
        super(owner, "Create New Map", true);
        
        JButton btCreate = new JButton("Create");
        JButton btCancel = new JButton("Cancel");
        
        spWidth = new JSpinner(new SpinnerNumberModel(30, 10, 500, 1));
        spHeight = new JSpinner(new SpinnerNumberModel(30, 10, 500, 1));
        cbGround = new JComboBox<>(new GameObject[]{new Gras(), new Rock()});
        
        btCreate.setAction(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ready = true;
                DlgNewMap.this.setVisible(false);
            }
        });
        btCancel.setAction(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DlgNewMap.this.setVisible(false);
            }
        });
        
        this.setLayout(new GridLayout(5, 1, 5, 5));
        this.add(spWidth);
        this.add(spHeight);
        this.add(cbGround);
        this.add(btCreate);
        this.add(btCancel);
    }
    
    public boolean isReady() { return ready; }
    
    public int getLevelWidth() { return (Integer) spWidth.getValue(); }
    
    public int getLevelHeight() { return (Integer) spHeight.getValue(); }
    
    public GameObject getGround() { return (GameObject) cbGround.getSelectedItem(); }
    
}
