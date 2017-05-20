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

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
public class start extends JPanel {
    JTextField RaceChoice;
    JTextField RaceChoice2;
    public start()
//    {new GridLayout(2,3)
    {
        JTabbedPane tabs = new JTabbedPane();
        JPanel tab1 = new JPanel(new GridLayout(3,2));
        tab1.setPreferredSize(new Dimension(640,640));
        JLabel Welcome = new JLabel("Welcome");
        JLabel gamename = new JLabel("GameName");
        JLabel Race = new JLabel("What Species would you like to be? \n (look at Species tab for more info) player 1");
        RaceChoice = new JTextField("\t");
        JLabel Race2 = new JLabel("What Species would you like to be? \n (look at Species tab for more info) player 1");
        RaceChoice2 = new JTextField("\t");
        JButton starting = new JButton("Start");
        starting.addActionListener(new Listener());
        Welcome.setFont(new Font(Font.SERIF, 1, 24));

        tabs.add("tab1", tab1);
        tab1.add(Welcome );
        tab1.add(gamename );
        tab1.add(Race );
        tab1.add(RaceChoice);
        tab1.add(Race2);
        tab1.add(RaceChoice2);
        add(tabs);


    }
    public class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int a = (int)(Math.random() * 12 + 1);
            int b = (int)(Math.random() * 12 + 1);
            while(b == a)
            {
                b = (int)(Math.random() * 12 + 1);
            }
            player p1 = new player(getRaceChoice(RaceChoice), a);
            player p2 = new player(getRaceChoice(RaceChoice2), b);

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

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Hello Button");
        frame.setSize(200,100);
        frame.setLocation(200,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new start());
        frame.setVisible(true);
    }





}