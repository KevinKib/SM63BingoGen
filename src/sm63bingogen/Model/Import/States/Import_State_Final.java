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
public class Import_State_Final extends Import_State {

    @Override
    public Import_State action(Import_Event e) {
        return this;
    }
    
    @Override
    public Import_State_Enum getType() {
        return Import_State_Enum.Final;
    }
    
}
