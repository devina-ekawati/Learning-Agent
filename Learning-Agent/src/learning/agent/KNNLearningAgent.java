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
  private int[] foldNum;
  private static int K_FOLD;
  private static int FOLD_NUM;
  
  // Konstruktor
  public KNNLearningAgent(int _k, DataCollection _dataCollection) {
    dataCollection = _dataCollection;
    k = _k;
    nPredictionTrue = new ArrayList<Integer>();
    K_FOLD = 10;
    FOLD_NUM = dataCollection.getTotalData() / 10;
    foldNum = new int[10];
    divideByTen();
  }
  
  // Getter
  public int getNPredictionTrue(int i) {
    return nPredictionTrue.get(i);
  }
  
  // Method
  private void divideByTen() {
    for (int i=0; i<10; i++) {
      foldNum[i] = FOLD_NUM;
    }
    int remainder = dataCollection.getTotalData() % 10;
    for (int i=0; i<remainder; i++) {
     foldNum[i]++;
    }
  }
  
  public float countFullTrainingAccuracy() {
    return (float)nPredictionTrue.get(0) / (float)dataCollection.getData().size()*100f;
  }
  public float countTenFoldAccuracy(int i) {
    float result = (float)nPredictionTrue.get(i) / (float)foldNum[i] * 100f;
    return result;
  }
  public float countTenFoldAccuracyMean() {
    int nTruePredictionSum = 0;
    for (int i=0; i<nPredictionTrue.size(); i++) {
      nTruePredictionSum += nPredictionTrue.get(i);
    }
    return (float) nTruePredictionSum / (float)dataCollection.getTotalData() * 100f;
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
    for (int foldOrder=0; foldOrder<K_FOLD; foldOrder++) {
      int nTrue = 0;
      // Perhitungan setiap fold
      for (int idxFold=0; idxFold<foldNum[foldOrder]; idxFold++) {
        Datum datum = dataCollection.getData().get(i);
        KNearestNeighbour knn = new KNearestNeighbour(k, dataCollection, datum);
        String result = knn.doAlgorithm(i, i+foldNum[foldOrder]-1);
        String classAttribute = dataCollection.getData().get(i).getClassAttribute();
        if (result.equals(classAttribute)) {
          // Prediksi benar
          nTrue++;
        }
        i++;
      }
      nPredictionTrue.add(nTrue);
    }
  }
}
