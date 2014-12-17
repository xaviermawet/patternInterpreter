package Interpreter;

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
                        return Token.TRUE;
                    case "false":
                        return Token.FALSE;
                    case "and":
                        return Token.AND;
                    case "or":
                        return Token.OR;
                    default:
                        return new Token(streamTokenizer.sval);
                }
            case LexicalAnalyser.leftBracket:
                return Token.LEFT_BRACKET;
            case LexicalAnalyser.rightBracket:
                return Token.RIGHT_BRACKET;
            case StreamTokenizer.TT_EOF:
                return Token.END;
            case StreamTokenizer.TT_NUMBER:
                return new Token("INVALID : Number found");
            case StreamTokenizer.TT_EOL:
                return new Token("INVALID : End Of Line found");
            default:
                return new Token("INVALID : Invalid token found");
        }
    }
    //</editor-fold>
}
