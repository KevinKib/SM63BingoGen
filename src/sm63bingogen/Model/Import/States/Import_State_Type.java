/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen.Model.Import.States;

import sm63bingogen.Model.Import.Import_Event;
import sm63bingogen.Model.Import.Import_State;
import sm63bingogen.Model.Import.Import_State_Enum;

/**
 *
 * @author Kévin
 */
public class Import_State_Type extends Import_State {

    @Override
    public Import_State action(Import_Event e) {
        Import_State finalState;
        
        switch(e) {
            case Letter: case Number:
                finalState = this;
                break;
            case EndOfLine:
                finalState = new Import_State_Final();
                break;
            default:
                finalState = new Import_State_Error(); 
        }
        
        return finalState;
    }
    
    @Override
    public Import_State_Enum getType() {
        return Import_State_Enum.Type;
    }
    
}
