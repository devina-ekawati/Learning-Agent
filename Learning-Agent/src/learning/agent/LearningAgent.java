/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.agent;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Devina
 */
public class LearningAgent {
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    System.out.println("NAIVE BAYES AND KNN EXPLORER");
    System.out.println("Nama file: ");
    Scanner s = new Scanner(System.in);
    String fileName = s.nextLine();
    DataCollection data = new DataCollection();
    data.readFile(fileName);
    System.out.println();
    
    System.out.println("Pilihan menu:\n1. NaiveBayes Full Training\n2. NaiveBayes TenFold Training\n3. KNN Full Training\n4. KNN NaiveBayes");
    System.out.println("Menu yang dipilih: ");
    // NaiveBayes
    NaiveBayesLearning nbAgent = new NaiveBayesLearning(data);
    nbAgent.fillWithAtrFrequency();
    nbAgent.countProbability();
    int menu = s.nextInt();
    System.out.println();
    
    if (menu == 1) {
      // NB Full Training
    ArrayList<BigDecimal> accuracy2 = new ArrayList<BigDecimal>();
    accuracy2 = nbAgent.getAccuracyFullTraining();
    System.out.println("Persentase akurasi benar : " + accuracy2.get(0).multiply(new BigDecimal(100), MathContext.UNLIMITED) + "%");
    } else if (menu == 2) {
      // NB Ten Fold
    ArrayList<BigDecimal> accuracy = new ArrayList<BigDecimal>();
    accuracy = nbAgent.getAccuracyTenFold();
    System.out.println("Persentase akurasi benar: " + accuracy.get(0).multiply(new BigDecimal(100), MathContext.UNLIMITED) + "%");
    } else if (menu == 3) {
      // KNN Full Training
      System.out.println("Masukkan k: ");
      int k = s.nextInt();
    KNNLearningAgent fullTrainingAgent = new KNNLearningAgent(k, data);
    fullTrainingAgent.fullTrainingKNN();
    System.out.println("Persentase akurasi benar : " + fullTrainingAgent.countFullTrainingAccuracy()+"%");
    } else if (menu == 4) {
      // KNN Ten-Fold Cross Validation
      System.out.println("Masukkan k: ");
      int k = s.nextInt();
      KNNLearningAgent tenFoldAgent = new KNNLearningAgent(k, data);
      tenFoldAgent.tenFoldKNN();
      System.out.println("Persentase akurasi rata-rata: " + tenFoldAgent.countTenFoldAccuracyMean()+"%");
    } else {
      System.out.println("Menu salah.");
    }
  }
}
