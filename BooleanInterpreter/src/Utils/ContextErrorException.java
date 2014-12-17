package Utils;

/**
 *
 * @author Nakim
 */
public class ContextErrorException extends Exception
{
    public ContextErrorException(String identifier)
    {
        super("Invalid identifier " + identifier);
    }
}
