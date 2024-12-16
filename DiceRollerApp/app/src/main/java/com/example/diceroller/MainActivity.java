package com.example.diceroller;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView diceImage;
    Random randomGenrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        diceImage = findViewById(R.id.diceImage);
        Button rollButton = findViewById(R.id.rollButton);
        randomGenrator = new Random();

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rollDice();
            }
        });



    }

    void rollDice()
    {
        int diceRoll = randomGenrator.nextInt(6)+1;
        int drawableResource;

        switch(diceRoll)
        {
            case 1:
                drawableResource = R.drawable.diceone;
                break;
            case 2:
                drawableResource = R.drawable.dicetwo;
                break;
            case 3:
                drawableResource = R.drawable.dicethree;
                break;
            case 4:
                drawableResource = R.drawable.dicefour;
                break;
            case 5:
                drawableResource = R.drawable.dicefive;
                break;
            case 6:
                drawableResource = R.drawable.dicesix;
                break;
            default:
                throw new IllegalStateException("Unexpected value: "+diceRoll);
        }

        diceImage.setImageResource(drawableResource);
    }


}