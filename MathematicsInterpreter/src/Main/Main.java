package Main;

import Interpreter.Parser;
import Symbols.Symbol;
import Utils.Context;
import Utils.ContextErrorException;
import Utils.ParsingErrorException;
import Utils.ReturnValue;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Nakim
 */
public class Main
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Instancier le parseur en lui donnant la source de données
        //String calcul = "(x+10)*sin(x)-3*cos(x*x)";
        String calcul = "2^cos(30 + x^2)";
        InputStream stream = new ByteArrayInputStream(calcul.getBytes());
        Parser parser = new Parser(stream);
        
        // Create context (identifier - value)
        Context context = new Context();
        context.setValue("x", 42);
        
        try
        {
            Symbol expression = parser.parse();
            System.out.println("Parsing OK");
        
            double result = expression.interpret(context);
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
