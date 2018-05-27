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

    /**
     * Unique instance of the BingoGen class.
     */
    private static BingoGen instance;
    
    /**
     * Goal list imported from the .txt file.
     */
    private ArrayList<Goal> goalList;
    
    /**
     * JSON seed object.
     */
    private JSONSeed seed;
    
    public ArrayList<Goal> getGoalList() {
        return goalList;
    }
    
    public JSONSeed getSeed() {
        return seed;
    }
    
    
    /**
     * Constructor of the BingoGen class.
     */
    private BingoGen() {
        this.goalList = new ArrayList<>();
        this.seed = new JSONSeed();
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
     * Generates a new seed.
     * @param linebreak Decides whether the seed will contain linebreaks or not.
     */
    public void generate() {
        
        // Import goals (only if it hasn't been done before)
        if (this.goalList.isEmpty()) {
           try {
            //get().importGoals("C:\\Users\\Kévin\\Documents\\GitHub\\SM63Hacks\\SM63BingoGen\\src\\sm63bingogen\\goalList.txt");
            get().importGoals(".\\goalList.txt");
            } catch(Exception e) {
                System.out.println(e);
            } 
        }
        
        this.seed.generate();
    }

    /**
     * Copies the current JSON-generated code to the clipboard.
     */
    public void copyToClipboard() {
        if (this.seed.isGenerated()) {
            TextTransfer textTransfer = new TextTransfer();
            textTransfer.setClipboardContents(this.seed.getSeed());
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
