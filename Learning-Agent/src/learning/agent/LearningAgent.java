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
    DataCollection data = new DataCollection();
    data.readFile("weather.nominal.arff");
    // Full Training Agent
    KNNLearningAgent fullTrainingAgent = new KNNLearningAgent(5, data);
    fullTrainingAgent.fullTrainingKNN();
    System.out.println("Prediksi benar: " + fullTrainingAgent.getNPredictionTrue(0));
    System.out.println("Akurasi: " + fullTrainingAgent.countFullTrainingAccuracy());
    
    // Ten-Fold Cross Validation Agent
    KNNLearningAgent tenFoldAgent = new KNNLearningAgent(5, data);
    tenFoldAgent.tenFoldKNN();
    System.out.println("Akurasi total: " + tenFoldAgent.countTenFoldAccuracyMean());
    
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
