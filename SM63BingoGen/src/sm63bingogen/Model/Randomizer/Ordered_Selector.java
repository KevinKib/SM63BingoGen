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
 * The main principle of this class is to :
 * 1 - Generate an arbitrary number of random seeds
 * 2 - Calculate the sum of the length of the goals for any valid line
 *     ( rows, columns, diagonals)
 * 3 - Calculate the standard deviation of these sums
 * 4 - Pick the seed with the lowest standard deviation
 * @author Kévin
 */
public class Ordered_Selector implements GoalFiller {
    
    private final int NB_REPEATS = 5;

    @Override
    public void fillGoalList(ArrayList<Goal> goalList, int nbExpectedGoals) {
        
        // Generate a list of 25 goals that will stay fixed
        Random random = new Random();
        
        ArrayList<Goal> currentGoals = new ArrayList<>();
        random.fillGoalList(currentGoals, nbExpectedGoals);
        
        ArrayList<Goal> currentList = new ArrayList<>();
        ArrayList<Goal> calculatedList = new ArrayList<>();
        double bestStandardDeviation = -1;
        
        
        // Fix the excluded goal exploit (re-use the same goals everytime)
        
        for (int i = 0; i < NB_REPEATS; i++) {
            // Fill the current list
            currentList.clear();
            random.fillGoalList(currentList, nbExpectedGoals);
            
            // Calculate standard deviation
            double standardDeviation = standardDeviation(currentList);
            
            if (i == 0 || standardDeviation < bestStandardDeviation) {
                calculatedList.clear();
                calculatedList.addAll(currentList);
                bestStandardDeviation = standardDeviation;
            }
        }
        
        goalList.addAll(calculatedList);
        
    }
    
    private double standardDeviation(ArrayList<Goal> goalList) {
        // Calculate the average difficulty
        double avg = 0;
        double sum = 0;
        for(Goal goal : goalList) {
            sum += goal.getLength();
        }
        avg = sum/goalList.size();
        
        // Subtract, pow
        sum = 0;
        double goalLength;
        
        for (Goal goal : goalList) {
            goalLength = goal.getLength() - avg;
            goalLength = Math.pow(goalLength, 2);
            sum += goalLength;
        }
        
        // Calculate standard deviation
        double standardDeviation = Math.sqrt(sum/goalList.size());
        
        return standardDeviation;
    }
    
}
