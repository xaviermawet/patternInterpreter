package Symbols;

import Interpreter.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class SecondOrExpression implements NonTerminalSymbol
{
    private final NonTerminalSymbol orexpr;

    public SecondOrExpression(NonTerminalSymbol orexpr)
    {
        this.orexpr = orexpr;
    }
    
    @Override
    public boolean interpret(Context context) throws ContextErrorException
    {
        if (this.orexpr == null)
            return false;
        return this.orexpr.interpret(context);
    }   
}
