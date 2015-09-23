package edu.fau.ce.group8.assignment04c;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Page4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);

        GlobalUser gu = (GlobalUser) getApplication();


        TextView id = (TextView) findViewById(R.id.id);

        TextView name = (TextView) findViewById(R.id.name);
        TextView pass = (TextView) findViewById(R.id.password);
        TextView age = (TextView) findViewById(R.id.age);
        TextView diff = (TextView) findViewById(R.id.diff);
        TextView gender = (TextView) findViewById(R.id.gender);
        TextView level = (TextView) findViewById(R.id.level);

        System.out.println("GlobalUser Name in name P4 is : " + gu.getName());

//        id.setText("Latest Score Details");

        name.setText("Name : " + gu.getName());
        pass.setText("Pass : " + gu.getPass());
        age.setText("Age : " + gu.getAge());

        String s = gu.getDiff().toString();
        diff.setText("Difficulty : " + s);
        gender.setText("Gender : " + gu.getGender());

        Integer i = gu.getLevel();
        s = i.toString();
        level.setText("Level :" + s);

        //Everything below is for home button
        LinearLayout layout = (LinearLayout) findViewById(R.id.lowerLL);

        Button home = new Button(this);
        addButton(home, "Start Over", 1);
        layout.addView(home);
        // padding left, top, right, bottom. all int

        home.setOnClickListener(homeListener);

    }

    private void addButton(Button name, String text, Integer id) {
        name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        name.setText(text);
        name.setId(id);
    }

    private View.OnClickListener homeListener = new View.OnClickListener() {
        public void onClick(View v) {

            Intent k = new Intent(Page4.this, Page1.class);
            startActivity(k);
        }
    };

}
