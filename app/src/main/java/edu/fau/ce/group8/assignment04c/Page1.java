package edu.fau.ce.group8.assignment04c;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class Page1 extends AppCompatActivity {

    protected Button restart;
    protected Button submit;

    protected EditText name;
    protected EditText pass;
    protected TextView age;
    protected SeekBar sBar;
    protected int sBarValue = 0;
    protected String s = null;
    private RadioGroup radiogroup;
    private RadioButton male, female;
    private Switch mode;
    private View.OnClickListener submitListener = new View.OnClickListener() {
        public void onClick(View v) {

            GlobalUser gN = (GlobalUser) getApplication();
            //submitF();
            gN.setName(name.getText().toString());
            gN.setPass(pass.getText().toString());
            gN.setAge(age.getText().toString());

            int selectedId = radiogroup.getCheckedRadioButtonId();
            if (selectedId == male.getId()) {
                gN.setGender("Male");
            }
            if (selectedId == female.getId()) {
                gN.setGender("Female");
            }

            if (mode.isChecked()) {
                gN.setDiff(1);
            } else {
                gN.setDiff(0);
            }


            submitF();
        }
    };
    private View.OnClickListener startListener = new View.OnClickListener() {
        public void onClick(View v) {
            startF();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        mode = (Switch) findViewById(R.id.switch1);
        mode.setChecked(false);

        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        male = (RadioButton) findViewById(R.id.radiomale);
        female = (RadioButton) findViewById(R.id.radiofemale);

        restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(startListener);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(submitListener);

        name = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);

        age = (TextView) findViewById(R.id.textView4);

        sBar = (SeekBar) findViewById(R.id.seekBar);
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sBarValue = progress;
                s = Integer.toString(sBarValue);
                age.setText(s);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                s = Integer.toString(sBarValue);
                age.setText(s);
            }
        });
    }

    private void submitF() {
        Intent p2 = new Intent(Page1.this, Page2.class );
        startActivity(p2);

    }

    private void startF() {
        Intent k = new Intent(Page1.this, Start.class);
        startActivity(k);
    }

}
