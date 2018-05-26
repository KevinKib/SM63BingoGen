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
 *
 * @author Kévin
 */
public class BingoGen {

    private static BingoGen instance;
    
    private ArrayList<Goal> goalList;
    private ArrayList<Goal> currentGoalList;
    
    
    private BingoGen() {
        this.goalList = new ArrayList<>();
        this.currentGoalList = new ArrayList<>();
    }
    
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
            String everything = sb.toString();
            //System.out.println(everything);
        } finally {
            br.close();
        }
        
        
    }
    
    private void fillCurrentGoalArray() {
        int goalNum = 0;
        
        // Has to give 25 goals
        while (goalNum < 25) {
            
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
                goalNum++;
            }
        }
        
        assert (goalNum == 25);
    }
    
    public String generate(boolean linebreak) {
        
        try {
            BingoGen.get().importGoals("\\Users\\Kévin\\Documents\\NetBeansProjects\\SM63BingoGen\\src\\sm63bingogen\\goalList.txt");
            //BingoGen.get().importGoals("./goalList.txt");
        } catch(Exception e) {
            System.out.println(e);
        }
        
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
           which are a comma and a space */
        if (linebreak)  res = res.substring(0, res.length() - 3);
        else            res = res.substring(0, res.length() - 2);
        
        res += " ]";
        
        return res;
    }
    
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
    
    public static BingoGen get() {
        if (instance == null) {
            instance = new BingoGen();
        }
        return instance;
    }
    
}
