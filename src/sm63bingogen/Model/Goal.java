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
    private String type;
    
    public Goal() {
        this("", 1, "");
    }
    
    public Goal(String name) {
        this(name, 1, "");
    }
    
    public Goal(String name, int length) {
        this(name, length, "");
    }
    
    public Goal(String name, int length, String type) {
        this.name = name;
        this.length = length;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String toString() {
        return "{\"name\": \""+this.name+"\"}";
    }
    
}
