package Interpreter;

import Symbols.NonTerminalSymbol;
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
    private final LexicalAnalyser lexicalAnalyser;

    public Parser(InputStream inputStream)
    {
        this.lexicalAnalyser = new LexicalAnalyser(inputStream);
    }
    
    public void parse() throws ParsingErrorException, IOException
    {
            lexicalAnalyser.nextToken();
            lexicalAnalyser.nextToken();
            lexicalAnalyser.nextToken();
            lexicalAnalyser.nextToken();
            lexicalAnalyser.nextToken();
            lexicalAnalyser.nextToken();
            lexicalAnalyser.nextToken();
            lexicalAnalyser.nextToken();
            lexicalAnalyser.nextToken();
            lexicalAnalyser.nextToken();
            lexicalAnalyser.nextToken();
            lexicalAnalyser.nextToken();
            lexicalAnalyser.nextToken();
    }
    
    private boolean match(NonTerminalSymbol expectedToken)
    {
        return false;
    }
}
