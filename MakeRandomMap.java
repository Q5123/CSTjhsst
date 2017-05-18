import java.io.*;
public class MakeRandomMap
{
   private int tempx, tempy;
   public void makeDataFile() throws Exception
   {
      System.setOut(new PrintStream(new FileOutputStream("Map1.txt")));
      int numitems = (int)(Math.random() * 5 + 25);
      int[] array = new int[numitems*2];
      System.out.println(numitems);
      for(int k = 0; k < numitems*2; k+=2)
      {
         array[k] = (int)Math.random() * 1600;
         array[k+1] = (int)Math.random() * 900;
      }
   }
}