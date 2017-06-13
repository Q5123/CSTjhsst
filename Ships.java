import java.lang.Math;
import java.awt.*;
import javax.swing.*;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.lang.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.*;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Image; //Imports
public class Ships
{
   //fields
   public int myX;
   public int myY; //location
   public String Owner; 
   public int myDistance; //Distance that ship orbits around planet
   public int theta; //useless
   BufferedImage img; //my image
   public int mySpeed; //How far the ship goes. 
   public int myStatus; //The class knows whether it is in flight to another planet, or orbiting around a planet.
   public double myAngle; //Angle that the ship travels at one tick.
   public double myOrbit; //Orbit around the current planet
   public double myAnglePos; //Position on the polar plane
   public JPanel myPanel;
   public Boolean Orbiting; //is orbiting?
   public Boolean atk; //is in flight?
   double slope;
   public int Strength;
   private double myScale = 0.2; //How much image is scaled down
   int b; 
   public boolean reached; //did i reach the destination?
   Planet myPlanet; //My current planet
   
   //modifier methods
   public void setX(int mx) //Sets x-coord
   {
      myX = mx;
   }
   public void setY(int my) //Sets y-coord
   {
      myY = my;
   }
   public void setScale(int s) //sets the scale for theship
   {
      myScale = s;
   }
   
   //accessor methods
   public Planet getPlanet()
   {
      return myPlanet;
   }
   public double getScale() //Gets scale for the ship
   {
      return myScale;
   }
   public void setImagePath(String s) //Sets the image path for the ship
   {
      try {
         img = ImageIO.read(new File("GShips/" + s + ".png"));
      }
      catch(Exception e){}
   }
   public String getImagePath(String s) //Gets the image path for the ship
   {
      return "GShips/" + s + ".png";
   }

   //constructors
   /**
    * Description Constructor for Ship
    *
    * @param mx or x pos, my or y pos, s or speed, d or distance from planet, race or race, panel or Jpanel to display in, and p1 or player that owns the ship
    */
   public Ships(int mx, int my, int s, int d, String race, JPanel p, player pl)
   {
      myX = mx;
      myY = my;
      myDistance = d;
      Owner = race;
      mySpeed  = s;
      myPanel = p;
      //setImagePath(i);
      Orbiting = false;
      atk = false;
      switch(pl.myRace) { //depending on the race and tier of ship you choose to build, it will display the proper image by going through the switch statements
         case "Cyberman":
            switch (pl.myTier) {
               case 0:
                  setImagePath("4B");
                  Strength = 1;
                  break;
               case 1:
                  setImagePath("6B");
                  Strength = 2;
                  break;
               case 3:
                  setImagePath("10B");
                  Strength = 3;
                  break;
            }
            break;//Sets fields to arguments
         case "Dalek":
            switch (pl.myTier) {
               case 0:
                  setImagePath("1");
                  Strength = 1;
                  break;
               case 1:
                  setImagePath("2");
                  Strength = 2;
                  break;

               case 3:
                  setImagePath("3");
                  Strength = 3;
                  break;
            }
            break;
         case "Human":
            switch (pl.myTier) {
               case 0:
                  setImagePath("6");
                  Strength = 1;
                  break;
               case 1:
                  setImagePath("11");
                  Strength = 2;
                  break;
               case 3:
                  setImagePath("13");
                  Strength = 3;
                  break;
            }
            break;
         case "Jawa":
            switch (pl.myTier) {
               case 0:
                  setImagePath("4B");
                  Strength = 1;
                  break;
               case 1:
                  setImagePath("5B");
                  Strength = 2;
                  break;
               case 3:
                  setImagePath("6B");
                  Strength = 3;
                  break;
            }
            break;
         default:
            setImagePath("1");
            Strength = 1;
            break;
      }
   }
   /**
    * Description Creates new orbit around planet
    *
    * @param arg = planet that it is orbitting around
    */
   public void newOrbit(Planet arg) //Initializes a new orbit.
   {
      if(Orbiting == true){}
      else {
         myDistance = 5;
         myDistance += (int)(arg.getRadius()); //Sets the distance that it orbits at
         myOrbit = (myDistance * 2 * Math.PI);
         myAngle = (mySpeed * myDistance)/360;
         myAnglePos = 0;
         Orbiting = true;
      }
   }
   /**
    * Description Draws ship on orbit
    *
    * @param arg = Planet arg and g = Graphics2d
    */
   public void drawCirclePath(Planet arg, Graphics2D g) //Draws the ship where it is.
   {
      /////////////////////////////////////////////////////////////////////////////////////Getting Information
      int planetX = arg.getX(); 
      int planetY = arg.getY(); //Gets the planet's location
      int w = img.getWidth();
      int h = img.getHeight(); //Gets the size of the image
      myX = (int)(Math.cos(Math.toRadians(myAnglePos)) * myDistance) + planetX;
      myY = (int)(-Math.sin(Math.toRadians(myAnglePos)) * myDistance) + planetY; //Finds ship's current x and y coordinates.
      myAnglePos += myAngle;
      if(myAnglePos > 360)
         myAnglePos -= 360; //Increments the angle pos of the ship.
      /////////////////////////////////////////////////////////////////////////////////////Rotates Image
      AffineTransform saveCurrent = g.getTransform(); //useless
      BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB); //new BufferedImage to store changes
      AffineTransform rot = AffineTransform.getTranslateInstance(h, w);  //New AffineTransform
      rot.rotate(Math.toRadians(-myAnglePos)); //Rotate
      AffineTransform placeImg = new AffineTransform(); //New AffineTransform
      placeImg.translate(myX * (1 - myScale), myY * (1 - myScale)); //Moves image
      placeImg.concatenate(rot); //Combines placeImg and rot
      /////////////////////////////////////////////////////////////////////////////////////Resize image
      AffineTransform at = new AffineTransform(); //makes the image resizeable
      at.scale(0.2, 0.2); //sets the size
      AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);//Creates AffineTransform needed to shrink image
      after = scaleOp.filter(img, after); //Transforms the image
      /////////////////////////////////////////////////////////////////////////////////////Draws Image                 
      g.drawImage(after, placeImg, null); //Draw things
   }
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   //distance formula
   public double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));                                   //distance formula
   }   
}
