package com.gilfoyle.findthegilfoyle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv_Time;
    TextView tv_Score;
    ImageView imageView;
    ConstraintLayout constraintLayout;
    int score;
    Handler handler= new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_Score=findViewById(R.id.tv_Score);
        tv_Time=findViewById(R.id.tv_Time);
        imageView=findViewById(R.id.imageView3);
        constraintLayout=findViewById(R.id.cns);



    }
    public void btn_Start(View view){


       view.setVisibility(View.INVISIBLE);

      new CountDownTimer(10000,500){

            @Override
            public void onTick(long l) {

                tv_Time.setText("Time: "+l/1000);

                 runnable= new Runnable() {
                    @Override
                    public void run() {
                        imageView.setVisibility(View.VISIBLE);
                        int x = (int) ((Math.random() * (constraintLayout.getWidth() - imageView.getWidth() - 1)));
                        int y = (int) ((Math.random() * (constraintLayout.getHeight() - imageView.getHeight() - 1)));
                        imageView.setX(x);
                        imageView.setY(y);
                    }
                };
                handler.post(runnable);
            }

            @Override
            public void onFinish() {

                AlertDialog.Builder myAlert= new AlertDialog.Builder(MainActivity.this);

                myAlert.setTitle("Game Over");
                myAlert.setMessage("Try again ?");
                myAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        recreate();
                    }
                });
                myAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                });
                myAlert.setCancelable(false);
                myAlert.show();




            }
        }.start();

    }
    public void imgv(View view){
        score++;
        handler.removeCallbacks(runnable);
        imageView.setVisibility(View.INVISIBLE);
        tv_Score.setText("Score: "+score);
    }
}