/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.agent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William Sentosa
 */
public class ConfusionMatrix {
    private int[][] matrix;
    private List<String> classes;
    private final int defaultSize = 4;
    private int size;
    
    public ConfusionMatrix() {
        classes = new ArrayList<String>();
        matrix = new int [defaultSize][defaultSize];
        size = defaultSize;
    }
    
    public ConfusionMatrix(List<String> classes) {
        this.classes = classes;
        size = classes.size();
        matrix = new int [size][size];
    }
    
    public int[][] getMatrix() {
        return matrix;
    }
    
    public int getSize() {
        return size;
    }
    
    public List<String> getClasses() {
        return classes;
    }
    
    public void setMatrix(int[][] mat) {
        matrix = mat;
    }
    
    public void setClasses(List<String> classes) {
        this.classes = classes;
        size = classes.size();
    }
    
    public void display() {
        System.out.print("           ");
        for(String kelas : classes) {
            System.out.print(kelas + "  ");
        }
        System.out.println();
        for(int i=0; i<size; i++) {
            System.out.print(classes.get(i) + "     ");
            for(int j=0; j<size; j++) {
                System.out.print(matrix[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
