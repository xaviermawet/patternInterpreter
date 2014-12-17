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
    //<editor-fold defaultstate="collapsed" desc="Static variables">
    private static final int plus         = '+';
    private static final int minus        = '-';
    private static final int times        = '*';
    private static final int divided      = '/';
    private static final int exponent     = '^';
    private static final int leftBracket  = '(';
    private static final int rightBracket = ')';
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Priates varibales">
    private final InputStream inputStream;
    private final StreamTokenizer streamTokenizer;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public LexicalAnalyser(InputStream inputStream)
    {
        this.inputStream = inputStream;
        this.streamTokenizer = new StreamTokenizer(this.inputStream);
        this.streamTokenizer.ordinaryChar(LexicalAnalyser.minus);
        this.streamTokenizer.ordinaryChar(LexicalAnalyser.divided);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public Token nextToken() throws IOException
    {
        switch(streamTokenizer.nextToken())
        {
            case StreamTokenizer.TT_WORD:
                switch(streamTokenizer.sval.toUpperCase())
                {
                    case "SIN":
                        return Token.SIN;
                    case "COS":
                        return Token.COS;
                    default:
                        return new Token(streamTokenizer.sval);
                }
            case LexicalAnalyser.plus:
                return Token.PLUS;
            case LexicalAnalyser.minus:
                return Token.MINUS;
            case LexicalAnalyser.times:
                return Token.TIMES;
            case LexicalAnalyser.divided:
                return Token.DIVIDED;
            case LexicalAnalyser.exponent:
                return Token.EXPONENT;
            case LexicalAnalyser.leftBracket:
                return Token.LEFT_BRACKET;
            case LexicalAnalyser.rightBracket:
                return Token.RIGHT_BRACKET;
            case StreamTokenizer.TT_EOF:
                return Token.END;
            case StreamTokenizer.TT_NUMBER:
                return new Token(String.valueOf(streamTokenizer.nval));
            case StreamTokenizer.TT_EOL:
                return new Token("ERROR : End Of Line found");
            default:
                return new Token("ERROR : Invalid token found");
        }
    }
    //</editor-fold>
}
