import java.lang.Math;
import java.awt.*;
import javax.swing.*;
public class JawaShip extends Ships
{
   public int Tier;
   public int myX;
   public int myY;
   public String Owner;
   public int myDistance; //Distance that ship orbits around planet
   public int theta;
   Image img;
   public int mySpeed; //How far the ship goes. 
   public int myStatus; //The class knows whether it is in flight to another planet, or orbiting around a planet.
   public double myAngle; //Angle that the ship travels at one tick.
   public double myOrbit; //Orbit around the current planet
   public double myAnglePos; //Position on the polar plane
   public JPanel myPanel;
   public JawaShip(int mx, int my, int d, String o)
   {
      super(mx, my, d, o);
   }
   public JawaShip(int mx, int my,int l, String s, int d, String o, JPanel p)
   {
      super(mx, my, d, l, s , p, o);
   }
}