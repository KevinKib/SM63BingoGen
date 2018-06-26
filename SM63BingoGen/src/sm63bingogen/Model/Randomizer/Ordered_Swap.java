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
 * 1 - Generate a random sheet, calculate its standard deviation
 * 2 - Calculate the sum of each line, take the lowest and highest one
 * 3 - Generate new sheets swapping each number of these columns between them
 * 4 - Calculate their standard deviation, take the sheet with the lowest one
 * 5 - Repeat from task 2 to 5 until the normal sheet is taken three
 *     times in a row.
 * 
 * To debug
 * @author Kévin
 */
public class Ordered_Swap implements GoalFiller {
    
    private final int NB_MAX_REPEATS = 3;
    private int nbRepeats;
    private ArrayList<Goal> currentLine;
    private ArrayList<Goal> lowestLine;
    private ArrayList<Goal> highestLine;
    private Double lineLabel;
    private Double lowestLabel;
    private Double highestLabel;
    private ArrayList<Goal> currentList;
    private ArrayList<Goal> bestSwappedList;
    
    public Ordered_Swap() {
        this.initialisation();
    }
    
    public final void initialisation() {
        this.nbRepeats = 0;
        this.currentList = new ArrayList<>();
        this.bestSwappedList = new ArrayList<>();
        this.currentLine = new ArrayList<>();
        this.lowestLine = new ArrayList<>();
        this.highestLine = new ArrayList<>();
        this.lineLabel = 0.0;
        this.lowestLabel = null;
        this.highestLabel = null; 
    }

    @Override
    public void fillGoalList(ArrayList<Goal> goalList, int nbExpectedGoals) {
        
        this.initialisation();
        
        // Generate a random sheet, calculate its standard deviation
        Random random = new Random();
        random.fillGoalList(currentList, nbExpectedGoals);
        
        while(nbRepeats < NB_MAX_REPEATS) {
            System.out.println(this.nbRepeats);
            
            // Calculate the sum of each line, take the lowest and highest one
            
            // For each row
            for (int i = 0; i < 5; i++) {
                currentLine.clear();
                
                // For each cell
                for (int j = 0; j < 5; j++) {
                    currentLine.add(currentList.get(5*j+i));
                    lineLabel += currentList.get(5*j+i).getLength();
                }
                refreshLabels();
            }
            // For each col
            for (int i = 0; i < 5; i++) {
                currentLine.clear();
                
                // For each cell
                for (int j = 0; j < 5; j++) {
                    currentLine.add(currentList.get(5*i+j));
                    lineLabel += currentList.get(5*i+j).getLength();
                }
                refreshLabels();
            }
            // TL-DR
            currentLine.clear();
            for (int i = 0; i < 5; i++) {
                currentLine.add(currentList.get(6*i));
                lineLabel += currentList.get(6*i).getLength();
            }
            refreshLabels();
            // DL-TR
            currentLine.clear();
            for (int i = 0; i < 5; i++) {
                currentLine.add(currentList.get(4*i+4));
                lineLabel += currentList.get(4*i+4).getLength();
            }
            refreshLabels();
            
            
            // We have the lowest and highest line. Now for standard deviation
            double originalStandardDeviation = standardDeviation(currentList);
            double bestStandardDeviation = originalStandardDeviation;
            this.bestSwappedList.addAll(currentList);
            
            System.out.println(lowestLine);
            System.out.println(highestLine);
            
            for (Goal goalLowest : lowestLine) {
                for (Goal goalHighest : highestLine) {
                    swap(goalLowest, goalHighest);
                    double standardDeviation = standardDeviation(currentList);
                    if (standardDeviation < bestStandardDeviation) {
                        this.bestSwappedList.clear();
                        this.bestSwappedList.addAll(currentList);
                    }
                    swap(goalHighest, goalLowest);
                }
            }
            
            if (bestStandardDeviation < originalStandardDeviation) {
                this.nbRepeats = 0;
            }
            else {
                this.nbRepeats++;
            }
            
            System.out.println("nbRepeats : "+this.nbRepeats);
        }
        
        // Return the result
        goalList.addAll(currentList);
        
    }
    
    private void swap(Goal goal1, Goal goal2) {
        int index1 = this.currentList.indexOf(goal1);
        int index2 = this.currentList.indexOf(goal2);
        
        Goal goalInt = this.currentList.get(this.currentList.indexOf(goal1));
        this.currentList.set(index1, goal2);
        this.currentList.set(index2, goalInt);
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
    
    private void refreshLabels() {
        // Lowest refresh
        if (lowestLabel == null) {
            lowestLabel = lineLabel;
            update(lowestLabel, lowestLine);
        }
        else if (lineLabel < lowestLabel) {
            lowestLabel = lineLabel;
            update(lowestLabel, lowestLine);
        }

        // Highest refresh
        if (highestLabel == null) {
            highestLabel = lineLabel;
            update(highestLabel, highestLine);
        }
        else if (lineLabel > highestLabel) {
            highestLabel = lineLabel;
            update(highestLabel, highestLine);
        }
        
        lineLabel = 0.0;
    }
    
    private void update(Double label, ArrayList<Goal> line) {
        line.clear();
        line.addAll(currentLine);
    }
    
}
