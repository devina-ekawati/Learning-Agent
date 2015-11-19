package learning.agent;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angelynz95
 */
public class Datum {
  // Atribut
  private ArrayList<String> attributes;
  
  // Konstruktor
  public Datum(ArrayList<String> _attributes) {
    attributes = _attributes;
  }
  
  // Getter
  public ArrayList<String> getAttributes() {
      return attributes;
  }
  public String getClassAttribute() {
    return get(size()-1);
  }
  public String get(int i) {
    return attributes.get(i);
  }
  // Setter
  public void set(int i, String attribute) {
    attributes.set(i, attribute);
  }
  
  // Method
  public int size() {
    return attributes.size();
  }
}
