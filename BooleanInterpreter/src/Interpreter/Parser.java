package Interpreter;

import Symbols.*;
import Utils.ParsingErrorException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author nakim
 */

// Syntax analyser
public class Parser
{
    private Token currentToken;
    private final LexicalAnalyser lexicalAnalyser;
    

    public Parser(InputStream inputStream)
    {
        this.currentToken = null;
        this.lexicalAnalyser = new LexicalAnalyser(inputStream);
    }
    
    public NonTerminalSymbol parse() throws ParsingErrorException, IOException
    {
        // Get the first token
        this.currentToken = this.lexicalAnalyser.nextToken();
        
        // Procedure that fits with the axiom        
        return this.expressionProcedure();
    }
    
    private NonTerminalSymbol expressionProcedure() throws ParsingErrorException,
                                                           IOException
    {
        NonTerminalSymbol orexpr = this.orExpressionProcedure();
        // <expression>::=<orexpr>
        return new Expression(orexpr);
    }
    
    private NonTerminalSymbol orExpressionProcedure() throws ParsingErrorException,
                                                             IOException
    {
        NonTerminalSymbol andexpr = this.andExpressionProcedure();
        NonTerminalSymbol secondorexpr = this.secondOrExpressionProcedure();
        // <orexpr> ::= <andexpr> 'or' <secondorexpr>
        return new OrExpression(andexpr, secondorexpr);
    }
    
    private NonTerminalSymbol andExpressionProcedure() throws ParsingErrorException,
                                                              IOException
    {
        NonTerminalSymbol simpleexpr = this.simpleExpressionProcedure();
        NonTerminalSymbol secondandexpr = this.secondAndExpressionProcedure();
        // <andexpr> ::= <simpleexpr> 'and' <secondandexpr>
        return new AndExpression(simpleexpr, secondandexpr);
    }
    
    private NonTerminalSymbol secondOrExpressionProcedure() throws ParsingErrorException,
                                                                   IOException
    {
        // <secondorexpr> ::= <orexpr>
        //                  | 'false'        
        
        if (this.currentToken.match(Token.OR))
        {
            this.currentToken = this.lexicalAnalyser.nextToken();
            NonTerminalSymbol orexpr = this.orExpressionProcedure();
            // <secondorexpr> ::= <orexpr>
            return new SecondOrExpression(orexpr);
        }
        
        if (this.currentToken.match(Token.INVALID))
            throw new ParsingErrorException(this.currentToken.getLexem());
        
        // <secondorexpr> ::= 'false'
        return new SecondOrExpression(null); // interpret returns 'false'
    }
    
    private NonTerminalSymbol secondAndExpressionProcedure() throws ParsingErrorException,
                                                                    IOException
    {
        // <secondandexpr> ::= <andexpr>
        //                   | 'true'
        
        if (this.currentToken.match(Token.AND))
        {
            this.currentToken = this.lexicalAnalyser.nextToken();
            NonTerminalSymbol andexpr = this.andExpressionProcedure();
            // <secondandexpr> ::= <andexpr>
            return new SecondAndExpression(andexpr);
        }
        
        if (this.currentToken.match(Token.INVALID))
            throw new ParsingErrorException(this.currentToken.getLexem());
        
        // <secondandexpr> ::= 'true'
        return new SecondAndExpression(null); // interpret returns 'true'
    }
    
    private NonTerminalSymbol simpleExpressionProcedure() throws IOException, ParsingErrorException
    {
        if (this.currentToken.match(Token.LEFT_BRACKET))
            return this.bracketExpressionProcedure();
        return this.identifierProcedure();
    }
    
    private NonTerminalSymbol identifierProcedure() throws IOException
    {
        Token identifierToken = this.currentToken;
        this.currentToken = this.lexicalAnalyser.nextToken();
        return new Identifier(identifierToken);
    }
    
    private NonTerminalSymbol bracketExpressionProcedure() throws ParsingErrorException, IOException
    {
        // <bracketExpression> ::= '(' <expression> ')'
        
        if (!this.currentToken.match(Token.LEFT_BRACKET))
            throw new ParsingErrorException("token is " + this.currentToken + 
                " but left bracket was expected");
        
        // Get <expression>
        this.currentToken = this.lexicalAnalyser.nextToken();
        NonTerminalSymbol expression = this.expressionProcedure();
        
        if (!this.currentToken.match(Token.RIGHT_BRACKET))
            throw new ParsingErrorException("token is " + this.currentToken + 
                " but right bracket was expected");
        
        this.currentToken = this.lexicalAnalyser.nextToken();
        return new BracketExpression(expression);
    }
}
