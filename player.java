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
   private boolean lifeStatus;
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

          case 4: setMyRace("Jawa");
          break;
      }

   }
    public player()
    {
        myTurn = Boolean.parseBoolean(null);
    }
   
   public ArrayList<Integer> getPlanets()
   {
      return planets;
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
        }
        return 0;
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
}