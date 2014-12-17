package Interpreter;

import Symbols.Expression;
import Symbols.Symbol;
import Utils.ParsingErrorException;
import java.io.IOException;
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
    public Symbol parse() throws IOException, ParsingErrorException
    {
        this.currentToken = this.lexicalAnalyser.nextToken();
        
        Symbol expression = this.expressionProcedure();
        
        // TODO : vérifier que le dernier token est bien end sinon exception
        
        return expression;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private methods">
    private Symbol expressionProcedure() throws IOException, ParsingErrorException
    {
        // <expression> ::= <terme> '+' <expression>
        //                | <terme> '-' <expression>
        //                | <terme>
        
        
        // On récupère le <terme>
        Symbol terme = this.termeProcedure();
        
        // On récupère le signe '+' ou '-'
        this.currentToken = this.lexicalAnalyser.nextToken();
        Token sign = this.currentToken;
        
        System.out.println("Sign : " + sign);
        
        // <expression> ::= <terme>
        if (sign.match(Token.END))
            return new Expression(terme);
        
        // L'expression n'est pas composée d'un unique terme
        
        // On récupère l'<expression>
        Symbol expression = this.expressionProcedure();
        
        // <expression> ::= <terme> '+' <expression>
        if (sign.match(Token.PLUS))
            return new Expression(terme, Expression.ADDITION, expression);
        // <expression> ::= <terme> '-' <expression>
        if (sign.match(Token.MINUS))
            return new Expression(terme, Expression.SUBTRACTION, expression);
        
        throw new ParsingErrorException(this.currentToken.getLexem());
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
