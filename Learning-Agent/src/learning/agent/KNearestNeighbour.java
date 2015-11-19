/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.agent;

import java.util.*;

/**
 *
 * @author angelynz95
 */
public class KNearestNeighbour {
  // Atribut
  private DataCollection dataCollection;
  private int k;
  private ArrayList<String> attributes;
  private ArrayList<Integer> distance;  // Jarak neighbour
  private Integer[] distanceAt;
  
  // Konstruktor
  public KNearestNeighbour(int _k, DataCollection _dataCollection, ArrayList<String> _attributes) {
    dataCollection = new DataCollection();
    dataCollection.copy(_dataCollection);
    k = _k;
    attributes = _attributes;
    distance = new ArrayList<Integer>();
    distanceAt = new Integer[attributes.size()];
    for (int i = 0; i < distanceAt.length; i++) {
      distanceAt[i] = 0;
    }
  }
  
  // Getter
  public String getKelas() {
    return attributes.get(attributes.size()-1);
  }
  
  // Method
  public void doAlgorithm() {
    findDistance();
    findKelas();
    //display();
  }
  
  public void findDistance() {
    Integer countDistance;
    
    for (int i = 0; i < dataCollection.getData().size(); i++) {
      countDistance = 0;
      
      // Membandingkan jarak setiap attribute dengan attribute pada dataCollection ke-i
      for (int j = 0; j < attributes.size(); j++) {
          if (!attributes.get(j).equals(dataCollection.getDatumAt(i).getAttributes().get(j))) {
              countDistance++;
          }
      }
      distance.add(countDistance);
      
      for (int j = 0; j < attributes.size(); j++) {
          if (countDistance.equals(j)) {
              (distanceAt[j])++;
          }
      }
    }
  }
  
  public void findKelas() {
    boolean stopLoop;
    int maxDistance, countMaxDistance, countKelas[], bottomDistance, topDistance, x;
    
    countKelas = new int[dataCollection.getAttributeType().get(attributes.size()-1).size()];
    for (int i = 0; i < countKelas.length; i++) {
      countKelas[i] = 0;
    }
    
    x = 0;
    stopLoop = false;
    bottomDistance = 0;
    topDistance = distanceAt[0];
    while (!stopLoop && (x < attributes.size())) {
        if ((k > bottomDistance) && (k <= topDistance)) {
            maxDistance = bottomDistance;
            countMaxDistance = k - bottomDistance;
            stopLoop = true;
        } else {
            bottomDistance = topDistance;
            topDistance = topDistance + distanceAt[x+1];
        }
        
        x++;
    }
    if (k <= distanceAt[0]) {
      maxDistance = 0;
      countMaxDistance = k;
    } else if ((k > distanceAt[0]) && k <= (distanceAt[0] + distanceAt[1])) {
      maxDistance = 1;
      countMaxDistance = k - distanceAt[0];
    } else if ((k > (distanceAt[0] + distanceAt[1])) && k <= (distanceAt[0] + distanceAt[1] + distanceAt[2])) {
      maxDistance = 2;
      countMaxDistance = k - (distanceAt[0] + distanceAt[1]);
    } else if ((k > (distanceAt[0] + distanceAt[1] + distanceAt[2])) && k <= (distanceAt[0] + distanceAt[1] + distanceAt[2] + distanceAt[3])) {
      maxDistance = 3;
      countMaxDistance = k - (distanceAt[0] + distanceAt[1] + distanceAt[2]);
    } else if ((k > (distanceAt[0] + distanceAt[1] + distanceAt[2] + distanceAt[3])) && k <= (distanceAt[0] + distanceAt[1] + distanceAt[2] + distanceAt[3] + distanceAt[4])) {
      maxDistance = 4;
      countMaxDistance = k - (distanceAt[0] + distanceAt[1] + distanceAt[2] + distanceAt[3]);
    } else if ((k > (distanceAt[0] + distanceAt[1] + distanceAt[2] + distanceAt[3] + distanceAt[4])) && k <= (distanceAt[0] + distanceAt[1] + distanceAt[2] + distanceAt[3] + distanceAt[4] + distanceAt[5])) {
      maxDistance = 5;
      countMaxDistance = k - (distanceAt[0] + distanceAt[1] + distanceAt[2] + distanceAt[3] + distanceAt[4]);
    } else {  // (k > (distanceAt[0] + distanceAt[1] + distanceAt[2] + distanceAt[3] + distanceAt[4] + distanceAt[5])) && k <= (distanceAt[0] + distanceAt[1] + distanceAt[2] + distanceAt[3] + distanceAt[4] + distanceAt[5] + distanceAt[6])
      maxDistance = 6;
      countMaxDistance = k - (distanceAt[0] + distanceAt[1] + distanceAt[2] + distanceAt[3] + distanceAt[4] + distanceAt[5]);
    }
    
    for (int i = 0; i < dataCollection.getData().size(); i++) {
        if (distance.get(i) < maxDistance) {
            for (int j = 0; j < countKelas.length; j++) {
                if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                    (countKelas[j])++;
                }
            }
        } else if (distance.get(i).equals(maxDistance) && (countMaxDistance > 0)) {
            for (int j = 0; j < countKelas.length; j++) {
                if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                    (countKelas[j])++;
                }
            }

            countMaxDistance--;
        }
    }
    
    // Mencari kelas dengan count terbanyak
    int maxCount = countKelas[0], countMax = 0, maxIndex = 0;
    
    for (int i = 1; i < countKelas.length; i++) {
      if (countKelas[i] > maxCount) {
        maxCount = countKelas[i];
        maxIndex = i;
      }
    }
    
    for (int i = 0; i < countKelas.length; i++) {
      if (countKelas[i] == maxCount) {
        countMax++;
      }
    }
    
    if (countMax > 1) {
        attributes.set(attributes.size()-1, "none");
    } else { // countMax <= 1
        for (int i = 0; i < countKelas.length; i++) {
            if (maxIndex == i) {
                attributes.set(attributes.size()-1, dataCollection.getAttributeType().get(attributes.size()-1).get(i));
            }
        }
    }
}
  
  public void display() {
    for (int i = 0; i < 7; i++) {
      System.out.println(distanceAt[i]);
    }
    System.out.println();
    
  }
}
