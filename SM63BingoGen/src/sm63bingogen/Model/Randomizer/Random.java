/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen.Model.Randomizer;

import java.util.ArrayList;
import sm63bingogen.Model.BingoGen;
import sm63bingogen.Model.Goal;

/**
 *
 * @author Kévin
 */
public class Random implements GoalFiller {

    @Override
    public void fillGoalList(ArrayList<Goal> goalList, int nbExpectedGoals) {
        int nbGoals = 0;
        
        // Has to give x number of goals
        while (nbGoals < nbExpectedGoals) {
            
            // Gives a value in the range of 0 and goalList.size()
            int randIndex = (int) Math.floor(Math.random() * (BingoGen.get().getGoalList().size()));
            
            Goal newGoal = BingoGen.get().getGoalList().get(randIndex);
            
            if(!goalList.contains(newGoal)) {
                goalList.add(newGoal);
                nbGoals++;
            }
        }
        
        assert (nbGoals == nbExpectedGoals);
    }
    
}
