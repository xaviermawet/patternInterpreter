package Interpreter;

import Token.Token;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamTokenizer;

/**
 *
 * @author nakim
 */
public class LexicalAnalyser
{
    private static final int openParenthesis = '(';
    private static final int closeParenthesis = ')';
    
    private InputStream inputStream;
    private StreamTokenizer streamTokenizer;

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
            case StreamTokenizer.TT_EOF:
                System.out.println("EOF");
                break;
            case StreamTokenizer.TT_WORD:
                switch(streamTokenizer.sval)
                {
                    case "true":
                        System.out.println("WORD true");
                        break;
                    case "false":
                        System.out.println("WORD false");
                        break;
                    case "and":
                        System.out.println("WORD and");
                        break;
                    default:
                        System.out.println("Autre WORD : " + streamTokenizer.sval);
                }
                break;
            case StreamTokenizer.TT_NUMBER:
                double asci = streamTokenizer.nval;
                System.out.println("number");
                break;
            case StreamTokenizer.TT_EOL:
                System.out.println("end of line");
                break;
            case LexicalAnalyser.openParenthesis:
                System.out.println("(");
                break;
            case LexicalAnalyser.closeParenthesis:
                System.out.println(")");
                break;
            default:
                System.out.println("Autre...");
                break;
        }
        
        return null;
    }
    //</editor-fold>
}
