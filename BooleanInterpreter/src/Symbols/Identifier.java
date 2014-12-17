package Symbols;

import Interpreter.Context;
import Interpreter.Token;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class Identifier implements NonTerminalSymbol
{
    private final String identifier;

    public Identifier(Token identifier)
    {
        this.identifier = identifier.getLexem();
    }

    @Override
    public boolean interpret(Context context) throws ContextErrorException
    {
        return context.getValue(identifier);
    }
}
