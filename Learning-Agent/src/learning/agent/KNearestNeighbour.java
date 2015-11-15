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
  private String buying;
  private String maint;
  private String doors;
  private String persons;
  private String lugBoot;
  private String safety;
  private String kelas;
  private ArrayList<Integer> distance;  // Jarak neighbour
  private Integer[] distanceAt;
  
  // Konstruktor
  public KNearestNeighbour(int _k, DataCollection _dataCollection, String _buying, String _maint, String _doors, String _persons, String _lugBoot, String _safety) {
    dataCollection = new DataCollection();
    dataCollection.copy(_dataCollection);
    k = _k;
    buying = _buying;
    maint = _maint;
    doors = _doors;
    persons = _persons;
    lugBoot = _lugBoot;
    safety = _safety;
    distance = new ArrayList<Integer>();
    distanceAt = new Integer[7];
    for (int i = 0; i < 7; i++) {
      distanceAt[i] = 0;
    }
  }
  
  // Getter
  public String getKelas() {
    return kelas;
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
      
      // Membandingkan atribut buying
      if (!buying.equals(dataCollection.getDatumAt(i).getBuying())) {
        countDistance++;
      }
      
      // Membandingkan atribut maint
      if (!maint.equals(dataCollection.getDatumAt(i).getMaint())) {
        countDistance++;
      }
      
      // Membandingkan atribut doors
      if (!doors.equals(dataCollection.getDatumAt(i).getDoors())) {
        countDistance++;
      }
      
      // Membandingkan atribut persons
      if (!persons.equals(dataCollection.getDatumAt(i).getPersons())) {
        countDistance++;
      }
      
      // Membandingkan atribut lugBoot
      if (!lugBoot.equals(dataCollection.getDatumAt(i).getLugBoot())) {
        countDistance++;
      }
      
      // Membandingkan atribut safety
      if (!safety.equals(dataCollection.getDatumAt(i).getSafety())) {
        countDistance++;
      }
      
      distance.add(countDistance);
      
      if (countDistance.equals(0)) {
        (distanceAt[0])++;
      } else if (countDistance.equals(1)) {
        (distanceAt[1])++;
      } else if (countDistance.equals(2)) {
        (distanceAt[2])++;
      } else if (countDistance.equals(3)) {
        (distanceAt[3])++;
      } else if (countDistance.equals(4)) {
        (distanceAt[4])++;
      } else if (countDistance.equals(5)) {
        (distanceAt[5])++;
      } else {  // countDistance.equals(6)
        (distanceAt[6])++;
      }
    }
  }
  
  public void findKelas() {
    int maxDistance, countMaxDistance, countKelas[];
    
    countKelas = new int[4];
    for (int i = 0; i < 4; i++) {
      countKelas[i] = 0;
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
        if (dataCollection.getDatumAt(i).getKelas().equals("unacc")) {
          (countKelas[0])++;
        } else if (dataCollection.getDatumAt(i).getKelas().equals("acc")) {
          (countKelas[1])++;
        } else if (dataCollection.getDatumAt(i).getKelas().equals("good")) {
          (countKelas[2])++;
        } else {  // dataCollection.getDatumAt(i).getKelas().equals("vgood")
          (countKelas[3])++;
        }
      } else if (distance.get(i).equals(maxDistance) && (countMaxDistance > 0)) {
        if (dataCollection.getDatumAt(i).getKelas().equals("unacc")) {
          (countKelas[0])++;
        } else if (dataCollection.getDatumAt(i).getKelas().equals("acc")) {
          (countKelas[1])++;
        } else if (dataCollection.getDatumAt(i).getKelas().equals("good")) {
          (countKelas[2])++;
        } else {  // dataCollection.getDatumAt(i).getKelas().equals("vgood")
          (countKelas[3])++;
        }
        
        countMaxDistance--;
      }
    }
    
    // Mencari kelas dengan count terbanyak
    int maxCount = countKelas[0], countMax = 0, maxIndex = 0;
    
    for (int i = 1; i < 4; i++) {
      if (countKelas[i] > maxCount) {
        maxCount = countKelas[i];
        maxIndex = i;
      }
    }
    
    for (int i = 0; i < 4; i++) {
      if (countKelas[i] == maxCount) {
        countMax++;
      }
    }
    
    if (countMax > 1) {
      kelas = "none";
    } else { // countMax <= 1
      if (maxIndex == 0) {
        kelas = "unacc";
      } else if (maxIndex == 1) {
        kelas = "acc";
      } else if (maxIndex == 2) {
        kelas = "good";
      } else {  // maxIndex == 3
        kelas = "vgood";
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
