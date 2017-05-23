import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
public class Glotfelty extends JPanel
{
   JPanel panel;
   JButton button;
   JTextArea area;
   public Glotfelty()
   {
      panel = new JPanel();
      button = new JButton("grade here");
      area = new JTextArea();
      button.addActionListener(new Listener());
      area.setPreferredSize(new Dimension(300,300));
      add(panel);
      panel.add(area);
      panel.add(button);
      
   
   }
   
   public class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         area.setText(area.getText() + "                                                      " +  "\n"  + " not apa format, why does this matter, what " +  "\n"  + " does this all mean, GRAMMMER, did " +  "\n"  + " you even read the book, this" +  "\n"  +   "hypothisis is hypothtical, fuck you 1/10");
      
      
      
      }
   
   
   }
   
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Glofelty Grading");
      frame.setSize(640,640);
      frame.setLocation(200,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Glotfelty());
        frame.setVisible(true);

   
   }
}