package com.example.myapplication;

import android.app.Activity;
import android.content.Context;

import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

    private PImage background;

    private int x;
    private int y;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        background = loadImage("background.png");
        background.resize(displayWidth, displayHeight);

        x = 0;
        y = 0;

    }

    public void draw() {
        image(background, -(x%displayWidth), 0);
        image(background, -((x%displayWidth) - displayWidth), 0);
        x+=5;
    }

}
