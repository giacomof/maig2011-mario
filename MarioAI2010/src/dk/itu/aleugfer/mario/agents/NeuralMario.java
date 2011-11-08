
package dk.itu.aleugfer.mario.agents;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;

import java.util.Arrays;
import java.util.Random;

public class NeuralMario extends BasicMarioAIAgent implements Agent
{
    public NeuralMario()
    {
        super("NeuralMario");
        reset();
    }

    private Random R = null;
    
    /*final*/
    protected byte[][] levelScene;
    /*final */
    protected byte[][] enemies;

    public void reset()
    {
        action = new boolean[Environment.numberOfKeys];
        action[Mario.KEY_RIGHT] = true;
        action[Mario.KEY_SPEED] = true;
    }

    public boolean[] getAction()
    {

        
        for (int i = 0; i < mergedObservation.length; i++) {
            System.out.println(Arrays.toString(mergedObservation[i]));
        }
                    
                    
                    
                    
        action[Mario.KEY_SPEED] = action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
            return action;
    }
    
    
    public void integrateObservation(Environment environment) {
        
        int zLevelScene = 1;
        int zLevelEnemies = 0;
        
        levelScene = environment.getLevelSceneObservationZ(zLevelScene);
        enemies = environment.getEnemiesObservationZ(zLevelEnemies);
        mergedObservation = environment.getMergedObservationZZ(1, 0);

        this.marioFloatPos = environment.getMarioFloatPos();
        this.enemiesFloatPos = environment.getEnemiesFloatPos();
        this.marioState = environment.getMarioState();

        receptiveFieldWidth = environment.getReceptiveFieldWidth();
        receptiveFieldHeight = environment.getReceptiveFieldHeight();

        // It also possible to use direct methods from Environment interface.
        //
        marioStatus = marioState[0];
        marioMode = marioState[1];
        isMarioOnGround = marioState[2] == 1;
        isMarioAbleToJump = marioState[3] == 1;
        isMarioAbleToShoot = marioState[4] == 1;
        isMarioCarrying = marioState[5] == 1;
        getKillsTotal = marioState[6];
        getKillsByFire = marioState[7];
        getKillsByStomp = marioState[8];
        getKillsByShell = marioState[9];
    }

}
