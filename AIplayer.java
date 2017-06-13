/**
 * Created by bhatn on 5/19/2017.
 */

public class AIplayer extends player{

    public AIplayer(Planet[] p)
    {
        super(4, p.length);
    }
    public int turns = 0;


    /**
     * Description...
     *
     * @param s = String
     * @return always returns 0
     */
    public int getBenefits(String s)//Since Jawa has no benefits getbenefits is always 0
    {
        return 0;
    }
    /**
     * Description Controls Aiplayers Ai
     *
     * @param myPlanets = array of planets
     * @return void
     */
    public void makeMove(Planet[] myPlanets) //Adds one Iron to research and one Iron to building for each planet everyturn ensuring the games difficulty increases.
    {

            for (int i = 0; i < myPlanets.length; i++) {
                turns++;

                if (myPlanets[i].myPlayer.isMe(this)) {
                    myPlanets[i].AiConsole("build", turns, myTier);
                    myPlanets[i].AiConsole("research", turns, 0);

                }

            }
        }
    }
