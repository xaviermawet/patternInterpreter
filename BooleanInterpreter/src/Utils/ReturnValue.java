package Utils;

/**
 *
 * @author Nakim
 */
public enum ReturnValue
{
    SUCCESS(0),
    FAILURE(-1);

    private int returnCode;

    private ReturnValue(int returnCode)
    {
        this.returnCode = returnCode;
    }

    public int getReturnCode()
    {
        return returnCode;
    }
}
