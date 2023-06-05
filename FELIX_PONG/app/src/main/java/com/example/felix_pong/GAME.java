package com.example.felix_pong;



import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.RelativeLayout;


public class GAME extends Activity implements SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;
    private float mBallInitialX, mBallInitialY;

    private boolean golseu;

    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;
    private float mPlayerPaddleX, mPlayerPaddleY, mPlayerPaddleWidth, mPlayerPaddleHeight;
    private float mEnemyPaddleX, mEnemyPaddleY, mEnemyPaddleWidth, mEnemyPaddleHeight;

    private float mBallX, mBallY, mBallRadius, mBallSpeedX, mBallSpeedY;
    private float mScreenWidth, mScreenHeight;
    private static final int PADDLE_COLOR = Color.BLUE;
    private static final int BALL_COLOR = Color.RED;

    public int player1Score, enemy2Score;
    private String difficulty;
    public String playerEmail;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent2 = getIntent();
        String playerEmail = intent2.getStringExtra("email");

        Intent intent = getIntent();
        difficulty = intent.getStringExtra("difficulty");

        golseu = false;

        player1Score = 0;
        enemy2Score = 0;

        mSurfaceView = new SurfaceView(this);
        setContentView(mSurfaceView);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        mSurfaceView.setFocusable(true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Landscape mode
            mPlayerPaddleWidth = mScreenWidth * 0.1f;
            mPlayerPaddleHeight = mScreenHeight * 0.05f;
            mPlayerPaddleX = (mScreenWidth - mPlayerPaddleWidth) / 2f;
            mPlayerPaddleY = mScreenHeight * 0.9f;

            mEnemyPaddleWidth = mScreenWidth * 0.1f;
            mEnemyPaddleHeight = mScreenHeight * 0.05f;
            mEnemyPaddleX = (mScreenWidth - mEnemyPaddleWidth) / 2f;
            mEnemyPaddleY = mScreenHeight * 0.1f;
        } else {
            // Portrait mode
            mPlayerPaddleWidth = mScreenWidth * 0.2f;
            mPlayerPaddleHeight = mScreenHeight * 0.05f;
            mPlayerPaddleX = (mScreenWidth - mPlayerPaddleWidth) / 2f;
            mPlayerPaddleY = mScreenHeight * 0.9f;

            mEnemyPaddleWidth = mScreenWidth * 0.2f;
            mEnemyPaddleHeight = mScreenHeight * 0.05f;
            mEnemyPaddleX = (mScreenWidth - mEnemyPaddleWidth) / 2f;
            mEnemyPaddleY = mScreenHeight * 0.1f;
        }
    }
    private void mudaDificuldade(String difficulty) {
        switch (difficulty) {
            case "easy":
                mBallSpeedX = mScreenWidth * 0.01f;
                mBallSpeedY = mScreenHeight * 0.01f;
                mPlayerPaddleWidth = mScreenWidth * 0.3f;
                break;
            case "medium":
                mBallSpeedX = mScreenWidth * 0.02f;
                mBallSpeedY = mScreenHeight * 0.02f;
                mPlayerPaddleWidth = mScreenWidth * 0.2f;
                mEnemyPaddleWidth = mScreenWidth * 0.4f;
                break;

            case "hard":
                mBallSpeedX = mScreenWidth * 0.04f;
                mBallSpeedY = mScreenHeight * 0.04f;
                mPlayerPaddleWidth = mScreenWidth * 0.1f;
                mEnemyPaddleWidth = mScreenWidth*0.6f;
                mEnemyPaddleX = (mScreenWidth-mEnemyPaddleWidth)*2f;
                break;
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        mBallInitialX = mScreenWidth / 2f;
        mBallInitialY = mScreenHeight / 2f;

        mScreenWidth = mSurfaceView.getWidth();
        mScreenHeight = mSurfaceView.getHeight();

        mBallRadius = mScreenWidth * 0.03f;
        mBallX = mScreenWidth / 2f;
        mBallY = mScreenHeight / 2f;
        mBallSpeedX = mScreenWidth * 0.02f;
        mBallSpeedY = mScreenHeight * 0.02f;

        mPlayerPaddleWidth = mScreenWidth * 0.2f;
        mPlayerPaddleHeight = mScreenHeight * 0.05f;
        mPlayerPaddleX = (mScreenWidth - mPlayerPaddleWidth) / 2f;
        mPlayerPaddleY = mScreenHeight * 0.9f;

        mEnemyPaddleWidth = mScreenWidth * 0.2f;
        mEnemyPaddleHeight = mScreenHeight * 0.05f;
        mEnemyPaddleX = (mScreenWidth - mEnemyPaddleWidth) / 2f;
        mEnemyPaddleY = mScreenHeight * 0.1f;

        mudaDificuldade(difficulty);

        startGameLoop();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Do nothing
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Do nothing
    }

    private void startGameLoop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    update();
                    draw();

                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        // Desenha as paletas do jogador e do inimigo
        mPaint.setColor(PADDLE_COLOR);
        canvas.drawRect(mPlayerPaddleX, mPlayerPaddleY, mPlayerPaddleX + mPlayerPaddleWidth, mPlayerPaddleY + mPlayerPaddleHeight, mPaint);
        canvas.drawRect(mEnemyPaddleX, mEnemyPaddleY, mEnemyPaddleX + mEnemyPaddleWidth, mEnemyPaddleY + mEnemyPaddleHeight, mPaint);

        // Desenha a bola
        mPaint.setColor(BALL_COLOR);
        canvas.drawCircle(mBallX, mBallY, mBallRadius, mPaint);

        // Verifica se o inimigo marcou um ponto
        if (enemy2Score > 0 && !golseu) {
            // Redefine a posição da bola para o centro da tela
            mBallX = mScreenWidth / 2f;
            mBallY = mScreenHeight / 2f;
            golseu = true;
        } else if (player1Score > 0) {
            golseu = false;
        }

        // Atualiza a posição da bola e das paletas
        update();

        // Desenha as pontuações
        mPaint.setTextSize(60);
        canvas.drawText(Integer.toString(player1Score), mScreenWidth / 2f - 100, mScreenHeight - 50, mPaint);
        canvas.drawText(Integer.toString(enemy2Score), mScreenWidth / 2f + 100, mScreenHeight - 50, mPaint);
    }

    private void draw() {
        Canvas canvas = mSurfaceHolder.lockCanvas();

        if (canvas != null) {
            Paint linePaint = new Paint();
            linePaint.setColor(Color.WHITE);
            linePaint.setStrokeWidth(10);
            canvas.drawLine(0, mScreenHeight/2, mScreenWidth, mScreenHeight/2, linePaint);
            canvas.drawColor(Color.BLACK);

            mPaint.setColor(PADDLE_COLOR);
            canvas.drawRect(mPlayerPaddleX, mPlayerPaddleY, mPlayerPaddleX + mPlayerPaddleWidth, mPlayerPaddleY + mPlayerPaddleHeight, mPaint);
            canvas.drawRect(mEnemyPaddleX, mEnemyPaddleY, mEnemyPaddleX + mEnemyPaddleWidth, mEnemyPaddleY + mEnemyPaddleHeight, mPaint);

            mPaint.setColor(BALL_COLOR);
            canvas.drawCircle(mBallX, mBallY, mBallRadius, mPaint);


            Paint paintTextoEN = new Paint();
            paintTextoEN.setTextSize(30);
            paintTextoEN.setColor(Color.WHITE);
            canvas.drawText("Pontuação Inimigo:" + enemy2Score, mScreenWidth/2-160, 50, paintTextoEN);

            Paint paintTextoPL= new Paint();
            paintTextoPL.setTextSize(30);
            paintTextoPL.setColor(Color.WHITE);
            canvas.drawText("Pontuação Jogador:" + player1Score, mScreenWidth/2-160, 1465, paintTextoPL);
            mSurfaceHolder.unlockCanvasAndPost(canvas);

            if (mBallY + mBallRadius >= mScreenHeight) {
                mBallX = mBallInitialX;
                mBallY = mBallInitialY+450;
                mBallSpeedX = Math.abs(mBallSpeedX);
                mBallSpeedY = Math.abs(mBallSpeedY);
                mBallX += mBallSpeedX;
                mBallY += mBallSpeedY;
            }
        }

    }
    private void update() {
        mBallX += mBallSpeedX;
        mBallY += mBallSpeedY;

        if (mBallX - mBallRadius < 0) {
            mBallX = mBallRadius;
            mBallSpeedX = -mBallSpeedX;
        } else if (mBallX + mBallRadius > mScreenWidth) {
            mBallX = mScreenWidth - mBallRadius;
            mBallSpeedX = -mBallSpeedX;
        }

        if (mBallY - mBallRadius < 0) {
            mBallY = mBallRadius;
            player1Score++;
            mBallSpeedY = -mBallSpeedY;
            switch (difficulty){
                case "easy":
                    if (player1Score==5){
                        Intent intent = new Intent(GAME.this, VITORIAPLAYER.class);
                        player1Score=0;
                        enemy2Score=0;
                        startActivity(intent);
                    }
                case "medium":
                    if (player1Score==7) {
                        Intent intent = new Intent(GAME.this, VITORIAPLAYER.class);
                        player1Score = 0;
                        enemy2Score = 0;
                        startActivity(intent);
                    }
                case "hard":
                    if (player1Score==10){
                        Intent intent = new Intent(GAME.this, VITORIAPLAYER.class);
                        player1Score=0;
                        enemy2Score=0;
                        startActivity(intent);
                    }
            }
        } else if (mBallY + mBallRadius > mScreenHeight) {
            mBallY = mScreenHeight - mBallRadius;
            enemy2Score++;
            mBallSpeedY = -mBallSpeedY;
            switch (difficulty){
                case "easy":
                    if (enemy2Score==5){
                        Intent intent = new Intent(GAME.this, VITORIAENEMY.class);
                        player1Score=0;
                        enemy2Score=0;
                        startActivity(intent);
                    }
                case "medium":
                    if (enemy2Score==7) {
                        Intent intent = new Intent(GAME.this, VITORIAENEMY.class);
                        player1Score = 0;
                        enemy2Score = 0;
                        startActivity(intent);
                    }
                case "hard":
                    if (enemy2Score==10){
                        Intent intent = new Intent(GAME.this, VITORIAENEMY.class);
                        player1Score=0;
                        enemy2Score=0;
                        startActivity(intent);
                    }
            }
        }

        if (mBallY + mBallRadius >= mPlayerPaddleY) {
            float paddleLeft = mPlayerPaddleX;
            float paddleRight = mPlayerPaddleX + mPlayerPaddleWidth;
            float paddleTop = mPlayerPaddleY;
            float paddleBottom = mPlayerPaddleY + mPlayerPaddleHeight;

            if (mBallX + mBallRadius >= paddleLeft && mBallX - mBallRadius <= paddleRight) {
                mBallY = paddleTop - mBallRadius;
                mBallSpeedY = -mBallSpeedY;
            } else if (mBallY + mBallRadius >= paddleTop && mBallY - mBallRadius <= paddleBottom) {
                if (mBallX + mBallRadius >= paddleLeft && mBallX < mPlayerPaddleX) {
                    mBallX = paddleLeft - mBallRadius;
                    mBallSpeedX = -mBallSpeedX;
                } else if (mBallX - mBallRadius <= paddleRight && mBallX > mPlayerPaddleX + mPlayerPaddleWidth) {
                    mBallX = paddleRight + mBallRadius;
                    mBallSpeedX = -mBallSpeedX;
                }
            }
        }

        if (mBallY - mBallRadius <= mEnemyPaddleY + mEnemyPaddleHeight) {
            float paddleLeft = mEnemyPaddleX;
            float paddleRight = mEnemyPaddleX + mEnemyPaddleWidth;
            float paddleTop = mEnemyPaddleY;
            float paddleBottom = mEnemyPaddleY + mEnemyPaddleHeight;

            if (mBallX + mBallRadius >= paddleLeft && mBallX - mBallRadius <= paddleRight) {
                mBallY = paddleBottom + mBallRadius;
                mBallSpeedY = -mBallSpeedY;
            } else if (mBallY - mBallRadius <= paddleBottom && mBallY + mBallRadius >= paddleTop) {
                if (mBallX + mBallRadius >= paddleLeft && mBallX < mEnemyPaddleX) {
                    mBallX = paddleLeft - mBallRadius;
                    mBallSpeedX = -mBallSpeedX;
                } else if (mBallX - mBallRadius <= paddleRight && mBallX > mEnemyPaddleX + mEnemyPaddleWidth) {
                    mBallX = paddleRight + mBallRadius;
                    mBallSpeedX = -mBallSpeedX;
                }
            }
        }
        float enemyPaddleCenterX = mEnemyPaddleX + mEnemyPaddleWidth / 2f;
        float ballCenterX = mBallX;

        if (ballCenterX > enemyPaddleCenterX) {
            mEnemyPaddleX += 5;
        } else if (ballCenterX < enemyPaddleCenterX) {
            mEnemyPaddleX -= 5;
        }

        if (mEnemyPaddleX < 0) {
            mEnemyPaddleX = 0;
        } else if (mEnemyPaddleX + mEnemyPaddleWidth > mScreenWidth) {
            mEnemyPaddleX = mScreenWidth - mEnemyPaddleWidth;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mPlayerPaddleX = event.getX() - mPlayerPaddleWidth / 2f;
                break;
        }
        return true;

    }

    private Paint mScorePaint;

    private void surfaceCreated(){
        mScorePaint = new Paint();
        mScorePaint.setColor(Color.BLUE);
        mScorePaint.setTextSize(100);

    }

    @Override
    protected void onPause(){
        super.onPause();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.updatePlayerPoint(playerEmail,player1Score);
    }
}
