package Symbols;

import Interpreter.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class SecondAndExpression implements NonTerminalSymbol
{
    private final NonTerminalSymbol andexpr;

    public SecondAndExpression(NonTerminalSymbol andexpr)
    {
        this.andexpr = andexpr;
    }
    
    @Override
    public boolean interpret(Context context) throws ContextErrorException
    {
        if (this.andexpr == null)
            return true;
        return this.andexpr.interpret(context);
    }
}
