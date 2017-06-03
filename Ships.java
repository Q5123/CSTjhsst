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
import java.awt.Image;
public class Ships
{
   public int myX;
   public int myY;
   public String Owner;
   public int myDistance; //Distance that ship orbits around planet
   public int theta;
   BufferedImage img;
   public int mySpeed; //How far the ship goes. 
   public int myStatus; //The class knows whether it is in flight to another planet, or orbiting around a planet.
   public double myAngle; //Angle that the ship travels at one tick.
   public double myOrbit; //Orbit around the current planet
   public double myAnglePos; //Position on the polar plane
   public JPanel myPanel;
   public Boolean Orbiting;
   public Boolean atk;
   double slope;
   private double myScale = 0.2;
   int b;
   public boolean reached;
   Planet myPlanet;
   
   public void setX(int mx)
   {
      myX = mx;
   }
   
   public void setY(int my)
   {
      myY = my;
   }
   
   public void setScale(int s)
   {
      myScale = s;
   }
   public double getScale()
   {
      return myScale;
   }
   public void setImagePath(String s) {

      try {
         img = ImageIO.read(new File("GShips/" + s + ".png"));
      }
      catch(Exception e){}
   }
      
      
      

   public String getImagePath(String s)
   {
      
      return "GShips/" + s + ".png";
   }

   public Ships(int mx, int my, int d, String o)
   {
      myX = mx;
      myY = my;
      myDistance = d;
      Owner = o;
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
      atk = false;
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
      setImagePath(race+tier);   
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
      int planetX = arg.getX(); 
      int planetY = arg.getY();
      int w = img.getWidth();                                                                //gets the width of the image
      int h = img.getHeight();                                                               //gets the height of the image
      myX = (int)(Math.cos(Math.toRadians(myAnglePos)) * myDistance) + planetX;
      myY = (int)(-Math.sin(Math.toRadians(myAnglePos)) * myDistance) + planetY; //Finds ship's current x and y coordinates.
      myAnglePos += myAngle;
      if(myAnglePos > 360)
         myAnglePos -= 360; //Increments the angle pos of the ship.
      /////////////////////////////////////////////////////////////////////////////////////
      AffineTransform saveCurrent = g.getTransform();
      BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB); 
      AffineTransform rot = AffineTransform.getTranslateInstance(h, w);
      rot.rotate(Math.toRadians(-myAnglePos));
      AffineTransform placeImg = new AffineTransform();
      placeImg.translate(myX * (1 - myScale), myY * (1 - myScale));
      placeImg.concatenate(rot); 
      /////////////////////////////////////////////////////////////////////////////////////
      AffineTransform at = new AffineTransform();                                            //makes the image resizeable
      at.scale(0.2, 0.2);                                                                        //sets the size
      AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);//transforms the image
      after = scaleOp.filter(img, after);   
      /////////////////////////////////////////////////////////////////////////////////////                  
      g.drawImage(after, placeImg, null);
   }
   public void Attack(Planet arg2, Graphics g)
   {
      
      if(!atk){
         slope = (myY - arg2.myY)/(myX - arg2.myX);
         int mx = arg2.myX;
         int my = arg2.myY;
         b = (int)((slope * mx) - my);
         atk = true;
      }
      
      else if(!reached){
         BufferedImage ship = img;
         g.drawImage(ship, myX, myY, null);
         myX+= mySpeed;
         myY = (int)((slope * myX) + b);
         if(distance(myX, myY, arg2.getCenter()[0], arg2.getCenter()[1]) < myDistance)
         {
            reached = true;
         }
      }
   }
   //scale down stuff

   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   //distance formula
   public double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));                                   //distance formula
   }   
}
