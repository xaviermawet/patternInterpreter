package Main;

import Interpreter.Parser;
import Interpreter.Context;
import Symbols.NonTerminalSymbol;
import Utils.ContextErrorException;
import Utils.ParsingErrorException;
import Utils.ReturnValue;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
        // Instancier le parseur en lui donnant la source de données
        String text = "(true and X) or (Y and Z)";
        InputStream stream = new ByteArrayInputStream(text.getBytes());
        Parser parser = new Parser(stream);
        
        // Create context (identifier - value)
        Context context = new Context();
        context.setValue("true", true);
        context.setValue("false", false);
        context.setValue("x", false);
        context.setValue("y", true);
        context.setValue("z", true);
        
        try
        {
            // "Build" tree
            NonTerminalSymbol symbol = parser.parse();
            
            System.out.println("Parsing Ok...");
            
            // Interprets result with the context
            boolean result = symbol.interpret(context);
            System.out.println("Result : " + result);

        }
        catch (ContextErrorException ex)
        {
            System.err.println("Context error : " + ex.getMessage());
            System.exit(ReturnValue.FAILURE.getReturnCode());
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
