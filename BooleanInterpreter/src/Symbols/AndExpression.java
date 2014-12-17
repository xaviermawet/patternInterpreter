package Symbols;

import Interpreter.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class AndExpression implements NonTerminalSymbol
{
    private final NonTerminalSymbol simpleexpr;
    private final NonTerminalSymbol secondandexprx;

    public AndExpression(NonTerminalSymbol simpleexpr, NonTerminalSymbol secondandexprx)
    {
        this.simpleexpr = simpleexpr;
        this.secondandexprx = secondandexprx;
    }
    
    @Override
    public boolean interpret(Context context) throws ContextErrorException
    {
        // <andexpr> ::= <simpleexpr> 'and' <secondandexpr>
        return (this.simpleexpr.interpret(context) &&
                this.secondandexprx.interpret(context));
    }
}
