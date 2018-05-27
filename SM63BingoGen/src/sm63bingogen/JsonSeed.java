/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen;

import java.util.ArrayList;

/**
 *
 * @author Kévin
 */
public class JsonSeed {
    
    /**
     * ArrayList that contains the goals of the JsonSeed.
     */
    private ArrayList<Goal> goalList;
    /**
     * Functional JSON seed for BingoSync.
     */
    private String seed;
    /**
     * JSON seed with linebreaks used for display.
     */
    private String seedLinebreak;
    
    private boolean isGenerated;
    
    public ArrayList<Goal> getGoalList() {
        return goalList;
    }
    
    public String getSeed() {
        return seed;
    }
    
    public String getSeedLinebreak() {
        return seedLinebreak;
    }
    
    public boolean isGenerated() {
        return isGenerated;
    }
    
    public JsonSeed() {
        this.goalList = new ArrayList<>();
        this.seed = "";
        this.seedLinebreak = "";
        this.isGenerated = false;
    }

    /**
     * Fills the goalList array with 25 new goals.
     */
    private void fillGoalList() {
        int nbGoals = 0;
        
        // Has to give 25 goals
        while (nbGoals < 25) {
            
            // Gives a value in the range of 0 and goalList.size()
            int randIndex = (int) Math.floor(Math.random() * (BingoGen.get().getGoalList().size()));
            
            /*
             * Manages a potential exception that would happen if Math.random() returned 1.
             * In that case randIndex would be equal to the goalList size,
             * which we'll call n.
             * Considering the goalList values are indexed between 0 to n-1,
             * having randIndex = n would cause an IndexOutOfBounds exception.
             */
            if (randIndex == BingoGen.get().getGoalList().size()) {
                randIndex -= 1;
            }
            
            Goal newGoal = BingoGen.get().getGoalList().get(randIndex);
            
            if(!this.goalList.contains(newGoal)) {
                this.goalList.add(newGoal);
                nbGoals++;
            }
        }
        
        assert (nbGoals == 25);
    }
    
    /**
     * Generates a new JSON seed.
     */
    public void generate() {
        
        // If the code comes from the "Generate JSON" button, reinitiate the goal array.
        // Will be rewritten
        this.goalList.clear();
        this.fillGoalList();
        
        // JSON initiation
        this.seed = "[ ";
        this.seedLinebreak = this.seed;
        
        // Goal loop
        for (Goal goal : this.goalList) {
            this.seed += goal;
            this.seedLinebreak += goal;
            
            // Character separation
            this.seed += ", ";
            this.seedLinebreak += ", \n";
        }
        
        /* We remove the two last characters,
           which are a comma and a space.
           We also remove the linebreak if there was one. */
        this.seed = this.seed.substring(0, this.seed.length() - 2);
        this.seedLinebreak = this.seedLinebreak.substring(0, this.seedLinebreak.length() - 3);
        
        this.seed += " ]";
        this.seedLinebreak += " ]";
        
        this.isGenerated = true;
    }
    
}
