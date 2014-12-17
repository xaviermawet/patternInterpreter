package Interpreter;

/**
 *
 * @author Nakim
 */
public class Token
{
    //<editor-fold defaultstate="collapsed" desc="Static variables">
    public static final Token PLUS;
    public static final Token MINUS;
    public static final Token TIMES;
    public static final Token DIVIDED;
    public static final Token EXPONENT;
    public static final Token SIN;
    public static final Token COS;
    public static final Token LEFT_BRACKET;
    public static final Token RIGHT_BRACKET;
    
    public static final Token END;
    public static final Token ERROR;
    
    static
    {
        PLUS     = new Token("+");
        MINUS    = new Token("-");
        TIMES    = new Token("*");
        DIVIDED  = new Token("/");
        EXPONENT = new Token("^");
        SIN      = new Token("sin");
        COS      = new Token("cos");
        
        LEFT_BRACKET  = new Token("(");
        RIGHT_BRACKET = new Token(")");
        
        END   = new Token("END");
        ERROR = new Token("ERROR");
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private varibales">
    private String lexeme;
    //</editor-fold>

    public Token(String lexeme)
    {
        this.lexeme = lexeme.toUpperCase();
    }

    public String getLexem()
    {
        return this.lexeme;
    }

    public void setLexem(String lexem)
    {
        this.lexeme = lexem.toUpperCase();
    }
    
    public boolean match(Token token)
    {
        //return this.lexeme.equalsIgnoreCase(token.getLexem());
        return this.lexeme.startsWith(token.getLexem());
    }

    @Override
    public String toString()
    {
        return this.getLexem();
    }
}
