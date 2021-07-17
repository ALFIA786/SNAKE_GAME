package com.alfiaali;

import javax.swing.*;

public class GameFrame extends JFrame {

    GameFrame(){

        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();      // when we add components to J rame this pack function is actually going to take our
        // j frame and fit it snugly around all of the components that we add to the frame...
        this.setVisible(true);
        this.setLocationRelativeTo(null);  //The target screen mentioned below is a screen to which
        // the window should be placed after the setLocationRelativeTo method is called.
        //If the component is null, or the GraphicsConfiguration associated with this component is null,
        // the window is placed in the center of the screen.
        // The center point can be obtained with the GraphicsEnvironment.getCenterPoint method.
    }
}
