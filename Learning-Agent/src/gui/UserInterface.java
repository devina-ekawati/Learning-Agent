/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.java.swing.plaf.windows.WindowsFileChooserUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Devina
 */
public class UserInterface {
  JFrame frame;
  JPanel mainMenu;
  JPanel implementationUI;
  JPanel applicationUI;
  JPanel implementationUIResult;
  
  public UserInterface() {
    frame = new JFrame("Learning Agent");
    mainMenu = new JPanel();
    implementationUI = new JPanel();
    implementationUIResult = new JPanel();
    applicationUI = new JPanel();
    start();
    try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (ClassNotFoundException ex) {
        
    } catch (InstantiationException ex) {
        //Logger.getLogger(ExcelTest.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        //Logger.getLogger(ExcelTest.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
        //Logger.getLogger(ExcelTest.class.getName()).log(Level.SEVERE, null, ex);
    }
    //SwingUtilities.updateComponentTreeUI(w);
    frame.pack();
    frame.getContentPane().setBackground(Color.white);
    frame.setSize(750, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
  
  public void start() {
    mainMenu.setLayout(new GridLayout(2,1));
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2, 50, 50));
    panel.setBorder(BorderFactory.createEmptyBorder(120, 150, 120, 150));
    
    // Button classify
    JButton classify = new JButton ("Classify");
    classify.setMinimumSize(new Dimension(100, 100));
    classify.setMaximumSize(new Dimension(100, 100));
    classify.setPreferredSize(new Dimension(100, 100));
    classify.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof Component) {
            JDialog setup = new JDialog(frame, true);
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(7,2,10,10));
            
            JLabel buyLabel = new JLabel("Buying");
            panel.add(buyLabel);
            String[] buyingAtr = {"vhigh","high","med","low"};
            final JComboBox<String> buying = new JComboBox<String>(buyingAtr);
            panel.add(buying);
            
            JLabel maintLabel = new JLabel("Maint");
            panel.add(maintLabel);
            String[] maintAtr = {"vhigh","high","med","low"};
            final JComboBox<String> maint = new JComboBox<String>(maintAtr);
            panel.add(maint);
            
            JLabel doorsLabel = new JLabel("Doors");
            panel.add(doorsLabel);
            String[] doorsAtr = {"2.0","3.0","4.0","5more"};
            final JComboBox<String> doors = new JComboBox<String>(doorsAtr);
            panel.add(doors);
            
            JLabel personsLabel = new JLabel("Persons");
            panel.add(personsLabel);
            String[] personsAtr = {"2.0","4.0","more"};
            final JComboBox<String> persons = new JComboBox<String>(personsAtr);
            panel.add(persons);
            
            JLabel logbootLabel = new JLabel("Lug_boot");
            panel.add(logbootLabel);
            String[] lugbootAtr = {"small","med","big"};
            final JComboBox<String> lugboot = new JComboBox<String>(lugbootAtr);
            panel.add(lugboot);
            
            JLabel safetyLabel = new JLabel("Safety");
            panel.add(safetyLabel);
            String[] safetyAtr = {"low", "med","high"};
            final JComboBox<String> safety = new JComboBox<String>(safetyAtr);
            panel.add(safety);
            
            panel.add(new Label());
            JButton ok = new JButton("OK");
            ok.setSize(new Dimension(5,5));
            panel.add(ok);
            
