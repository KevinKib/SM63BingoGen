/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen.Model;

import sm63bingogen.Model.JsonSeed;
import sm63bingogen.Model.Goal;
import sm63bingogen.Exceptions.NoGeneratedJsonException;
import sm63bingogen.Exceptions.NoImportedFileException;
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

    // Unique instance of the BingoGen class.
    private static BingoGen instance;
    // Goal list imported from the .txt file.
    private ArrayList<Goal> goalList;
    // JSON jsonSeed object.
    private JsonSeed jsonSeed;
    // Path of the goalList file.
    private String filepath;
    
    
    /**
     * Constructor of the BingoGen class.
     */
    private BingoGen() {
        this.goalList = new ArrayList<>();
        this.jsonSeed = new JsonSeed();
        this.filepath = "";
    }
    
    /**
     * Imports all the goals from a .txt file.
     * @param filename File path of the .txt file.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void importGoals(String filename) throws FileNotFoundException, IOException {
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
     * Generates a new jsonSeed.
     * @throws sm63bingogen.Exceptions.NoImportedFileException
     */
    public void generate() throws NoImportedFileException {
        
        // Import goals (only if it hasn't been done before)
        if (this.goalList.isEmpty()) {
            try {
                get().importGoals(this.filepath);
            } catch(FileNotFoundException e) {
                throw new NoImportedFileException();
            } catch(IOException e) {
                System.out.println(e);
            }
        }
        
        if (!this.goalList.isEmpty()) {
            this.jsonSeed.generate();
        }
        else {
            throw new NoImportedFileException();
        }
        
    }

    /**
     * Copies the current JSON-generated code to the clipboard.
     * @throws sm63bingogen.Exceptions.NoGeneratedJsonException
     */
    public void copyToClipboard() throws NoGeneratedJsonException{
        if (this.jsonSeed.isGenerated()) {
            TextTransfer textTransfer = new TextTransfer();
            textTransfer.setClipboardContents(this.jsonSeed.getSeed());
        }
        else {
            // No JSON has been generated yet, so we display an error.
            throw new NoGeneratedJsonException();
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
    
    
    
    public ArrayList<Goal> getGoalList() {
        return goalList;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    
    public JsonSeed getJsonSeed() {
        return jsonSeed;
    }
    
}
