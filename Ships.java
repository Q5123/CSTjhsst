import java.lang.Math;
import java.awt.*;
import javax.swing.*;
public class Ships
{
   public int myX;
   public int myY;
   public String Owner;
   public int myDistance; //Distance that ship orbits around planet
   public int theta;
   ImageIcon img;
   public int mySpeed; //How far the ship goes. 
   public int myStatus; //The class knows whether it is in flight to another planet, or orbiting around a planet.
   public double myAngle; //Angle that the ship travels at one tick.
   public double myOrbit; //Orbit around the current planet
   public double myAnglePos; //Position on the polar plane
   public JPanel myPanel;
   public Boolean Orbiting;
   public Boolean atk;
   double slope;
   int b;
   Planet myPlanet;
   
   public void setX(int mx)
   {
      myX = mx;
   }
   
   public void setY(int my)
   {
      myY = my;
   }
   
   public void setImage(String s)
   {
      img = new ImageIcon("GShips/" + s + ".png");
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
      setImage(i);
      Orbiting = false;
      atk = false;
   }
   public void newOrbit(Planet arg) //Initializes a new orbit.
   {
      if(Orbiting){}
      else {
         int a = arg.getX(); 
         int b = arg.getY(); //Gets center of planet
         myDistance = (int) (Math.random() * 25 + 1);
         myDistance += (int)(arg.getRadius()); //Sets the distance that it orbits at
         myOrbit = (myDistance * 2 * Math.PI);
         myAngle = (mySpeed / myOrbit) * 180 / Math.PI;
         myAnglePos = 0;
         Orbiting = true;
      }
   }
   public void drawCirclePath(Planet arg, Graphics g) //Draws the ship where it is.
   {
      int planetX = arg.getX(); 
      int planetY = arg.getY();
      myX = (int)(Math.cos(myAnglePos) * myOrbit) + planetX;
      myY = (int)(Math.sin(myAnglePos) * myOrbit) + planetY; //Finds ship's current x and y coordinates.
      myAnglePos += myAngle;
      if(myAnglePos > 360)
         myAnglePos -= 360; //Increments the angle pos of the ship.
      ImageIcon ship = img;
      RotatedIcon newShip = new RotatedIcon(ship, myAnglePos);
      newShip.paintIcon(myPanel, g, myX, myY);
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
      
      else {
         ImageIcon ship = img;
         ship.paintIcon(myPanel, g, myX, myY);
         myX+= mySpeed;
         myY = (int)((slope * myX) + b);
      }
   }
}