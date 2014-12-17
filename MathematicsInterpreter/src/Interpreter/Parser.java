package Interpreter;

import Symbols.Expression;
import Symbols.Symbol;
import Symbols.Terme;
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
        
        // On récupère le signe '+' ou '-' (ou alors END ou une parenthèse fermante)
        this.currentToken = this.lexicalAnalyser.nextToken();
        
        // <expression> ::= <terme>
        if (this.currentToken.match(Token.END) ||
            this.currentToken.match(Token.RIGHT_BRACKET))
            return new Expression(terme);
        
        // L'expression n'est pas composée d'un unique terme
        
        // On récupère l'<expression>
        Symbol expression = this.expressionProcedure();
        
        // <expression> ::= <terme> '+' <expression>
        if (this.currentToken.match(Token.PLUS))
            return new Expression(terme, Expression.ADDITION, expression);
        // <expression> ::= <terme> '-' <expression>
        if (this.currentToken.match(Token.MINUS))
            return new Expression(terme, Expression.SUBTRACTION, expression);
        
        throw new ParsingErrorException(this.currentToken.getLexem());
    }
    
    private Symbol termeProcedure() throws IOException, ParsingErrorException
    {
        // <terme> ::= <facteur> '*' <terme>
        //           | <facteur> '/' <terme>
        //           | <facteur>

        // On récupère le <facteur>
        Symbol facteur = this.facteurProcedure();

        // On récupère le signe '*' ou '/'
        this.currentToken = this.lexicalAnalyser.nextToken();

        // <terme> ::= <facteur> '*' <terme>
        if (this.currentToken.match(Token.TIMES))
        {
            Symbol terme = this.termeProcedure();
            return new Terme(facteur, Terme.MULTIPLICATION, terme);
        }
        // <terme> ::= <facteur> '/' <terme>
        if (this.currentToken.match(Token.DIVIDED))
        {
            Symbol terme = this.termeProcedure();
            return new Terme(facteur, Terme.DIVISION, terme);
        }
        if (this.currentToken.match(Token.ERROR))
            throw new ParsingErrorException(this.currentToken.getLexem());

        // <terme> ::= <facteur>
        return new Terme(facteur);
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
