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




public class Game implements Runnable{
   public BufferedImage[] icon;
   final int WIDTH = 1600;
   final int HEIGHT = 900;
   Planet[] arr;
   Ships[] ship;
   ImageIcon imgb;
   JFrame frame;
   Canvas canvas;
   JPanel panel;
   player p1;
   player p2;

   BufferStrategy bufferStrategy;
   public boolean needsInstantiation;
   public Ships s;

   ArrayList<Boolean> needsInstance;
   ArrayList<Ships> ActShips;
   ArrayList<Integer> reference;
   

   public Game(){
      frame = new JFrame("Basic Game");
      needsInstantiation = true;
      panel = (JPanel) frame.getContentPane();
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      panel.setLayout(null);
      imgb = new ImageIcon("game background.jpg");
      canvas = new Canvas();
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
      arr = new Planet[10];
      icon = new BufferedImage[arr.length];
      needsInstance = new ArrayList<Boolean>();
      ActShips = new ArrayList<Ships>();
      reference = new ArrayList<Integer>();
      
      try{
         File f = new File("Map1.txt");
         Scanner s = new Scanner(f);
         File g = new File("Names.txt");
         Scanner h = new Scanner(g);
         ArrayList<String> namas = new ArrayList<String>(64);
         for(int i = 0; i < 31; i++)
         {
            namas.add(i, h.nextLine());
         }
            
         for(int i = 0; i < arr.length; i++)
         {
            int x = s.nextInt();
            int y = s.nextInt();
            int t = (int)(Math.random()*5);
            int im = (int)(Math.random()*11 + 1);
            int r = t * 10;
            String name = namas.get(i);
            String img = null;
            if(im < 10) {
               img = "0" + im;
            }
            
            else {
               img = "" + im;
            }
         
            int Iron = (int)(Math.random()*11);
         
            arr[i] = new Planet(x,y,t,0,img,"Santron",Iron, r, name, null);
         
         }
      }
      catch(Exception e){System.out.println("Not initilized");}
      
      
     
      
      panel.add(canvas);
      
      canvas.addMouseListener(new MouseControl());
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setResizable(false);
      frame.setVisible(true);
      
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      
      canvas.requestFocus();
   }
   private class Listener implements ActionListener
   {
      private double myX;
      public Listener(double x)
      {
         myX = x;
      }
      public void actionPerformed(ActionEvent e)
      {
           	
            
      }
   }
   
   public void displayButton(int i, Graphics2D g) throws Exception
   {
      BufferedImage buttonIcon = ImageIO.read(new File(arr[i].getImagePath()));
      icon[i] = scaleDown(buttonIcon);
      arr[i].update(x,icon[i],g, panel);
      g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
      g.drawImage(icon[i], null, arr[i].myX, arr[i].myY); 
      arr[i].Radius = icon[i].getWidth() * .1;   
   }
   
   public void ships(Graphics2D g)
   {
      
   
   
   
   }


   
        
   private class MouseControl extends MouseAdapter{
      public void mouseClicked(MouseEvent e) {
         int x=e.getX();
         int y=e.getY();
         for(int i = 0; i < arr.length; i++) {
            if(arr[i].liesInPlanet(x,y))
               arr[i].displayInfo(arr, panel);
         }
         
      }
      
   }
   
   public BufferedImage scaleDown(BufferedImage img)
   {
      int w = img.getWidth();
      int h = img.getHeight();
      BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
      AffineTransform at = new AffineTransform();
      at.scale(.1, .1);
      AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
      after = scaleOp.filter(img, after);
      return after;
   
   
   }
   
   long desiredFPS = 60;
   long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
    
   boolean running = true;
   
   public void run(){
      
      long beginLoopTime;
      long endLoopTime;
      long currentUpdateTime = System.nanoTime();
      long lastUpdateTime;
      long deltaLoop;
      
      while(running){
         beginLoopTime = System.nanoTime();
         
         render();
         
         lastUpdateTime = currentUpdateTime;
         currentUpdateTime = System.nanoTime();
         update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));
         
         endLoopTime = System.nanoTime();
         deltaLoop = endLoopTime - beginLoopTime;
           
         if(deltaLoop > desiredDeltaLoop){
               //Do nothing. We are already late.
         }
         else{
            try{
               Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
            }
            catch(InterruptedException e){
                   //Do nothing
            }
         }
      }
   }
   
   private void render() {
      Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
      imgb.paintIcon(panel,g,0,0);
      render(g);
      g.dispose();
      bufferStrategy.show();
      
   }
   
   //TESTING
   private double x = 0;
   
   /**
    * Rewrite this method for your game
    */
   protected void update(int deltaTime){
      x += deltaTime * 0.2;
      while(x > 500){
         x -= 500;
      }
   }
   
   /**
    * Rewrite this method for your game
    */
   protected void render(Graphics2D g){
      
      for(int i = 0; i < arr.length; i++) {
         try{
            displayButton(i,g);
         }
         catch(Exception e){System.out.println("Could not Display " + arr[i].getImagePath());}
      }
      for(int i = 0; i < arr.length; i++)
      {
         g.drawOval(arr[i].myX,arr[i].myY, (int)arr[i].Radius, (int)arr[i].Radius);
         
         if(arr[i].myShipsT1 > 0) 
         {
            if(needsInstantiation)
            {
               double[] dob = arr[i].getCenter();
               s = new Ships((int)dob[0],(int)dob[1],10,10, "CyberMen", panel,"1B");
               s.newOrbit(arr[i]);
            }
            else
               s.drawCirclePath(arr[i], g);
         }
      }
   }
   
   public static void main(String [] args){
      Game ex = new Game();
      new Thread(ex).start();
   }

}