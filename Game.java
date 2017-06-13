//import all packages
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.lang.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import java.io.File;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

//Game class
public class Game implements Runnable{
   //all fields
   public BufferedImage[] icon;
   final int WIDTH = 1600;
   final int HEIGHT = 900;
   public Planet[] arr;
   public ImageIcon imgb;
   public JFrame frame;
   public Canvas canvas; //displays and updates the stuff on the screen
   public JPanel panel;
   public player p1;
   public player p2;
   public AIplayer A;
   public Scanner sl; //reads file f
   public Scanner h; //reads file j
   public File f; //planet info
   public File j; //planet names
   public Graphics2D g;
   JLabel plylabel;
   
   //more fields
   BufferStrategy bufferStrategy;
   public boolean needsInstantiation;
   public Ships s;
   
   ArrayList<Boolean> needsInstance; //keeps track of ship instantiation
   ArrayList<Ships> ActShips; //holds ships
   ArrayList<Integer> reference; //holds ship information
   
   //Game constructor
   public Game(player px1, player px2)
   {
      frame = new JFrame("Basic Game");
      needsInstantiation = true;
      panel = (JPanel) frame.getContentPane();
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      panel.setLayout(new BorderLayout());
      imgb = new ImageIcon("game background.jpg");
      
      //instantitates the canvas and sets boundary
      canvas = new Canvas();
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
      
      arr = new Planet[10];
      icon = new BufferedImage[arr.length];
      needsInstance = new ArrayList<Boolean>();
      ActShips = new ArrayList<Ships>();
      reference = new ArrayList<Integer>();
      
      //instantiates players
      p1 = px1;
      p2 = px2;
      A = new AIplayer(arr);
      
      //checks if if files are avalible
      try{
         f = new File("Map1.txt");
         sl = new Scanner(f);
         j = new File("Names.txt");
         h = new Scanner(j);
         
         ArrayList<String> namas = new ArrayList<String>(10); //array of planet
         for(int i = 0; i < 31; i++) //takes planet names from file and transfers to namas
         {
            namas.add(i, h.nextLine());
         }  
         for(int i = 0; i < arr.length; i++) //creates planets based on information in file sl
         {
            //planet constructor arguments
            int x = sl.nextInt();
            int y = sl.nextInt();
            int t = (int)(Math.random()*5);
            int im = (int)(Math.random()*11 + 1);
            double r = t * 10;
            String name = namas.get(i);
            String img;
            if(im < 10) {
               img = "0" + im;
            }
            else {
               img = "" + im;
            }
            int Iron = (int)(Math.random()*11);

            arr[i] = new Planet(x,y,t,0,img,"Jawa",Iron, r, name, A, panel); //instantiates planets
         }
         //all planets start owned by ai, so this splits them evenly between all the players
         arr[p1.firstPlanet].switchPlayer(p1);
         arr[p2.firstPlanet].switchPlayer(p2);
      }
      catch(Exception e){System.out.println("Not initialized");}
      
      A.setMyTurn();
      
      //adds bottom bar that controls turns and tells player race
      panel.add(canvas, BorderLayout.CENTER); 
      JPanel southPanel = new JPanel(new BorderLayout());
      JLabel turnLabel = new JLabel(currentPlayer().myRace);
      southPanel.add(turnLabel, BorderLayout.WEST);
      //adds button that switches turns
      JButton endTurn = new JButton("end Turn");
      endTurn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              switchTurn();
              turnLabel.setText(currentPlayer().myRace);
          }
      });
      southPanel.add(endTurn,BorderLayout.EAST);
      southPanel.add(plylabel = new JLabel("Iron: " + currentPlayer().getIron(arr) + "\n  Used Iron: " + currentPlayer().getUsedIron(arr) + "\n  Research Tier: " + currentPlayer().myTier), BorderLayout.WEST);
      panel.add(southPanel, BorderLayout.SOUTH);

      canvas.addMouseListener(new MouseControl()); //listener that checks for mouse actions within canvas borders
      
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
    public void switchTurn() //switches turns between players
    {
        if(p1.myTurn)
        {
            p1.myTurn = false;
            p2.myTurn = true;
        }
        else if(p2.myTurn){
            p2.myTurn= false;
            A.myTurn = true;
        }
        else if(A.myTurn) {
            A.myTurn = false;
            p1.myTurn = true;
        }
        plylabel.setText(("Iron: " + currentPlayer().getIron(arr) + "\n    Used Iron:" + currentPlayer().getUsedIron(arr) + "\n  Research Tier: " + currentPlayer().myTier));

    }

    public player currentPlayer() //gets what player's turn it currently is
    {
       if(p1.myTurn)
          return p1;
       else if(p2.myTurn)
          return p2;
       else
          return A;
    }
   
   public void displayPlanets(int i, Graphics2D g) throws Exception //displays buttons on the game
   {
      BufferedImage buttonIcon = ImageIO.read(new File(arr[i].getImagePath()));
      icon[i] = scaleDown(buttonIcon);
      g.setFont(new Font("Tunga", 1, 24));
      g.setColor(Color.blue);
      g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC); //part of scaling the image
      //draws the button and text on it
      g.drawImage(icon[i], null, arr[i].myX, arr[i].myY);
      arr[i].Radius = icon[i].getWidth() * .1;
      g.drawString(arr[i].name, arr[i].myX, arr[i].myY); 
   }
   private class MouseControl extends MouseAdapter{ //checks if the mouse clicks on planets and displays planet information
      public void mouseClicked(MouseEvent e) {
         int x=e.getX();
         int y=e.getY();
         for(int i = 0; i < arr.length; i++) {
            if(arr[i].liesInPlanet(x,y))
               arr[i].displayInfo(arr, panel, currentPlayer(), g);
         }  
      }  
   } 
   public BufferedImage scaleDown(BufferedImage img) //scales down images
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
   
   public void run(){ //Runs the game and shows image movement
      
      long beginLoopTime;
      long endLoopTime;
      long currentUpdateTime = System.nanoTime();
      long lastUpdateTime;
      long deltaLoop;
      
      while(running){ //while it is running, make sure it is running correctly and at the right pace
         beginLoopTime = System.nanoTime();
         
         render(); 
         
         lastUpdateTime = currentUpdateTime;
         currentUpdateTime = System.nanoTime();
         update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));
         
         endLoopTime = System.nanoTime();
         deltaLoop = endLoopTime - beginLoopTime;
           
         if(deltaLoop > desiredDeltaLoop){
               //Do nothing
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
   
   private void render() { //renders the stuff in the game window
      g = (Graphics2D) bufferStrategy.getDrawGraphics();
      imgb.paintIcon(panel,g,0,0);
      render(g, arr);
      g.dispose();
      bufferStrategy.show();
   }
<<<<<<< HEAD
   
   /**
    * Rewrite this method for your game
    */
   public void render(Graphics2D g, Planet[] arr){
      
      for(int i = 0; i < arr.length; i++) {
         try{
            displayPlanets(i,g);
         }
         catch(Exception e){System.out.println("Could not Display " + arr[i].getImagePath());}
      }
      for(int i = 0; i < arr.length; i++)
      {
         g.setColor(Color.white);
         if(arr[i].myPlayer.isMe(currentPlayer()))
         g.drawOval(arr[i].myX,arr[i].myY, (int)arr[i].Radius, (int)arr[i].Radius);

         else {
            g.setColor(Color.black);
            g.drawOval(arr[i].myX,arr[i].myY, (int)arr[i].Radius, (int)arr[i].Radius);
         }
          arr[i].displayShips(g, arr[i]);

      }
   }
   public static void main(String [] args){
      player p1 = new player(1, 1);
      player p2 = new player(2, 2);
      Game ex = new Game(p1,p2);

   }
   


=======
>>>>>>> 0c9f860365daadcfb2c52162d61c819f140871be
}
