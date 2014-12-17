package Symbols;

import Interpreter.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class BracketExpression implements NonTerminalSymbol
{
    private final NonTerminalSymbol expression;

    public BracketExpression(NonTerminalSymbol expression)
    {
        this.expression = expression;
    }

    @Override
    public boolean interpret(Context context) throws ContextErrorException
    {
        return this.expression.interpret(context);
    }
}
