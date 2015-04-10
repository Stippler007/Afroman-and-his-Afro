/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package klassen;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.text.FieldPosition;
import java.text.ParsePosition;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import klassen.karte.GameObject;

/**
 *
 * @author Julian
 */
public class LevelBuilder extends JFrame{
 
    public LevelBuilder() {
        JPanel controls = new JPanel();
        JTextField width = new JTextField("30", 3);
        JTextField height = new JTextField("30", 3);
        JButton buildMap = new JButton("Build");
        
        controls.setLayout(new GridLayout(1, 5, 10, 10));
        controls.add(new JLabel("Width"));
        controls.add(width);
        controls.add(new JLabel("Height"));
        controls.add(height);
        controls.add(buildMap);
        
        this.add(controls);
        this.add(new LevelGrid());
        this.setSize(800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        LevelBuilder builder = new LevelBuilder();
        builder.setVisible(true);
    }
}

class LevelGrid extends JPanel {
    
    private GameObject[][] map;
    
    public LevelGrid() {
        this.setLayout(new BorderLayout(5, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }
}