            setup.add(panel);
            setup.setFocusable(true);
            setup.setSize(300, 300);
            setup.setVisible(true);
        }
      }
    });
    
    // Button implementation
    JButton implementation = new JButton("Implementation");
    implementation.setMinimumSize(new Dimension(100, 100));
    implementation.setMaximumSize(new Dimension(100, 100));
    implementation.setPreferredSize(new Dimension(100, 100));
    implementation.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof Component) {
            implementation();
            frame.setContentPane(implementationUI);
            frame.invalidate();
            frame.validate();
        }
      }
    });
    
    // Menambah button ke jpabel
    panel.add(classify);
    panel.add(implementation);
    
    // Label untuk Icon
    JLabel icon = new JLabel("ICON");
    icon.setHorizontalAlignment(JLabel.CENTER);
    
    // Menambah label dan panel ke panel main menu
    mainMenu.add(icon);
    mainMenu.add(panel);
    
    // menambah panel ke frame
    frame.add(mainMenu);
  }
  
  public void implementation() {
    implementationUI.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    // Label untuk Icon
    JLabel icon = new JLabel("ICON");
    icon.setHorizontalAlignment(JLabel.CENTER);
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 3;
    c.insets = new Insets(100,0,0,0);
    c.anchor = GridBagConstraints.PAGE_START;
    //c.fill = GridBagConstraints.HORIZONTAL;
    implementationUI.add(icon, c);
    // Textbox untuk file
    JTextField file = new JTextField(40);
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 2;
    c.anchor = GridBagConstraints.CENTER;
    c.insets = new Insets(0,0,0,100);
    implementationUI.add(file, c);
    // Button browse
    JButton browse = new JButton("Browse");
    browse.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        WindowsFileChooserUI wui = new WindowsFileChooserUI(fileChooser);
        wui.installUI(fileChooser);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          file.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
      }
    });
    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    c.anchor = GridBagConstraints.CENTER;
    c.insets = new Insets(0,50,0,0);
    implementationUI.add(browse, c);
    
    // Button untuk reset text box
    JButton reset = new JButton("Reset");
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    c.anchor = GridBagConstraints.LAST_LINE_END;
    c.insets = new Insets(0,0,100,0);
    implementationUI.add(reset, c);
    reset.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        file.setText("");
      }
    });
    
    // Button untuk classify
    JButton classify = new JButton("Classify");
    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    c.anchor = GridBagConstraints.LAST_LINE_END;
    c.insets = new Insets(0,0,100,200);
    classify.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof Component) {
            JDialog setup = new JDialog(frame, true);
            JPanel panel1 = new JPanel();
            
            panel1.setLayout(new GridLayout(3,2, 10, 10));
            JLabel algorithmLabel = new JLabel("Algorithm");
            algorithmLabel.setHorizontalAlignment(JLabel.CENTER);
            panel1.add(algorithmLabel);
            
            String[] algorithms = {"K-NN","Naive Bayes"};
            final JComboBox<String> algorithm = new JComboBox<String>(algorithms);
            panel1.add(algorithm);
            
            JLabel kLabel = new JLabel("k");
            kLabel.setHorizontalAlignment(JLabel.CENTER);
            panel1.add(kLabel);
            
            JTextField k = new JTextField(2);
            panel1.add(k, c);
            algorithm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              String val = String.valueOf(algorithm.getSelectedItem());
              if (val.compareTo("K-NN") == 0) {
                kLabel.setVisible(true);
                k.setVisible(true);
              } else {
                kLabel.setVisible(false);
                k.setVisible(false);
              }
             }
            });
            
            JLabel testLabel = new JLabel("Test options");
            testLabel.setHorizontalAlignment(JLabel.CENTER);
            panel1.add(testLabel);
            
            String[] methods = {"Full Training","10 Folds"};
            final JComboBox<String> method = new JComboBox<String>(methods);
            panel1.add(method);
            
            JPanel panel2 = new JPanel();
            panel2.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.anchor = GridBagConstraints.CENTER;
            panel2.add(panel1, c);
            
            
            JButton ok = new JButton("OK");
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.anchor = GridBagConstraints.LAST_LINE_END;
            panel2.add(ok, c);
            
            setup.add(panel2);
            setup.setFocusable(true);
            setup.setSize(200, 200);
            setup.setVisible(true);
        }
      }
    });
    implementationUI.add(classify, c);
    frame.add(implementationUI);
  }
  
  
  
  public void showImplementationResult() {
    implementationUIResult.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    // Label untuk icon
    JLabel icon = new JLabel("ICON");
    icon.setHorizontalAlignment(JLabel.CENTER);
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 0;
    c.weighty = 0;
    c.anchor = GridBagConstraints.FIRST_LINE_START;
    implementationUIResult.add(icon, c);
    // Textbox untuk file
    JTextField file = new JTextField(30);
    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 0;
    c.weighty = 0;
    c.anchor = GridBagConstraints.FIRST_LINE_START;
    //c.insets = new Insets(0,0,0,0);
    implementationUIResult.add(file, c);
    // Button browse
    JButton browse = new JButton("Browse");
    browse.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        WindowsFileChooserUI wui = new WindowsFileChooserUI(fileChooser);
        wui.installUI(fileChooser);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
        }
      }
    });
    c.gridx = 2;
    c.gridy = 0;
    c.weightx = 0;
    c.weighty = 0;
    c.anchor = GridBagConstraints.FIRST_LINE_START;
    implementationUIResult.add(browse, c);
    // Dropdown untuk algoritma
    String[] algorithms = {"K-NN","Naive Bayes"};
    final JComboBox<String> algorithm = new JComboBox<String>(algorithms);
    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 0;
    c.weighty = 0;
    c.anchor = GridBagConstraints.CENTER;
    c.insets = new Insets(0,0,0,0);
    implementationUI.add(algorithm, c);
    // Dropdown untuk method
    String[] methods = {"Full Training","10 Folds"};
    final JComboBox<String> method = new JComboBox<String>(methods);
    c.gridx = 1;
    c.gridy = 2;
    c.weightx = 0;
    c.weighty = 0;
    c.anchor = GridBagConstraints.CENTER;
    c.insets = new Insets(0,0,0,0);
    implementationUI.add(method, c);
    frame.add(implementationUIResult);
    
  }
  
  public void classify() {
    // Label untuk Icon
    JLabel icon = new JLabel("ICON");
    applicationUI.add(icon);
    frame.add(applicationUI);
  }
  
  public static void main(String[] args) {
    new UserInterface();
  }
}
