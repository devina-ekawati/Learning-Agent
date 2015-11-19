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
  private static final int FOLD_NUM = 10;
  
  // Konstruktor
  public KNNLearningAgent(int _k, DataCollection _dataCollection) {
    dataCollection = _dataCollection;
    k = _k;
    nPredictionTrue = new ArrayList<Integer>();
  }
  
  // Getter
  public int getNPredictionTrue(int i) {
    return nPredictionTrue.get(i);
  }
  
  // Method
  public float countFullTrainingAccuracy() {
    return (float)nPredictionTrue.get(0) / (float)dataCollection.getData().size()*100f;
  }
  public float countTenFoldAccuracy(int i) {
    return (float)nPredictionTrue.get(i) / FOLD_NUM*100f;
  }
  public float countAccuracyMean() {
    int accuracySum = 0;
    for (int i : nPredictionTrue) {
      System.out.println("Akurasi-"+i+": " + countTenFoldAccuracy(i));
      accuracySum += countTenFoldAccuracy(i);
    }
    return (float) accuracySum / (float)nPredictionTrue.size()*100f;
  }
          
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
  
  public void tenFoldKNN() {
    int i = 0;
    while (i < dataCollection.getData().size()) {
      // Perhitungan setiap fold
      int nTrue = 0;
      int idxFold = 0;
      while (idxFold<FOLD_NUM && i<dataCollection.getData().size()) {
        Datum datum = dataCollection.getData().get(i);
        KNearestNeighbour knn = new KNearestNeighbour(k, dataCollection, datum);
        String result = knn.doAlgorithm(i, i+FOLD_NUM-1);
        String classAttribute = dataCollection.getData().get(i).getClassAttribute();
        if (result.equals(classAttribute)) {
          // Prediksi benar
          nTrue++;
        }
        idxFold++;
        i++;
      }
      nPredictionTrue.add(nTrue);
    }
  }
}
