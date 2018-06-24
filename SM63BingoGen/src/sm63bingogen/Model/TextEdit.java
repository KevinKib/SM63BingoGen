/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen.Model;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/**
 * @author paul -- https://stackoverflow.com/users/6315137/paul
 * https://stackoverflow.com/questions/17161587/how-to-center-align-text-in-jtextarea?lq=1
 */
public class TextEdit {
    
    public static void centerText (JTextArea ta)
    {
        BufferedImage fake1 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D fake2 = fake1.createGraphics();
        FontMetrics fm = fake2.getFontMetrics(ta.getFont());

        int lines = ta.getLineCount();
        ArrayList<String> list = new ArrayList<>();
        try
        {
            for (int i = 0; i < lines; i++)
            {
                int start = ta.getLineStartOffset(i);
                int end = ta.getLineEndOffset(i);

                String line = ta.getText(start, end - start).replace("\n","");
                list.add (line.trim());
            }
        }
        catch (BadLocationException e)
        {
            System.out.println(e);
        }
        alignLines (list, fm, ta);
    }

    private static void alignLines (ArrayList<String> list, FontMetrics fm, JTextArea ta)
    {
        String leading = "      ";
        int longest = -1;
        for (String s : list)
        {
            if (fm.stringWidth(s) > longest)
                longest = fm.stringWidth(s);
        }
        for (int n=0; n<list.size(); n++)
        {
            String s = list.get(n);
            if (fm.stringWidth(s) >= longest)
                continue;
            while (fm.stringWidth(s) < longest)
                s = ' '+s+' ';
            list.set(n, s);
        }
        StringBuilder sb = new StringBuilder();
        for (String s : list)
        {
            sb.append(leading).append(s).append('\n');
        }
        ta.setText (sb.toString());
    }
    
}
