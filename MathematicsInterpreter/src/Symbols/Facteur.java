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
    
    public Facteur(Symbol atome)
    {
        this(atome, null);
    }
    
    @Override
    public double interpret(Context context) throws ContextErrorException
    {
        if (this.facteur == null)
            return this.atome.interpret(context);
        
        return Math.pow(this.atome.interpret(context),
                        this.facteur.interpret(context));
    }

    @Override
    public Symbol derive(Context context) throws ContextErrorException
    {
        if (this.facteur == null)
            return this.atome.derive(context);
        
        // x^n  --> n*x^(n-1) avec n == facteur et x == atome
        
        // (n-1)
        Symbol exponentExpression = new Expression(this.facteur, Expression.SUBTRACTION, new Nombre(1));
        // x^(n-1)
        Symbol newFacteur = new Facteur(this.atome, exponentExpression);
        
        // n*x^(n-1)
        return new Terme(this.facteur, Terme.MULTIPLICATION, newFacteur);
    }
}
