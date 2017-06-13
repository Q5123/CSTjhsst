//import necessary packages
import java.awt.*;
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
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import javax.swing.event.*;
import pagelayout.*;
import pagelayout.util.*;
import static pagelayout.EasyCell.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class start extends JPanel {
    //fields
    JTextField RaceChoice;
    JTextField RaceChoice2;
        protected JLabel jLabel13;
        protected JTextField jTextField1;
        protected JLabel jLabel14;
        protected JTextField jTextField2;
        protected JButton jButton4;
        protected JTextArea jTextArea1;
        protected JTextArea jTextArea3;
        protected JTabbedPane tabbedPane6$Tab;
        protected CellGrid grid4;
        protected TabbedPaneCell tabbedPane6;
        protected Column root;
        PageLayout layout;
        
        //convenience method to create the object Start.
        public static start createGUIObject()
        {
            return new start();
        }

        protected void createComponents() //creates labels, buttons, and text fields in the startup screen
        {
            jLabel13 = new javax.swing.JLabel(); 
            jLabel13.setText("Race P1");

            jTextField1 = new javax.swing.JTextField(); //place where p1 chooses their race
            jTextField1.setText("Cybermen");
            setDimensions(jTextField1, 75, 6, 23, 32767, 23, 655, 23);

            jLabel14 = new javax.swing.JLabel();
            jLabel14.setText("Race P2");

            jTextField2 = new javax.swing.JTextField(); //place where p2 chooses their race
            jTextField2.setText("Human");
            setDimensions(jTextField2, 75, 6, 23, 32767, 23, 655, 23);

            jButton4 = new javax.swing.JButton(); //start game button
            jButton4.setText("Start");
            //explains bonuses of each race
            jTextArea1 = new javax.swing.JTextArea();
            jTextArea1.setText("Dalek: Atk bonus  Cybermen: Resource bonus Human: Research bonus Jawa: Not meant to be played by a human, meant for testing");
            setTADimensions(jTextArea1, 17, 58, 246, 115, 32767, 32767, 726, 330);
            //rules for the game
            jTextArea3 = new javax.swing.JTextArea();
            jTextArea3.setText("If a planet is surrounded by a white sircle it isn't your planet, if its sorouned by a  black circle then it is. If the circle is red then it is out of turns. Commands can be givin to each planet by clicking on the icon. There are four commands, build, atk, research, and terraform. Build, atk, and research are self explanatory, terraform will provide extra Iron on each planet and will allow building. Iron is needed to build and research. Once you have enough Iron you will advance a research tier. While higher tier ships require more Iron they have more atk power. Planets can only support so many ships.");
            setTADimensions(jTextArea3, 17, 58, 246, 115, 32767, 32767, 726, 330);

            tabbedPane6$Tab = new javax.swing.JTabbedPane();
            setDimensions(tabbedPane6$Tab ,267, 159, 32767, 32767, 747, 374);

            decorateComponents();
        }

        protected void createCells() //crates the grid that the components will go into
        {
            grid4 = grid(jLabel13, jTextField1, eol(),
                    jLabel14, jTextField2, eol(),
                    jButton4, skip());

            tabbedPane6 = new TabbedPaneCell( tabbedPane6$Tab);
            tabbedPane6.add("Game Starter",grid4);
            tabbedPane6.add("Races",jTextArea1);
            tabbedPane6.add("Basics",jTextArea3);

            root = column(none, none,  tabbedPane6);

            setConstraints();
        }
        //dummy actions for jtextfields because we dont need the listeners
        public void jTextField1TextEntered(ActionEvent e)
        {
            System.out.println("Text has been entered in jTextField1.");
        }
        public void jTextField2TextEntered(ActionEvent e)
        {
            System.out.println("Text has been entered in jTextField2.");
        }
        //start button is clicked
        public void jButton4Clicked(ActionEvent e)
        {
            //gives player a random number of planets that is not even
            int a = (int)(Math.random() * 12 + 1); 
            int b = (int)(Math.random() * 12 + 1);
            while(b == a)
            {
                b = (int)(Math.random() * 12 + 1);
            }
            player p1 = new player(a , getRaceChoice(jTextField2));
            player p2 = new player(b , getRaceChoice(jTextField1));
            
            Game ex = new Game(p1,p2); //instatiates game
            new Thread(ex).start();
        }

        protected void decorateComponents()
        {
            jTextField1.addActionListener(
                    new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            jTextField1TextEntered(e);
                        }
                    });
            jTextField2.addActionListener(
                    new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            jTextField2TextEntered(e);
                        }
                    });
            jButton4.addActionListener(
                    new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            jButton4Clicked(e);
                        }
                    });
        }
        //creating the layout for a given container
        public void createGUI(Container container)
        {
            createComponents();
            createCells();
            layout=root.createLayout(container);
        }
        //provides an alternative way to create the GUI on a JPanel. It returns a JPanel containing the GUI.
        public JPanel getJPanel()
        {
            JPanel p=new JPanel();
            createGUI(p);
            return p;
        }
        //used for testing
        public static void testGUI()
        {
            JFrame f=new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            createGUIObject().createGUI(f.getContentPane());
            f.pack();
            Dimension d=f.getPreferredSize();
            f.setSize(new Dimension(d.width,d.height));
            f.setVisible(true);
        }
        public static void main(String[] args)
        {
            SwingUtilities.invokeLater(
                    new Runnable(){public void run(){testGUI();}});
        }
        //turns the race chosen into an argument the player class can recognize
        public int getRaceChoice(JTextField j)
        {
            String s = j.getText();
            switch(s){
                case "Cybermen": return 1;
                case "Dalek": return 2;
                case "Human": return 3;
                default: return 4;
            }
        }  
        public class Listener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                int a = (int)(Math.random() * 12 + 1);
                int b = (int)(Math.random() * 12 + 1);
                while(b == a)
                {
                    b = (int)(Math.random() * 12 + 1);
                }
                player p1 = new player(a , getRaceChoice(jTextField2));
                player p2 = new player(b , getRaceChoice(jTextField1));

                Game ex = new Game(p1,p2);
                new Thread(ex).start();
            }
        }
}
