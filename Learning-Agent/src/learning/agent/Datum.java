package learning.agent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angelynz95
 */
public class Datum {
  // Atribut
  private String buying;
  private String maint;
  private String doors;
  private String persons;
  private String lugBoot;
  private String safety;
  private String kelas;
  
  // Konstruktor
  public Datum(String _buying, String _maint, String _doors, String _persons, String _lugBoot, String _safety, String _kelas) {
    buying = _buying;
    maint = _maint;
    doors = _doors;
    persons = _persons;
    lugBoot = _lugBoot;
    safety = _safety;
    kelas = _kelas;
  }
  
  // Getter
  public String getBuying() {
    return buying;
  }
  
  public String getMaint() {
    return maint;
  }
  
  public String getDoors() {
    return doors;
  }
  
  public String getPersons() {
    return persons;
  }
  
  public String getLugBoot() {
    return lugBoot;
  }
  
  public String getSafety() {
    return safety;
  }
  
  public String getKelas() {
    return kelas;
  }
}
