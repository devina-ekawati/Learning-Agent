/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.agent;

import java.util.ArrayList;

/**
 *
 * @author Devina
 */
public class LearningAgent {
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    DataCollection data = new DataCollection();
    data.readFile("weather.nominal.arff");
    //data.readFile("car.arff");
    
    // Testing KNN
//    ArrayList<String> test = new ArrayList<String>();
//    test.add("sunny");
//    test.add("hot");
//    test.add("high");
//    test.add("TRUE");
//    test.add("-none-");
//    
//    KNearestNeighbour neighbour = new KNearestNeighbour(5, data, test);
//    neighbour.doAlgorithm();
//    System.out.println(neighbour.getKelas());
  }
  
}
