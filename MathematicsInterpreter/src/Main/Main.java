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
        String calcul = "2*x*sin(3*x)";
        
        // Instancier le parseur en lui donnant la source de données
        InputStream stream = new ByteArrayInputStream(calcul.getBytes());
        Parser parser = new Parser(stream);
        
        // Create context (identifier - value)
        Context context = new Context();
        context.setValue("x", 42);
        
        try
        {
            Symbol expression = parser.parse();
            System.out.println("Parsing OK");
            
            Symbol derive = expression.derive(context);
        
            double result = expression.interpret(context);
            System.out.println("Result expression : " + result);
            
            result = derive.interpret(context);
            System.out.println("Result dérivée : " + result);
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
