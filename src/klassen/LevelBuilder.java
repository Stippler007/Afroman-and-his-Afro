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
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private LevelGrid grid;
    
    public LevelBuilder() {
        JPanel controls = new JPanel();
        JComboBox<GameObject> cbSetGO = new JComboBox<>(new GameObject[]{new Gras(), new Rock()});
        JButton btNewMap = new JButton("New Map");
        JButton btSave = new JButton("Save");
        JButton btLoad = new JButton("Load");
        grid = new LevelGrid();
        
        cbSetGO.setAction(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                grid.setCurrentGameObject((GameObject) cbSetGO.getSelectedItem());
            }
        });
        btNewMap.setAction(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DlgNewMap dlg = new DlgNewMap(LevelBuilder.this);
                dlg.setVisible(true);
                
                if(dlg.isReady()) {
                    System.out.println(dlg.getWidth()+" "+dlg.getHeight()+" "+dlg.getGround());
                    grid.resetMap(dlg.getWidth(), dlg.getHeight(), dlg.getGround());
                }
            }
        });
        
        controls.setSize(controls.getWidth(), 20);
        controls.setLayout(new GridLayout(1, 5, 10, 10));
        controls.add(cbSetGO);
        controls.add(btNewMap);
        controls.add(btSave);
        controls.add(btLoad);
        
        this.setLayout(new BorderLayout(5, 5));
        this.setSize(800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(controls, BorderLayout.NORTH);
        this.add(grid, BorderLayout.CENTER);
    }
    
    public GameObject[][] getMap() { return grid.map; }
    
    public static void main(String[] args) {
        LevelBuilder builder = new LevelBuilder();
        builder.setVisible(true);
    }
}

class LevelGrid extends JPanel {
    
    private int width;
    private int height;
    private GameObject currentGO;
    GameObject[][] map;
    
    public LevelGrid() {
        
    }

    public void setCurrentGameObject(GameObject go) { currentGO = go; }
    
    public void resetMap(int width, int height, GameObject go) {
        this.width = width;
        this.height = height;
        map = new GameObject[width][height];
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = go;
            }
        }
        System.out.println(map);
        System.out.println(this.width);
        System.out.println(this.height);
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
        
        this.setSize(200, 200);
        this.setLayout(new GridLayout(5, 1, 5, 5));
        this.setLocationRelativeTo(null);
        this.add(spWidth);
        this.add(spHeight);
        this.add(cbGround);
        this.add(btCreate);
        this.add(btCancel);
    }
    
    public boolean isReady() { return ready; }
    
    public int getLevelWidth() { 
        try {
            System.out.println("H"+spWidth.getModel().getValue());
            spWidth.commitEdit();
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            spWidth.setValue(30);
        }
        return (Integer) spWidth.getValue(); 
    }
    
    public int getLevelHeight() { 
        try {
            spHeight.commitEdit();
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            spHeight.setValue(30);
        }
        return (Integer) spHeight.getValue(); 
    }
    
    public GameObject getGround() { return (GameObject) cbGround.getSelectedItem(); }
    
}
