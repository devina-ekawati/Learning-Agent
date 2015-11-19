/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.agent;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author William Sentosa
 */
public class NaiveBayesLearning {
    
    private DataCollection dataCollection;
    private BigDecimal[][] model;
    
    public NaiveBayesLearning(DataCollection _dataCollection) {
      dataCollection = _dataCollection;
      // Inisialisasi ukuran matriks model
      int totalAttribute = 0;
      for (int i = 0; i < dataCollection.getTotalAttribute() - 1; i++) {
        totalAttribute += dataCollection.getTotalAttributeType(i);
      }
      int totalClass = dataCollection.getTotalAttributeType(dataCollection.getTotalAttribute() - 1);
      System.out.println("Jumlah atribut = " + totalAttribute);
      System.out.println("Jumlah kelas = " + totalClass);
      model = new BigDecimal[totalAttribute][totalClass];
      // Inisialisasi nilai model;
      for (int i = 0; i < totalAttribute; i++) {
        for (int j = 0; j < totalClass; j++) {
          model[i][j] = BigDecimal.ZERO;
        }
      }
    }
    
    private void fillWithAtrFrequency() {
      ArrayList<String> classType = new ArrayList<String>();
      classType = dataCollection.getAttributeType().get(dataCollection.getAttributeName().size() - 1);
      for (Datum datum: dataCollection.getData()) {
        ArrayList<String> temp = new ArrayList<String>();
        temp = datum.getAttributes();
        String kelas = temp.get(temp.size()-1);
        for (int i = 0; i < temp.size(); i++) {
          
        }
      }
    }
    
    
    public static void main(String[] args) {
      DataCollection dataCol = new DataCollection();
      dataCol.readFile("car.arff");
      NaiveBayesLearning agent = new NaiveBayesLearning(dataCol);
    }
    
}
