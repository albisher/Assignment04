package edu.fau.ce.group8.assignment04c;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

import static edu.fau.ce.group8.assignment04c.R.id.linearLayout3;

public class Page3 extends AppCompatActivity {

    private Vector<Integer> levelArray = new Vector();
    private Vector<String> nameArray = new Vector();

    private GlobalUser gN;
    private int pos;
    //private int levelArray[] = new int[]
    //LinearLayout layout;
    //LinearLayout.LayoutParams layoutParams;
    private View.OnClickListener homeListener = new View.OnClickListener() {
        public void onClick(View v) {

            Intent k = new Intent(Page3.this, Page1.class);
            startActivity(k);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        gN = (GlobalUser) getApplication();
        if (gN.getIf()) {
            // todo add what we need to do once the user already exist
            // todo remove duplicates
        } else {
            // default that getIf vector is setup = false
            // we needed to change that to true once we set the vector.
            gN.setUpVectors();
            gN.setIf(true);
        }

        int arrSize = gN.getArrSize();
        arrSize++;

        int levelArray[] = new int[arrSize];
        String nameArray[] = new String[arrSize];
        int i;
        int currentLevel = gN.getLevel();
        String currentName = gN.getName();


        int lev;
        String name;

        // why ?
        gN.addLevelArray(0);
        gN.addNameArray("");

        for (i = 0; i < arrSize; i++)
        {
            lev = gN.getLevelArray(i);
            name = gN.getNameArray(i);
            levelArray[i] = lev;
            nameArray[i] = name;
        }




        for (i = 0; i<arrSize; i++)
        {
            if(currentLevel > gN.getLevelArray(i))
            {
                if(i == arrSize - 1)
                {

                } else {
                    for(int j = arrSize; j >i; j--)
                    {
                        pos = j-1;

                        // todo check why crash if the level gets above current max.
                        levelArray[j] = levelArray[pos];
                        nameArray[j] = nameArray[pos];
                    }
                }
                levelArray[i] = currentLevel;
                nameArray[i] = currentName;
                pos = i;
                i = arrSize;
            }
        }

        //arrSize++;

        for (i = 0; i<arrSize; i++){
            // we check if the array we are dealing with does not have the exact name
            // todo check why it is changing size of array and causing crash "out of boundry"
//            if (gN.nameArray.contains(nameArray[i])) {
//                System.out.println("duplicate name" + gN.nameArray.get(i) );
//            } else {
            gN.setLevelArray(levelArray[i], i);
            gN.setNameArray(nameArray[i], i);
//            }
        }


        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout3);
        for (i = 0; i < arrSize; i++) {
            Button btnTag = new Button(this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btnTag.setText(nameArray[i] + " Level " + levelArray[i]);
            btnTag.setId(i);


            layout.addView(btnTag);

//            if ( pos == i ) {
//            }

            //((Button) findViewById(i)).setOnClickListener(this);
        }

        i++;

        Button blank = new Button(this);
        blank.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        blank.setText("");
        blank.setId(i);
        layout.addView(blank);

        i++;

        Button home = new Button(this);
        home.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        home.setText("Start Over");
        home.setId(i);
        layout.addView(home);
        //home.setOnClickListener(homeListener);

        home.setOnClickListener(homeListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

