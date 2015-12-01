/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.java.swing.plaf.windows.WindowsFileChooserUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import java.util.logging.*;
import javax.imageio.ImageIO;

/**
 *
 * @author Devina
 */
public class UserInterface {
    JFrame frame;
    
    TransparentJPanel bgUI;
    TransparentJPanel mainMenu;
    TransparentJPanel implementationUI;
    TransparentJPanel applicationUI;
    TransparentJPanel implementationUIResult;
  
    public UserInterface() throws IOException {
        frame = new JFrame("goclass");
        bgUI = new TransparentJPanel();
        mainMenu = new TransparentJPanel();
        implementationUI = new TransparentJPanel();
        implementationUIResult = new TransparentJPanel();
        applicationUI = new TransparentJPanel();
        
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
        // menambah panel ke frame
        //frame.add(bgUI);
        frame.pack();
        frame.setSize(750, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void start() throws IOException {
        mainMenu.setLayout(new BorderLayout());
        // Menambah background
        BackgroundPane bgPane;
        bgPane = new BackgroundPane();
        try {
            BufferedImage bg = ImageIO.read(new File("assets/bg.jpg"));
            bgPane.setBackgroundImage(bg);
        } catch (IOException e) {
            e.printStackTrace();
        };
        
        // Container
        TransparentJPanel container = new TransparentJPanel();
        container.setLayout(new GridLayout(2, 1));
        TransparentJPanel panel = new TransparentJPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 150, 130, 150));

        // Button classify
        JButton classify = new JButton ("Classify");
        classify.setMinimumSize(new Dimension(130, 50));
        classify.setMaximumSize(new Dimension(130, 50));
        classify.setPreferredSize(new Dimension(130, 50));
        classify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source instanceof Component) {
                    JDialog setup = new JDialog(frame, true);
                    TransparentJPanel panel = new TransparentJPanel();
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
        implementation.setMinimumSize(new Dimension(20, 20));
        implementation.setMaximumSize(new Dimension(20, 20));
        implementation.setPreferredSize(new Dimension(20, 20));
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
        implementation.setMinimumSize(new Dimension(130, 50));
        implementation.setMaximumSize(new Dimension(130, 50));
        implementation.setPreferredSize(new Dimension(130, 50));
        
        // Menambah button ke jlabel
        panel.add(classify);
        panel.add(implementation);

        // Label untuk Logo
        BufferedImage logoImage = ImageIO.read(new File("assets/logo.png"));
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        // Menambah label dan panel ke panel main menu
        container.add(logoLabel);
        container.add(panel);    
        bgPane.add(container);
        mainMenu.add(bgPane);
        frame.add(mainMenu);
    }

    public void implementation() {
        implementationUI.setLayout(new BorderLayout());
        // Menambah background
        BackgroundPane bgPane;
        bgPane = new BackgroundPane();
        try {
            BufferedImage bg = ImageIO.read(new File("assets/bg.jpg"));
            bgPane.setBackgroundImage(bg);
        } catch (IOException e) {
            e.printStackTrace();
        };
        
        // Container
        TransparentJPanel container = new TransparentJPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // Tombol back
        JButton back = new JButton("Back");
        container.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source instanceof Component) {
                    try {
                        start();
                    } catch (IOException ex) {
                        Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    frame.setContentPane(mainMenu);
                    frame.invalidate();
                    frame.validate();
                }
            }
        });
        
        // Label untuk Logo
        BufferedImage logoImage = null;
        try {
            logoImage = ImageIO.read(new File("assets/logo.png"));
        } catch (IOException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(60, 0, 0, 0);
        container.add(logoLabel, c);
        
        // Textbox untuk file
        JTextField file = new JTextField(30);
        file.setFont(new Font("Roboto", Font.PLAIN, 14));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(300, 0, 0, 0);
        container.add(file, c);
        
        // Button browse
        JButton browse = new JButton("Browse");
        browse.setFont(new Font("Roboto", Font.PLAIN, 14));
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
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        container.add(browse, c);

        // Button untuk reset text box
        JButton reset = new JButton("Reset");
        reset.setFont(new Font("Roboto", Font.PLAIN, 14));
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(50, 0, 0, 0);
        container.add(reset, c);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file.setText("");
            }
        });

        // Button untuk classify
        JButton classify = new JButton("Classify");
        classify.setFont(new Font("Roboto", Font.PLAIN, 14));
        classify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source instanceof Component) {
                    JDialog setup = new JDialog(frame, true);
                    TransparentJPanel panel1 = new TransparentJPanel();

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
                    panel1.add(k);
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

                    TransparentJPanel panel2 = new TransparentJPanel();
                    panel2.setLayout(new GridBagLayout());
                    panel2.add(panel1);


                    JButton ok = new JButton("OK");
                    GridBagConstraints c = new GridBagConstraints();
                    c.gridx = 0;
                    c.gridy = 0;
                    c.weightx = 0;
                    c.weighty = 0;
                    c.anchor = GridBagConstraints.LAST_LINE_END;
                    c.insets = new Insets(120,0,0,0);
                    panel2.add(ok, c);

                    setup.add(panel2);
                    setup.setFocusable(true);
                    setup.setSize(250, 250);
                    setup.setVisible(true);
                }
            }
        });
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.insets = new Insets(50, 0, 0, 0);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        container.add(classify, c);
        
        bgPane.add(container);
        implementationUI.add(bgPane);
        frame.add(implementationUI);
    }

    public void showImplementationResult() {
        implementationUIResult.setLayout(new BorderLayout());
        // Menambah background
        BackgroundPane bgPane;
        bgPane = new BackgroundPane();
        try {
            BufferedImage bg = ImageIO.read(new File("assets/bg.jpg"));
            bgPane.setBackgroundImage(bg);
        } catch (IOException e) {
            e.printStackTrace();
        };
        
        // Container
        TransparentJPanel container = new TransparentJPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // Tombol back
        JButton back = new JButton("Back");
        container.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source instanceof Component) {
                    implementation();
                    frame.setContentPane(mainMenu);
                    frame.invalidate();
                    frame.validate();
                }
            }
        });

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
    
    /* Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            new UserInterface();
        } catch (IOException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
