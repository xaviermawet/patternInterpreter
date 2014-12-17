package Interpreter;

import Symbols.Symbol;
import java.io.InputStream;

/**
 *
 * @author Nakim
 */
public class Parser
{
    //<editor-fold defaultstate="collapsed" desc="Private varibles">
    private Token currentToken;
    private final LexicalAnalyser lexicalAnalyser;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Parser(InputStream inputStream)
    {
        this.currentToken = null;
        this.lexicalAnalyser = new LexicalAnalyser(inputStream);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public Symbol parse()
    {
        return null;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private methods">
    private Symbol expressionProcedure()
    {
        return null;
    }
    
    private Symbol termeProcedure()
    {
        return null;
    }
    
    private Symbol facteurProcedure()
    {
        return null;
    }
    
    private Symbol atomeProcedure()
    {
        return null;
    }
    
    private Symbol nombreProcedure()
    {
        return null;
    }
    //</editor-fold>
}
