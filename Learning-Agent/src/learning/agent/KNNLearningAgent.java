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
  private static int FOLD_NUM;
  
  // Konstruktor
  public KNNLearningAgent(int _k, DataCollection _dataCollection) {
    dataCollection = _dataCollection;
    k = _k;
    nPredictionTrue = new ArrayList<Integer>();
    FOLD_NUM = dataCollection.getTotalData() / 10;
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
    float result;
    if (i == nPredictionTrue.size()) {
      // Fold terakhir
      int lastFoldNum = dataCollection.getTotalData() % FOLD_NUM;
      result = (float)nPredictionTrue.get(i) / (float)lastFoldNum * 100f;
    } else {
      result = (float)nPredictionTrue.get(i) / (float)FOLD_NUM * 100f;
    }
    return result;
  }
  public float countTenFoldAccuracyMean() {
    int nTruePredictionSum = 0;
    for (int i : nPredictionTrue) {
      nTruePredictionSum += nPredictionTrue.get(i);
    }
    return (float) nTruePredictionSum / (float)nPredictionTrue.size() * 100f;
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
    while (i < dataCollection.getTotalData()) {
      int nTrue = 0;
      int idxFold = 0;
      int idxStartFold = i;
      int idxLastFold = i+FOLD_NUM-1;
      if (FOLD_NUM == 1) {
        idxLastFold = i+ FOLD_NUM - 1;
      } else if (idxLastFold > dataCollection.getTotalData()) {
        // Fold terakhir
        idxLastFold = dataCollection.getTotalData();
      }
      // Perhitungan setiap fold
      while (idxFold<FOLD_NUM && i<dataCollection.getTotalData()) {
        Datum datum = dataCollection.getData().get(i);
        KNearestNeighbour knn = new KNearestNeighbour(k, dataCollection, datum);
        String result = knn.doAlgorithm(idxStartFold, idxLastFold);
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
