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
  ArrayList<String> attributeName;
  ArrayList<ArrayList<String>> attributeType;
  
  // Konstruktor
  public DataCollection() {
    data = new ArrayList<Datum>();
    attributeName = new ArrayList<String>();
    attributeType = new ArrayList<ArrayList<String>>();
  }
  
  // Getter
  public ArrayList<Datum> getData() {
    return data;
  }
  
  public Datum getDatumAt(int i) {
    return data.get(i);
  }
  
  public ArrayList<String> getAttributeName() {
      return attributeName;
  }
  
  public ArrayList<ArrayList<String>> getAttributeType() {
      return attributeType;
  }
  
  // Method
  public void readFile(String fileName) {
    ArrayList<String> attributes;
    ArrayList<String> value;
    String line = null;
    
    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      try {
        line = bufferedReader.readLine();   // ignore @relation
        line = bufferedReader.readLine();   // ignore blank line
        
        // Membaca @attribute dari file
        while (!(line = bufferedReader.readLine()).trim().equals("")) {
            String[] splitResult = line.split("[\\s{},]+");
            // Menyimpan nama attribute
            attributeName.add(splitResult[1]);
            
            // Menyimpan value attribute
            value = new ArrayList<String>();
            for (int i = 2; i < splitResult.length; i++) {
                value.add(splitResult[i]);
            }
            attributeType.add(value);
//          Datum datum = new Datum();
//          data.add(datum);
        }
        line = bufferedReader.readLine();   // ignore @data
        
        // Membaca @data dari file
        while ((line = bufferedReader.readLine()) != null) {
            String[] splitResult = line.split("[\\s{},]+");
            attributes = new ArrayList<String>();
            
            // Menyimpan data attribute
            for (int i = 0; i < attributeName.size(); i++) {
                attributes.add(splitResult[i]);
            }
            Datum datum = new Datum(attributes);
            data.add(datum);
        }
      } catch (IOException ex) {
        Logger.getLogger(DataCollection.class.getName()).log(Level.SEVERE, null, ex);
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(DataCollection.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void copy(DataCollection src) {
      data = new ArrayList<Datum>();
      for(Datum datum : src.getData()) {
          ArrayList<String> temp1 = new ArrayList<String>();
          
          Datum temp2 = new Datum(datum.getAttributes());
          data.add(temp2);
      }
  }
}
