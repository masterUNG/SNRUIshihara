package appewtc.masterung.snruishihara;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private TextView txtQuestion;
    private ImageView imvIshihara;
    private RadioGroup ragChoice;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private Button btnAnswer;
    private int intRadio, intIndex, intScore, intUserChoose[], intAnswerTrue[];
    private MyModel objMyModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial Widget
        initialWidget();

        //Button Controller
        buttonController();

        //Radio Controller
        radioController();

        //Interface MyModel
        interfaceMyModel();

    }   // onCreate

    private void interfaceMyModel() {
        objMyModel = new MyModel();
        objMyModel.setOnMyModelChangeListener(new MyModel.OnMyModelChangeListener() {
            @Override
            public void onMyModelChangeListener(MyModel myModel) {

                //Change View by Model
                changeView(myModel.getIntButton());

            }
        });
    }

    private void changeView(int intModel) {

        int intImage[] = new int[]{R.drawable.ishihara_01, R.drawable.ishihara_02,
                R.drawable.ishihara_03, R.drawable.ishihara_04,
                R.drawable.ishihara_05, R.drawable.ishihara_06,
                R.drawable.ishihara_07, R.drawable.ishihara_08,
                R.drawable.ishihara_09, R.drawable.ishihara_10};
        imvIshihara.setImageResource(intImage[intModel]);

        //Change Choice
        int intChoice[] = new int[]{R.array.time1, R.array.time2,
                R.array.time3, R.array.time4,
                R.array.time5, R.array.time6,
                R.array.time7, R.array.time8,
                R.array.time9, R.array.time10};

        String strChoice[] = getResources().getStringArray(intChoice[intModel]);
        radChoice1.setText(strChoice[0]);
        radChoice2.setText(strChoice[1]);
        radChoice3.setText(strChoice[2]);
        radChoice4.setText(strChoice[3]);

    }


    private void radioController() {
        ragChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //Sound Effect
                MediaPlayer objMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_shut);
                objMediaPlayer.start();

                //setUp intRadio
                switch (checkedId) {
                    case R.id.radioButton:
                        intRadio = 1;
                        break;
                    case R.id.radioButton2:
                        intRadio = 2;
                        break;
                    case R.id.radioButton3:
                        intRadio = 3;
                        break;
                    case R.id.radioButton4:
                        intRadio = 4;
                        break;
                    default:
                        intRadio = 0;
                        break;
                }   // switch

            }   // event
        });
    }

    private void buttonController() {
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sound Effect
                MediaPlayer objMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_long);
                objMediaPlayer.start();

                //Check Answer
                checkAnswer();

            }   // event
        });
    }

    private void checkAnswer() {

        if (intRadio == 0) {

            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.showDialog(MainActivity.this);

        } else {

            //Check Score
            checkScore();

            //Check Times
            checkTimes();

            //Clear Check
            ragChoice.clearCheck();

        }

    }

    private void checkScore() {

        intAnswerTrue = new int[]{1, 2, 3, 1, 2, 3, 1, 2, 4, 4};
        intUserChoose = new int[10];

        intUserChoose[intIndex] = intRadio;
        if (intUserChoose[intIndex] == intAnswerTrue[intIndex]) {
            intScore++;
        }

    }


    private void checkTimes() {

        if (intIndex == 9) {

            //Intent to ShowScore
            Intent objIntent = new Intent(MainActivity.this, ShowScoreActivity.class);
            objIntent.putExtra("Score", intScore);
            startActivity(objIntent);
            finish();

        } else {

            //Call View by Controller
            txtQuestion.setText(Integer.toString(intIndex + 2) + ". What is this ?");
            intIndex += 1;

            //Call Model by Controller
            objMyModel.setIntButton(intIndex);

        }

    }

    private void initialWidget() {
        txtQuestion = (TextView) findViewById(R.id.textView2);
        imvIshihara = (ImageView) findViewById(R.id.imageView);
        ragChoice = (RadioGroup) findViewById(R.id.ragChoice);
        radChoice1 = (RadioButton) findViewById(R.id.radioButton);
        radChoice2 = (RadioButton) findViewById(R.id.radioButton2);
        radChoice3 = (RadioButton) findViewById(R.id.radioButton3);
        radChoice4 = (RadioButton) findViewById(R.id.radioButton4);
        btnAnswer = (Button) findViewById(R.id.button);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemAboutMe:
                Intent objIntent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(objIntent);
                break;
            case R.id.itemHowTo:
                Intent myIntent = new Intent(MainActivity.this, HowToUseActivity.class);
                startActivity(myIntent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
