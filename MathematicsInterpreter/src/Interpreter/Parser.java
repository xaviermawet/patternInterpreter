package Interpreter;

import Symbols.Atome;
import Symbols.Expression;
import Symbols.Facteur;
import Symbols.Identifier;
import Symbols.Nombre;
import Symbols.Symbol;
import Symbols.Terme;
import Symbols.TrigoAtome;
import Utils.ParsingErrorException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Nakim
 */
public class Parser
{
    //<editor-fold defaultstate="collapsed" desc="Private varibles">
    private Token currentToken;
    private final LexicalAnalyser lexicalAnalyser;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Parser(InputStream inputStream)
    {
        this.currentToken = null;
        this.lexicalAnalyser = new LexicalAnalyser(inputStream);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public Symbol parse() throws IOException, ParsingErrorException
    {
        this.currentToken = this.lexicalAnalyser.nextToken();
        
        Symbol expression = this.expressionProcedure();
        
        // TODO : vérifier que le dernier token est bien end sinon exception
        
        return expression;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private methods">
    private Symbol expressionProcedure() throws IOException, ParsingErrorException
    {
        System.out.println("Début Procedure expression : " + this.currentToken);
        
        // <expression> ::= <terme> '+' <expression>
        //                | <terme> '-' <expression>
        //                | <terme>
        
        
        // On récupère le <terme>
        Symbol terme = this.termeProcedure();
        System.out.println("Retour Procedure expression : " + this.currentToken);
        
        // Après avoir récupéré le terme, le curseur se trouve sur le +, le - ou END
        
        // <expression> ::= <terme>
        if (this.currentToken.match(Token.END) ||
            this.currentToken.match(Token.RIGHT_BRACKET))
        {
            System.out.println("Fin procédure par ')' ou END");
            return new Expression(terme);
        }
        
        // L'expression n'est pas composée d'un unique terme
        
        // <expression> ::= <terme> '+' <expression>
        if (this.currentToken.match(Token.PLUS))
        {
            System.out.println("on passe le signe +");
            this.currentToken = this.lexicalAnalyser.nextToken();
            
            // On récupère l'<expression>
            Symbol expression = this.expressionProcedure();
            
            return new Expression(terme, Expression.ADDITION, expression);
        }
        // <expression> ::= <terme> '-' <expression>
        if (this.currentToken.match(Token.MINUS))
        {
            System.out.println("on passe le signe -");
            this.currentToken = this.lexicalAnalyser.nextToken();
            
            // On récupère l'<expression>
            Symbol expression = this.expressionProcedure();
            
            return new Expression(terme, Expression.SUBTRACTION, expression);
        }
        
        throw new ParsingErrorException(this.currentToken.getLexem());
    }
    
    private Symbol termeProcedure() throws IOException, ParsingErrorException
    {
        System.out.println("    Début Procedure terme    : " + this.currentToken);
        
        // <terme> ::= <facteur> '*' <terme>
        //           | <facteur> '/' <terme>
        //           | <facteur>

        // On récupère le <facteur>
        Symbol facteur = this.facteurProcedure();
        System.out.println("    Retour Procedure terme    : " + this.currentToken);

        // Après avoir récupéré le facteur, le curseur se trouve sur le *, le / ou END

        // <terme> ::= <facteur> '*' <terme>
        if (this.currentToken.match(Token.TIMES))
        {
            System.out.println("    on passe le signe *");
            this.currentToken = this.lexicalAnalyser.nextToken();
            
            // on récupère le <terme>
            Symbol terme = this.termeProcedure();
            
            return new Terme(facteur, Terme.MULTIPLICATION, terme);
        }
        // <terme> ::= <facteur> '/' <terme>
        if (this.currentToken.match(Token.DIVIDED))
        {
            System.out.println("    on passe le signe /");
            this.currentToken = this.lexicalAnalyser.nextToken();
            
            // On récupère le <terme>
            Symbol terme = this.termeProcedure();
            
            return new Terme(facteur, Terme.DIVISION, terme);
        }
        if (this.currentToken.match(Token.ERROR))
            throw new ParsingErrorException(this.currentToken.getLexem());

        // <terme> ::= <facteur>
        return new Terme(facteur);
    }
    
    private Symbol facteurProcedure() throws IOException, ParsingErrorException
    {
        System.out.println("        Début Procedure facteur : " + this.currentToken);
        
        // <facteur> ::= <Atome> '^' <facteur>
        //             | <Atome>
        
        // On récupère l'<Atome>
        Symbol atome = this.atomeProcedure();
        System.out.println("        Retour Procedure facteur : " + this.currentToken);
        
        // Après avoir récupéré l'atome, le curseur se trouve sur le ^ ou END
        
        // <facteur> ::= <Atome> '^' <facteur>
        if (this.currentToken.match(Token.EXPONENT))
        {
            System.out.println("    on passe le signe ^");
            this.currentToken = this.lexicalAnalyser.nextToken();
            
            // On récupère le <facteur>
            Symbol facteur = this.facteurProcedure();
            
            return new Facteur(atome, facteur);
        }
        if (this.currentToken.match(Token.ERROR))
            throw new ParsingErrorException(this.currentToken.getLexem());
        
        // <facteur> ::= <Atome>
        return new Facteur(atome);
    }
    
    private Symbol atomeProcedure() throws IOException, ParsingErrorException
    {
        System.out.println("            Début Procedure atome : " + this.currentToken);
        
        // <Atome> ::= <nombre>
        //           | '(' <expression> ')'
        //           | sin <atome>
        //           | cos <atome>
        
        // <Atome> ::= '(' <expression> ')'
        if (this.currentToken.match(Token.LEFT_BRACKET))
        {   
            System.out.println("                Début parenthèse : " + this.currentToken);
            this.currentToken = this.lexicalAnalyser.nextToken();
            System.out.println("                Lancement procedure : " + this.currentToken);
            Symbol expression = this.expressionProcedure();
            
            System.out.println("                fin parenthèse : " + this.currentToken);
            
            if(!this.currentToken.match(Token.RIGHT_BRACKET))
                throw new ParsingErrorException("token is " + this.currentToken + 
                " but right bracket was expected");
            
            // On passe au token après la parenthèse
            this.currentToken = this.lexicalAnalyser.nextToken();

            return new Atome(expression);
        }
        // <Atome> ::= sin <atome>
        if (this.currentToken.match(Token.SIN))
        {
            System.out.println("                Début SIN : " + this.currentToken);
            this.currentToken = this.lexicalAnalyser.nextToken();
            Symbol atome = this.atomeProcedure();
            System.out.println("                fin SIN : " + this.currentToken);
            return new TrigoAtome(atome, TrigoAtome.SIN);
        }
        // <Atome> ::= cos <atome>
        if(this.currentToken.match(Token.COS))
        {
            System.out.println("                Début COS : " + this.currentToken);
            this.currentToken = this.lexicalAnalyser.nextToken();
            Symbol atome = this.atomeProcedure();
            System.out.println("                fin COS : " + this.currentToken);
            return new TrigoAtome(atome, TrigoAtome.COS);
        }
        // <Atome> ::= <nombre>
        Token identifierToken = this.currentToken;
        System.out.println("                Nombre ou id : " + identifierToken);
        try
        {
            Double number = Double.parseDouble(identifierToken.getLexem());
            System.out.println("                C'était un nombre");
            return new Nombre(number);
        }
        catch (NumberFormatException e)
        {
            System.out.println("                C'était une lettre");
            return new Identifier(identifierToken);
        }
        finally
        {
            System.out.println("                On passe au suivant");
            this.currentToken = this.lexicalAnalyser.nextToken();
        }
    }
    //</editor-fold>
}
