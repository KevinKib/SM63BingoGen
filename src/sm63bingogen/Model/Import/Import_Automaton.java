/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen.Model.Import;

import sm63bingogen.Model.Goal;
import sm63bingogen.Model.Import.States.*;

/**
 *
 * @author Kévin
 */
public class Import_Automaton {
    
    private Import_State state;
    
    private String title;
    private String length;
    private String type;
    private String line;
    private char currentChar;
    private int currentCharPos;

    public Import_Automaton() {
        initialisation();
    }
    
    private void initialisation() {
        this.state = new Import_State_Title();
        this.line = "";
        this.title = "";
        this.length = "";
        this.type = "";
        this.currentChar = ' ';
        this.currentCharPos = 0;
    }
    
    /**
     * Interprets a line, and returns a goal from it.
     * @param _line The line that has to be interpreted by the automaton.
     * @return The new goal that has been interpreted (null if it's invalid).
     */
    public Goal readLine(String _line) {
        
        initialisation();
        this.line = _line;

        while(this.state.getType() != Import_State_Enum.Final && this.state.getType() != Import_State_Enum.Error) {

            Import_Event event = this.determineEvent();
            this.evolute(event);
            this.addCharacter();
            
            currentCharPos++;
        }

        // Goal creation
        Goal goal = this.createGoal();
        return goal;
    }
    
    /**
     * Determines which event to return depending on the current readed
     * character and the automaton state.
     * @return An event of type Import_Event.
     */
    private Import_Event determineEvent() {
        Import_Event event;
        
        if (currentCharPos < line.length()) {
            currentChar = line.charAt(currentCharPos);
            if (Character.isLetter(currentChar) || currentChar == ' ') {
                event = Import_Event.Letter;
            }
            else if (Character.isDigit(currentChar)) {
                event = Import_Event.Number;
            }
            else if (currentChar == ',') {
                event = Import_Event.Comma;
            }
            else {
                event = Import_Event.Invalid;
            }
        }
        else {
            event = Import_Event.EndOfLine;
        }
        
        return event;
    }
    
    /**
     * Adds the character that's been read to the goal variables depending
     * on the current state.
     */
    private void addCharacter() {
        switch(this.state.getType()) {
            case Title:
                title += currentChar;
                break;
            case Length:
                length += currentChar;
                break;
            case Type:
                type += currentChar;
                break;
        }
    }
    
    /**
     * Creates a goal after the automaton process.
     * @return The goal that will be returned at the end by the main function readLine.
     */
    private Goal createGoal() {
        Goal goal;
        Integer lengthInt = 0;
        
        if (!length.equals("")) {
            lengthInt = Integer.parseInt(length);
        }
        
        
        if (this.state.getType() == Import_State_Enum.Final) {
            
            if (!type.equals("")) {
                goal = new Goal(title, lengthInt, type);
            }
            else if (length != "") {
                goal = new Goal(title, lengthInt);
            }
            else {
                goal = new Goal(title);
            }
        }
        else {
            goal = null;
        }
        
        return goal;
    }
    
    
    public void getEvent() {
        
    }
    
    public void evolute(Import_Event e) {
        if (this.state != null) {
            this.state = this.state.action(e);
        }
    }

    public Import_State getState() {
        return state;
    }

    public void setState(Import_State state) {
        this.state = state;
    }
    
    
    
}
