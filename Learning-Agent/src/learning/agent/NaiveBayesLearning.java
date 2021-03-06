/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.agent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.io.*;

/**
 *
 * @author William Sentosa
 */
public class NaiveBayesLearning {
    
    private DataCollection dataCollection;
    private ArrayList<BigDecimal[][]> model;
    private ArrayList<String> classType;
    private ArrayList<BigDecimal> countClass;
    
    public NaiveBayesLearning(DataCollection _dataCollection) {
      dataCollection = _dataCollection;
      model = new ArrayList<BigDecimal[][]>();
      countClass = new ArrayList<BigDecimal>();
      classType = new ArrayList<String>();
      classType = dataCollection.getAttributeType().get(dataCollection.getAttributeName().size() - 1);
      int totalClass = classType.size();
      for (int i = 0; i < dataCollection.getTotalAttribute() - 1; i++) {
        int totalAttributeType = dataCollection.getTotalAttributeType(i);
        BigDecimal[][] bigDecimal = makeBigDecimalMatrix(totalAttributeType, totalClass);
        model.add(bigDecimal);
      }
      for (int i = 0; i < totalClass; i++) {
        countClass.add(BigDecimal.ZERO);
      }
    }
    
    public ArrayList<BigDecimal[][]> getModel() {
      return model;
    }
    
    private BigDecimal[][] makeBigDecimalMatrix(int rowEff, int colEff) {
      BigDecimal[][] bigDecimal = new BigDecimal[rowEff][colEff];
      for (int i = 0; i < rowEff; i++) {
        for (int j = 0; j < colEff; j++) {
          bigDecimal[i][j] = BigDecimal.ZERO;
        }
      }
      return bigDecimal;
    }
    
    public void fillWithAtrFrequency() {
      for (Datum datum: dataCollection.getData()) {
        ArrayList<String> temp = new ArrayList<String>();
        temp = datum.getAttributes();
        // Memperoleh kelas dan indeks dari jenis kelas tersebut
        String kelas = temp.get(temp.size()-1);
        int col = 0, row = 0;
        for (int i = 0; i < classType.size(); i++) {
          if (kelas.compareTo(classType.get(i)) == 0) {
            col = i;
            BigDecimal val = countClass.get(i).add(new BigDecimal(1));
            countClass.set(i, val);
            break;
          }
        }
        for (int i = 0; i < temp.size() - 1; i++) {
          ArrayList<String> attributeType = dataCollection.getAttributeType().get(i);
          for (int j = 0; j < attributeType.size(); j++) {
            if (temp.get(i).compareTo(attributeType.get(j)) == 0) {
              row = j;
              break;
            }
          }
          model.get(i)[row][col] = model.get(i)[row][col].add(new BigDecimal(1));
        }
      }  
    }
    
    public void printModel() {
      for (int i = 0; i < model.size(); i++) {
        for (int j = 0; j < dataCollection.getTotalAttributeType(i); j++) {
          for (int k = 0; k < classType.size(); k++)  {
            System.out.print(model.get(i)[j][k] + " ");
          }
          System.out.println();
        }
        System.out.println();
      }
    }
    
