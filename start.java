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
    JTextField RaceChoice;
    JTextField RaceChoice2;

        // Variables declaration - do not modify




    /*
       Generated code.
       ---------------
       This class is best used in applications by
       extending it, and overriding the method
       'decorateComponents' in the subclass.
       All the EventListeners should be added
       to the various components in the over-ridden
       method 'docorateComponents'.


       Once the subclass has been appropriately
       defined and compiled, it can be used by
       constructing a object of the derived subclass
       and calling the 'createGUI(Container c)'
       method of the object.

       Alternatively, (new Start()).getJPanel()
       may be used to create a JPanel containing the GUI.

       For an example, see the 'testGUI' method of
       of this class.

       A quick test without the need to write any code
       can be performed by compiling and
       running this class by the following shell commands

    > javac -classpath pagelayoutX.XX.jar;. Start.java
    > java -classpath pagelayoutX.XX.jar;. Start

       Here 'pagelayoutX.XX.jar is the appropriate
       jar file for the pagelayout layout manager
       of version 1.14 or later.
    */
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
        /*
           This is just a convenience method
           to create the object Start.
        */
        public static start createGUIObject()
        {
            return new start();
        }
        /*
           Normally this  method does not need
           to be modified.
        */
        protected void createComponents()
        {

            jLabel13 = new javax.swing.JLabel();
            jLabel13.setText("Race P1");

            jTextField1 = new javax.swing.JTextField();
            jTextField1.setText("Cybermen");
            setDimensions(jTextField1, 75, 6, 23, 32767, 23, 655, 23);

            jLabel14 = new javax.swing.JLabel();
            jLabel14.setText("Race P2");

            jTextField2 = new javax.swing.JTextField();
            jTextField2.setText("Human");
            setDimensions(jTextField2, 75, 6, 23, 32767, 23, 655, 23);

            jButton4 = new javax.swing.JButton();
            jButton4.setText("Start");

            jTextArea1 = new javax.swing.JTextArea();
            jTextArea1.setText("Dalek: Atk bonus  Cybermen: Resource bonus Human: Research bonus Jawa: Not meant to be played by a human, meant for testing");
            setTADimensions(jTextArea1, 17, 58, 246, 115, 32767, 32767, 726, 330);

            jTextArea3 = new javax.swing.JTextArea();
            jTextArea3.setText("If a planet is surrounded by a white sircle it isn't your planet, if its sorouned by a  black circle then it is. If the circle is red then it is out of turns. Commands can be givin to each planet by clicking on the icon. There are four commands, build, atk, research, and terraform. Build, atk, and research are self explanatory, terraform will provide extra Iron on each planet and will allow building. Iron is needed to build and research. Once you have enough Iron you will advance a research tier. While higher tier ships require more Iron they have more atk power. Planets can only support so many ships.");
            setTADimensions(jTextArea3, 17, 58, 246, 115, 32767, 32767, 726, 330);

            tabbedPane6$Tab = new javax.swing.JTabbedPane();
            setDimensions(tabbedPane6$Tab ,267, 159, 32767, 32767, 747, 374);

            decorateComponents();

        }
        /*
           Normally this  method does not need
           to be modified.
        */
        protected void createCells()
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

        /*
           The following 'dummy action' methods should
           to be modified for the application.
        */
/*
   A dummy action for jTextField1.
*/
        public void jTextField1TextEntered(ActionEvent e)
        {
            System.out.println("Text has been entered in jTextField1.");
        }

        /*
           A dummy action for jTextField2.
        */
        public void jTextField2TextEntered(ActionEvent e)
        {
            System.out.println("Text has been entered in jTextField2.");
        }

        /*
           A dummy action for jButton4.
        */
        public void jButton4Clicked(ActionEvent e)
        {
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

        /*
           Normally this  method does not need
           to be modified.
        */
        private void setConstraints()
        {
        }
        /*
           Override this  method in a subclass
           to add your own event listeners.
           Here actionListeners have been added to each
           button to print a message when the button
           is clicked.
        */
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
        /*
           This method should be called for creating
           the layout for a given container.
        */
        public void createGUI(Container container)
        {
            createComponents();
            createCells();
            layout=root.createLayout(container);
        }
        /*
           This method provides an alternative way to
           create the GUI on a JPanel.
           It returns a JPanel containing the GUI.
        */
        public JPanel getJPanel()
        {
            JPanel p=new JPanel();
            createGUI(p);
            return p;
        }
        /*
           This method may be called for testing the
           generated code. It should not require
           the generated code to be modified in any way.
        */
        public static void testGUI()
        {
            JFrame f=new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            createGUIObject().createGUI(f.getContentPane());
            //Alternatively, use
            //f.add(createGUIObject().getJPanel())
            f.pack();
            Dimension d=f.getPreferredSize();
            f.setSize(new Dimension(d.width,d.height));
            f.setVisible(true);
        }
        /*
           This calls the method for testing the
           generated code. It should not require
           the generated code to be modified in any way.
        */
        public static void main(String[] args)
        {
            SwingUtilities.invokeLater(
                    new Runnable(){public void run(){testGUI();}});
        }




    // End of variables declaration

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







}