/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen.Model.Randomizer;

import java.util.ArrayList;
import sm63bingogen.Model.Goal;

/**
 *
 * @author Kévin
 */
public interface GoalFiller {
    
    /**
     * Fills the goalList array with 25 new goals.
     * The goalList is empty when passed.
     */
    void fillGoalList(ArrayList<Goal> goalList, int nbExpectedGoals);
    
}
