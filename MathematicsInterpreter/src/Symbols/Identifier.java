package Symbols;

import Utils.Context;
import Interpreter.Token;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class Identifier implements Symbol
{
    private final String identifier;

    public Identifier(Token identifier)
    {
        this.identifier = identifier.getLexem();
    }

    @Override
    public double interpret(Context context) throws ContextErrorException
    {
        return context.getValue(identifier);
    }

    @Override
    public Symbol derive(Context context) throws ContextErrorException
    {
        System.out.println("dérivée d'un identifier vaut 1");
        return new Nombre(1);
    }
}
