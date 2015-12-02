/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import learning.agent.ConfusionMatrix;
import learning.agent.DataCollection;
import learning.agent.NaiveBayesLearning;

/**
 *
 * @author William Sentosa
 */
public class ConfusionMatrixPanel extends TransparentJPanel {
  private ConfusionMatrix matrix;
  
  public ConfusionMatrixPanel(ConfusionMatrix mat) {
    matrix = mat;
    int size = mat.getSize();
    this.setLayout(new GridLayout(size+1,size+1,1,1));
    this.setBorder(BorderFactory.createLineBorder(Color.white));
    this.setPreferredSize(new Dimension(size*70, size*30));
  }
  
  private void addComponent() {
    String temp = new String();
    int size = matrix.getSize();
    JLabel label1 = new JLabel("");
    add(label1);
    for(String kelas : matrix.getClasses()) {
      JLabel label = new JLabel(kelas);
      label.setForeground(Color.white);
      label.setFont(new Font("Roboto", Font.PLAIN, 14));
      add(label);
    } 
    
    for(int i=0; i<size; i++) {
        JLabel label3 = new JLabel(matrix.getClasses().get(i) + "");
        label3.setForeground(Color.white);
        label3.setFont(new Font("Roboto", Font.PLAIN, 14));
        add(label3);
        for(int j=0; j<size; j++) {
            JLabel label2 = new JLabel(matrix.getMatrix()[i][j] + "");
            label2.setForeground(Color.white);
            label2.setFont(new Font("Roboto", Font.PLAIN, 14));
            add(label2);
        }
        System.out.println();
    }
    
  }
  
  public void initComponent() {
    addComponent();
  }

  public static void main(String args[]) {
     JFrame frame = new JFrame();
     JPanel mainPanel = new JPanel();
     ConfusionMatrix matrix;
     mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
     DataCollection dataCol = new DataCollection();
     dataCol.readFile("car.arff");
     dataCol.randomizeData();
     NaiveBayesLearning agent = new NaiveBayesLearning(dataCol);
     agent.fillWithAtrFrequency();
     agent.countProbability();
      
     matrix = agent.getFullTrainingConfusionMatrix();
     
     
     ConfusionMatrixPanel panel = new ConfusionMatrixPanel(matrix);
     panel.initComponent();
     mainPanel.add(panel);
     frame.setLocation(30, 30);
     frame.add(mainPanel);
     frame.setSize(500, 500);
     
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setVisible(true);
  }
}
