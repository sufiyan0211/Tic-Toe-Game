package com.example.sufiy.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // Yellow = 0
    // Red    = 1
    // empty  = 2

    int ActivePlayer = 0;

    boolean game_is_active  = true ;

    int[] game_state = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winning_position = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropin(View view){

        ImageView counter = (ImageView) view;

        int tapped_counter = Integer.parseInt(counter.getTag().toString());

        if(game_state[tapped_counter] == 2 && game_is_active) {

            game_state[tapped_counter] = ActivePlayer;

            counter.setTranslationY(-2000);

            if (ActivePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                ActivePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                ActivePlayer = 0;
            }

            counter.animate().translationYBy(2000).rotationX(36000).setDuration(100);

            String message = "";

            for (int[] winningpositons : winning_position) {

                if (game_state[winningpositons[0]] == game_state[winningpositons[1]] && game_state[winningpositons[1]] == game_state[winningpositons[2]] && game_state[winningpositons[0]] != 2) {

                    game_is_active = false;

                    if (ActivePlayer == 0) {
                        message = "Red";
                    } else {
                        message = "Yellow";
                    }

                    TextView txt_winner = (TextView) findViewById(R.id.txt_winner);
                    Button btn_play_again = (Button) findViewById(R.id.btn_play_again);

                    txt_winner.setText(message + " has won!");
                    txt_winner.setVisibility(View.VISIBLE);

                    btn_play_again.setVisibility(View.VISIBLE);



                }
            }

        }



    }


    public void play_again(View view){

        TextView txt_winner = (TextView) findViewById(R.id.txt_winner);
        Button btn_play_again = (Button) findViewById(R.id.btn_play_again);

        txt_winner.setVisibility(View.INVISIBLE);

        btn_play_again.setVisibility(View.INVISIBLE);

        GridLayout grid = findViewById(R.id.grid);

        for(int i=0; i<grid.getChildCount(); i++) {

            ImageView counter = (ImageView) grid.getChildAt(i);

            counter.setImageDrawable(null);

        }


        ActivePlayer = 0;

        game_is_active  = true ;

        for(int i = 0; i<game_state.length; i++){
            game_state[i] = 2;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}
