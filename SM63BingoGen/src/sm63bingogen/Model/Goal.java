/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen.Model;

/**
 *
 * @author Kévin
 */
public class Goal {
    
    // Might feature a weight attribute in the future.
    
    private String name;
    private int difficulty;
    
    public Goal(String name) {
        this.name = name;
        this.difficulty = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
        return "{\"name\": \""+this.name+"\"}";
    }
    
}
