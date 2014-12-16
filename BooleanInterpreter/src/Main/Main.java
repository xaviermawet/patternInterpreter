package Main;

import Interpreter.LexicalAnalyser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nakim
 */
public class Main
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String text = "(true and X) or (Y and Z)";
        InputStream stream = new ByteArrayInputStream(text.getBytes());
        
        LexicalAnalyser analyser = new LexicalAnalyser(stream);
        try
        {
            analyser.nextToken();
            analyser.nextToken();
            analyser.nextToken();
            analyser.nextToken();
            analyser.nextToken();
            analyser.nextToken();
            analyser.nextToken();
            analyser.nextToken();
            analyser.nextToken();
        }
        catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
