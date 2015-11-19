/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.agent;

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
    
//    KNearestNeighbour neighbour = new KNearestNeighbour(5, data, "vhigh", "vhigh", "2", "2", "small", "low");
//    neighbour.doAlgorithm();
//    System.out.println(neighbour.getKelas());
  }
  
}
