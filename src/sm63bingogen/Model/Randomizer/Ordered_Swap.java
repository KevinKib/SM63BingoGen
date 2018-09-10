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
    
    private final int NB_MAX_REPEATS = 1;
    private int nbRepeats;
    private ArrayList<Goal> currentLine;
    private ArrayList<Goal> lowestLine;
    private ArrayList<Goal> highestLine;
    private Double lineLabel;
    private Double lowestLabel;
    private Double highestLabel;
    private ArrayList<Goal> currentList;
    private ArrayList<Goal> bestSwappedList;
    private Double originalStD;
    private Double bestStD;
    
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
        this.originalStD = 0.0;
        this.bestStD = 0.0;
    }

    @Override
    public void fillGoalList(ArrayList<Goal> goalList, int nbExpectedGoals) {
        
        this.initialisation();
        
        // Generate a random sheet, calculate its standard deviation
        Random random = new Random();
        random.fillGoalList(currentList, nbExpectedGoals);
        System.out.println("Basis : "+standardDeviation(currentList));
        
        while(nbRepeats < NB_MAX_REPEATS) {
            
            // Calculate the sum of each line, take the lowest and highest one
            generateLineBounds();
            
            // We have the lowest and highest line. Now for standard deviation
            this.originalStD = standardDeviation(currentList);
            this.bestStD = originalStD;
            
            this.bestSwappedList.clear();
            this.bestSwappedList.addAll(currentList);
            
            for (Goal goalLowest : lowestLine) {
                for (Goal goalHighest : highestLine) {
                    swap(goalLowest, goalHighest);
                    double standardDeviation = standardDeviation(currentList);
                    if (standardDeviation < bestStD) {
                        bestStD = standardDeviation;
                        this.bestSwappedList.clear();
                        this.bestSwappedList.addAll(currentList);
                    }
                    swap(goalHighest, goalLowest);
                }
            }
            
            if (bestStD < originalStD) {
                this.nbRepeats = 0;
                this.currentList.clear();
                this.currentList.addAll(this.bestSwappedList);
            }
            else {
                this.nbRepeats++;
            }
            
            System.out.println(bestStD);
        }
        
        
        System.out.println("");
        // Return the result
        goalList.addAll(currentList);
        
    }
    
    private void generateLineBounds() {
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
    }
    
    private void swap(Goal goal1, Goal goal2) {
        int index1 = this.currentList.indexOf(goal1);
        int index2 = this.currentList.indexOf(goal2);
        
        Goal goalInt = this.currentList.get(this.currentList.indexOf(goal1));
        this.currentList.set(index1, goal2);
        this.currentList.set(index2, goalInt);
    }
    
    /**
     * Calculates the standard deviation of a board, by calculating the StD
     * of the sum of each line that composes it.
     * @param goalList Board passed in parameter.
     * @return Standard deviation of the board.
     */
    private double standardDeviation(ArrayList<Goal> goalList) {
        ArrayList<Double> labels = generateLabelList(goalList);
        return calculateStDLabels(labels);
    }
    
    /**
     * Calculates the standard deviation of a board, by calculating the StD
     * of the sum of each line that composes it.
     * @param goalList Board passed in parameter.
     * @return Standard deviation of the board.
     */
    private ArrayList<Double> generateLabelList(ArrayList<Goal> goalList) {
        // For each row
        ArrayList<Double> labels = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            currentLine.clear();

            // For each cell
            for (int j = 0; j < 5; j++) {
                currentLine.add(goalList.get(5*j+i));
                lineLabel += goalList.get(5*j+i).getLength();
            }
            labels.add(lineLabel);
            lineLabel = 0.0;
        }
        // For each col
        for (int i = 0; i < 5; i++) {
            currentLine.clear();

            // For each cell
            for (int j = 0; j < 5; j++) {
                currentLine.add(goalList.get(5*i+j));
                lineLabel += goalList.get(5*i+j).getLength();
            }
            labels.add(lineLabel);
            lineLabel = 0.0;
        }
        // TL-DR
        currentLine.clear();
        for (int i = 0; i < 5; i++) {
            currentLine.add(goalList.get(6*i));
            lineLabel += goalList.get(6*i).getLength();
        }
        labels.add(lineLabel);
        lineLabel = 0.0;
        
        // DL-TR
        currentLine.clear();
        for (int i = 0; i < 5; i++) {
            currentLine.add(goalList.get(4*i+4));
            lineLabel += goalList.get(4*i+4).getLength();
        }
        labels.add(lineLabel);
        lineLabel = 0.0;
        
        return labels;
        
    }
    
    /**
     * Calculates the standard deviation of a list of labels.
     * @param labels Labels passed in parameter.
     * @return A standard deviation of a list of labels.
     */
    private double calculateStDLabels(ArrayList<Double> labels) {
        // Calculate the average difficulty
        double avg = 0;
        double sum = 0;
        for(Double d : labels) {
            sum += d;
        }
        avg = sum/labels.size();
        
        // Subtract, pow
        sum = 0;
        double goalLength;
        
        for (Double d : labels) {
            goalLength = d - avg;
            goalLength = Math.pow(goalLength, 2);
            sum += goalLength;
        }
        
        // Calculate standard deviation
        double standardDeviation = Math.sqrt(sum/labels.size());
        
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
