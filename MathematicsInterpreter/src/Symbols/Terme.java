package Symbols;

import Utils.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class Terme implements Symbol
{
    public static final boolean MULTIPLICATION;
    public static final boolean DIVISION;
    
    private final Symbol  facteur;
    private final boolean multiplication;
    private final Symbol  terme;
    
    static
    {
        MULTIPLICATION = true;
        DIVISION = false;
    }

    public Terme(Symbol facteur, boolean multiplication, Symbol terme)
    {
        this.facteur = facteur;
        this.multiplication = multiplication;
        this.terme = terme;
    }

    public Terme(Symbol facteur)
    {
        this.facteur = facteur;
        this.multiplication = false;
        this.terme = null;
    }

    @Override
    public double interpret(Context context) throws ContextErrorException
    {
        if (this.terme == null)
            return this.facteur.interpret(context);
        
        return (this.multiplication) ?
               this.facteur.interpret(context) * this.terme.interpret(context) :
               this.facteur.interpret(context) / this.terme.interpret(context);
    }
}
