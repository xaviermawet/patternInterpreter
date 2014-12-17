package Main;

import Interpreter.LexicalAnalyser;
import Interpreter.Parser;
import Interpreter.Token;
import Symbols.Symbol;
import Utils.Context;
import Utils.ContextErrorException;
import Utils.ReturnValue;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String calcul = "(x+10)*sin(x)-3*cos(x*x)";
        InputStream stream = new ByteArrayInputStream(calcul.getBytes());
        Parser parser = new Parser(stream);
        
        // Create context (identifier - value)
        Context context = new Context();
        context.setValue("x", 42);
        
        Symbol expression = parser.parse();
        System.out.println("Parsing OK");
        
        try
        {
            double result = expression.interpret(context);
            System.out.println("Result : " + result);
        }
        catch (ContextErrorException ex)
        {
            System.err.println("Context error : " + ex.getMessage());
            System.exit(ReturnValue.FAILURE.getReturnCode());
        }
    }
}
