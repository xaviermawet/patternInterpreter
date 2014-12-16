package Token;

import java.util.HashMap;

/**
 *
 * @author Nakim
 */
public class Context
{
    private HashMap<String, Boolean> values;

    //<editor-fold defaultstate="collapsed" desc="Constructeur">
    public Context()
    {
        this.values = new HashMap<>();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public Boolean setValue(String identifier, boolean value)
    {
        return this.values.put(identifier, value);
    }
    
    public boolean getValue(String identifier)
    {
        return this.values.get(identifier);
    }
    //</editor-fold>
}
