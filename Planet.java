import java.awt.*;
import javax.swing.*;
import java.util.*;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
public class Planet
{
   public String img;
   public int myTier;
   public int myShipsT1;
   public int myShipsT2;
   public int myShipsT3;
   public String Race;
   public double myStrength;
   private int Iron;
   public int myX;
   public int myY;
   public double Radius;
   public double circumference;
   public String name;
   boolean atk;
   public ArrayList<Ships> myShips;
   public int[] stored;
   public player myPlayer;
   public Planet(int x, int y, int t, int mS, String i, String s, int iro, double r, String n, player p)
   {
      img = i;
      myTier = t;
      Race= s;
      Iron = iro;
      myPlayer = p;
      myStrength = mS + (t/2) + p.getBenefits("atk");
      Radius = r;
      myX = x;
      myY = y;
      name = n;
      myShips = new ArrayList<Ships>();
   }

   public void swtichPlayer(player p)
   {
      myPlayer = p;
   }

   public boolean isMyPlayer(player p)
   {
      if(p.myRace.equals(myPlayer.myRace))
         return true;
      return false;
   }
   public double getRadius()
   {
      return Radius;
   }
   public int getX(){
      return myX;
   }
   
   public int getY(){
      return myY;
   }
   public String getImg() {
      return img;
   }
   
   public int getTier() {
      return myTier;
   }
   
   public int getShips(int i) {
      if(i == 1)
         return myShipsT1;
      
      else if(i == 2)
         return myShipsT2;
      
      else
         return myShipsT3;
   }
   
   public String getRace() {
      return Race;
   }
   
   public double getStrength() {
      return myStrength;
   }
   
   public double getCircumference() {
      return circumference;
   }
   public void attack(Planet p, int s)
   { 
      
   
   }

   public void addShips(Planet arg)
   {
       myShips.add(new Ships(0,0,10, 127, Race, null, "1B"));
       myShips.get(myShips.size() - 1).newOrbit(arg);
   }
   public void displayShips(Graphics g, Planet arg)
   {
       if(myShips.size() == myShipsT1)
       for(int i = 0; i < myShips.size(); i++ )
       {
           myShips.get(i).drawCirclePath(arg, g);
       }
       else {
           addShips(arg);
           displayShips(g, arg);
       }
   }
   public double[] getCenter()
   {
      double x = myX + Radius;
      double y = myY - Radius;
      double[] dob = new double[2];
      dob[0] = x;
      dob[1] = y;
      return dob;
   
   }

   public void update(double x, BufferedImage im, Graphics2D g, JPanel panel)
   {
     AffineTransform rot = new AffineTransform();
     double dob[] = getCenter();
     rot.rotate(Math.toRadians(45), dob[0], dob[1]);
     g.drawImage(im, rot, panel);  
   }
   
   public double calcStrength(Planet arg, player p)
   {
      return arg.getShips(1) + arg.getShips(2) + arg.getShips(3) + (arg.getTier() / 2) + p.getBenefits("atk");
   }
   public double calcStrength(player p)
   {
      return myShipsT1 + myShipsT2 + myShipsT3 + (myTier / 2) + p.getBenefits("atk");
   }
   
   public void console(String s, Planet[] p, JPanel panel)
   {
      try {
         String j = s.substring(0, s.indexOf("."));
         if(j.equals("setIron"))
            Iron = Integer.parseInt(s.substring(s.indexOf(".") + 1));
         
         else if(j.equals("setShipsT1")) {
            myShipsT1 = Integer.parseInt(s.substring(s.indexOf(".") + 1));
            myStrength = calcStrength(myPlayer);
         }
      }
      catch(StringIndexOutOfBoundsException e){}
         
         
      if(s.equals("atk"))
      {
         String attacked = JOptionPane.showInputDialog(panel, "who are you attacking?");
         int ArrOfAttacked = -1;
         for(int i = 0; i < p.length; i++)
         {
            if(attacked.equals(p[i].name))
            {
               ArrOfAttacked = i;
            }
         }
         if(ArrOfAttacked == -1)
         {
            System.out.println("thats not a planet");
         }
         
         
         
         
         else
         {
            String attacker = JOptionPane.showInputDialog(panel, "from which planet");
            int ArrOfAttacker = -1;
            
            for(int i = 0; i < p.length; i++)
            {
               if(attacker.equals(p[i].name))
               {
                  ArrOfAttacker = i;
               }
            }
            if(ArrOfAttacker == -1)
            {
               System.out.println("thats not a planet");
            }
            
            else
            {
               int amount = Integer.parseInt(JOptionPane.showInputDialog(panel, "how many?"));
               
               
               store(ArrOfAttacker, ArrOfAttacked, amount);
                
            }
            
         
         }
      }
      
      else
         JOptionPane.showMessageDialog(panel ,"Sorry, that's not a command" , "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
      
   }
   
   public void store(int a, int b, int c)
   {
      stored = new int[3];
      stored[0] = a;
      stored[1] = b;
      stored[2] = c;
   }
      
   public int[] getStored()
   {
      return stored;
   }
      
   public void myShipsAtkT1(Graphics g)
   {
         
      
      
   }
   
   
   public String getImagePath()
   {
      return ("planets/planet_" + img + ".png");
   }
   
   public void setImage(String i)
   {
      img = i;
   }
   
   public boolean liesInPlanet(int x, int y)
   {
      double dx = x - myX;
      double dy = y - myY;
      double r = Radius;
      return dx * dx + dy * dy <= r * r;
   } 
   
   public void displayInfo(Planet[] arg, JPanel p)
   {
      System.out.println("Clicked " + name);
      console(JOptionPane.showInputDialog(null, getInfo()), arg, p);
   }
   public void switchOwner(String s)
   {
       Race = s;
   }
   public String getInfo()
   {
      String s ="Name: " + name + "\n" + "Owner: " + Race + "\n " + " Iron : " + Iron + "\n " + " Strength : " + myStrength + "\n ";
      return s;
   }

   public void attack(Planet p)
   {

   }
      
}