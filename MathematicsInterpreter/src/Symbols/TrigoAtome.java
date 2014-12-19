package Symbols;

import Utils.Context;
import Utils.ContextErrorException;

/**
 *
 * @author Nakim
 */
public class TrigoAtome implements Symbol
{
    public static final int SIN = 1;
    public static final int COS = 2;
    public static final int TAN = 3;
    
    private final Symbol atome;
    private final int trigonometricFunction;

    public TrigoAtome(Symbol atome, int trigonometricFunction)
    {
        this.atome = atome;
        this.trigonometricFunction = trigonometricFunction;
    }

    @Override
    public double interpret(Context context) throws ContextErrorException
    {
        switch(this.trigonometricFunction)
        {
            case SIN:
                return Math.sin(Math.toRadians(this.atome.interpret(context)));
            case COS:
                return Math.cos(Math.toRadians(this.atome.interpret(context)));
            case TAN:
                return Math.tan(Math.toRadians(this.atome.interpret(context)));
            default:
                return this.atome.interpret(context);
        }
    }

    @Override
    public Symbol derive(Context context) throws ContextErrorException
    {
        Symbol trigonoDerived;
        switch(this.trigonometricFunction)
        {
            case SIN:
                trigonoDerived = new TrigoAtome(this.atome, TrigoAtome.COS);
                break;
            case COS:
                trigonoDerived = new Expression(new Nombre(0), Expression.SUBTRACTION,
                    new TrigoAtome(this.atome, TrigoAtome.SIN));
                break;
            case TAN:
                return this;
            default:
                return this;
        }
        // f(x)' = x'*f'(x)
        return new Terme(this.atome.derive(context), Terme.MULTIPLICATION, trigonoDerived);
    }
}
