package learning.agent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author angelynz95
 */
public class DataCollection {
  // Atribut
  ArrayList<Datum> data;
  
  // Konstruktor
  public DataCollection() {
    data = new ArrayList<Datum>();
  }
  
  // Getter
  public ArrayList<Datum> getData() {
    return data;
  }
  
  public Datum getDatumAt(int i) {
    return data.get(i);
  }
  
  // Method
  public void readFile(String fileName) {
    String line = null;
    
    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      try {
        while ((line = bufferedReader.readLine()) != null) {
          String[] dataValues = line.split(",");
          Datum datum = new Datum(dataValues[0], dataValues[1], dataValues[2], dataValues[3], dataValues[4], dataValues[5], dataValues[6]);
          data.add(datum);
        }
      } catch (IOException ex) {
        Logger.getLogger(DataCollection.class.getName()).log(Level.SEVERE, null, ex);
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(DataCollection.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void displayData() {
    for (int i = 0; i < data.size(); i++) {
      System.out.print(data.get(i).getBuying() + " ");
      System.out.print(data.get(i).getMaint() + " ");
      System.out.print(data.get(i).getDoors() + " ");
      System.out.print(data.get(i).getPersons() + " ");
      System.out.print(data.get(i).getLugBoot() + " ");
      System.out.print(data.get(i).getSafety() + " ");
      System.out.println(data.get(i).getKelas() + " ");
    }
  }
}
