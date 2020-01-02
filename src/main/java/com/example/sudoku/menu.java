package com.example.sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class menu extends JPanel {


    private FrameSet frameSet = null;

    public menu() {
        frameSet = new FrameSet();
        initComponents();
        frameSet.setTitle("目錄");
        frameSet.add(this);//?b???????s?W???Op1
        this.setVisible(true);
    }
    public menu(FrameSet frameset){
        frameSet = frameset;
        initComponents();
        this.setVisible(true);
        frameSet.setTitle("目錄");
        frameSet.add(this);//?b???????s?W???Op1
        frameSet.setVisible(true);//?????O
    }
    private void initComponents(){
        frameSet.add(this);
        this.setLayout(null);
        JButton btn1 = new JButton("簡單");
        btn1.setBounds(90, 30, 200, 50);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                easy singleMarch = new easy(easy.Mode_simpleAI,frameSet);
                setVisible(false);
                frameSet.add(singleMarch);
                frameSet.setVisible(true);

            }
        });
        this.add(btn1);

        JButton btn2 = new JButton("困難");
        btn2.setBounds(90, 100, 200, 50);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                middle singleMarch = new middle(middle.Mode_simpleAI,frameSet);
                setVisible(false);
                frameSet.add(singleMarch);
                frameSet.setVisible(true);
            }
        });
        this.add(btn2);

        /* JButton btn3 = new JButton("困難");
        btn3.setBounds(90, 170, 200, 50);
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new hard(frameSet);
                setVisible(false);
            }
        });
        this.add(btn3);*/


        JButton btn4 = new JButton("離開");
        btn4.setBounds(90, 240, 200, 50);
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(btn4);
    }
}