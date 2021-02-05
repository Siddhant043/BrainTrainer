package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> answersArr = new ArrayList<Integer>(); // creating answers holding array
    Button goButton, button0, button1, button2, button3, playAgainButton;
    int locationOfCorrectAnswer;
    TextView resultTextView, scoreTextView, questionTextView, timerTextView;
    ConstraintLayout gameLayout;
    int score = 0;
    int numberOfQuestions = 0;

    // starting button visibility
    public void start(View view){ //starting go button functions
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(scoreTextView); // playAgain functions requires a view to be passed, as we are not using view inside so it does not matters which one to pass.
    }

    // function runs when a option is clicked
    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!");
            score++;
        }
        else
            resultTextView.setText("Wrong!");
        numberOfQuestions++ ;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    // the function is used whenever the game is ends and at the beginning.
    public void playAgain(View view){
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText(null);
        newQuestion();
        new CountDownTimer(30100, 1000){  // timer function

            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished - 100) / 1000;
                timerTextView.setText(seconds+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();
    }

    // function which shows a new question every time it is called.
    public void newQuestion(){
        Random r = new Random(); //random object to pick random numbers
        int a = r.nextInt(21);
        int b = r.nextInt(21);

        questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b)); // setting question
        locationOfCorrectAnswer = r.nextInt(4); //deciding a location for right answer in array
        answersArr.clear();

        for(int i=0; i<4; i++){ // loop to store values in array
            if(i == locationOfCorrectAnswer){ // if loop comes to the specified ans pos then enter the answer
                answersArr.add(a+b);
            }else{ // if the loop is on other positions then enter wrong answers
                int wrongAnswer = r.nextInt(41);
                while(wrongAnswer == a+b){ // check if the random wrong ans is not the right ans by mistake
                    wrongAnswer = r.nextInt(41);
                };
                answersArr.add(wrongAnswer);
            };
        };
        // Setting options value (random ans is changed in array not on buttons)
        button0.setText(Integer.toString(answersArr.get(0)));
        button1.setText(Integer.toString(answersArr.get(1)));
        button2.setText(Integer.toString(answersArr.get(2)));
        button3.setText(Integer.toString(answersArr.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.goButton);
        questionTextView = (TextView)findViewById(R.id.questionsTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);



    }
}