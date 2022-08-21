import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 239023
 */
public class Building implements Hub {
    
    private String buildingName;
    private int maximumPower;
    private int minimumPower;
    private int currentPercentPower;
    private int currentAmountOfPower;
    private boolean buildingIsOperating;
    private ArrayList<Hub> shortestPathFromGenerator;
    
    
    /**
     * Summary: Creates an object of instance Building using the PIV's
     * @param name: name of building
     * @param powerMax: maximum power of the building
     * @param percentCanLose: percentage the building can lose and still operate
     * @param powerCurrent: current power of the building
     */
    public Building (String name, int powerMax, int percentCanLose, int powerCurrent)
    {
        buildingName = name;
        maximumPower = powerMax;
        minimumPower = 100 - percentCanLose;
        currentPercentPower = powerCurrent;
        currentAmountOfPower = (int)(((double)currentPercentPower*maximumPower)/100.0);
        buildingIsOperating = true;
        shortestPathFromGenerator = new ArrayList<Hub>();
    }
    
    
    
    /**
     * Summary: Returns the PIV String name
     * @return the Building name as a String
     */
    public String getName()
    {
        return buildingName;
    }
    
    
    /**
     * Summary: Sets the PIV currentAmountOfPower to the given
     * 			parameter powerAmount, then checks if the building 
     * 			is still operating
     * @param powerAmount: an integer value for the new amount
     * 			of power for the building
     */
    public void setAmountOfPower(int powerAmount)
    {
    	currentAmountOfPower = powerAmount;
    	checkIsOperating();
    }
    
    
    /**
     * Summary: Returns the PIV currentAmountOfPower
     * @return an int value of the current amount of 
     * 			power for the building
     */
    public int getAmountOfPower()
    {
    	return currentAmountOfPower;
    }
    
    
    /**
     * Summary: Returns the PIV int percentCurrent
     * @return the power of the Building as an integer
     */
    public int getPowerPercentage()
    {
        return currentPercentPower;
    }
    
    
    /**
     * Summary: Changes the PIV percentCurrent to the given parameter, then
     * 			checks if the building is still operating
     * @param percent: the new percentage of power for the building
     */
    public void setPowerPercentage(int percent)
    {
        currentPercentPower = percent;
        checkIsOperating();
    }
    
    
    /**
     * Summary: Returns the PIV maximumPower
     * @return: an integer value for the maximum power the
     * 			building holds when it is operating at 100%
     */
    public int getMaxPower()
    {
    	return maximumPower;
    }
    
    
    /**
     * Summary: Returns the PIV minimumPower
     * @return an integer value for the minimum amount of 
     * 			power used by the building for it to operate
     */
    public int getMinimumPercentage()
    {
    	return minimumPower;
    }
    
    
    /**
     * Summary: Checks to see if the building is operating based on 
     *          whether it has the power capacity above the percentage necessary
     * @return whether or not the Building is at operating capacity
     */
    public boolean checkIsOperating()
    {
        buildingIsOperating = currentPercentPower >= minimumPower;
        return buildingIsOperating;
    }
    
    
    /**
     * Summary: Returns the PIV ArrayList path
     * @return an ArrayList of the optimal path taken to get from the 
     * 			Generator to the Building
     */
    public ArrayList<Hub> getPath()
    {
        return shortestPathFromGenerator;
    }
    
    
    /**
     * Summary: Sets PIV path to the given parameter p
     * @param newPath: the new value for PIV shortestPathFromGenerator
     * 					to set for the building
     */
    public void setPath(ArrayList<Hub> newPath)
    {
        shortestPathFromGenerator = newPath;
    }
    
    
    /**
     * Summary: Creates a String representation of the building
     * 			using its name
     * @return the String PIV buildingName
     */
    public String toString()
    {
    	return getName();
    }
    
}