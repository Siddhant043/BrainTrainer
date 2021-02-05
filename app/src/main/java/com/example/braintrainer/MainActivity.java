package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

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
    Button goButton;
    int locationOfCorrectAnswer;
    TextView result;

    public void start(View view){ // starting button
        goButton.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view){ // function runs when a option is clicked
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            Log.i("Correct!", "You got it!");
            result.setText("Correct!");
        }else{
            Log.i("Wrong!", "Better luck next time.");
            result.setText("Wrong!");
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.goButton);
        TextView questionTextView = (TextView)findViewById(R.id.questionsTextView);
        Button button0 = (Button)findViewById(R.id.button0);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        result = (TextView) findViewById(R.id.resultTextView);

        result.setText(null); // Setting initial value of result so nothing appears on starting

        Random r = new Random(); //random object to pick random numbers
        int a = r.nextInt(21);
        int b = r.nextInt(21);

        questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b)); // setting question
        locationOfCorrectAnswer = r.nextInt(4); //deciding a location for right answer in array
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
}