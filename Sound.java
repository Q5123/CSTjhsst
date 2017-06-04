/**
 * Created by bhatn on 6/3/2017.
 */
import sun.audio.*;
import java.io.*;
public class Sound implements Runnable {
    public void music()
    {
        AudioPlayer Pl = AudioPlayer.player;
        AudioStream BGM = null;
        AudioData D = null;

        ContinuousAudioDataStream loop = null;
        try {
            BGM = new AudioStream(new FileInputStream("C:\Users\bhatn\CSTjhsst\NGo.mp3"));
            D = BGM.getData();
        }

        catch(FileNotFoundException e)
        {
            System.out.println("not found");
        }

        catch(java.io.IOException e){ System.out.println("not found");}
        Pl.start(loop);


    }

    public void run()
    {
//        try {
            music();
//        }
//        catch(Exception e){System.out.println("not working");}
    }

    public static void main(String[] args)
    {
        new Sound().music();
    }
}
