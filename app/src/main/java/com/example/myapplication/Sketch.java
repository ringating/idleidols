package com.example.myapplication;

import android.os.AsyncTask;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Sketch extends PApplet {

    class ScrollingBackground {
        private int x;
        private int y;
        private int scrollX;
        private int scrollY;
        private PImage image;

        ScrollingBackground(int x, int y, int scrollX, int scrollY, String imageName)
        {
            this.x = x;
            this.y = y;
            this.scrollX = scrollX;
            this.scrollY = scrollY;
            this.image = loadImage(imageName);
            this.image.resize(displayWidth, displayHeight);
        }

        void scroll()
        {
            image(image, -(x%displayWidth), -(y%displayHeight));
            image(image, -((x%displayWidth) - displayWidth), -(y%displayHeight));
            image(image, -(x%displayWidth), -((y%displayHeight) - displayHeight));
            image(image, -((x%displayWidth) - displayWidth), -((y%displayHeight) - displayHeight));
            this.x += scrollX;
            this.y += scrollY;
        }

        void setScrollSpeed(int xSpeed, int ySpeed)
        {
            this.scrollX = xSpeed;
            this.scrollY = ySpeed;
        }

        @SuppressWarnings("SameParameterValue")
        void changeScrollSpeed(int xChange, int yChange)
        {
            this.scrollX += xChange;
            this.scrollY += yChange;
        }
    }

    public void settings() {
        size(displayWidth, displayHeight, OPENGL);
    }

    private ScrollingBackground homeBackground;

    public void setup() {
        frameRate(60);
        homeBackground = new ScrollingBackground(0, 0, 1, 1, "background.png");
    }

    public void draw() {
        homeBackground.scroll();
    }
}
