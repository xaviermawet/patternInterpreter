package Symbols;

import Utils.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class Nombre implements Symbol
{
    private double number;

    public Nombre(double number)
    {
        this.number = number;
    }

    @Override
    public double interpret(Context context) throws ContextErrorException
    {
        return this.number;
    }

    @Override
    public Symbol derive(Context context) throws ContextErrorException
    {
        System.out.println("dérivée d'un nombre vaut 0");
        return new Nombre(0);
    }
}
