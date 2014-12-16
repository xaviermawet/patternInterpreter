package Main;

import Interpreter.Parser;
import Interpreter.Context;
import Utils.ParsingErrorException;
import Utils.ReturnValue;
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
        // Create context (identifier - value)
        Context context = new Context();
        context.setValue("true", true);
        context.setValue("false", false);
        context.setValue("x", true);
        context.setValue("y", false);
        context.setValue("z", true);
        
        // Instancier le parseur en lui donnant la source de données
        String text = "(true and X) or (Y and Z)";
        InputStream stream = new ByteArrayInputStream(text.getBytes());
        Parser parser = new Parser(stream);
        
        try
        {
            parser.parse();
        }
        catch (ParsingErrorException ex)
        {
            System.err.println("Parsing error : " + ex.getMessage());
            System.exit(ReturnValue.FAILURE.getReturnCode());
        }
        catch (IOException ex)
        {
            System.err.println("I/O error : " + ex.getMessage());
            System.exit(ReturnValue.FAILURE.getReturnCode());
        }
    }
}
