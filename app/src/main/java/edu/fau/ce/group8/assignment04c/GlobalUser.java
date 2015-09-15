package edu.fau.ce.group8.assignment04c;

/**
 * Created by Group8, aalbisher on 9/12/15.
 */

import android.app.Application;
import java.util.Vector;

// class NamingConvention
public class GlobalUser extends Application {
    String name;
    String pass;
    String age;
    int level;
    boolean ifSetUp = false;

    Vector<Integer> levelArray = new Vector();
    Vector<String> nameArray = new Vector();


    public void setUpVectors () {
        levelArray.add(1);
        levelArray.add(2);
        levelArray.add(3);
        nameArray.add("Bill");
        nameArray.add("Jane");
        nameArray.add("Luke");
    }

    // this does not look good for security,
    // there should be a way better than using setters as public
    public String getName (){
        return this.name;
    }

    public void setName(String s){
        this.name = s;
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

    public int getLevel (){return this.level;}
    public void setLevel(int i){this.level = i;}

    public String getNameArray (int i){return this.nameArray.elementAt(i);}
    public void setNameArray(String j){this.nameArray.add(j);}

    public int getLevelArray (int i){return this.levelArray.elementAt(i);}
    public void setLevelArray(int i){this.levelArray.add(i);}

    public boolean getIf(){
        return this.ifSetUp;
    }

    public void setIf(boolean s){
        this.ifSetUp = s;
    }


}
