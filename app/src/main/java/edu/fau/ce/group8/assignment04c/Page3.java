package edu.fau.ce.group8.assignment04c;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class Page3 extends AppCompatActivity {

    private GlobalUser globalUser;

    private int pos;

    private Integer topUsersNumber = 10;
    private View.OnClickListener homeListener = new View.OnClickListener() {
        public void onClick(View v) {

            Intent k = new Intent(Page3.this, Page1.class);
            startActivity(k);
        }
    };
    private View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            showInformationAboutUser(v);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        globalUser = (GlobalUser) getApplication();

        if (!globalUser.getIf()) {//changed this statement to check for it being empty instead of full
            globalUser.setUpVectors();
            globalUser.setIf(true);
        }

        int arrSize = globalUser.getArrSize();//get number of entries before new add
        arrSize++;//add one to number of entries to prepare for new add

        int levelArray[] = new int[arrSize];
        String nameArray[] = new String[arrSize];
        int i;
        int currentLevel = globalUser.getLevel();
        String currentName = globalUser.getName();


        int lev;
        String name;

        globalUser.addLevelArray(0);//pushes an extra cell into the vector to accomodate new addition.
        // Want it to start blank so new addition can be placed in correct order
        globalUser.addNameArray("");

        for (i = 0; i < arrSize; i++)//fills array with data from global vectors
        //the last position ( Array[arrSize -1] ) will have level 0 and and name " "
        {
            lev = globalUser.getLevelArray(i);
            name = globalUser.getNameArray(i);
            levelArray[i] = lev;
            nameArray[i] = name;
        }

//        int done = 0;
        for (i = 0; i<arrSize; i++)
        {
            if (currentLevel > levelArray[i])
            {
                if (i == arrSize - 1)//need to not shift down if it's the last position
                {
                    levelArray[i] = currentLevel;
                    nameArray[i] = currentName;


                } else {
                    for (int j = arrSize - 1; j > i; j--)//sets j to last cell
                    //iterates until it gets to spot we want to put the new score into
                    //so the array gets shifted down from the spot of the new insert
                    {
                        pos = j - 1;//always the cell right before j
                        levelArray[j] = levelArray[pos];//shifts everything down a cell up to wanted position
                        nameArray[j] = nameArray[pos];
                    }
                    levelArray[i] = currentLevel;//sets new entry into its place in array
                    nameArray[i] = currentName;
                }

                pos = i;//sets pos to index of newest addition in array
                i = arrSize;
            }
        }


        for (i = 0; i < arrSize; i++) {//copies new array from Page3 to global vector.
            globalUser.setLevelArray(levelArray[i], i);
            globalUser.setNameArray(nameArray[i], i);
        }


        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout3);

        // to display only the desired number set in topUserNumber
        int forLimit = topUsersNumber;
        if (topUsersNumber > arrSize) {
            forLimit = arrSize;
        }

        Integer highestLevelUser = 0;
        for (i = 0; i < forLimit; i++) {
            Button btnTag = new Button(this);
            // addButton(btnName, Text, id)
            addButton(btnTag, (nameArray[i] + " Level " + levelArray[i]), i);
            layout.addView(btnTag); //show (i) button in layout

            // todo link (i) button to that user details and goto p4 to show info there
            if (levelArray[i] > highestLevelUser) {
                highestLevelUser = levelArray[i];
            }
        }

        i++;

        Button blank = new Button(this);
        addButton(blank, "Latest Score Details", i);
        layout.addView(blank);
        blank.setOnClickListener(btnListener);

        i++;

        Button home = new Button(this);
        addButton(home, "Start Over", i);
        layout.addView(home);
        home.setOnClickListener(homeListener);

    }

    private void showInformationAboutUser(View v) {


        Intent k = new Intent(Page3.this, Page4.class);
        startActivity(k);
    }

    private void addButton(Button name, String text, Integer id) {
        name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        name.setText(text);
        name.setId(id);
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

