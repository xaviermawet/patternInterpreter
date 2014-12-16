package Interpreter;

/**
 *
 * @author Nakim
 */
public class Token
{
    //<editor-fold defaultstate="collapsed" desc="Static variables">
    public static final Token TRUE;
    public static final Token FALSE;
    public static final Token AND;
    public static final Token OR;
    public static final Token LEFT_BRACKET;
    public static final Token RIGHT_BRACKET;
    
    static
    {
        TRUE  = new Token("true");
        FALSE = new Token("false");
        AND   = new Token("and");
        OR    = new Token("or");
        LEFT_BRACKET  = new Token("(");
        RIGHT_BRACKET = new Token(")");
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
        return this.lexeme.equalsIgnoreCase(token.getLexem());
    }
}
