package com.exercises.ami.my2048;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.TextView;

import com.exercises.ami.my2048.model.GameBoard2048;

import java.util.Random;

import static android.R.attr.animation;


public class BoardView extends GridLayout {
    GameBoard2048 board;

    public BoardView(Context context) {
        super(context);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    @Override
    public void addView(View child) {
        super.addView(child);
    }

 void initializeView(int n)
 {
     this.board= new GameBoard2048(n);
     this.setColumnCount(board.getN());
     this.setRowCount(board.getN());
     redraw();
     this.invalidate();
    // swipeUp();
     Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
     alphaAnimation.setDuration(1000);

     AnimationSet set = new AnimationSet(true);
     set.addAnimation(alphaAnimation);
     LayoutAnimationController controller =
             new LayoutAnimationController(set, 1.25f);
     this.setLayoutAnimation(controller);

 }
    void setGameBoard2048(GameBoard2048 board)
    {
        this.board=board;
    }

    void redraw() {
        this.removeAllViews();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // int index=0;
        for (int i = 0; i < board.getN(); i++)
        {
            for (int j = 0; j < board.getN(); j++)
            {
                int currentValue=board.getBoard()[i][j];
                TextView tv= new TextView(getContext());
                tv.setWidth(this.getWidth()/board.getN());
                tv.setHeight(this.getHeight()/board.getN());
                tv.setText(currentValue==0?"":""+currentValue);
                tv.setTextSize(22);
                switch (currentValue)
                {
                    case 0: tv.setBackgroundResource(R.drawable.empty_square);break;
                    case 2:  tv.setBackgroundResource(R.drawable.squaretwo);break;
                    case 4:  tv.setBackgroundResource(R.drawable.squarefour);break;
                    case 8:  tv.setBackgroundResource(R.drawable.square_eight);break;
                    case 16:  tv.setBackgroundResource(R.drawable.squaresixteen);break;
                    case 32:  tv.setBackgroundResource(R.drawable.square_32);break;
                    case 64:  tv.setBackgroundResource(R.drawable.square_64);break;
                    case 128:  tv.setBackgroundResource(R.drawable.square_one_two_eight);break;
                    case 256:  tv.setBackgroundResource(R.drawable.square_two_five_six6);break;
                    case 512:  tv.setBackgroundResource(R.drawable.square_two_five_six6);break;

                }
                tv.setGravity(Gravity.CENTER);
                this.addView(tv);
                //index++;

            }
        }
    }

    void swipeLeft()
    {
        boolean tileMoved, movementOccured=false;
        int index=0, n=board.getN();
        for (int i=0; i<n;i++)
        {
            for (int j=1; j<n;j++)  //starting from second row.
            {
                tileMoved=false;
                int tempY=j;
                if (board.getBoard()[i][j]==0) continue;//empty background.
                if (board.getBoard()[i][j-1]==0)
                {   //moving across all empty squares.
                    while (tempY>0&&board.getBoard()[i][tempY-1]==0)
                    {
                        board.getBoard()[i][tempY - 1] = board.getBoard()[i][tempY];
                        board.getBoard()[i][tempY] = 0;
                        tempY--;
                        movementOccured=true;
                        tileMoved=true;
//                        Animation animateLeft = AnimationUtils.loadAnimation(getContext(), R.anim.move_left);
//                        if (getChildAt(n*i+tempY+1).getBackground()!=getResources().getDrawable(R.drawable.empty_square))
//                            this.getChildAt(n*i+tempY+1).startAnimation(animateLeft);
                    }
                }
                //checking for equality.
                if (tempY>0&&board.getBoard()[i][tempY-1]==board.getBoard()[i][tempY])
                {
                    board.getBoard()[i][tempY-1]*=2;
                    board.getBoard()[i][tempY]=0;
                    movementOccured=true;
                    tileMoved=true;
                }
                else tempY++;//for the animation correctness.
                index++;
                if (tileMoved)
                {
                    TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0
                            , Animation.RELATIVE_TO_SELF,-(j-tempY+1)
                            ,Animation.RELATIVE_TO_SELF ,0 ,Animation.RELATIVE_TO_SELF , 0 );
                    translate.setDuration(200);
                    this.getChildAt(n*i+j).startAnimation(translate);
                }

            }
        }
        //do this at end of turn.
        if(movementOccured) {
            int x, y;
            Random random=new Random();
            while (board.getBoard()[x = random.nextInt(n)][y = random.nextInt(n)] != 0) ;
            board.getBoard()[x][y] = 2;
            redraw();
        }
      //  board.swipeLeft();
      //  Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.rocket);
       // this.getChildAt(5).startAnimation(hyperspaceJumpAnimation);
    }
    void swipeUp()
    {
         board.swipeUp();
        redraw();
    }
    void  swipeDown()
    {
        board.swipeDown();
        redraw();
    }
    void  swipeRight()
    {
        board.swipeRight();
        redraw();
    }
}
