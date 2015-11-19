/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.agent;

import java.util.ArrayList;

/**
 *
 * @author Irene
 */
public class KNNLearningAgent {
  // Atribut
  private DataCollection dataCollection;
  private int k;
  private ArrayList<Integer> nPredictionTrue; // Jumlah prediksi kelas yang benar setiap folding
  
  // Konstruktor
  public KNNLearningAgent(int _k, DataCollection _dataCollection) {
    dataCollection = _dataCollection;
    k = _k;
  }
  
  // Method
  public void fullTrainingKNN() {
    int nTrue = 0;
    for (int i=0; i<dataCollection.getData().size(); i++) {
      Datum datum = dataCollection.getData().get(i);
      KNearestNeighbour knn = new KNearestNeighbour(k, dataCollection, datum);
      String result = knn.doAlgorithm();
      String classAttribute = dataCollection.getData().get(i).getClassAttribute();
      if (result.equals(classAttribute)) {
        // Prediksi benar
        nTrue++;
      }
    }
    nPredictionTrue.add(nTrue);
  }
  
  // Belum selesai
  public void tenFoldKNN() {
    int i = 0;
    while (i < dataCollection.getData().size()) {
      int nTrue = 0;
      Datum datum = dataCollection.getData().get(i);
      KNearestNeighbour knn = new KNearestNeighbour(k, dataCollection, datum);
      String result = knn.doAlgorithm();
      String classAttribute = dataCollection.getData().get(i).getClassAttribute();
      if (result.equals(classAttribute)) {
        // Prediksi benar
        nTrue++;
      }
    }
  }
  
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
