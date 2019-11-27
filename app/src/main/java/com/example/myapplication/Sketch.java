package com.example.myapplication;

import processing.core.PApplet;
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
            x += scrollX;
            y += scrollY;
        }
    }

    public void settings() {
        size(displayWidth, displayHeight);
    }

    private ScrollingBackground homeBackground;

    public void setup() {
        frameRate(60);
        homeBackground = new ScrollingBackground(0, 0, 3, 3, "background.png");
    }

    public void draw() {
        homeBackground.scroll();
    }
}
