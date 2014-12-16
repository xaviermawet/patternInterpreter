package Utils;

/**
 *
 * @author Nakim
 */
public class ParsingErrorException extends Exception
{
    public ParsingErrorException(String message)
    {
        super("Invalid lexem : " + message);
    }
}
