package com.exercises.ami.my2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.exercises.ami.my2048.model.GameBoard2048;

public class MainActivity extends AppCompatActivity {

    BoardView boardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardView =(BoardView)findViewById(R.id.BoardGridView);
        boardView.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeRight() { boardView.swipeRight();}
            @Override
            public void onSwipeLeft() { boardView.swipeLeft();}
            @Override
            public void onSwipeUp() { boardView.swipeUp();}
            @Override
            public void onSwipeDown() { boardView.swipeDown();}
        });
        boardView.initializeView(4);
       // boardView.swipeUp();
        boardView.invalidate();
    }
}
