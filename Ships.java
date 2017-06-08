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
   private double myScale = 0.2; //How much image is scaled down
   int b; 
   public boolean reached; //did i reach the destination?
   Planet myPlanet; //My current planet
   
   public void setX(int mx) //Sets x-coord
   {
      myX = mx;
   }
   
   public void setY(int my) //Sets y-coord
   {
      myY = my;
   }
   public Planet getPlanet()
   {
      return myPlanet;
   }
   public void setScale(int s) //sets the scale for theship
   {
      myScale = s;
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

   public Ships(int mx, int my, int d, String o)
   {
      myX = mx;
      myY = my;
      myDistance = d;
      Owner = o;   // sets fields to arguments
   }
   public Ships(int mx, int my, int s, int d, String o, JPanel p, String i)
   {
      myX = mx;
      myY = my;
      myDistance = d;
      Owner = o;
      mySpeed  = s;
      myPanel = p;
      setImagePath(i);
      Orbiting = false;
      atk = false; //sets fields to arguments
   }
   public Ships(int mx, int my, int s, int d, String race, JPanel p, int tier)
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
      setImagePath(race+tier);   //Sets fields to arguments
   }
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
   public void Attack(Planet arg2, Graphics g) //Unused
   { 
      if(!atk)
      {
         /////////////////////////////////////////////////////////////////////////////////////Gets Information 
         slope = (myY - arg2.myY)/(myX - arg2.myX); //Finds slope needed
         int planetX = arg2.myX; 
         int planetY = arg2.myY; //Gets location of destination
         b = (int)((slope * planetX) - planetY); //The b value in y = mx + b
         atk = true; //I attack now.
         /////////////////////////////////////////////////////////////////////////////////////Calculations
         int yDiff = myY - planetY;
         int xDiff = myX - planetX; //Calculates vert. and horiz. components between planet and ship
         myAnglePos = Math.atan(yDiff / xDiff); //Calculates angle needed to rotate
         /////////////////////////////////////////////////////////////////////////////////////Rotates Image
         AffineTransform saveCurrent = g.getTransform(); //line not used
         BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB); //new BufferedImage to store changes
         AffineTransform rot = AffineTransform.getTranslateInstance(h, w);  //New AffineTransform
         rot.rotate(Math.toRadians(-myAnglePos)); //Rotate
         AffineTransform placeImg = new AffineTransform(); //New AffineTransform
         placeImg.translate(myX * (1 - myScale), myY * (1 - myScale)); //Moves image
         placeImg.concatenate(rot); //Combines placeImg and rot
         AffineTransform at = new AffineTransform(); //makes the image resizeable
         at.scale(0.2, 0.2); //sets the size
         AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);//Creates AffineTransform needed to shrink image
         after = scaleOp.filter(img, after); //Transforms the image
         /////////////////////////////////////////////////////////////////////////////////////Draws Image                 
         g.drawImage(after, placeImg, null); //Draw things
      }
      else if(!reached)
      {
         BufferedImage ship = img; //sets new BuffImg to img
         g.drawImage(ship, myX, myY, null); //Draws image
         myX+= mySpeed;
         myY = (int)((slope * myX) + b); //Moves ship
         /////////////////////////////////////////////////////////////////////////////////////Rotates Image
         AffineTransform saveCurrent = g.getTransform(); //line not used
         BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB); //new BufferedImage to store changes
         AffineTransform rot = AffineTransform.getTranslateInstance(h, w);  //New AffineTransform
         rot.rotate(Math.toRadians(-myAnglePos)); //Rotate
         AffineTransform placeImg = new AffineTransform(); //New AffineTransform
         placeImg.translate(myX * (1 - myScale), myY * (1 - myScale)); //Moves image
         placeImg.concatenate(rot); //Combines placeImg and rot
         AffineTransform at = new AffineTransform(); //makes the image resizeable
         at.scale(0.2, 0.2); //sets the size
         AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);//Creates AffineTransform needed to shrink image
         after = scaleOp.filter(img, after); //Transforms the image
         /////////////////////////////////////////////////////////////////////////////////////Draws Image                 
         g.drawImage(after, placeImg, null); //Draw things
         if(distance(myX, myY, arg2.getCenter()[0], arg2.getCenter()[1]) < myDistance) //Checks if it got there
         {
            reached = true; //I got there!
            myPlanet = arg2;
         }
      }
   }
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   //distance formula
   public double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));                                   //distance formula
   }   
}
