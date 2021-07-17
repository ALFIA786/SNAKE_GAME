package com.alfiaali;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNITS];  // hold all of the x body parts including the head all of our snake
    final int y[] = new int[GAME_UNITS];  // y cordinates
    int bodyParts = 6;       // initial amount of body parts
    int applesEaten;
    int appleX;      // x-cordinates where the apple is locted and its going to appear randomly each time that the snake eats an apple
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;


    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MykeyAdapter()); //it's notified whenever you change the state of key
        startGame();
    }
    public  void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
            // use parameter nd give them name graphics
        super.paintComponent(graphics);

    }
    public void draw(Graphics graphics){
    // draw method

        if (running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                graphics.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                graphics.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);

            }
            graphics.setColor(Color.red);
            graphics.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);//x – the x coordinate of the upper left corner of the oval to be filled.
                                                                   //  y – the y coordinate of the upper left corner of the oval to be filled.
                                                                  // width – the width of the oval to be filled.
                                                                 // height – the height of the oval to be filled.


            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    graphics.setColor(Color.green);
                    graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    graphics.setColor(new Color(45, 180, 0));
                    graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Ink Free", Font.BOLD, 75));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2,graphics.getFont().getSize());
        }
        else  {
            gameOver(graphics);
        }
    }
    public void newApple(){
     appleX =   random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE))*UNIT_SIZE;
     appleY =   random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE))*UNIT_SIZE;

    }
    public  void move(){
        // move parameter
        for (int i = bodyParts; i >0 ; i--) {
            x[i] =  x[i-0];
            y[i] =  y[i-0];
        }
        switch (direction){
            case 'U' :
            case 'D':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'L':
            case 'R':
                x[0] = x[0] - UNIT_SIZE;
                break;


        }

    }
    public  void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();

        }

    }
    public void checkCollisions() {
        // checks if head collides with body
        for (int i = bodyParts; i >0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // check if head touches left border
        if(x[0] < 0){
            running = false;
        }
        // check if head touches right border
        if(x[0] > SCREEN_WIDTH){
            running = false;
        }
        // check if head touch top border
        if(y[0] < 0){
            running = false;
        }
        // check if head touch bottom border
        if(y[0] > SCREEN_HEIGHT){
            running = false;
        }
        if(!running) {
            timer.stop();
        }
    }
    public void gameOver(Graphics graphics){
        // game over text
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("GAME OVER",(SCREEN_WIDTH - metrics.stringWidth("GAME OVER"))/2,SCREEN_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }
    public class MykeyAdapter extends KeyAdapter {
       @Override
        public void keyPressed(KeyEvent e) {
           switch (e.getKeyCode()){
               case KeyEvent.VK_LEFT:
                   if (direction != 'R'){
                       direction = 'L';
                   }
                   break;
                   case KeyEvent.VK_RIGHT:
                       if (direction != 'L'){
                   direction = 'R';
               }
               break;
               case KeyEvent.VK_UP:
                   if (direction != 'D'){
                       direction = 'U';
                   }
                   break;
               case KeyEvent.VK_DOWN:
                   if (direction != 'U'){
                       direction = 'D';
                   }
                   break;

           }
        }
    }
}
