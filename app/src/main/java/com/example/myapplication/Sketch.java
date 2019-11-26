package com.example.myapplication;

import processing.core.PApplet;

public class Sketch extends PApplet {
    public void settings() {
        size(600,600);
    }

    public void setup() {
        background(0, 0);
    }

    public void draw() {
        if (mousePressed) {
            ellipse(mouseX, mouseY, 50, 50);
        }
    }
}
