package com.craigcorstorphine.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Integer.*;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private TextView mLoadingText;

    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

    int value3;
    int answer;
    EditText answer_field;
    ProgressBar progressBar;
    int score;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNumber();
        progressBar = (ProgressBar) findViewById(R.id.answerProgress);
        progressBar.setMax(10);
        progressBar.setProgress(0);







        Button myButton = findViewById(R.id.answer_button);






            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!answer_field.getText().toString().equals("")){

                        answer = valueOf(answer_field.getText().toString());
                        //value3 = value1 * value2;


                        if(value3 == answer)
                        {
                            setNumber();

                            showToast("Correct");

                            score ++;
                            progressBar.setProgress(score);
                            endGame();
                            answer_field.setText("");



                        }
                        else {
                            showToast("wrong");
                            setNumber();
                            answer_field.setText("");
                        }

                    }

                }

             });

    }

    public void setNumber(){
        final ImageView leftNumber = findViewById(R.id.left_number);

        final ImageView rightNumber = findViewById(R.id.right_number);
        final int[] numberArray = new int[]{
                R.drawable.number_0,
                R.drawable.number1,
                R.drawable.number2,
                R.drawable.number3,
                R.drawable.number4,
                R.drawable.number5,
                R.drawable.number6,
                R.drawable.number7,
                R.drawable.number8,
                R.drawable.number9,
        };
        // Create a random number generator
        final Random randomNumberGenerator = new Random();


        final int value1 = randomNumberGenerator.nextInt(10);




        leftNumber.setImageResource(numberArray[value1]);

        // Create a new random number
        final int value2 = randomNumberGenerator.nextInt(10);

        // Set the right number image using an image from the numberArray.
        rightNumber.setImageResource(numberArray[value2]);
        answer_field = findViewById(R.id.answer_field);



        value3 = value1 * value2;
        
    }







    public void showToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    }
    private void endGame() {

        // Present an alert dialog if we reach the end.
        if(score == 10) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Well done! You got to 10! Would you like to try again?")
                    .setCancelable(false)
                    .setPositiveButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            setNumber();
                            progressBar.setProgress(0);
                            score = 0;
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }


}}
