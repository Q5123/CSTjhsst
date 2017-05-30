import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.audio.*;
   import java.io.*;
public class Music
{
   public String myPath;
   public AudioInputStream dankStream;
   public Clip clip;
   public FileInputStream input;
   public Music(String path)
   {
      myPath = path;
      try
      {
         input = new FileInputStream("./"+ path);
         clip = AudioSystem.getClip();
         dankStream = AudioSystem.getAudioInputStream(input);
         clip.open(dankStream);
      }
      catch(Exception e)
      {
         System.out.println(e.getMessage());
      }
   }        
   public void play()
   {
      clip.start();
   }
   public void loop()
   {
      clip.loop(666);
   }
}