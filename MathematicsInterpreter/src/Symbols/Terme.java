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

    @Override
    public Symbol derive(Context context) throws ContextErrorException
    {
        // <terme> ::= <facteur> '*' <terme>
        
        // <terme> ::= <facteur> '*' <terme>
        //           | <facteur> '/' <terme>
        //           | <facteur>
        
        // <terme> ::= <facteur>
        if (this.terme == null)
            return this.facteur.derive(context);
        
        // <terme> ::= <facteur> '*' <terme>
        //           =     x      *     y
        
        // x'
        Symbol facteurDerived = this.facteur.derive(context);
        // y'
        Symbol termeDerived = this.terme.derive(context);
        // x'*y
        Symbol leftTerme = new Terme(facteurDerived, Terme.MULTIPLICATION, this.terme);
        // x*y'
        Symbol rightTerme = new Terme(this.facteur, Terme.MULTIPLICATION, termeDerived);
        
        // (x*y)' = x'*y + x*y'
        if (this.multiplication)
            return new Expression(leftTerme, Expression.ADDITION, rightTerme);
        
        // (x/y)' = (x'*y - x*y')/ y^2
        
        // (x'*y - x*y')
        Symbol expression = new Expression(leftTerme, Expression.SUBTRACTION, rightTerme);
        // y^2
        Symbol newFacteur = new Facteur(this.terme, new Nombre(2));
        
        return new Terme(expression, Terme.DIVISION, newFacteur);
    }
}
