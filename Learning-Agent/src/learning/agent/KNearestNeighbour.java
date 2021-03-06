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
  private Datum attributes;
  private ArrayList<Integer> distance;  // Jarak neighbour
  private Integer[] distanceAt;
  
  // Konstruktor
  public KNearestNeighbour(int _k, DataCollection _dataCollection, Datum _attributes) {
    dataCollection = _dataCollection;
    k = _k;
    attributes = _attributes;
    distance = new ArrayList<Integer>();
    
    distanceAt = new Integer[attributes.size()];
    for (int i = 0; i < distanceAt.length; i++) {
      distanceAt[i] = 0;
    }
  }
  
  // Method
  public String doAlgorithm() {
    findDistance();
    String result = findKelas();
    return result;
  }
  
  public String doTopBottomAlgorithm(int index) {
    findDistance();
    String result = findTopBottomKelas(index);
    return result;
  }
  
  public String doTopBottomAlgorithm(int index, int firstIndex, int lastIndex) {
    findDistance(firstIndex, lastIndex);
    String result = findTopBottomKelas(index, firstIndex, lastIndex);
    return result;
  }
  
  public String doBottomTopAlgorithm(int index) {
    String result = findBottomTopKelas(index);
    return result;
  }
  
  public String doBottomTopAlgorithm(int index, int firstIndex, int lastIndex) {
    String result = findBottomTopKelas(index, firstIndex, lastIndex);
    return result;
  }
  
  public String doTopAlgorithm(int index) {
    String result = findTopKelas(index);
    return result;
  }
  
  public String doTopAlgorithm(int index, int firstIndex, int lastIndex) {
    String result = findTopKelas(index, firstIndex, lastIndex);
    return result;
  }
  
  public String doBottomAlgorithm(int index) {
    String result = findBottomKelas(index);
    return result;
  }
  
  public String doBottomAlgorithm(int index, int firstIndex, int lastIndex) {
    String result = findBottomKelas(index, firstIndex, lastIndex);
    return result;
  }
  
  private void findDistance() {
    Integer countDistance;
    
    for (int i = 0; i < dataCollection.getData().size(); i++) {
      countDistance = 0;
      
      // Membandingkan jarak setiap attribute dengan attribute pada dataCollection ke-i
      for (int j = 0; j < (attributes.size()-1); j++) {
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
  
  private void findDistance(int firstIndex, int lastIndex) {
    Integer countDistance;
    
    int iTemp;
    if (firstIndex == 0) {
        iTemp = lastIndex + 1;
    } else {
        iTemp = 0;
    }
    for (int i = iTemp; i < dataCollection.getData().size(); i++) {
      countDistance = 0;
      // Membandingkan jarak setiap attribute dengan attribute pada dataCollection ke-i
      for (int j = 0; j < (attributes.size()-1); j++) {
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
      
      if ((i+1) == firstIndex) {
          i = lastIndex;
      }
    }
  }
  
  private String findKelas() {
    boolean stopLoop;
    int maxDistance = 0, countMaxDistance = 0, countKelas[], bottomDistance, topDistance, x;
    
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
            maxDistance = x;
            countMaxDistance = k - bottomDistance;
            stopLoop = true;
        } else {
            bottomDistance = topDistance;
            if ((x+1) < attributes.size()) {
                topDistance = topDistance + distanceAt[x+1];
            }
        }
        x++;
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
    int maxCount = countKelas[0], maxIndex = 0;
    
    for (int i = 1; i < countKelas.length; i++) {
      if (countKelas[i] > maxCount) {
        maxCount = countKelas[i];
        maxIndex = i;
      }
    }
    
    return dataCollection.getAttributeType().get(attributes.size()-1).get(maxIndex);
  }
  
  private String findTopBottomKelas(int index) {
    boolean stopLoop;
    int maxDistance = 0, countMaxDistance = 0, countKelas[], bottomDistance, topDistance, x;
    
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
            maxDistance = x;
            countMaxDistance = k - bottomDistance;
            stopLoop = true;
        } else {
            bottomDistance = topDistance;
            if ((x+1) < attributes.size()) {
                topDistance = topDistance + distanceAt[x+1];
            }
        }
        x++;
    }

    int top = index-1, bottom = index+1, i;
    x = 0;
    while (x < k) {
      if ((top >= 0) && ((index-top) <= (bottom-index))) {
        i = top;
      } else {
        i = bottom;
      }
      
      if (x == 0) {
        i = index;
      }
      
      if (distance.get(i) < maxDistance) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          x++;
      } else if (distance.get(i).equals(maxDistance) && (countMaxDistance > 0)) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          countMaxDistance--;
          x++;
      }
        
      if (i == top) {
        top--;
        if (bottom >= dataCollection.getTotalData()) {
          bottom++;
        }
      } else if (i == bottom) {
        bottom++;
      }
    }
    
    // Mencari kelas dengan count terbanyak
    int maxCount = countKelas[0], countMax = 0, maxIndex = 0;
    
    for (i = 1; i < countKelas.length; i++) {
      if (countKelas[i] > maxCount) {
        maxCount = countKelas[i];
        maxIndex = i;
      }
    }
    
    return dataCollection.getAttributeType().get(attributes.size()-1).get(maxIndex);
  }
  
  private String findTopBottomKelas(int index, int firstIndex, int lastIndex) {
    boolean stopLoop;
    int maxDistance = 0, countMaxDistance = 0, countKelas[], bottomDistance, topDistance, x;
    
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
            maxDistance = x;
            countMaxDistance = k - bottomDistance;
            stopLoop = true;
        } else {
            bottomDistance = topDistance;
            if ((x+1) < attributes.size()) {
                topDistance = topDistance + distanceAt[x+1];
            }
        }
        x++;
    }
    
    int top = firstIndex-1, bottom = lastIndex+1, i, iTemp;
    x = 0;
    while (x < k) {
      if ((top >= 0) && ((index-top) <= (bottom-index))) {
        i = top;
      } else {
        if (bottom < dataCollection.getTotalData()) {
          i = bottom;
        } else {
          i = top;
        }
      }
      
      if (i > firstIndex) {
          iTemp = i - (lastIndex - firstIndex + 1);
      } else {
          iTemp = i;
      }
      if (distance.get(iTemp) < maxDistance) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          x++;
      } else if (distance.get(iTemp).equals(maxDistance) && (countMaxDistance > 0)) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          countMaxDistance--;
          x++;
      }
      
      if (i == top) {
        top--;
        if (bottom >= dataCollection.getTotalData()) {
          bottom++;
        }
      } else if (i == bottom) {
        bottom++;
      }
    }
    
    // Mencari kelas dengan count terbanyak
    int maxCount = countKelas[0], countMax = 0, maxIndex = 0;
    
    for (i = 1; i < countKelas.length; i++) {
      if (countKelas[i] > maxCount) {
        maxCount = countKelas[i];
        maxIndex = i;
      }
    }
    
    return dataCollection.getAttributeType().get(attributes.size()-1).get(maxIndex);
  }
  
  private String findBottomTopKelas(int index) {
    boolean stopLoop;
    int maxDistance = 0, countMaxDistance = 0, countKelas[], bottomDistance, topDistance, x;
    
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
            maxDistance = x;
            countMaxDistance = k - bottomDistance;
            stopLoop = true;
        } else {
            bottomDistance = topDistance;
            if ((x+1) < attributes.size()) {
                topDistance = topDistance + distanceAt[x+1];
            }
        }
        x++;
    }

    int top = index-1, bottom = index+1, i;
    x = 0;
    while (x < k) {
      if ((bottom < dataCollection.getTotalData()) && ((index-top) >= (bottom-index))) {
        i = bottom;
      } else {
        i = top;
      }
      
      if (x == 0) {
        i = index;
      }
      
      if (distance.get(i) < maxDistance) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          x++;
      } else if (distance.get(i).equals(maxDistance) && (countMaxDistance > 0)) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          countMaxDistance--;
          x++;
      }
      
      if (i == bottom) {
        bottom++;
        if (top < 0) {
          top--;
        }
      } else if (i == top) {
        top--;
      }
    }
    
    // Mencari kelas dengan count terbanyak
    int maxCount = countKelas[0], maxIndex = 0;
    
    for (i = 1; i < countKelas.length; i++) {
      if (countKelas[i] > maxCount) {
        maxCount = countKelas[i];
        maxIndex = i;
      }
    }

    return dataCollection.getAttributeType().get(attributes.size()-1).get(maxIndex);
  }
  
  private String findBottomTopKelas(int index, int firstIndex, int lastIndex) {
    boolean stopLoop;
    int maxDistance = 0, countMaxDistance = 0, countKelas[], bottomDistance, topDistance, x;
    
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
            maxDistance = x;
            countMaxDistance = k - bottomDistance;
            stopLoop = true;
        } else {
            bottomDistance = topDistance;
            if ((x+1) < attributes.size()) {
                topDistance = topDistance + distanceAt[x+1];
            }
        }
        x++;
    }
    
    int top = firstIndex-1, bottom = lastIndex+1, i, iTemp;
    x = 0;
    while (x < k) {
      if ((bottom < dataCollection.getTotalData()) && ((index-top) >= (bottom-index))) {
        i = bottom;
      } else {
        if (top >= 0) {
          i = top;
        } else {
          i = bottom;
        }
      }
      
      if (i > firstIndex) {
          iTemp = i - (lastIndex - firstIndex + 1);
      } else {
          iTemp = i;
      }
      if (distance.get(iTemp) < maxDistance) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          x++;
      } else if (distance.get(iTemp).equals(maxDistance) && (countMaxDistance > 0)) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          countMaxDistance--;
          x++;
      }
      
      if (i == bottom) {
        bottom++;
        if (top < 0) {
          top--;
        }
      } else if (i == top) {
        top--;
      }
    }
    
    // Mencari kelas dengan count terbanyak
    int maxCount = countKelas[0], countMax = 0, maxIndex = 0;
    
    for (i = 1; i < countKelas.length; i++) {
      if (countKelas[i] > maxCount) {
        maxCount = countKelas[i];
        maxIndex = i;
      }
    }
    
    return dataCollection.getAttributeType().get(attributes.size()-1).get(maxIndex);
  }
  
  private String findTopKelas(int index) {
    boolean stopLoop;
    int maxDistance = 0, countMaxDistance = 0, countKelas[], bottomDistance, topDistance, x;
    
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
            maxDistance = x;
            countMaxDistance = k - bottomDistance;
            stopLoop = true;
        } else {
            bottomDistance = topDistance;
            if ((x+1) < attributes.size()) {
                topDistance = topDistance + distanceAt[x+1];
            }
        }
        x++;
    }

    int top = index-1, bottom = index+1, i;
    x = 0;
    while (x < k) {
      if (top >= 0) {
        i = top;
      } else {
        i = bottom;
      }
      
      if (x == 0) {
        i = index;
      }
      
      if (distance.get(i) < maxDistance) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          x++;
      } else if (distance.get(i).equals(maxDistance) && (countMaxDistance > 0)) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          countMaxDistance--;
          x++;
      }
        
      if (i == top) {
        top--;
      } else if (i == bottom) {
        bottom++;
      }
    }
    
    // Mencari kelas dengan count terbanyak
    int maxCount = countKelas[0], countMax = 0, maxIndex = 0;
    
    for (i = 1; i < countKelas.length; i++) {
      if (countKelas[i] > maxCount) {
        maxCount = countKelas[i];
        maxIndex = i;
      }
    }
    
    return dataCollection.getAttributeType().get(attributes.size()-1).get(maxIndex);
  }
  
  private String findTopKelas(int index, int firstIndex, int lastIndex) {
    boolean stopLoop;
    int maxDistance = 0, countMaxDistance = 0, countKelas[], bottomDistance, topDistance, x;
    
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
            maxDistance = x;
            countMaxDistance = k - bottomDistance;
            stopLoop = true;
        } else {
            bottomDistance = topDistance;
            if ((x+1) < attributes.size()) {
                topDistance = topDistance + distanceAt[x+1];
            }
        }
        x++;
    }
    
    int top = firstIndex-1, bottom = lastIndex+1, i, iTemp;
    x = 0;
    while (x < k) {
      if (top >= 0) {
        i = top;
      } else {
        i = bottom;
      }
      
      if (i > firstIndex) {
          iTemp = i - (lastIndex - firstIndex + 1);
      } else {
          iTemp = i;
      }
      if (distance.get(iTemp) < maxDistance) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          x++;
      } else if (distance.get(iTemp).equals(maxDistance) && (countMaxDistance > 0)) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          countMaxDistance--;
          x++;
      }
      
      if (i == top) {
        top--;
      } else if (i == bottom) {
        bottom++;
      }
    }
    
    // Mencari kelas dengan count terbanyak
    int maxCount = countKelas[0], countMax = 0, maxIndex = 0;
    
    for (i = 1; i < countKelas.length; i++) {
      if (countKelas[i] > maxCount) {
        maxCount = countKelas[i];
        maxIndex = i;
      }
    }
    
    return dataCollection.getAttributeType().get(attributes.size()-1).get(maxIndex);
  }
  
  private String findBottomKelas(int index) {
    boolean stopLoop;
    int maxDistance = 0, countMaxDistance = 0, countKelas[], bottomDistance, topDistance, x;
    
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
            maxDistance = x;
            countMaxDistance = k - bottomDistance;
            stopLoop = true;
        } else {
            bottomDistance = topDistance;
            if ((x+1) < attributes.size()) {
                topDistance = topDistance + distanceAt[x+1];
            }
        }
        x++;
    }

    int top = index-1, bottom = index+1, i;
    x = 0;
    while (x < k) {
      if (bottom < dataCollection.getTotalData()) {
        i = bottom;
      } else {
        i = top;
      }
      
      if (x == 0) {
        i = index;
      }
      
      if (distance.get(i) < maxDistance) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          x++;
      } else if (distance.get(i).equals(maxDistance) && (countMaxDistance > 0)) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          countMaxDistance--;
          x++;
      }
      
      if (i == bottom) {
        bottom++;
      } else if (i == top) {
        top--;
      }
    }
    
    // Mencari kelas dengan count terbanyak
    int maxCount = countKelas[0], maxIndex = 0;
    
    for (i = 1; i < countKelas.length; i++) {
      if (countKelas[i] > maxCount) {
        maxCount = countKelas[i];
        maxIndex = i;
      }
    }

    return dataCollection.getAttributeType().get(attributes.size()-1).get(maxIndex);
  }
  
  private String findBottomKelas(int index, int firstIndex, int lastIndex) {
    boolean stopLoop;
    int maxDistance = 0, countMaxDistance = 0, countKelas[], bottomDistance, topDistance, x;
    
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
            maxDistance = x;
            countMaxDistance = k - bottomDistance;
            stopLoop = true;
        } else {
            bottomDistance = topDistance;
            if ((x+1) < attributes.size()) {
                topDistance = topDistance + distanceAt[x+1];
            }
        }
        x++;
    }
    
    int top = firstIndex-1, bottom = lastIndex+1, i, iTemp;
    x = 0;
    while (x < k) {
      if (bottom < dataCollection.getTotalData()) {
        i = bottom;
      } else {
        i = top;
      }
      
      if (i > firstIndex) {
          iTemp = i - (lastIndex - firstIndex + 1);
      } else {
          iTemp = i;
      }
      if (distance.get(iTemp) < maxDistance) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          x++;
      } else if (distance.get(iTemp).equals(maxDistance) && (countMaxDistance > 0)) {
          for (int j = 0; j < countKelas.length; j++) {
              if (dataCollection.getDatumAt(i).getAttributes().get(attributes.size()-1).equals(dataCollection.getAttributeType().get(attributes.size()-1).get(j))) {
                  (countKelas[j])++;
              }
          }
          countMaxDistance--;
          x++;
      }
      
      if (i == bottom) {
        bottom++;
      } else if (i == top) {
        top--;
      }
    }
    
    // Mencari kelas dengan count terbanyak
    int maxCount = countKelas[0], countMax = 0, maxIndex = 0;
    
    for (i = 1; i < countKelas.length; i++) {
      if (countKelas[i] > maxCount) {
        maxCount = countKelas[i];
        maxIndex = i;
      }
    }
    
    return dataCollection.getAttributeType().get(attributes.size()-1).get(maxIndex);
  }
}
