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
    private int[] count;
    private float[][] buying;
    private float[][] maint;
    private float[][] doors;
    private float[][] persons;
    private float[][] lug_boot;
    private float[][] safety;
    
    public NaiveBayesLearning(DataCollection _dataCollection) {
        dataCollection = _dataCollection;
        count = new int[4];
        buying = new float[4][4];
        maint = new float[4][4];
        doors = new float[4][4];
        persons = new float[3][4];
        lug_boot = new float[3][4];
        safety = new float[3][4];
    }
    
    public float[][] getBuyingModel() {
        float[][] result = new float[4][4];
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                result[i][j] = buying[i][j];
            }
        }
        return result;
    }
    
    public float[][] getMaintModel() {
        float[][] result = new float[4][4];
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                result[i][j] = maint[i][j];
            }
        }
        return result;
    }
    public float[][] getDoorsModel() {
        float[][] result = new float[4][4];
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                result[i][j] = doors[i][j];
            }
        }
        return result;
    }
    public float[][] getPersonsModel() {
        float[][] result = new float[4][4];
        for(int i=0; i<3; i++) {
            for(int j=0; j<4; j++) {
                result[i][j] = persons[i][j];
            }
        }
        return result;
    }
    public float[][] getlugBoot() {
        float[][] result = new float[4][4];
        for(int i=0; i<3; i++) {
            for(int j=0; j<4; j++) {
                result[i][j] = lug_boot[i][j];
            }
        }
        return result;
    }
    public float[][] getSafetyModel() {
        float[][] result = new float[4][4];
        for(int i=0; i<3; i++) {
            for(int j=0; j<4; j++) {
                result[i][j] = safety[i][j];
            }
        }
        return result;
    }
    
    public void process() {
        fillWithAtrFrequency();
        countProbability();
    }
    
    private void fillWithAtrFrequency() {
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
                    lug_boot[2][col]++;
                    break;
            }
            switch (datum.getSafety()) {
                case "low": 
                    safety[0][col]++;
                    break;
                case "med": 
                    safety[1][col]++;
                    break;
                case "high": 
                    safety[2][col]++;
                    break;
            }
            System.out.println("*** Buying ***");
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    System.out.print(buying[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("*** Maint ***");
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    System.out.print(maint[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("*** Doors ***");
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    System.out.print(doors[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("*** Persons ***");
            for(int i=0; i<3; i++) {
                for(int j=0; j<4; j++) {
                    System.out.print(persons[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("*** Lug_boot ***");
            for(int i=0; i<3; i++) {
                for(int j=0; j<4; j++) {
                    System.out.print(lug_boot[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("*** Safety ***");
            for(int i=0; i<3; i++) {
                for(int j=0; j<4; j++) {
                    System.out.print(safety[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("*** Maint ***");
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    System.out.print(maint[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
    
    private void countProbability() {
        for(int i=0; i<4; i++) {
            count[i] = (int)countInCol(buying, 4, i);
        }
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                buying[i][j] = buying[i][j]/count[j];
            }
        }
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                maint[i][j] = maint[i][j]/count[j];
            }
        }
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                doors[i][j] = doors[i][j]/count[j];
            }
        }
        for(int i=0; i<3; i++) {
            for(int j=0; j<4; j++) {
                persons[i][j] = persons[i][j]/count[j];
            }
        }
        for(int i=0; i<3; i++) {
            for(int j=0; j<4; j++) {
                lug_boot[i][j] = lug_boot[i][j]/count[j];
            }
        }
        for(int i=0; i<3; i++) {
            for(int j=0; j<4; j++) {
                safety[i][j] = safety[i][j]/count[j];
            }
        }
        
    }
    
    private float countInCol(float[][] table, int maxRow, int col) {
        float c = 0;
        for(int i=0; i<maxRow; i++) {
            c = c + table[i][col];
        }
        return c;
    }
    
    public float probability(String atr1, String atr2, String atr3, String atr4, String atr5, String atr6, String kelas) {
        float result=0;
        int col=0;
        switch(kelas) {
            case "unacc" : 
                col = 0;
                result = (float)count[col]/ (float)(count[0]+count[1]+count[2]+count[3]);
                break;
            case "acc" : 
                col = 1;
                result = (float)count[col]/(float)(count[0]+count[1]+count[2]+count[3]);
                break;
            case "good" : 
                col = 2;
                result = (float)count[col]/(float)(count[0]+count[1]+count[2]+count[3]);
                break;
            case "vgood" : 
                col = 3;
                result = (float)count[col]/(float)(count[0]+count[1]+count[2]+count[3]);
                break;
        }
        //System.out.println("1: "+result);
        switch(atr1) {
            case "vhigh" :  result = result*buying[0][col]; break;
            case "high" :  result = result*buying[1][col]; break;
            case "med" :  result = result*buying[2][col]; break;
            case "low" :  result = result*buying[3][col]; break;
        }
        //System.out.println("2: " + result);
        switch(atr2) {
            case "vhigh" :  result = result*maint[0][col]; break;
            case "high" :  result = result*maint[1][col]; break;
            case "med" :  result = result*maint[2][col]; break;
            case "low" :  result = result*maint[3][col]; break;
        }
        //System.out.println("3: " + result);
        switch(atr3) {
            case "2" :  result = result*doors[0][col]; break;
            case "3" :  result = result*doors[1][col]; break;
            case "4" :  result = result*doors[2][col]; break;
            case "5more" :  result = result*doors[3][col]; break;
        }
        //System.out.println("4: "+result);
        switch(atr4) {
            case "2" :  result = result*persons[0][col]; break;
            case "3" :  result = result*persons[1][col]; break;
            case "more" :  result = result*persons[2][col]; break;
        }
        //System.out.println("5: "+result);
        switch(atr5) {
            case "small" :  result = result*lug_boot[0][col]; break;
            case "med" :  result = result*lug_boot[1][col]; break;
            case "big" :  result = result*lug_boot[2][col]; break;
        }
        //System.out.println("6: "+result);
        System.out.println("safety : " + safety[0][col]);
        switch(atr6) {
            case "low" :  result = result*safety[0][col]; break;
            case "med" :  result = result*safety[1][col]; break;
            case "high" :  result = result*safety[2][col]; break;
        }
        //System.out.println("7: "+result);
        return result;
    }
    
    public static void main(String[] args) {
        DataCollection dataCol = new DataCollection();
        dataCol.readFile("data.txt");
        NaiveBayesLearning agent = new NaiveBayesLearning(dataCol);
        agent.process();
        float[][] result;
        System.out.println("Safety model"); 
        result = agent.getSafetyModel();
        /*
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Probabilitas untuk data pertama, kelas unacc : " + agent.probability("med","low","2","4","med","high","unacc"));
        
        System.out.println("Probabilitas untuk data pertama, kelas acc : " + agent.probability("med","low","2","4","med","high","acc"));
        System.out.println("Probabilitas untuk data pertama, kelas good : " + agent.probability("med","low","2","4","med","high","good"));
        System.out.println("Probabilitas untuk data pertama, kelas vgood : " + agent.probability("med","low","2","4","med","high","vgood"));
        */
    }
    
}
