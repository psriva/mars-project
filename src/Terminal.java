
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 239023
 */
public class Terminal implements Hub {
    
    private String terminalName;
     
    
    /**
     * Summary: Creates an object of instance Terminal using the PIV's
     * @param name: name of terminal
     */
    public Terminal (String name)
    {
        terminalName = name;
    }
    
    
    /**
     * Summary: Returns PIV String name
     * @return the name of the Terminal
     */
    public String getName()
    {
        return terminalName;
    }
    
    
    /**
     * Summary: Calls the method getName() and returns its value
     * @return a String representation for the Terminal as its name
     */
    public String toString()
    {
    	return getName();
    }
    
}