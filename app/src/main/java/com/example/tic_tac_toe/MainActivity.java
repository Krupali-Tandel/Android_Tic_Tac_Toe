package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //    player represenstation
//    0 - x
//    1 - o

    int activePlayer = 0;
    //status meaning
//    0 - x
//    1 - 0
//    2 - null or blank
    int [][]winPositions = {{0,1,2} ,{3,4,5} ,{6,7,8},
            {0,3,6} ,{1,4,7} ,{2,5,8},
            {0,4,8} ,{2,4,6}};

    int gameState[] = {2,2,2,2,2,2,2,2,2};
    //making the variable to check the status of the game
    boolean gameActive = true ;
    //making the counter variable to check the if the player is not cheating on the game
    static int counter = 0;
    //getting the id of the status field
    TextView status = findViewById(R.id.status);
    //playerTap function
    public void playerTap(View view){
        ImageView img = (ImageView)view;
        Toast.makeText(this, "The image is clicked ", Toast.LENGTH_SHORT).show();
        //to get the tag of the cell when tapped
        int tappedimg = Integer.parseInt(img.getTag().toString());
        //if the game is not activate then reset the game by calling the reset function
        if(!gameActive){
            gameReset(view);
        }
//        if the cell is blank at that location
        if(gameState[tappedimg] == 2){
            counter++ ;

            //place it with the player who is playing
            gameState[tappedimg] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                //if the activeplayer is 0 then set the image to x
                img.setImageResource(R.drawable.x);
                if(counter == 9 ){
                    gameActive = false ;
                }
                //then it will be the turn of the next player therefore updating the activeplayer status
                activePlayer = 1;
                status.setText("O's Turn. Tap To Play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                status.setText("X's Turn. Tap To Play");
            }
            img.animate().translationX(1000f).setDuration(300);
        }
        //this variable is used to check whether the match is a draw or something
        int flag = 0 ;
        //check for the winning positions
        for(int[] winPosition  : winPositions ){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2){
                String winstr ;
                flag = 1;
                //obtain the player who have won the game
                if(gameState[winPosition[0]]== 0){
                    winstr = "X Won";
                }
                else{
                    winstr = "O Won";

                }
                status.setText(winstr);
            }
        }

        //if the match is a draw then
        if(counter == 9 && flag == 0){
            status.setText("Match Draw");
            counter = 0;
        }

    }
    public void gameReset(View view){
        //resetting the variable back to the normal state
        gameActive = true ;
        activePlayer = 0;
        counter = 0 ; //setting the counter to zero
        //reseting the gamestate
        for(int i = 0 ;i<gameState.length ; i++){
            gameState[i] = 2;
        }
        //updating the images in the tic tac toe
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);

        status.setText("X's Turn to play");
    }
}