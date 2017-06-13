/**
 * Created by bhatn on 5/19/2017.
 */

public class AIplayer extends player{

    public AIplayer(Planet[] p)
    {
        super(4, p.length);
    }

    public int getBenefits(String s)
    {
        return 0;
    }
    public void makeMove(Planet[] myPlanets)
    {
        for(int i = 0; i < myPlanets.length; i++)
        {
            myPlanets[i].AiConsole("build", myPlanets[i].Iron/2, myTier);
            myPlanets[i].AiConsole("research", myPlanets[i].Iron/2 -1, 0);

        }
    }
}
