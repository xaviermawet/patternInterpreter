package Symbols;

import Utils.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public interface Symbol
{
    public double interpret(Context context) throws ContextErrorException;
    public Symbol derive(Context context) throws ContextErrorException;
}