    public void writeModelToFile(String filename) {
      try {
        FileWriter fileWriter = new FileWriter(filename);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < model.size(); i++) {
          bufferedWriter.write("Model " + dataCollection.getAttributeName().get(i));
          bufferedWriter.newLine();
          bufferedWriter.write("\t");
          for (int k = 0; k < classType.size(); k++)  {
            bufferedWriter.write(classType.get(k) + "\t");
          }
          bufferedWriter.newLine();
          for (int j = 0; j < dataCollection.getTotalAttributeType(i); j++) {
            bufferedWriter.write(dataCollection.getAttributeType().get(i).get(j) + "\t");
            for (int k = 0; k < classType.size(); k++)  {
              bufferedWriter.write(model.get(i)[j][k] + "\t");
            }
            bufferedWriter.newLine();
          }
          bufferedWriter.newLine();
        }

        bufferedWriter.close();
      }
      catch(IOException ex) {
          System.out.println("Error writing to file '" + filename + "'");
      }
    }
    
    public void countProbability() {
      for (int i = 0; i < model.size(); i++) {
        ArrayList<BigDecimal> _countInCol = new ArrayList<BigDecimal>();
        for (int j = 0; j < dataCollection.getTotalAttributeType(i); j++) {
          for (int k = 0; k < classType.size(); k++)  {
            _countInCol.add(countInCol(model.get(i), dataCollection.getTotalAttributeType(i), k));
          }
        }
        for (int j = 0; j < dataCollection.getTotalAttributeType(i); j++) {
          for (int k = 0; k < classType.size(); k++)  {
            model.get(i)[j][k] = model.get(i)[j][k].divide(_countInCol.get(k), MathContext.DECIMAL128);
          }
        }
      }
    }
    
    public BigDecimal countInCol(BigDecimal[][] table, int maxRow, int col) {
      BigDecimal temp = new BigDecimal(0);
      for (int i = 0; i < maxRow; i++) {
        temp = temp.add(table[i][col]);
      }
      //System.out.println(temp);
      return temp;
    }
    
    
    public BigDecimal probability(Datum datum, String _class) {
      BigDecimal result = new BigDecimal(0);
      //String kelas = datum.getClassAttribute();
      int col = 0, row = 0;
      for (int i = 0; i < classType.size(); i++) {
        if (_class.compareTo(classType.get(i)) == 0) {
          col = i;
          break;
        }
      }
      result = result.add(countClass.get(col).divide(new BigDecimal(dataCollection.getData().size()), MathContext.DECIMAL128));
      for (int i = 0; i < datum.getAttributes().size() - 1; i++) {
        
        ArrayList<String> attributeType = dataCollection.getAttributeType().get(i);
        for (int j = 0; j < attributeType.size(); j++) {
          if (datum.getAttributes().get(i).compareTo(attributeType.get(j)) == 0) {
            row = j;
            break;
          }
        }
        result = result.multiply(model.get(i)[row][col], MathContext.UNLIMITED);
      }
      return result;
    }
    
    public String classify(Datum datum) {
      ArrayList<BigDecimal> probability = new ArrayList<BigDecimal>();
      for (int i = 0; i < classType.size(); i++) {
        BigDecimal val = probability(datum, classType.get(i));
        probability.add(val);
      }
      int imax = 0;
      for (int i = 1; i < classType.size(); i++) {
        
        if (probability.get(i).compareTo(probability.get(imax)) > 0) {
          imax = i;
        }
      }
      return classType.get(imax);
    }
    
    public ArrayList<BigDecimal> getAccuracyFullTraining() {
      ArrayList<BigDecimal> accuracy = new ArrayList<BigDecimal>();
      BigDecimal countTrue = new BigDecimal(0);
      BigDecimal countFalse = new BigDecimal(0);
      for (int i = 0; i < dataCollection.getData().size(); i++) {
        String result = classify(dataCollection.getData().get(i));
        ArrayList<String> temp = new ArrayList<String>();
        temp = dataCollection.getData().get(i).getAttributes();
        // Memperoleh kelas dan indeks dari jenis kelas tersebut
        String kelas = temp.get(temp.size()-1);
        if (result.compareTo(kelas) == 0) {
          countTrue = countTrue.add(new BigDecimal(1));
        } else {
          countFalse = countFalse.add(new BigDecimal(1));
        }
      }
      accuracy.add(countTrue.divide(new BigDecimal(dataCollection.getData().size()), MathContext.DECIMAL128));
      accuracy.add(countFalse.divide(new BigDecimal(dataCollection.getData().size()), MathContext.DECIMAL128));
      return accuracy;
    }
    
    private int searchIndex(List<String> list, String value ) {
        int i = 0;
        for(i=0; i<list.size(); i++) {
            if(list.get(i).equals(value)) return i;
        }
        return -1;
    }
    
    public ConfusionMatrix getFullTrainingConfusionMatrix() {
      List<String> classes = dataCollection.getAttributeType().get(dataCollection.getAttributeName().size()-1);
      ConfusionMatrix mat = new ConfusionMatrix(classes);
      int row, col;
      int[][] tab = new int [classes.size()][classes.size()];
      ArrayList<String> temp;
      for (int i = 0; i < dataCollection.getData().size(); i++) {
        String result = classify(dataCollection.getData().get(i));  
        col = searchIndex(classes, result);
        temp = dataCollection.getData().get(i).getAttributes();
        // Memperoleh kelas dan indeks dari jenis kelas tersebut
        String kelas = temp.get(temp.size()-1);
        row = searchIndex(classes, kelas);
        tab[row][col]++;
      }
      mat.setMatrix(tab);
      return mat;
    }
    
    private int[] divideByTen(int length) {
        int[] result = new int[11];
        for(int i=0; i<11; i++) {
            result[i] = 0;
        }
        for(int i=0; i< length; i++) {
            result[i % 10]++;
        }
        return result;
    }
    
    public ArrayList<BigDecimal> getAccuracyTenFold() {
      ArrayList<BigDecimal> accuracy = new ArrayList<BigDecimal>();
      int[] length = divideByTen(dataCollection.getData().size());
      ArrayList<Datum> list = dataCollection.getData();
      int start = 0;
      int end = length[0];
      BigDecimal countTrue = new BigDecimal(0);
      BigDecimal countFalse = new BigDecimal(0);
      for(int i=0; i<10; i++) {
        ArrayList<Datum> list1 = new ArrayList<Datum>(list.subList(0, start));
        if(end >= list.size()) {
            end = list.size();
        }
        ArrayList<Datum> list2 = new ArrayList<Datum>(list.subList(end, list.size()));
        ArrayList<Datum> listTest = new ArrayList<Datum>(list.subList(start, end));
        ArrayList<Datum> listResult = new ArrayList<Datum>();
        listResult.addAll(list1);
        listResult.addAll(list2);
        DataCollection data = new DataCollection();
        data.setData(listResult);
        data.setAttributeName(dataCollection.getAttributeName());
        data.setAttributeType(dataCollection.getAttributeType());
        NaiveBayesLearning agent = new NaiveBayesLearning(data);
        agent.fillWithAtrFrequency();
        agent.countProbability();
        for(Datum d : listTest) {
            if((d.getAttributes().get(d.getAttributes().size()-1)).compareTo(agent.classify(d)) == 0) {
                countTrue = countTrue.add(new BigDecimal(1));
            } else {
                countFalse = countFalse.add(new BigDecimal(1));
            }
        }
        start = start + length[i];
        end = end + length[i+1];
      }
      accuracy.add(countTrue.divide(new BigDecimal(dataCollection.getData().size()), MathContext.DECIMAL128));
      accuracy.add(countFalse.divide(new BigDecimal(dataCollection.getData().size()), MathContext.DECIMAL128));
      return accuracy;
    }
    
    public ArrayList<ArrayList<BigDecimal>> getAccuracyTenFoldRate() {
      ArrayList<ArrayList<BigDecimal>> accuracy = new ArrayList<ArrayList<BigDecimal>>();
      int[] length = divideByTen(dataCollection.getData().size());
      ArrayList<Datum> list = dataCollection.getData();
      int start = 0;
      int end = length[0];
      BigDecimal countTrue = new BigDecimal(0);
      BigDecimal countFalse = new BigDecimal(0);
      for(int i=0; i<10; i++) {
        ArrayList<Datum> list1 = new ArrayList<Datum>(list.subList(0, start));
        if(end >= list.size()) {
            end = list.size();
        }
        ArrayList<Datum> list2 = new ArrayList<Datum>(list.subList(end, list.size()));
        ArrayList<Datum> listTest = new ArrayList<Datum>(list.subList(start, end));
        ArrayList<Datum> listResult = new ArrayList<Datum>();
        listResult.addAll(list1);
        listResult.addAll(list2);
        DataCollection data = new DataCollection();
        data.setData(listResult);
        data.setAttributeName(dataCollection.getAttributeName());
        data.setAttributeType(dataCollection.getAttributeType());
        NaiveBayesLearning agent = new NaiveBayesLearning(data);
        agent.fillWithAtrFrequency();
        agent.countProbability();
        for(Datum d : listTest) {
            if((d.getAttributes().get(d.getAttributes().size()-1)).compareTo(agent.classify(d)) == 0) {
                countTrue = countTrue.add(new BigDecimal(1));
            } else {
                countFalse = countFalse.add(new BigDecimal(1));
            }
        }
        start = start + length[i];
        end = end + length[i+1];
        ArrayList<BigDecimal> temp = new ArrayList<BigDecimal>();
        temp.add(countTrue.divide(new BigDecimal(length[i]), MathContext.DECIMAL128));
        temp.add(countFalse.divide(new BigDecimal(length[i]), MathContext.DECIMAL128));
        countTrue = new BigDecimal(0);
        countFalse = new BigDecimal(0);
        accuracy.add(temp);
      }
      return accuracy;
    }
    
    public ConfusionMatrix getTenFoldConfusionMatrix() {
      List<String> classes = dataCollection.getAttributeType().get(dataCollection.getAttributeName().size()-1);
      ConfusionMatrix mat = new ConfusionMatrix(classes);
      int row, col;
      int[][] tab = new int [classes.size()][classes.size()];
      ArrayList<String> temp;
      int[] length = divideByTen(dataCollection.getData().size());

      ArrayList<Datum> list = dataCollection.getData();
      int start = 0;
      int end = length[0];
 
      for(int i=0; i<10; i++) {
        ArrayList<Datum> list1 = new ArrayList<Datum>(list.subList(0, start));
        if(end >= list.size()) {
            end = list.size();
        }
        ArrayList<Datum> list2 = new ArrayList<Datum>(list.subList(end, list.size()));
        ArrayList<Datum> listTest = new ArrayList<Datum>(list.subList(start, end));
        ArrayList<Datum> listResult = new ArrayList<Datum>();
        listResult.addAll(list1);
        listResult.addAll(list2);
        DataCollection data = new DataCollection();
        data.setData(listResult);
        data.setAttributeName(dataCollection.getAttributeName());
        data.setAttributeType(dataCollection.getAttributeType());
        NaiveBayesLearning agent = new NaiveBayesLearning(data);
        agent.fillWithAtrFrequency();
        agent.countProbability();
        for(Datum d : listTest) {
            row = searchIndex(classes, d.getAttributes().get(d.getAttributes().size()-1)); 
            col = searchIndex(classes, agent.classify(d));
            tab[row][col]++;
        }
        start = start + length[i];
        end = end + length[i+1];
      }
      mat.setMatrix(tab);
      return mat;
    }
    
    public static void main(String[] args) {
      DataCollection dataCol = new DataCollection();
      dataCol.readFile("car.arff");
      dataCol.randomizeData();
      NaiveBayesLearning agent = new NaiveBayesLearning(dataCol);
      agent.fillWithAtrFrequency();
      
      agent.countProbability();
      
      
      ArrayList<BigDecimal> accuracy;
      
      accuracy = agent.getAccuracyFullTraining();
      System.out.println("*** Akurasi Full Training ***");
      System.out.println("Persentase benar : " + accuracy.get(0));
      System.out.println("Persentase salah : " + accuracy.get(1));
      System.out.println(accuracy.get(0).add(accuracy.get(1)));
      
      accuracy = agent.getAccuracyTenFold();
      System.out.println("*** Akurasi Ten Fold Test ***");
      System.out.println("Persentase benar : " + accuracy.get(0));
      System.out.println("Persentase salah : " + accuracy.get(1));
      System.out.println(accuracy.get(0).add(accuracy.get(1)));
      //agent.printModel();
      
      ArrayList<ArrayList<BigDecimal>> results = agent.getAccuracyTenFoldRate();
      BigDecimal countTrue = new BigDecimal(0);
      BigDecimal countFalse = new BigDecimal(0);
      for(ArrayList<BigDecimal> arr : results) {
          countTrue.add(arr.get(0));
          countFalse.add(arr.get(1));
          System.out.println(arr.get(0) + " , " + arr.get(1));
      }
      
      System.out.println("**** Full Training Confusion Matrix ****");
      ConfusionMatrix mat;
      mat = agent.getFullTrainingConfusionMatrix();
      mat.display();
      
      System.out.println("**** Ten Fold Confusion Matrix ****");
      mat = agent.getTenFoldConfusionMatrix();
      mat.display();
     
      
    }
    
}
