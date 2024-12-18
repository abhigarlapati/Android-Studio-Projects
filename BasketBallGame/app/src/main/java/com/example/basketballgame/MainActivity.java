package com.example.basketballgame;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView basket,ball;
    TextView scoreText;
    RelativeLayout gameLayout;
    Handler handler = new Handler();
    int score = 0;
    int screenWidth;
    boolean isGameRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        basket = findViewById(R.id.basket);
        ball = findViewById(R.id.ball);
        scoreText = findViewById(R.id.score);
        gameLayout = findViewById(R.id.gameLayout);

        screenWidth = getResources().getDisplayMetrics().widthPixels;

        startGame();

        gameLayout.setOnTouchListener((view,event)->{
            if(event.getAction() == MotionEvent.ACTION_MOVE){
                float x = event.getX();
                if((x > 0) && (x < (screenWidth - basket.getWidth()))){
                    basket.setX(x);
                }
            }
            return  true;
        });

    }
    void startGame(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(!isGameRunning) return;

                ball.setY(ball.getY()+10);

                if(ball.getY()+ball.getHeight() >= basket.getY() &&
                    ball.getX() + ball.getWidth() >= basket.getX() &&
                    ball.getX() <= basket.getX() + basket.getWidth()){
                    score++;
                    scoreText.setText("Score: "+score);
                    resetBall();
                }

                if(ball.getY() > gameLayout.getHeight())
                {
                    isGameRunning=false;
                    scoreText.setText("Game Over! Final Score: "+ score);
                }

                if(isGameRunning)
                {
                    handler.postDelayed(this,30);
                }
            }
        });
    }

    void resetBall()
    {
        Random random = new Random();
        int randomX = random.nextInt(screenWidth - ball.getWidth());
        ball.setX(randomX);
        ball.setY(0);
    }

}


