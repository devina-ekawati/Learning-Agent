/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import learning.agent.ConfusionMatrix;
import learning.agent.DataCollection;
import learning.agent.NaiveBayesLearning;

/**
 *
 * @author William Sentosa
 */
public class NaiveBayesModelPanel extends TransparentJPanel{
  private BigDecimal[][] model;
  private List<String> classes;
  private List<String> types;
  
  public NaiveBayesModelPanel (BigDecimal[][] model, List<String>classes, List<String>types) {
    this.model = model;
    this.classes = classes;
    this.types = types;
    int row = model.length;
    int col = model[1].length;
    this.setLayout(new GridLayout(row+1,col+1,1,1));
    this.setBorder(BorderFactory.createLineBorder(Color.white));
    this.setPreferredSize(new Dimension(col*70, row*30));
  }
  
  
  private void addComponent() {
    String temp = new String();
    int row = model.length;
    int col = model[1].length;
    JLabel label1 = new JLabel("");
    add(label1);
    for(int i=0; i<col; i++) {
      JLabel label = new JLabel(classes.get(i));
      label.setForeground(Color.white);
      label.setFont(new Font("Roboto", Font.PLAIN, 14));
      add(label);
    } 
    
    MathContext mc = new MathContext(3);
    
    for(int i=0; i<row; i++) {
        JLabel label3 = new JLabel(types.get(i) + "");
        label3.setForeground(Color.white);
        label3.setFont(new Font("Roboto", Font.PLAIN, 14));
        add(label3);
        for(int j=0; j<col; j++) {
            JLabel label2 = new JLabel(model[i][j].round(mc) + "");
            label2.setForeground(Color.white);
            label2.setFont(new Font("Roboto", Font.PLAIN, 14));
            add(label2);
        }
    }
    
  }
  
  public void initComponent() {
    addComponent();
  }

  public static void main(String args[]) {
     JFrame frame = new JFrame();
     JPanel mainPanel = new JPanel();
     mainPanel.setBorder(BorderFactory.createLineBorder(Color.white));
     DataCollection dataCol = new DataCollection();
     dataCol.readFile("car.arff");
     dataCol.randomizeData();
     NaiveBayesLearning agent = new NaiveBayesLearning(dataCol);
     agent.fillWithAtrFrequency();
     agent.countProbability();
     
     BigDecimal[][] model = agent.getModel().get(4);
     List<String> classes = dataCol.getAttributeType().get(dataCol.getAttributeName().size()-1);
     List<String> types = dataCol.getAttributeType().get(4);
     ConfusionMatrix matrix = agent.getFullTrainingConfusionMatrix();
     ConfusionMatrixPanel panel = new ConfusionMatrixPanel(matrix);
     NaiveBayesModelPanel panel2 = new NaiveBayesModelPanel(model, classes, types);
     panel.initComponent();
     panel2.initComponent();
     
     mainPanel.add(panel);
     mainPanel.add(panel2);
     
     frame.add(mainPanel);
     frame.setSize(500, 500);
     
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setVisible(true);
  }
}
