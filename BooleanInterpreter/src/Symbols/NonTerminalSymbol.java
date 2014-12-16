package Symbols;

import Interpreter.Context;

/**
 *
 * @author nakim
 */
public interface NonTerminalSymbol
{
    public boolean execute(Context context);
}
