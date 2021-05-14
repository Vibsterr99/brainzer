package com.example.brainzer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startbutton;
    TextView result;
    TextView points;
    Button ans1;
    Button ans2;
    Button ans3;
    Button ans4;
    TextView sum;
    TextView timer;
    Button game;
    RelativeLayout thegame;
    ArrayList<Integer> answers= new ArrayList<>();
    CountDownTimer time;
    int answerlocation;
    int score;
    int numberofquestion;

    public void startgame(View view){
        game.setVisibility(View.INVISIBLE);
        thegame.setVisibility(View.VISIBLE);
        ans1.setVisibility(View.VISIBLE);
        ans2.setVisibility(View.VISIBLE);
        ans3.setVisibility(View.VISIBLE);
        ans4.setVisibility(View.VISIBLE);
        sum.setVisibility(View.VISIBLE);
        score=0;
        numberofquestion=0;



        time=new CountDownTimer(41000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                result.setText("Your Score is "+Integer.toString(score)+"/"+Integer.toString(numberofquestion));
                question();
                numberofquestion++;
                points.setText(Integer.toString(score)+"/"+Integer.toString(numberofquestion));
                ans1.setVisibility(View.INVISIBLE);
                ans2.setVisibility(View.INVISIBLE);
                ans3.setVisibility(View.INVISIBLE);
                ans4.setVisibility(View.INVISIBLE);
                sum.setVisibility(View.INVISIBLE);
                newgame();
            }
        }.start();
    }
    public void newgame(){
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                game.setVisibility(View.VISIBLE);
                thegame.setVisibility(View.INVISIBLE);
                points.setText("0/0");
                result.setText("");
            }
        }.start();
    }

    public void question(){
        Random  rand=new Random();
        int a=rand.nextInt(101);
        int b=rand.nextInt(101);
        sum.setText(a +"+"+ b);
        answerlocation=rand.nextInt(4);

        answers.clear();
        int incorrectanswer;

        for(int i=0;i<4;i++){

            if(i==answerlocation){
                answers.add(a+b);
            }
            else{
                incorrectanswer=rand.nextInt(201);
                while(incorrectanswer==a+b){
                    incorrectanswer=rand.nextInt(201);
                }answers.add(incorrectanswer);

            }

        }

        ans1.setText(Integer.toString(answers.get(0)));
        ans2.setText(Integer.toString(answers.get(1)));
        ans3.setText(Integer.toString(answers.get(2)));
        ans4.setText(Integer.toString(answers.get(3)));
    }

    @SuppressLint("SetTextI18n")
    public void chooseanswer(View v){
        if(v.getTag().toString().equals(Integer.toString(answerlocation))){
            score++;
            result.setText("CORRECT");
        }else{
            result.setText("WRONG");
        }
        numberofquestion++;
        points.setText(Integer.toString(score)+"/"+Integer.toString(numberofquestion));
        question();
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ans1= findViewById(R.id.ans1);
        ans2= findViewById(R.id.ans2);
        ans3= findViewById(R.id.ans3);
        ans4= findViewById(R.id.ans4);

        game=findViewById(R.id.startgame);
        thegame=findViewById(R.id.TheGame);

        startbutton= findViewById(R.id.start);
        sum= findViewById(R.id.sum);
        points=findViewById(R.id.score);
        result=findViewById(R.id.result);
        timer=findViewById(R.id.timer);


        question();




    }
}
