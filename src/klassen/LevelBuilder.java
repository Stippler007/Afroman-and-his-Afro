/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package klassen;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
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
    //TODO: change Background
    public LevelBuilder() {
        JPanel controls = new JPanel();
        JComboBox<GameObject> cbSetGO = new JComboBox<>(new GameObject[]{new Rock(), new Gras()});
        JButton btNewMap = new JButton();
        JButton btSave = new JButton();
        JButton btLoad = new JButton();
        grid = new LevelGrid();
        
        grid.setCurrentGameObject(cbSetGO.getItemAt(0));
        
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
                    //System.out.println(dlg.getLevelWidth()+" "+dlg.getLevelHeight()+" "+dlg.getGround());
                    grid.resetMap(dlg.getLevelWidth(), dlg.getLevelHeight(), dlg.getGround());
                }
            }
        });
        btSave.setAction(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.showSaveDialog(LevelBuilder.this);
                File f = fc.getSelectedFile();
                
                if(f != null) {
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos.writeObject(grid.ground);
                        oos.writeObject(grid.layer);
                        oos.close();
                    } catch (IOException ex) {
                        System.out.println("Could not Save");
                    }
                }
            }
        });
        btLoad.setAction(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(LevelBuilder.this);
                File f = fc.getSelectedFile();
                
                if(f != null) {
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                        LevelBuilder.this.grid.ground = (GameObject[][]) ois.readObject();
                        LevelBuilder.this.grid.layer = (GameObject[][]) ois.readObject();
                        ois.close();
                        grid.repaint();
                    } catch (Exception ex) {
                        System.out.println("Could not load");
                    }
                }
            }
        });
        
        btNewMap.setText("New Map");
        btSave.setText("Save");
        btLoad.setText("Load");
        
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
    
    public GameObject[][] getGround() { return grid.ground; }
    public GameObject[][] getLayer() { return grid.layer; }
    
    public static void main(String[] args) {
        LevelBuilder builder = new LevelBuilder();
        builder.setVisible(true);
    }
}

class LevelGrid extends JPanel {
    
    private int width;
    private int height;
    private GameObject currentGO;
    GameObject[][] ground;
    GameObject[][] layer;
    
    private int padding = 10;
    private double scale = 1;
    private double translationX = 0;
    private double translationY = 0;

    public LevelGrid() {
        MouseAdapter ma = new MouseAdapter() {
            private int x0 = 0;
            private int y0 = 0;
            
            @Override
            public void mouseMoved(MouseEvent e) {
                x0 = e.getX();
                y0 = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                translationX = translationX+e.getX()-x0;
                translationY = translationY+e.getY()-y0;
                x0 = e.getX();
                y0 = e.getY();
                LevelGrid.this.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                int i = (int) ((e.getX()-padding-translationX)/(25*scale));
                int j = (int) ((e.getY()-padding-translationY)/(25*scale));
                System.out.println(i+" "+j+" "+e.getButton());
                
                if(e.getButton() == 1) {
                    layer[i][j] = currentGO;
                } else if(e.getButton() == 3) {
                    layer[i][j] = null;
                }
                
                LevelGrid.this.repaint();
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                    if(e.getUnitsToScroll() > 0) {
                        scale = scale*1.1;
                    } else {
                        scale = scale*0.9;
                    }
                    LevelGrid.this.repaint();
                }
            }
        };
        this.addMouseListener(ma);
        this.addMouseMotionListener(ma);
        this.addMouseWheelListener(ma);
    }
    
    public void setCurrentGameObject(GameObject go) { currentGO = go; }
    
    public void resetMap(int width, int height, GameObject go) {
        this.width = width;
        this.height = height;
        ground = new GameObject[height][width];
        layer = new GameObject[height][width];
        
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[i].length; j++) {
                ground[i][j] = go;
            }
        }
        System.out.println(ground);
        System.out.println(this.width);
        System.out.println(this.height);
        
        this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        
        if(ground != null) {
            Graphics2D g2d = (Graphics2D) g;
            
            g2d.translate(translationX, translationY);
            g2d.scale(scale, scale);
            
            for (int i = 0; i < ground.length; i++) {
                for (int j = 0; j < ground[i].length; j++) {
                    GameObject go = ground[i][j];
                    g2d.drawImage(go.getLook(), padding+i*25, padding+j*25, null);
                    
                    go = layer[i][j];
                    if(go != null) {
                        g2d.drawImage(go.getLook(), padding+i*25, padding+j*25, null);
                    }
                }
            }
        }
    }
}

class DlgNewMap extends JDialog {
    
    private JSpinner spWidth, spHeight;
    private JComboBox<GameObject> cbGround;
    private boolean ready = false;
    
    public DlgNewMap(JFrame owner) {
        super(owner, "Create New Map", true);
        
        JButton btCreate = new JButton();
        JButton btCancel = new JButton();

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
        
        btCreate.setText("Create");
        btCancel.setText("Cancel");
        
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
