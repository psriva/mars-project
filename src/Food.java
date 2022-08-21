import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 179923
 */
public class Food implements Comparable<Food>
{
    private String foodName;
    private String foodType;
    private double expiry;
    private int servings;
    
    /**
     * Summary: Creates an object of Food using the private instance variables above
     * @param foodName: name of food
     * @param foodType: type of food
     * @param expiry: expiry time (in years)
     * @param servings: amount of servings for food
     */
    public Food(String foodName, String foodType, double expiry, int servings)
    {
        this.foodName = foodName;
        this.foodType = foodType;
        this.expiry = expiry;
        this.servings = servings;
    }
    
    /**
     * Summary: gets and returns the name of the food
     * @return the name of the food as a String
     */
    public String getFoodName()
    {
        return foodName;
    }
    
    /**
     * Summary: gets and returns the foodType
     * @return "Protein", "Veggie/Fruit", or "Starch", depending on the food
     */
    public String getFoodType()
    {
        return foodType;
    }
    
    /**
     * Summary: gets and returns the expiry time of the food
     * @return the expiry time of the food as a double
     */
    public double getExpiry()
    {
        return expiry;
    }
    
    /**
     * Summary: gets and returns the amount of servings for the food
     * @return the amount of servings as an int 
     */
    public int getServings()
    {
        return servings;
    }
    
    /**
     * Summary: sets the amount of servings for the food to newServings
     * @param newServings: the new amount of servings
     */
    public void setServings(int newServings)
    {
        servings = newServings;
    }
    
    /**
     * Summary: creates a string of the foodName, foodType, expiry, and servings to display
     * @return the String representation of a food object
     */
    public String toString()
    {
        return foodName + " " + foodType + " " + expiry + " " + servings + "\n";
    }
	
    /**
    * Summary: compares the Food otherFood to the current food by comparing the expiry dates. 
    * @param otherFood: the food being compared to the current food
    * @return If the expiry date is greater for otherFood, returns -1, returns 0 if they're equal, and returns 1 if otherFood expiry date is less than current expiry date  
    */ 
    public int compareTo(Food otherFood) 
    {
	if(this.getExpiry() == otherFood.getExpiry())
        {
            return 0;
        }
        else if(this.getExpiry() > otherFood.getExpiry())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}