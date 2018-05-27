/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Singleton design pattern.
 * @author Kévin
 */
public class BingoGen {

    private static BingoGen instance;
    
    private ArrayList<Goal> goalList;
    private ArrayList<Goal> currentGoalList;
    
    /**
     * Constructor of the BingoGen class.
     */
    private BingoGen() {
        this.goalList = new ArrayList<>();
        this.currentGoalList = new ArrayList<>();
    }
    
    /**
     * Imports all the goals from a .txt file.
     * @param filename File path of the .txt file.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void importGoals(String filename) throws FileNotFoundException, IOException{
        this.goalList.clear();
        
        // Code to read a small file
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while (line != null) {
                this.goalList.add(new Goal(line));
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            
        } finally {
            br.close();
        }
        
        
    }
    
    /**
     * Fills the currentGoalList array with 25 new goals.
     */
    private void fillCurrentGoalArray() {
        int nbGoals = 0;
        
        // Has to give 25 goals
        while (nbGoals < 25) {
            
            // Gives a value in the range of 0 and goalList.size()
            int randIndex = (int) Math.floor(Math.random() * (this.goalList.size()));
            
            /*
             * Manages a potential exception that would happen if Math.random() returned 1.
             * In that case randIndex would be equal to the goalList size,
             * which we'll call n.
             * Considering the goalList values are indexed between 0 to n-1,
             * having randIndex = n would cause an IndexOutOfBounds exception.
             */
            if (randIndex == this.goalList.size()) {
                randIndex -= 1;
                System.out.println("This exception never happens !");
            }
            
            Goal newGoal = this.goalList.get(randIndex);
            
            if(!this.currentGoalList.contains(newGoal)) {
                this.currentGoalList.add(newGoal);
                nbGoals++;
            }
        }
        
        assert (nbGoals == 25);
    }
    
    /**
     * Generates a new JSON seed.
     * @param linebreak Decides if the seed will include linebreaks or not. (Linebreaks are used for the display in text areas)
     * @return A functional JSON seed for Bingosync.
     */
    public String generate(boolean linebreak) {
        
        // Import goals (only if it hasn't been done before)
        if (this.goalList.isEmpty()) {
           try {
            //BingoGen.get().importGoals("C:\\Users\\Kévin\\Documents\\GitHub\\SM63Hacks\\SM63BingoGen\\src\\sm63bingogen\\goalList.txt");
            BingoGen.get().importGoals(".\\goalList.txt");
            } catch(Exception e) {
                System.out.println(e);
            } 
        }
        
        // If the code comes from the "Generate JSON" button, reinitiate the goal array.
        // Will be rewritten
        if (linebreak) {
            this.currentGoalList.clear();
            this.fillCurrentGoalArray();
        }
        
        // JSON initiation
        String res = "[ ";
        
        // Goal loop
        for (Goal goal : this.currentGoalList) {
            res += goal;
            
            // Character separation
            if (linebreak) res += ", \n";
            else res += ", ";
        }
        
        /* We remove the two last characters,
           which are a comma and a space.
           We also remove the linebreak if there was one. */
        if (linebreak)  res = res.substring(0, res.length() - 3);
        else            res = res.substring(0, res.length() - 2);
        
        res += " ]";
        
        return res;
    }
    
    /**
     * Copies the current JSON-generated code to the clipboard.
     */
    public void copyToClipboard() {
        if (!this.currentGoalList.isEmpty()) {
            TextTransfer textTransfer = new TextTransfer();
            textTransfer.setClipboardContents(this.generate(false));
            System.out.println("Copied to clipboard !");
        }
        else {
            // No JSON has been generated yet, so we display an error.
            System.out.println("No generated JSON.");
        }
        
    }
    
    /**
     * Returns the instance of the BingoGen class.
     * There can only be one instance at a time, so this method
     * only creates an instance if none already exists.
     * @return The unique instance of the BingoGen class.
     */
    public static BingoGen get() {
        if (instance == null) {
            instance = new BingoGen();
        }
        return instance;
    }
    
}
