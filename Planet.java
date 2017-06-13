//import necessary packages
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
   //Fields
   public String img;
   public int myTier;
   public int myShipsT1;
   public int myShipsT2;
   public int myShipsT3;
   public String Race;
   public double myStrength;
   public  int Iron;
   public int myX;
   public int myY;
   public double Radius;
   public double circumference;
   public String name;
   boolean atk;
   public ArrayList<Ships> myShips;
   public int[] stored;
   public player myPlayer;
   public int myOff;
   public JPanel myPanel;
   public int IronUsed;

   public boolean action;


   public Planet(int x, int y, int t, int mS, String i, String s, int iro, double r, String n, player p, JPanel pan) //constructor

   {
      img = i; //planet appearance
      myTier = t; 
      Race = s; //which race controls the planet
      Iron = iro;
      myPlayer = p; //player that controls the planet
      myStrength = mS + (t/2) + p.getBenefits("atk");
      Radius = r; //size
      myX = x; //location on the map
      myY = y;
      name = n;
      myShips = new ArrayList<Ships>(); //ships the planet has
      myPanel = pan;
   }

   public void switchPlayer(player p) //switches which player controls the planet
   {
      myPlayer = p;
      Race = p.myRace;
   }
   public boolean isMyPlayer(player p) //checks if there player that controls the planet matches
   {
      if(p.myRace.equals(myPlayer.myRace))
         return true;
      return false;
   } 
   
   //Accessor methods
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
   public int getShips(int i) { //i indicates the tier of ship
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
   
   public void attack(Planet p, int s, Graphics2D g)//sends ships to another planet and if the amount of shops sent exceeds the planet defense, changes control of the planet attacked
   {
       if(s + myPlayer.getBenefits("atk") > p.calcStrength(p, p.myPlayer))
       {
          double remaining = s + myPlayer.getBenefits("atk") - p.myStrength;
          if(remaining > 0)
          {
              p.switchOwner(myPlayer.myRace);
              p.myShipsT1 = (int)remaining;
          }
          for(int i = 0; i < s; i++)
          {
              myShips.remove(i);
          }
       }

       else {
          JOptionPane.showConfirmDialog(myPanel,"Unsuccessful");
          for(int i = 0; i <= s; s++)
          {
             myShips.remove(i);
          }
          action = false;
       }
   }

   public void addShips(Planet arg) //Increases the number of ships on the planet
   {
       myShips.add(new Ships(0,0,10, 127, Race, myPanel, myPlayer));
       myShips.get(myShips.size() - 1).newOrbit(arg);
   }
   public void displayShips(Graphics2D g, Planet arg) //displays the ships around the planet
   {
       if(myShips.size() == myShipsT1 + myShipsT3 + myShipsT2)
       for(int i = 0; i < myShips.size(); i++ )
       {
           myShips.get(i).drawCirclePath(arg, g);
       }
       else {
           addShips(arg);
           displayShips(g, arg);
       }
   }
   public double[] getCenter() // gets the center position of the planet
   {
      double x = myX + Radius;
      double y = myY - Radius;
      double[] dob = new double[2];
      dob[0] = x;
      dob[1] = y;
      return dob;
   
   }


   public void update(double x, BufferedImage im, Graphics2D g, JPanel panel) //updates the ship movement
   {
     AffineTransform rot = new AffineTransform();
     double dob[] = getCenter();
     rot.rotate(Math.toRadians(45), dob[0], dob[1]);
     g.drawImage(im, rot, panel);  
   }

   
   //calculates the strength of the planet based on ships it has and other benefits
   public double calcStrength(Planet arg, player p) //calculates the strength of the planet based on ships it has and other benefits
   {
      return ((arg.getShips(1) + arg.getShips(2) + arg.getShips(3)) * p.myTier) + (arg.getTier() / 2) + p.getBenefits("atk");
   }
   public double calcStrength(player p)
   {
      return ((myShipsT1 + myShipsT2 + myShipsT3) * myPlayer.myTier) + (myTier / 2) + p.getBenefits("atk");
   }
   
   public void console(String s, Planet[] p, JPanel panel, player p1, Graphics2D g)
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
      if(action = true)
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
             console(JOptionPane.showInputDialog(panel, "That's not a planet"), p, panel, p1, g);
         }
         else
         {

            int ArrOfAttacker = -1;
            
            for(int i = 0; i < p.length; i++)
            {
               if(this.name.equals(p[i].name))
               {
                  ArrOfAttacker = i;
               }
            }
            if(ArrOfAttacker == -1)
            {
                console(JOptionPane.showInputDialog(panel, "That's not a planet"), p, panel, p1, g);
            }
            
            else
            {
               int amount = Integer.parseInt(JOptionPane.showInputDialog(panel, "how many?"));
               
               attack(p[ArrOfAttacked], amount, g);
                
            }
            
         
         }
      }
