package com.example.sudoku;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameSet extends JFrame {
    private JPanel jContentPane = null;
    private JFormattedTextField input = null;

    public FrameSet(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(0,0,400,400);
        this.setVisible(true);
        //this.setContentPane(getJContentPane());
    }
    /*public JPanel getJContentPane(int i, int j) {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getInput(i,j), null);
        }
        return jContentPane;
    }
    public JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getInput(), null);
        }
        return jContentPane;
    }

    public JFormattedTextField getInput() {  //��J���ת���l
        //int x=30*i+22, y=30*j+50;
        if (input == null) {
            javax.swing.text.NumberFormatter mf2 = null;
            mf2 = new javax.swing.text.NumberFormatter();
            mf2.setMaximum(new Integer((9)));
            mf2.setMinimum(new Integer(1));
            input = new JFormattedTextField(mf2);
            input.setBounds(new Rectangle(150, 1, 150, 150));  //�q�Ʀr��l �e2��m ��2���e
            input.setFont(new Font("Dialog", Font.PLAIN, 80));         //�q�Ʀr���r�j�p
            input.setHorizontalAlignment(JTextField.CENTER);
            input.setText("20");
        }
        return input;
    }*/
}
