package Symbols;

import Utils.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class Atome implements Symbol
{
    @Override
    public double interpret(Context context) throws ContextErrorException
    {
        return 0;
    }
}
