/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen.Model;

import java.util.ArrayList;
import sm63bingogen.Model.Randomizer.*;

/**
 *
 * @author Kévin
 */
public class JsonSeed {
    
    // Constant that indicates the number of goals in a seed.
    private final int NB_GOALS = 25;
    
    // ArrayList that contains the goals of the JsonSeed.
    private ArrayList<Goal> goalList;
    
    //Functional JSON seed for BingoSync.
    private String seed;
    
    // JSON seed with linebreaks used for display.
    private String seedLinebreak;
    
    // Boolean that checks if a seed has been generated.
    private boolean isGenerated;
    
    private GoalFiller goalFiller;
    
    public JsonSeed() {
        this.goalList = new ArrayList<>();
        this.seed = "";
        this.seedLinebreak = "";
        this.isGenerated = false;
        this.goalFiller = new Ordered_Swap();
    }
    
    /**
     * Generates a new JSON seed.
     */
    public void generate() {
        
        // Reinitiate the goal array.
        this.goalList.clear();
        this.goalFiller.fillGoalList(this.goalList, NB_GOALS);
        
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
    
}
