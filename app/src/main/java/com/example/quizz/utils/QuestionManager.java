package com.example.quizz.utils;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.quizz.R;
import com.example.quizz.data.Question;
import com.example.quizz.activities.MainActivity;

import java.util.HashMap;

public class QuestionManager{

    public Context myContext;
    public QuestionManager(Context passedContext){
        myContext = passedContext;
    }

    Question maskQuestion;
    public Question[] mQuestionPool = new Question[] {
            new Question(R.string.question_1,true),
            new Question(R.string.question_2,true),
            new Question(R.string.question_3,true),
            new Question(R.string.question_4,true),
            new Question(R.string.question_5,true),
            new Question(R.string.question_6,false),
            new Question(R.string.question_7,true),
            new Question(R.string.question_8,false),
            new Question(R.string.question_9,true),
            new Question(R.string.question_10,true),
            new Question(R.string.question_11,true),
            new Question(R.string.question_12,false),
            new Question(R.string.question_13,true),
    };

    public final int LEN = mQuestionPool.length;

    public void questionCorrectnessTrue(){
        if (mQuestionPool[MainActivity.attempt].getAnswer()){
            incrementScore();
        }
        else{
            decrementScore();
        }
    }
    public void questionCorrectnessFalse(){
        if (!mQuestionPool[MainActivity.attempt].getAnswer()){
            incrementScore();
        }
        else{
            decrementScore();
        }
    }

    public void incrementScore(){
        MainActivity.currentScore++;
    }
    public void decrementScore(){
        if (MainActivity.currentScore>0) {
            MainActivity.currentScore--;
        }
    }

    public void updateAttempt(){
        Log.d("Attempt","Attempt");
        if (MainActivity.attempt < LEN-1) {
            //myProgress.incrementProgressBy(PROGRESS_STEP);
            MainActivity.attempt++;
            maskQuestion = mQuestionPool[MainActivity.attempt];
            //tvQuestion.setText(maskQuestion.getEntry());
        }
        else if (MainActivity.attempt == LEN-1){
            //myToast = Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT);
            //myToast.show();
            openEndDialog();
        }
    }

    void openEndDialog(){
        AlertDialog.Builder endDialog = new AlertDialog.Builder(myContext);
        endDialog.setTitle("Game Over");
        endDialog.setCancelable(false);
        endDialog.setMessage("Your game is over. Your score is "+ MainActivity.currentScore);
        endDialog.setPositiveButton("Close App", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        endDialog.show();
    }


}
