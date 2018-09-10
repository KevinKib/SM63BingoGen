/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen.Model.Import;

/**
 *
 * @author Kévin
 */
public abstract class Import_State {
    
    private String line;
    
    /**
     * Action réalisée par l'état en fonction de l'évènement passé en paramètre.
     * @param e Evènement.
     * @return Etat suivant.
     */
    public abstract Import_State action(Import_Event e);
    
    public abstract Import_State_Enum getType();

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
    
    
}

