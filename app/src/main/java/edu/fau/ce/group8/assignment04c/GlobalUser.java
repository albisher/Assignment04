package edu.fau.ce.group8.assignment04c;

/**
 * Created by Group8
 */

import android.app.Application;

import java.util.Vector;

// class NamingConvention
public class GlobalUser extends Application {
    Integer id;
    String name;
    String pass;
    String age;
    String gender;
    Integer level;
    Integer diff;
    boolean ifSetUp = false;
    // why ?
    Vector<Integer> levelArray = new Vector();
    Vector<String> nameArray = new Vector();

    public GlobalUser getUser() {
        return this;
    }

    // needs to be there form the instructor
    // filler for arrays .. it is not using this class.
    public void setUpVectors () {
        levelArray.add(3);
        levelArray.add(2);
        levelArray.add(1);
        nameArray.add("Luke");
        nameArray.add("Jane");
        nameArray.add("Bill");
    }


    protected Integer getID() {
        return this.id;
    }

    protected void setId(Integer i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String s){
        this.name = s;
    }

    public Integer getDiff() {
        return this.diff;
    }
    public void setDiff(int s){
        this.diff = s;
    }

    public String getPass() {
        return this.pass;
    }
    public void setPass(String s){
        this.pass = s;
    }

    public String getAge() {
        return this.age;
    }
    public void setAge(String s){
        this.age = s;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String s) {
        this.gender = s;
    }

    public int getLevel() {
        return this.level;
    }
    public void setLevel(int i){this.level = i;}

    //
    public String getNameArray (int i){return this.nameArray.elementAt(i);}
    public void addNameArray(String s){this.nameArray.add(s);}
    public void setNameArray(String s, int i){this.nameArray.setElementAt(s, i);}

    public int getLevelArray(int i) {
        return this.levelArray.elementAt(i);
    }
    public void addLevelArray(int i){{this.levelArray.add(i);}}
    public void setLevelArray(int j, int i){this.levelArray.setElementAt(j, i);}

    public boolean getIf(){
        return this.ifSetUp;
    }

    public void setIf(boolean s){
        this.ifSetUp = s;
    }

    public int getArrSize() {
        return levelArray.size();
    }
}