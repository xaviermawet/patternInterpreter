package Symbols;

import Interpreter.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class OrExpression implements NonTerminalSymbol
{
    private final NonTerminalSymbol andexpr;
    private final NonTerminalSymbol secondorexpr;

    public OrExpression(NonTerminalSymbol andexpr, NonTerminalSymbol secondorexpr)
    {
        this.andexpr = andexpr;
        this.secondorexpr = secondorexpr;
    }
    
    @Override
    public boolean interpret(Context context) throws ContextErrorException
    {
        // <orexpr> ::= <andexpr> 'or' <secondorexpr>
        return (this.andexpr.interpret(context) ||
                this.secondorexpr.interpret(context));
    }   
}
