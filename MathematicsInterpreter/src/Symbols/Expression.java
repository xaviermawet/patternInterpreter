package Symbols;

import Utils.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class Expression implements Symbol
{
    private final Symbol  terme;
    private final Symbol  expression;
    private final boolean addition;

    public Expression(Symbol terme, Symbol expression, boolean addition)
    {
        this.terme = terme;
        this.expression = expression;
        this.addition = addition;
    }

    public Expression(Symbol terme)
    {
        this.terme = terme;
        this.addition = false;
        this.expression = null;
    }

    @Override
    public double interpret(Context context) throws ContextErrorException
    {
        if (this.expression == null)
            return this.terme.interpret(context);

        return (this.addition) ?
               this.terme.interpret(context) + this.expression.interpret(context) :
               this.terme.interpret(context) - this.expression.interpret(context);
    }
}
