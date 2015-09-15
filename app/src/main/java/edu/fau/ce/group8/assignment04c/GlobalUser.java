package edu.fau.ce.group8.assignment04c;

/**
 * Created by Group8
 */

import android.app.Application;
import java.util.Vector;

// class NamingConvention
public class GlobalUser extends Application {
    String name;
    String pass;
    String age;
    String gender;
    int level, diff;
    boolean ifSetUp = false;


    Vector<Integer> levelArray = new Vector();
    Vector<String> nameArray = new Vector();


    public void setUpVectors () {
        levelArray.add(3);
        levelArray.add(2);
        levelArray.add(1);
        nameArray.add("Luke");
        nameArray.add("Jane");
        nameArray.add("Bill");


    }

    // this does not look good for security,
    // there should be a way better than using setters as public
    public int getArrSize(){return levelArray.size();}


    public String getName (){
        return this.name;
    }

    public void setName(String s){
        this.name = s;
    }

    public int getDiff (){
        return this.diff;
    }

    public void setDiff(int s){
        this.diff = s;
    }

    public String getPass (){
        return this.pass;
    }

    public void setPass(String s){
        this.pass = s;
    }

    public String getAge (){
        return this.age;
    }

    public void setAge(String s){
        this.age = s;
    }

    public String getGender (){
        return this.gender;
    }

    public void setGender(String s){
        this.gender = s;
    }

    public int getLevel (){return this.level;}
    public void setLevel(int i){this.level = i;}

    public String getNameArray (int i){return this.nameArray.elementAt(i);}
    public void addNameArray(String s){this.nameArray.add(s);}
    public void setNameArray(String s, int i){this.nameArray.setElementAt(s, i);}

    public int getLevelArray (int i){return this.levelArray.elementAt(i);}
    public void addLevelArray(int i){{this.levelArray.add(i);}}
    public void setLevelArray(int j, int i){this.levelArray.setElementAt(j, i);}

    public boolean getIf(){
        return this.ifSetUp;
    }

    public void setIf(boolean s){
        this.ifSetUp = s;
    }


}
