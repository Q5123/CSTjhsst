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
/*    public start()
//    {new GridLayout(2,3)
    {
        JTabbedPane tabs = new JTabbedPane();
        JPanel tab1 = new JPanel(new GridLayout(4,2));
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
        tab1.add(starting);
        add(tabs);


    }*/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    /**
     *
     * @author bhatn
     */

        /**
         * Creates new form Started
         */
        public start() {
            initComponents();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {

            jScrollPane1 = new javax.swing.JScrollPane();
            jTextPane1 = new javax.swing.JTextPane();
            jTabbedPane2 = new javax.swing.JTabbedPane();
            jPanel1 = new javax.swing.JPanel();
            jTextField2 = new javax.swing.JTextField();
            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            jTextField1 = new javax.swing.JTextField();
            jButton1 = new javax.swing.JButton();
            jPanel2 = new javax.swing.JPanel();
            jScrollPane2 = new javax.swing.JScrollPane();
            jTextArea1 = new javax.swing.JTextArea();
            jPanel3 = new javax.swing.JPanel();
            jScrollPane3 = new javax.swing.JScrollPane();
            jTextArea2 = new javax.swing.JTextArea();

            jTextPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
            jScrollPane1.setViewportView(jTextPane1);

            jTextField2.setText("Cybermen");

            jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setText("Choose player 1 Race");

            jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel2.setText("Choose player 2 Race");

            jTextField1.setText("Human");

            jButton1.setLabel("Start");
            jButton1.addActionListener(new Listener());


            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                            .addComponent(jTextField1))
                                    .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(250, 256, 262)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(147, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(2, 2, 2)
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(38, 38, 38)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(49, Short.MAX_VALUE))
            );
            
            jTabbedPane2.addTab("Start", jPanel1);

            jTextArea1.setColumns(20);
            jTextArea1.setRows(5);
            jTextArea1.setText("Dalek : increased atk and def\n\nHuman : increased Rsh efficiency\n\nCybermen : increased build efficiency\n\nJawas: Any species without intergalactic travel\nNo benefits");
            jScrollPane2.setViewportView(jTextArea1);

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                                    .addContainerGap())
            );
            jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(84, Short.MAX_VALUE))
            );

            jTabbedPane2.addTab("Races", jPanel2);

            jTextArea2.setColumns(20);
            jTextArea2.setRows(5);
            jTextArea2.setText("Commands :\n \natk - to attack\n\nbuild - to build\n\ntransfer - to transfer");
            jScrollPane3.setViewportView(jTextArea2);

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
            );
            jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
            );

            jTabbedPane2.addTab("Commands", jPanel3);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane2)
            );
        }// </editor-fold>





        // Variables declaration - do not modify
        private javax.swing.JButton jButton1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JScrollPane jScrollPane3;
        private javax.swing.JTabbedPane jTabbedPane2;
        private javax.swing.JTextArea jTextArea1;
        private javax.swing.JTextArea jTextArea2;
        private javax.swing.JTextField jTextField1;
        private javax.swing.JTextField jTextField2;
        private javax.swing.JTextPane jTextPane1;
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
            Sound s = new Sound();
            new Thread(s).start();
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
        frame.setSize(640,640);
        frame.setLocation(200,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new start());
        frame.setVisible(true);
    }





}