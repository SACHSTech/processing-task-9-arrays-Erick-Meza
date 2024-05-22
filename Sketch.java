import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // Initializes variables
  float[] ArrSnowFlakeY = new float[30];
  boolean[] ArrHideSnowflake = new boolean[30];
  float fltSnowflakeX = 0;
  float fltPlayerX = 400;
  float fltPlayerY = 500;
  float fltSnowflakeY = 0;
  float fltCircleRadius = 25;
  boolean blnFasterFall;
  boolean blnSlowerFall;
  int intLives = 3;
  PImage imgLives;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {

    // Sets the size of the screen
    size(800, 600);

  }

  /**
   * Called once at the beginning of execution. Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {

    // Sets the background colour
    background(210, 255, 173);

    // Creates a for loop to set the array values to a random height
    for (int i = 0; i < ArrSnowFlakeY.length; i++) {

      // Sets the value of the current array index to random
      ArrSnowFlakeY[i] = random(height);

    }

    // Sets the heart image
    imgLives = loadImage("Heart.png");
    
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // Resets to the background
    background(50);

    // Creates a for loop for each snowflake
    for (int i = 0; i < ArrSnowFlakeY.length; i++) {

      // Sets the colour of the snowflakes
      fill(255, 255, 255);

      // Sets the value of the x-coordinate of the snowflake
      fltSnowflakeX = width * i / ArrSnowFlakeY.length;

      // Sets the value of the y-coordinate of the snowflake
      fltSnowflakeY = ArrSnowFlakeY[i];

      // Creates a conditional selective algorithm to determine if the snowflake is at the bottom of the screen
      if (ArrSnowFlakeY[i] + 15 > height) {

        // Deletes the snowflake and sets it's height to 0
        ArrSnowFlakeY[i] = 0;
        ArrHideSnowflake[i] = false;

      } else if (blnFasterFall == true) {

        // Increases the speed of he snowflake's fall
        ArrSnowFlakeY[i] += 10;

      } else if (blnSlowerFall == true) {

        // Decreases the speed of the snowflake's fall
        ArrSnowFlakeY[i] += 2;

      } else {

        // Continues the for loop
        ArrSnowFlakeY[i] += 6;

      }

      // Creates a conditional selective algorithm to check if the person has clicked on a snowflake
      if (mousePressed == true && dist(mouseX, mouseY, fltSnowflakeX, fltSnowflakeY) < fltCircleRadius) {

        // Sets the value of the snowflake array to true
        ArrHideSnowflake[i] = true;

      } else if (ArrHideSnowflake[i] == false) {

        // Prints out the snowflake
        ellipse(fltSnowflakeX, fltSnowflakeY, fltCircleRadius, fltCircleRadius);

      }
      
      // Creates a conditional selective algorithm to check if a snowflake has hit the player
      if (dist(fltSnowflakeX, fltSnowflakeY, fltPlayerX, fltPlayerY) < 15 && ArrHideSnowflake[i] == false) {

        // Decreases their lives
        intLives--;

        // Deletes the snowflake
        ArrHideSnowflake[i] = true;
        
      }

    }

    // Prints out the player's circle
    fill(0, 0, 255);
    ellipse(fltPlayerX, fltPlayerY, fltCircleRadius, fltCircleRadius);

    // Creates a for loop to print out the current lives
    for (int i = 0; i < intLives; i++){

      // Prints out the heart
      image(imgLives, 620 + (i * 60), 40);

    }

    // Creates a conditional selective algorithm ti check if there are no more lives left
    if (intLives == 0){

      // Sets the background to white and writes "GAME OVER"
      background(255, 255, 255);
      textSize(100);
      textAlign(CENTER);
      fill(0, 0, 0);
      text("GAME OVER", width / 2, height / 2);

      // Breaks the draw() loop
      noLoop();

      }
  }

  /**
   * Creates a method for when a key is pressed
   */
  public void keyPressed() {

    // Creates a conditional selective algorithm to check 
    if (keyCode == UP) {

      // Sets the value of the slow fall to true
      blnSlowerFall = true;

    } else if (keyCode == DOWN) {

      // Sets the value of the fast fall to true
      blnFasterFall = true;

    }
    
    // Creates a conditional selective algorithm to check the user's player input
      if (key == 'w') {

        // Moves the player up
        fltPlayerY -= 20;

      } else if (key == 'a') {

        // Moves the player left
        fltPlayerX -= 20;

      } else if (key == 's') {
        
        // Moves the player down
        fltPlayerY += 20;

      } else if (key == 'd') {

        // Moves the player right
        fltPlayerX += 20;

      }

  }

  /**
   * Creates a method for when a key is released
   */
  public void keyReleased() {

    // Creates a conditional selective algorithm to check which key is released
    if (keyCode == UP) {

      // Sets the slower fall to false
      blnSlowerFall = false;

    } else if (keyCode == DOWN) {
      
      // Sets the faster fall to false
      blnFasterFall = false;

    }

  }
  
}