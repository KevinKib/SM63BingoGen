/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen;

/**
 *
 * @author Kévin
 */
public class Goal {
    
    // Might feature a weight attribute in the future.
    
    private String name;
    private int weight;
    
    public Goal(String name) {
        this.name = name;
        this.weight = 1;
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
