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
public class player
{
   public ArrayList<Integer> planets;
   public boolean myTurn;
   public String myRace;
   public int firstPlanet;
   private boolean lifeStatus;
   public int myResearch;
   public int myTier;
   public player(int x, int r)
   {
      planets = new ArrayList<Integer>();
      planets.add(x);
      switch(r){
          case 1: setMyRace("CyberMen");
          break;

          case 2: setMyRace("Dalek");
          break;

          case 3: setMyRace("Human");
          break;

          default: setMyRace("Jawa");
          break;
      }
      firstPlanet = x;

   }
    public player()
    {
        myTurn = Boolean.parseBoolean(null);
    }

    public void addResearch(int x) {
       myResearch += x + getBenefits("research");
        if(myResearch > 10 && myResearch < 30)
        {
            myTier = 2;
        }

        else if(myResearch > 10)
        {
            myTier = 3;
        }


    }
   
   public boolean isMyTurn()
   {
      return myTurn;
   }
   public ArrayList<Integer> getPlanets()
   {
      return planets;
   }

   public boolean isMe(player p){
       if(myRace.equals(p.myRace))
       {
           return true;
       }
       else{
           return false;
       }
   }

   public void addPlanet(int x)
   {
      planets.add(x);
   }

   public void setMyTurn()
   {
      myTurn = true;
   }

   public void setNotMyTurn()
   {
      myTurn = false;
   }

   public void setMyRace(String s)
   {
       myRace = s;
   }

   public int getBenefits(String s)
    {
        switch(s) {
            case "atk":   if(myRace.equals("Dalek")){ return planets.size() / 2;}
            case "research": if(myRace.equals("Human")){ return planets.size() / 2;}
            case "build": if(myRace.equals("CyberMan")){ return planets.size() / 2;}
            default: return 0;
        }
    }
   public boolean amIDead()
   {
      return lifeStatus;
   }
   public boolean kill()
   {
      lifeStatus = false;
      return lifeStatus;
   }

   public int getIron(Planet[] pl) {
       int sum = 0;
       for(int i = 0; i < planets.size(); i++) { sum += pl[planets.get(i)].Iron;}
       return sum;
   }

   public int getUsedIron(Planet[] pl)
   {
       int sum = 0;
       for(int i = 0; i < planets.size(); i++) { sum += pl[planets.get(i)].IronUsed;}
       return sum;
   }
}