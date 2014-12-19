package Symbols;

import Utils.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class Expression implements Symbol
{
    public static final boolean ADDITION;
    public static final boolean SUBTRACTION;
    
    private final Symbol  terme;
    private final boolean addition;
    private final Symbol  expression;
    
    static
    {
        ADDITION  = true;
        SUBTRACTION = false;
    }

    public Expression(Symbol terme, boolean addition, Symbol expression)
    {
        this.terme = terme;
        this.addition = addition;
        this.expression = expression;
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

    @Override
    public Symbol derive(Context context) throws ContextErrorException
    {
        if (this.expression == null)
            return this.terme.derive(context);
        
        return new Expression(this.terme.derive(context), this.addition, 
                              this.expression.derive(context));
    }
}