else if(s.equals("terraform"))
{
    switch(myPlayer.myRace)
    {
        case "Human":
            setImage("16");
            Iron += myPlayer.myTier * 5;
            break;
        case "Cybermen":
            setImage("17");
            Iron += myPlayer.myTier * 5;
            break;
        case "Dalek":
            setImage("12");
            Iron += myPlayer.myTier * 5;
            break;
        case "Jawa":
            setImage("16");
            Iron += myPlayer.myTier * 5;
            break;
        default:
            break;

    }

    action = false;






}
      else if(s.equals("research"))
      {
          if(myPlayer.isMe(p1))
          {
              int amount = Integer.parseInt(JOptionPane.showInputDialog(panel, "how many?"));
              myPlayer.addResearch(amount);
              if(amount <= Iron - IronUsed) {
                  IronUsed += amount;
                  action = false;

              }


              else{
                  console(JOptionPane.showInputDialog(panel, "You don't have enough Iron"), p, panel, p1, g);
              }
          }


      }

      else if(s.equals("build"))
      {
         if(myPlayer.isMe(p1)) {
            int amount = Integer.parseInt(JOptionPane.showInputDialog(panel, "how many?"));
            if(myPlayer.myTier == 1) {
                if(amount <= Iron - IronUsed && myPlayer.getIron(p) >= myPlayer.getUsedIron(p)) {
                    IronUsed += amount;
                    myShipsT1 += amount;
                    action = false;
                }

                else{
                    console(JOptionPane.showInputDialog(panel, "You don't have enough Iron"), p, panel, p1, g);
                }


            }

            else if(myPlayer.myTier == 2)
            {
                if(amount <= Iron - (IronUsed * 2) && myPlayer.getIron(p) >= myPlayer.getUsedIron(p)) {
                    IronUsed += amount;
                    myShipsT2 += amount;
                    action = false;
                }

                else{
                    console(JOptionPane.showInputDialog(panel, "You don't have enough Iron"), p, panel, p1, g);
                }
            }

            else
            {
                if(amount <= Iron - (IronUsed * 3)&& myPlayer.getIron(p) >= myPlayer.getUsedIron(p)) {
                    IronUsed += amount;
                    myShipsT3 += amount;
                    action = false;
                }

                else{
                    console(JOptionPane.showInputDialog(panel, "You don't have enough Iron"), p, panel, p1, g);
                }
            }
         }

         else{
             console(JOptionPane.showInputDialog(panel, "This isn't your planet"), p, panel, p1, g);
         }

      }
      
      else
         JOptionPane.showMessageDialog(panel ,"Sorry, that's not a command" , "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);


      else{
          JOptionPane.showMessageDialog(panel, "This system is out of actions", "InfoBoc: ", JOptionPane.INFORMATION_MESSAGE);
      }
      
   }
   
   public void AiConsole(String s, int x, int t)
   {
      switch(s){
      case "build":
      switch(t){
      case 1:
      myShipsT1 += x;
      break;
      case 2:
      myShipsT2 += x;
      break;
      case 3:
      myShipsT3 += x;
      break;
      
      
      
      }
      break;

          case "research": myPlayer.addResearch(x);
      
      default:
      break;
      
      
      }
   
   
   
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
   
   public void displayInfo(Planet[] arg, JPanel p, player p1, Graphics2D g)
   {
      System.out.println("Clicked " + name);
      console(JOptionPane.showInputDialog(null, getInfo()), arg, p, p1, g);
   }
   public void switchOwner(String s)
   {
       Race = s;
   }
   public String getInfo()
   {
       myStrength = calcStrength(this, myPlayer);
      String s ="Name: " + name + "\n" + "Owner: " + Race + "\n " + " Iron : " + Iron + "\n " + " Strength : " + myStrength + "\n Tier: " + myTier + "\n Iron Used: " + IronUsed;
      return s;
   }
}
