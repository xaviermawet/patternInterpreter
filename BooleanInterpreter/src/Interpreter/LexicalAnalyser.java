package Interpreter;

import Utils.ParsingErrorException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamTokenizer;

/**
 *
 * @author nakim
 */
public class LexicalAnalyser
{
    private static final int leftBracket  = '(';
    private static final int rightBracket = ')';
    
    private final InputStream inputStream;
    private final StreamTokenizer streamTokenizer;

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public LexicalAnalyser(InputStream inputStream)
    {
        this.inputStream = inputStream;
        this.streamTokenizer = new StreamTokenizer(this.inputStream);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public Token nextToken() throws IOException
    {
        switch(streamTokenizer.nextToken())
        {
            case StreamTokenizer.TT_WORD:
                switch(streamTokenizer.sval)
                {
                    case "true":
                        System.out.println("true");
                        return Token.TRUE;
                    case "false":
                        System.out.println("false");
                        return Token.FALSE;
                    case "and":
                        System.out.println("and");
                        return Token.AND;
                    case "or":
                        System.out.println("or");
                        return Token.OR;
                    default:
                        System.out.println(streamTokenizer.sval);
                        return new Token(streamTokenizer.sval);
                }
            case LexicalAnalyser.leftBracket:
                System.out.println("(");
                return Token.LEFT_BRACKET;
            case LexicalAnalyser.rightBracket:
                System.out.println(")");
                return Token.RIGHT_BRACKET;
            case StreamTokenizer.TT_EOF:
                System.out.println("EOF");
                return Token.END;
            case StreamTokenizer.TT_NUMBER:
                System.out.println("Number found");
                return new Token("INVALID : Number found");
            case StreamTokenizer.TT_EOL:
                System.out.println("End Of Line found");
                return new Token("INVALID : End Of Line found");
            default:
                System.out.println("Invalid token found");
                return new Token("INVALID : Invalid token found");
        }
    }
    //</editor-fold>
}
