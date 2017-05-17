import java.io.*;
public class MakeRandomMap
{
   private int tempx, tempy;
   public void makeDataFile()
   {
      System.setOut(new PrintStream(new FileOutputStream("Map1.txt")));
      int numitems = (int)(Math.random() * 5 + 25);
      int[] arrayX = new int[numitems];
      int[] arrayY = new int[numitems];
      System.out.println(numitems);
      for(int k = 0; k < numitems*2; k++)
      {
         do{
            int temp;
         }while();
      }
   }
}