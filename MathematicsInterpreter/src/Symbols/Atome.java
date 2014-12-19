package Symbols;

import Utils.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class Atome implements Symbol
{
    // Can be a Number, an Expression, a SinAtome or a CosAtome
    private final Symbol atomeElement;

    public Atome(Symbol atomeElement)
    {
        this.atomeElement = atomeElement;
    }

    @Override
    public double interpret(Context context) throws ContextErrorException
    {
        return this.atomeElement.interpret(context);
    }

    @Override
    public Symbol derive(Context context) throws ContextErrorException
    {
        return this.atomeElement.derive(context);
    }
}
