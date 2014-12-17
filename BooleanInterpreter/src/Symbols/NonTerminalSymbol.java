package Symbols;

import Interpreter.Context;
import Utils.ContextErrorException;

/**
 *
 * @author nakim
 */
public interface NonTerminalSymbol
{
    public boolean interpret(Context context) throws ContextErrorException;
}
