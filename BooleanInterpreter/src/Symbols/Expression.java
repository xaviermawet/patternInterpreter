package Symbols;

import Interpreter.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class Expression implements NonTerminalSymbol
{
    private final NonTerminalSymbol orexpr;
    
    public Expression(NonTerminalSymbol orexpr)
    {
        this.orexpr = orexpr;
    }
    
    @Override
    public boolean interpret(Context context) throws ContextErrorException
    {
        return this.orexpr.interpret(context);
    }   
}
