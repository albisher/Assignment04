package edu.fau.ce.group8.assignment04c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Context;

public class Page2 extends AppCompatActivity {


    private int hits = 0; // number of spots hit

    private int level = 1;
    private int levelcap = level*10;
    private TextView hitsTextView; // displays high score
    private TextView missesTextView; // displays current score
    private Button submit;
    private ImageView moleImage;
    private ImageView topmissingImage;
    private ImageView bottommissingImage;
    private ProgressBar levelBar;
    Random  rX = new Random();
    Random rY = new Random();
    Timer moveMole = new Timer();
    TimerTask moleTask = new MoleTimerTask(Page2.this);



    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        submit = (Button) findViewById(R.id.endgame);
        submit.setOnClickListener(sListener);

        levelBar = (ProgressBar) findViewById(R.id.progressBar2);


        Button resetButton = (Button) findViewById(R.id.button1);
        resetButton.setOnClickListener(resetListener);

        hitsTextView = (TextView) findViewById(R.id.highScoreTextView);
        missesTextView = (TextView) findViewById(
                R.id.scoreTextView);


        moleImage = (ImageView) findViewById(R.id.themole);
        moleImage.setOnClickListener(moleListener);
        moleImage.bringToFront();

        GlobalUser gN = (GlobalUser)getApplication();
        int diff = gN.getDiff();

        if(diff == 0){
            moveMole.scheduleAtFixedRate(moleTask, 0, 2500);}
        if (diff == 1){
            moveMole.scheduleAtFixedRate(moleTask, 0, 1000);
        }


        topmissingImage = (ImageView) findViewById(R.id.topmissingView);
        topmissingImage.setOnClickListener(topmissListener);
        topmissingImage.setAlpha(0);

        bottommissingImage = (ImageView) findViewById(R.id.bottommissingView);
        bottommissingImage.setOnClickListener(bottommissListener);
        bottommissingImage.setAlpha(0);
    }


    public class MoleTimerTask extends TimerTask {

        private Context context;
        // Write Custom Constructor to pass Context
        public MoleTimerTask(Context con) {
            this.context = con;
        }
        public void run() {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            moleImage.setX(rX.nextInt(1000));
                            moleImage.setY(rY.nextInt(500));


                        }
                    });
                }

            }).start();

        }

    }

    private OnClickListener topmissListener = new OnClickListener() {
        public void onClick(View v) {
            //misses ++;
            hits--;
            displayScores();


        }
    };

    private OnClickListener bottommissListener = new OnClickListener() {
        public void onClick(View v) {
            //misses ++;
            hits--;
            displayScores();


        }
    };


    private OnClickListener moleListener = new OnClickListener() {
        public void onClick(View v) {
            hits ++;

            displayScores();


        }
    };


    private OnClickListener resetListener = new OnClickListener() {
        public void onClick(View v) {

            hits = 0;
            //misses = 0;
            level = 0;
            displayScores();
            Intent k = new Intent(Page2.this, Page1.class);
            startActivity(k);

        }
    };

    private OnClickListener sListener = new OnClickListener() {
        public void onClick(View v) {

            GlobalUser gN = (GlobalUser)getApplication();
            gN.setLevel(level);

            Intent k = new Intent(Page2.this, Page3.class);
            startActivity(k);

        }
    };


    private void displayScores()
    {
        // display the high score, current score and level
        if (hits==levelcap)
        {
            level++;
            levelcap = level*10;
            hits = 0;

        }
        if (hits < 0)
        {
            if (level > 1)
            {
                level--;
                levelcap = level*10;
                hits = 0;
            }
            if (level == 1)
            {
                hits = 0;
            }
        }


        levelBar.setMax(levelcap);
        levelBar.setProgress(hits);





        hitsTextView.setText(
                getString(R.string.hit_score) + " " + hits);

        missesTextView.setText(
                getString(R.string.misses) + " " + level);
    } // end function displayScores



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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