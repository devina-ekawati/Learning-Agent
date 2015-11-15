/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.agent;
import java.util.*;

/**
 *
 * @author William Sentosa
 */
public class NaiveBayesLearning {
    
    private DataCollection dataCollection;
    private float[][] buying;
    private float[][] maint;
    private float[][] doors;
    private float[][] persons;
    private float[][] lug_boot;
    private float[][] safety;
    
    public NaiveBayesLearning(DataCollection _dataCollection) {
        dataCollection.copy(_dataCollection);
        buying = new float[4][4];
        maint = new float[4][4];
        doors = new float[4][4];
        lug_boot = new float[3][4];
        safety = new float[3][4];
    }
    
    public void process() {
        int col=0;
        for(Datum datum: dataCollection.getData()) {
            switch (datum.getKelas()) {
                case "unacc":
                    col=0;
                    break;
                case "acc":
                    col=1;
                    break;
                case "good":
                    col=2;
                    break;
                case "vgood":
                    col=3;
                    break;
            }
            switch (datum.getBuying()) {
                case "vhigh": 
                    buying[0][col]++;
                    break;
                case "high": 
                    buying[1][col]++;
                    break;
                case "med": 
                    buying[2][col]++;
                    break;
                case "low": 
                    buying[3][col]++;
                    break;
            }
            switch (datum.getMaint()) {
                case "vhigh": 
                    maint[0][col]++;
                    break;
                case "high": 
                    maint[1][col]++;
                    break;
                case "med": 
                    maint[2][col]++;
                    break;
                case "low": 
                    maint[3][col]++;
                    break;
            }
            switch (datum.getDoors()) {
                case "2": 
                    doors[0][col]++;
                    break;
                case "3": 
                    doors[1][col]++;
                    break;
                case "4": 
                    doors[2][col]++;
                    break;
                case "5more": 
                    doors[3][col]++;
                    break;
            }
            switch (datum.getPersons()) {
                case "2": 
                    persons[0][col]++;
                    break;
                case "4": 
                    persons[1][col]++;
                    break;
                case "more": 
                    persons[2][col]++;
                    break;
            }
            switch (datum.getLugBoot()) {
                case "small": 
                    lug_boot[0][col]++;
                    break;
                case "med": 
                    lug_boot[1][col]++;
                    break;
                case "big": 
                    persons[2][col]++;
                    break;
            }
            switch (datum.getSafety()) {
                case "low": 
                    persons[0][col]++;
                    break;
                case "med": 
                    persons[1][col]++;
                    break;
                case "high": 
                    persons[2][col]++;
                    break;
            }
        }
        
        
    }
    
    private void processBuying() {
        
    }
    
}
