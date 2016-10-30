package com.exercises.ami.my2048.model;

import java.util.Random;


public class GameBoard2048
{
    int[][] board;
    int n, x, y;
    Random random;
    public GameBoard2048(int n) {
        this.board = new int[n][];
        this.n=n;
        for (int i=0; i<n;i++)
        {
            board[i]=new int[n];
        }
         random =new Random();
       int x= random.nextInt(n);
        int y=random.nextInt(n);
        board[x][y]=2;
    }


    public int[][] getBoard() {
        return board;
    }

    public int getN() {
        return n;
    }

    public void swipeLeft()
    {
        boolean movementOccured=false;

        for (int i=0; i<n;i++)
        {
            for (int j=1; j<n;j++)  //starting from second row.
            {
                int tempY=j;
                if (board[i][j]==0) continue;//empty background.
                if (board[i][j-1]==0)
                {   //moving across all empty squares.
                    while (tempY>0&&board[i][tempY-1]==0)
                    {
                        board[i][tempY - 1] = board[i][tempY];
                        board[i][tempY] = 0;
                        tempY--;
                        movementOccured=true;
                    }
                }
                //checking for equality.
                if (tempY>0&&board[i][tempY-1]==board[i][tempY])
                {
                    board[i][tempY-1]*=2;
                    board[i][tempY]=0;
                    movementOccured=true;
                }
            }
        }
        //do this at end of turn.
        if(movementOccured) {
            while (board[x = random.nextInt(n)][y = random.nextInt(n)] != 0) ;
            board[x][y] = 2;
        }
    }

    public void swipeUp()
    {
        boolean movementOccured=false;
        for (int i=0; i<n;i++)
        {
            for (int j=1; j<n;j++)  //starting from second row.
            {
                int tempY=j;
                if (board[j][i]==0) continue;//empty background.
                if (board[j-1][i]==0)
                {   //moving across all empty squares.
                    while (tempY>0&&board[tempY-1][i]==0)
                    {
                        board[tempY - 1][i] = board[tempY][i];
                        board[tempY][i] = 0;
                        tempY--;
                        movementOccured=true;
                    }
                }
                //checking for equality.
                if (tempY>0&&board[tempY-1][i]==board[tempY][i])
                {
                    board[tempY-1][i]*=2;
                    board[tempY][i]=0;
                    movementOccured=true;
                }
            }
        }
        //do this at end of turn.

        if (movementOccured)//if no movement Occured do not add tile;
        {
            board[x][y] = 2;
            while (board[x = random.nextInt(n)][y = random.nextInt(n)] != 0) ;
        }
    }

    public void swipeDown()
    {
        boolean movementOccured=false;

        for (int j=n-2; j>=0;j--)
        {
            for (int i=0; i<n;i++)  //starting from second row.
            {
                int tempY=j;
                if (board[j][i]==0) continue;//empty background.
                if (board[j+1][i]==0)
                {   //moving across all empty squares.
                    while (tempY<n-1&&board[tempY+1][i]==0)
                    {
                        board[tempY + 1][i] = board[tempY][i];
                        board[tempY][i] = 0;
                        tempY++;
                        movementOccured=true;
                    }
                }
                //checking for equality.
                if (tempY<n-1&&board[tempY+1][i]==board[tempY][i])
                {
                    board[tempY+1][i]*=2;
                    board[tempY][i]=0;
                    movementOccured=true;
                }
            }
        }
        //do this at end of turn.
        if(movementOccured) {
            while (board[x = random.nextInt(n)][y = random.nextInt(n)] != 0) ;
            board[x][y] = 2;
        }

    }

    public void swipeRight()
    {
        boolean movementOccured=false;

        for (int i=0; i<n;i++)
        {
            for (int j=n-2; j>=0;j--)  //starting from second row.
            {
                int tempY=j;
                if (board[i][j]==0) continue;//empty background.
                if (board[i][j+1]==0)
                {   //moving across all empty squares.
                    while (tempY<n-1&&board[i][tempY+1]==0)
                    {
                        board[i][tempY + 1] = board[i][tempY];
                        board[i][tempY] = 0;
                        tempY++;
                        movementOccured=true;
                    }
                }
                //checking for equality.
                if (tempY<n-1&&board[i][tempY+1]==board[i][tempY])
                {
                    board[i][tempY+1]*=2;
                    board[i][tempY]=0;
                    movementOccured=true;
                }
            }
        }
        //do this at end of turn.
        if (movementOccured){
          while (board[x=random.nextInt(n)][y=random.nextInt(n)]!=0);
            board[x][y]=2;  }

    }


}
