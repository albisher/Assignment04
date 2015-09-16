package edu.fau.ce.group8.assignment04c;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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

    private Random rX = new Random();
    private Random rY = new Random();
    private Timer moveHunted = new Timer();
    private TimerTask moleTask = new MoleTimerTask(Page2.this);

    private int hits = 0; // number of spots hit
    private int level = 1;
    private int levelcap = level*10;
    private TextView hitsTextView; // displays high score
    private TextView missesTextView; // displays current score
    private Button submit;
    private ImageView huntedImg;
    private ImageView shoot;
    private ProgressBar levelBar;
    private Handler mHandler = new Handler();

    private LinearLayout nB;
    private GlobalUser gN;


    private OnClickListener missListener = new OnClickListener() {
        public void onClick(View v) {
            System.out.println("Miss");
            //misses ++;
            hits--;

            shoot.setVisibility(View.INVISIBLE);
            //
            displayScores();
        }
    };

    private OnClickListener huntedListener = new OnClickListener() {
        public void onClick(View v) {
            hits ++;

            //
            // show the shooting mark on the click x,y
            shoot.setX(v.getX());
            shoot.setY(v.getY());
            shoot.setVisibility(View.VISIBLE);
            //
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

    // submit button listener
    private OnClickListener sListener = new OnClickListener() {
        public void onClick(View v) {
            gN.setLevel(level);

            Intent k = new Intent(Page2.this, Page3.class);
            startActivity(k);
        }
    };


    // functions added to help get the size of the screen.
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        shoot = (ImageView) findViewById(R.id.shoot);
        shoot.setVisibility(View.INVISIBLE);

        huntedImg = (ImageView) findViewById(R.id.theduck);
        huntedImg.setOnClickListener(huntedListener);
        huntedImg.bringToFront();

        submit = (Button) findViewById(R.id.endgame);
        submit.setOnClickListener(sListener);

        levelBar = (ProgressBar) findViewById(R.id.progressBar2);

        Button resetButton = (Button) findViewById(R.id.button1);
        resetButton.setOnClickListener(resetListener);

        hitsTextView = (TextView) findViewById(R.id.highScoreTextView);
        missesTextView = (TextView) findViewById(R.id.scoreTextView);

        // there click is not on huntedImg
        nB = (LinearLayout) findViewById(R.id.mainScreen);
        nB.setOnClickListener(missListener);

        gN = (GlobalUser) getApplication();
        int diff = gN.getDiff();

        //?
        if (diff == 0) {
            moveHunted.scheduleAtFixedRate(moleTask, 0, 2500);
        }
        if (diff == 1) {
            moveHunted.scheduleAtFixedRate(moleTask, 0, 1000);
        }

    }

    private void displayScores()
    {
        // display the high score, current score and level
        if (hits == levelcap) {
            level++;
            levelcap = level*10;
            hits = 0;

        }

        if (hits < 0) {
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


        hitsTextView.setText(getString(R.string.hit_score) + " " + hits);

        missesTextView.setText(getString(R.string.misses) + " " + level);
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
                            // todo huntedImg needs to not to go out side the screen
                            // the following System.prinnt helps to see the number passed in the
                            // function below
//                            System.out.println("width = "+ rX.nextInt(getScreenWidth()));
//                            System.out.println("height = " + rY.nextInt(getScreenHeight()));
                            huntedImg.bringToFront();
                            huntedImg.setX(rX.nextInt(getScreenWidth()) - (huntedImg.getWidth() / 2));
                            huntedImg.setY(rY.nextInt(getScreenHeight()) - (huntedImg.getHeight() / 2));
                        }
                    });
                }

            }).start();
        }
    }
}