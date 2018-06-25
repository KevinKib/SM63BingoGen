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
    private int length;
    
    public Goal(String name) {
        this(name, 1);
    }
    
    public Goal(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }
    
    public String toString() {
        return "{\"name\": \""+this.name+"\"}";
    }
    
}
