package Symbols;

import Utils.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class Facteur implements Symbol
{
    private final Symbol atome;
    private final Symbol facteur;

    public Facteur(Symbol atome, Symbol facteur)
    {
        this.atome = atome;
        this.facteur = facteur;
    }
    
    @Override
    public double interpret(Context context) throws ContextErrorException
    {
        if (this.facteur == null)
            return this.atome.interpret(context);
        
        return Math.pow(this.atome.interpret(context),
                        this.facteur.interpret(context));
    }
}
