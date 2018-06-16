/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen.IHM;

import java.util.HashMap;

/**
 *
 * @author Kévin
 */
public class Message {
    
    private static Message instance;
    private HashMap<String, String> messageList;
    
    private Message() {
        this.messageList = new HashMap<>();
        
        addMessage("title","SM63BingoGen 1.0");
        
        addMessage("about", "\nSuper Mario 63 Bingo Generator version 1.0.\nCreated by Sekanor.\nReleased on 16/06/2018.");
        addMessage("howToUse","\n1. Import your list of goals in .txt format.\n2. Generate your JSON board.\n3. Copy it into clipboard, and paste it in BingoSync.\n4. Enjoy !");
        
        addMessage("b_generateJson","Generate JSON");
        addMessage("b_generateJson_error","No file.");

        addMessage("b_copyClipboard","Copy to Clipboard");
        addMessage("b_copyClipboard_error","No JSON.");
        addMessage("b_copyClipboard_valid","Copied !");
    }
    
    private void addMessage(String title, String message) {
        this.messageList.put(title, message);
    }
    
    public String get(String title) {
        return this.messageList.get(title);
    }
    
    public static Message getInstance() {
        if (instance == null) {
            instance = new Message();
        }
        return instance;
    }
    
}
