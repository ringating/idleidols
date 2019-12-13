package com.example.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

public class Sketch extends PApplet {

    int multiplier = 1;
    int combo = 0;
    boolean hit;

    // For making a Scrolling Background that Scrolls in the Background
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

    // Displays an Idol with a body and head and soul
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
            this.x = x; //Position of the Idol
            this.y = y; //

            this.body = loadImage(bodyImage);   // Appearance of the Idol body
            this.head = loadImage(headImage);   //                    and head

            body.resize((int)(body.width * sizeMultiplier), (int)(body.height * sizeMultiplier));   // Resizes the body according to the sizeMultiplier
            head.resize((int)(900 * sizeMultiplier),(int)(900 * sizeMultiplier));                   // Same for the head; the head is already resized down because he head too big for he gotdamn screen

            this.xHeadOffset = (int)(xHeadOffset * sizeMultiplier); // Offsets the position of the head so he isn't decapitated
            this.yHeadOffset = (int)(yHeadOffset * sizeMultiplier); //

            this.yBodyOffset = (int)(yBodyOffset * sizeMultiplier); // Offsets the body for the same reason as abouve

            this.xBoundOffset = (int)(xBoundOffset * sizeMultiplier); // Changes the size of the mouse (or touch) detection box
            this.yBoundOffset = (int)(yBoundOffset * sizeMultiplier); //

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

    class BeatBar
    {
        private int xPosition;
        private int yPosition;
        private int hitCircleXPosition;
        private int hitCircleYPosition;
        private int beatBarHeight;
        private int beatBarWidth;
        private int hitCircleWidth;
        private int hitCircleHeight;
        private int timing = 0;
        List<PVector> coordinates;
        List<PVector> remove;
        private DisplayIdol idol;
        int n;
        int sizeOfText;
        boolean enlarge;

        BeatBar(int xPosition, int yPosition, int beatBarHeight, int beatBarWidth, DisplayIdol idol)
        {
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.beatBarHeight = beatBarHeight;
            this.beatBarWidth = beatBarWidth;
            this.hitCircleWidth = beatBarHeight;
            this.hitCircleHeight = beatBarHeight;
            this.sizeOfText = 64;
            hitCircleXPosition = xPosition - 350;
            hitCircleYPosition = yPosition;
            hitCircleWidth = 100;
            hitCircleHeight = 100;
            this.idol = idol;
            coordinates = new ArrayList<>();
            remove = new ArrayList<>();
            enlarge = false;
        }

        void draw()
        {
            rectMode(CENTER);
            fill(color(0, 0, 255));
            rect(xPosition, yPosition, beatBarWidth, beatBarHeight, 20);
            beatCircle();
            fill(color(0,0,0), 0);
            stroke(200,200,200);
            strokeWeight(5);
            ellipseMode(CENTER);
            ellipse(hitCircleXPosition, hitCircleYPosition, 100, 100);
            stroke(0,0);
            fill(color(0,0,0), 255);
        }

        void beatCircle()
        {
            timing++;
            if (timing >= 30)
            {
                coordinates.add(new PVector(xPosition + 500, yPosition));
                timing = 0;
            }

            hit = false;
            n = 0;
            for (PVector coords : coordinates)
            {
                fill(color(255, 0, 0));
                ellipse(coords.x, coords.y, 100, 100);
                coords.x -= 10;
                if (coords.x < xPosition - 500)
                {
                    multiplier = 1;
                    combo = 0;
                    remove.add(coords);
                }
                else if ((mousePressed && (idol.isMouseInIdol())) && isInHitCircle(coords))
                {
                    if (combo < 32)
                    {
                        combo++;
                    }
                    if (multiplier < 32 && (combo%4 == 0)) {
                        multiplier *= 2;
                        enlarge = true;
                    }
                    remove.add(coords);
                }
                hit = hit || isInHitCircle(coords);
            }
            multiplierNumber();
            if (sizeOfText > 64)
            {
                sizeOfText--;
            }

            if (!remove.isEmpty()) {
                coordinates.removeAll(remove);
                remove.clear();
            }
        }

        void multiplierNumber()
        {
            if (enlarge)
            {
                sizeOfText = 128;
                enlarge = false;
            }
            textAlign(CENTER, CENTER);
            textSize(sizeOfText);
            text("x" + multiplier, xPosition, yPosition - 100);
        }

        boolean isInHitCircle(PVector vector)
        {
            return vector.x > (hitCircleXPosition - hitCircleWidth) && vector.x < (hitCircleXPosition + hitCircleWidth/2);
        }
    }

    // PARTICLE SYSTEM CURRENTLY PLACEHOLDER

    // System of particles for particling
    class ParticleSystem {
        ArrayList<Particle> particles;
        PVector origin;

        ParticleSystem(PVector position) {
            origin = position.copy();
            particles = new ArrayList<Particle>();
        }

        void addParticle() {
            particles.add(new Particle(origin));
        }

        void run() {
            for (int i = particles.size()-1; i >= 0; i--) {
                Particle p = particles.get(i);
                p.run();
                if (p.isDead()) {
                    particles.remove(i);
                }
            }
        }
    }

    // Particle class for young particles to learn
    class Particle {
        PVector position;
        PVector velocity;
        PVector acceleration;
        float lifespan;

        Particle(PVector l) {
            acceleration = new PVector((float)0, (float)0.05);
            velocity = new PVector(random(-1, 1), random(-2, 0));
            position = l.copy();
            lifespan = (float)255.0;
        }

        void run() {
            update();
            display();
        }

        // Method to update position
        void update() {
            velocity.add(acceleration);
            position.add(velocity);
            lifespan -= 1.0;
        }

        // Method to display
        void display() {
            stroke(255, lifespan);
            fill(255, lifespan);
            ellipse(position.x, position.y, 8, 8);
        }

        // Is the particle still useful?
        boolean isDead() {
            if (lifespan < 0.0) {
                return true;
            } else {
                return false;
            }
        }
    }

    // PARTICLE SYSTEM CURRENTLY PLACEHOLDER

    // End Classes

    public void settings() {
        size(displayWidth, displayHeight, OPENGL);
    }

    private ScrollingBackground homeBackground;
    private DisplayIdol idol;
    private BeatBar beat;
    private int idolX;
    private int idolY;
    private boolean pressed;
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
        idolX = displayWidth/2 + 50;
        idolY = (displayHeight/2) - 150;
        idol = new DisplayIdol(idolX, idolY, -40, -300, 400, -200, -150, 0.8,"body.png", "onionHead.png");
        beat = new BeatBar(displayWidth/2, displayHeight - 600,  100, 1000, idol);

    }

    public void draw() {
        homeBackground.scroll();
        idol.draw();
        beat.draw();
        textSize(40);
    }


    public void mousePressed()
    {
        if (idol.isMouseInIdol())
        {
            agency.SetCurrency(agency.GetCurrentCurrency() + 10 * multiplier);
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    currency.setText(Integer.toString(agency.GetCurrentCurrency()));
                }
            });
            if (!hit)
            {
                multiplier = 1;
                combo = 0;
            }
        }
    }
}
