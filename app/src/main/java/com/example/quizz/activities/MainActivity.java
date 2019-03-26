package com.example.quizz.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import java.lang.Math;

import com.example.quizz.R;
import com.example.quizz.data.Question;
import com.example.quizz.utils.QuestionManager;

public class MainActivity extends Activity {

    TextView tvScore, tvQuestion;
    Button myTrueButton, myFalseButton;
    public static int currentScore;
    public static int attempt;
    Question maskQuestion;
    Toast myToast;
    ProgressBar myProgress;
    public Context MainContext = getApplicationContext();
    QuestionManager myQuestionManager = new QuestionManager(getApplicationContext());
    //final int LEN = myQuestionManager.mQuestionPool.length;
    final int PROGRESS_STEP = (int) Math.ceil(100.0/myQuestionManager.LEN);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = findViewById(R.id.score_text);
        tvScore.setText(getResources().getString(R.string.score_word) + 0 + "/" + myQuestionManager.LEN);
        tvQuestion = findViewById(R.id.question_text);
        myTrueButton = findViewById(R.id.true_button);
        myFalseButton = findViewById(R.id.false_button);
        myProgress = findViewById(R.id.progressBar);

        // check if the has jeust been created so do nothign otherwise (rotation) load varibale saved on savestate
        if (savedInstanceState != null){
            currentScore = savedInstanceState.getInt("Score");
            attempt = savedInstanceState.getInt("Attempt");
            tvScore.setText(getResources().getString(R.string.score_word) + currentScore + "/" + myQuestionManager.LEN);
            maskQuestion = myQuestionManager.mQuestionPool[attempt];
            tvQuestion.setText(maskQuestion.getEntry());
        }
        else{
            // Init
            currentScore = 0;
            attempt = 0;
            maskQuestion = myQuestionManager.mQuestionPool[attempt];
            tvQuestion.setText(maskQuestion.getEntry());
        }

        myTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myQuestionManager.questionCorrectnessTrue();
                myQuestionManager.updateAttempt();
                myProgress.incrementProgressBy(PROGRESS_STEP);
            }
        });

        myFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myQuestionManager.questionCorrectnessFalse();
                myQuestionManager.updateAttempt();
                myProgress.incrementProgressBy(PROGRESS_STEP);
            }
        });
    }
/*
    void questionCorrectnessTrue(){
        if (mQuestionPool[MainActivity.attempt].getAnswer()){
            incrementScore();
            //myToast = Toast.makeText(getApplicationContext(),"Well done!", Toast.LENGTH_SHORT);
            myToast.show();
        }
        else{
            //myToast = Toast.makeText(getApplicationContext(),"Wrong answer!", Toast.LENGTH_SHORT);
            myToast.show();
            decrementScore();
        }
    }

    void questionCorrectnessFalse(){
        if (!myQuestionManager.mQuestionPool[attempt].getAnswer()){
            incrementScore();
            myToast = Toast.makeText(getApplicationContext(),"Well done!", Toast.LENGTH_SHORT);
            myToast.show();
        }
        else{
            decrementScore();
            myToast = Toast.makeText(getApplicationContext(),"Wrong answer!", Toast.LENGTH_SHORT);
            myToast.show();
        }
    }


    void incrementScore(){
        currentScore++;
        tvScore.setText(getResources().getString(R.string.score_word) + currentScore + "/" + myQuestionManager.LEN);
    }

    void decrementScore(){
        if (currentScore>0){
            currentScore--;
            tvScore.setText(getResources().getString(R.string.score_word) + currentScore + "/" + myQuestionManager.LEN);}
    }

    void updateAttempt(){
        Log.d("Attempt","Attempt");
        if (attempt < myQuestionManager.LEN-1){
            myProgress.incrementProgressBy(PROGRESS_STEP);
            attempt++;
            maskQuestion = myQuestionManager.mQuestionPool[attempt];
            tvQuestion.setText(maskQuestion.getEntry());}
        else if (attempt == myQuestionManager.LEN-1){
            myToast = Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT);
            myToast.show();
            openEndDialog();
        }
    }
*/

/*
    void openEndDialog(){
        AlertDialog.Builder endDialog = new AlertDialog.Builder(MainActivity.this);
        endDialog.setTitle("Game Over");
        endDialog.setCancelable(false);
        endDialog.setMessage("Your game is over. Your score is "+ currentScore);
        endDialog.setPositiveButton("Close App", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        endDialog.show();
    }
*/

    // to store values during screen rotation
    @Override
    public void onSaveInstanceState(Bundle AppState){
        super.onSaveInstanceState(AppState);
        AppState.putInt("Score", currentScore);
        AppState.putInt("Attempt",attempt);
    }
}


/*
* Android Activity has 4 stages
* - Non-Existent
*   transition down here is OnCreate()
*   transition up here is OnDestroy()
* - Stopped
*   transition down here is onStart()
*   transition up here is OnStop()
* - Paused
*   transition down here is OnResume()
*   transition up here is OnPause()
* - Running
*
*   When you rotate the screen the system performs a complete loop of this activities reloading basically everything
*   So after OnPause() a method OnSaveInstanceState() is called, and here is where the devlepor has to save the variable values and so on.
*
* */
