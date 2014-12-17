package Utils;

import java.util.HashMap;

/**
 *
 * @author Nakim
 */
public class Context
{
    private final HashMap<String, Boolean> values;

    //<editor-fold defaultstate="collapsed" desc="Constructeur">
    public Context()
    {
        this.values = new HashMap<>();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public boolean setValue(String identifier, boolean value)
    {
        return this.values.put(identifier.toUpperCase(), value) != null;
    }
    
    public boolean getValue(String identifier) throws ContextErrorException
    {
        Boolean value = this.values.get(identifier.toUpperCase());
        if (value == null)
            throw new ContextErrorException(identifier);
        return value;
    }
    //</editor-fold>
}
