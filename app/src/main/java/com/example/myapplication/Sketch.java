package com.example.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

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
            imageMode(CORNER);
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

    class DisplayIdol {

        private int x;
        private int y;

        private PImage body;
        private PImage head;

        int xHeadOffset;
        int yHeadOffset;

        int yBodyOffset;

        int xLeftBound;
        int xRightBound;
        int yTopBound;
        int yBottomBound;

        int xBoundOffset;
        int yBoundOffset;

        DisplayIdol(int x, int y, int xHeadOffset, int yHeadOffset, int yBodyOffset, int xBoundOffset, int yBoundOffset, double sizeMultiplier, String bodyImage, String headImage)
        {
            this.x = x;
            this.y = y;

            this.body = loadImage(bodyImage);
            this.head = loadImage(headImage);

            body.resize((int)(body.width * sizeMultiplier), (int)(body.height * sizeMultiplier));
            head.resize((int)(900 * sizeMultiplier),(int)(900 * sizeMultiplier));

            this.xHeadOffset = (int)(xHeadOffset * sizeMultiplier);
            this.yHeadOffset = (int)(yHeadOffset * sizeMultiplier);

            this.yBodyOffset = (int)(yBodyOffset * sizeMultiplier);

            this.xBoundOffset = (int)(xBoundOffset * sizeMultiplier);
            this.yBoundOffset = (int)(yBoundOffset * sizeMultiplier);

            generateBounds();
        }

        @SuppressWarnings("SameParameterValue")
        void draw()
        {
            imageMode(CENTER);
            image(body, x, y + this.yBodyOffset);
            image(head, x + this.xHeadOffset, y + this.yHeadOffset);
        }

        void changeHead(String headImage, int xHeadOffset, int yHeadOffset)
        {
            this.head = loadImage(headImage);
            this.xHeadOffset = xHeadOffset;
            this.yHeadOffset = yHeadOffset;
            generateBounds();
        }

        void generateBounds()
        {
            this.xLeftBound = (x - body.width/2) - xBoundOffset;
            this.xRightBound = (x + body.width/2) + xBoundOffset;
            this.yBottomBound = ((y + body.height/2) + yBodyOffset) + yBoundOffset;
            this.yTopBound = (((y - body.height/2) + yBodyOffset) -  (head.height - (((y + head.height/2) + yHeadOffset) - ((y - body.height/2) + yBodyOffset)))) - yBoundOffset;
        }

        boolean isMouseInIdol()
        {
            boolean isInIdol = false;

            if ((mouseX > xLeftBound && mouseX < xRightBound) && (mouseY < yBottomBound && mouseY > yTopBound))
            {
                isInIdol = true;
            }

            return isInIdol;
        }
    }

    public void settings() {
        size(displayWidth, displayHeight, OPENGL);
    }

    private ScrollingBackground homeBackground;
    private DisplayIdol idol;
    private int idolX;
    private int idolY;
    private boolean incrementMonies;
    private boolean drawEffect;

    Agency agency;
    TextView currency;
    Activity act;

    public void setup() {

        act = this.getActivity();
        agency = (Agency) act.getApplicationContext();
        currency = act.findViewById(R.id.currency);

        frameRate(60);
        homeBackground = new ScrollingBackground(0, 0, 1, 1, "background.png");
        idolX = displayWidth/2;
        idolY = (displayHeight/2) - 150;
        idol = new DisplayIdol(idolX, idolY, -40, -300, 400, -200, -150, 0.8,"body.png", "onionHead.png");
    }

    public void draw() {
        homeBackground.scroll();
        idol.draw();
        textSize(40);
        fill(0,0,0);
        if (idol.isMouseInIdol())
        {
            text("PRESSED", 0, displayHeight/2);
        }
        else
        {
            text("NOT PRESSED", 0, displayHeight/2);
        }
    }


    public void mousePressed()
    {
        if (idol.isMouseInIdol())
        {
            agency.SetCurrency(agency.GetCurrentCurrency() + 10);
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    currency.setText(Integer.toString(agency.GetCurrentCurrency()));
                }
            });
        }
    }
}
