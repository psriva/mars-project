import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 179923
 */


public class MealType 
{
    private PriorityQueue<Food> foodsOfType;  
    
    /**
     * Summary: Initializes foodsOfType to create an object of MealType
     */
    public MealType() throws FileNotFoundException
    {
        foodsOfType = new PriorityQueue<Food>();
    }
    
    /**
     * Summary: Adds foodToAdd into the priority queue
     * @param foodToAdd: the food being added to the queue
     */
    public void addFood(Food foodToAdd)
    {
        foodsOfType.add(foodToAdd);
    }
    
    /**
     * Summary: gets the queue foodsOfType and returns it
     * @return the queue foodsOfType
     */
    public PriorityQueue<Food> getQueue()
    {
        return foodsOfType;
    }
    
    /**
     * Summary: removes the food with the shortest expiry date (compareTo(Food otherFood) method created in Food class)
     * @return the Food object that is removed from the queue 
     */
    public Food removeFood()
    {
        return foodsOfType.remove();
    }
    
    /**
     * Summary: returns the food with the shortest expiry date without removing it
     * @return the Food object that has the shortest expiry date in the queue 
     */
    public Food seeFood()
    {
    	return foodsOfType.peek();
    }
    
    /**
     * Summary: loops through the queue to return the foodsofType queue as a String 
     * @return the foodsOfType queue in String form
     */
    public String toString()
    {
        String result = "";
        while(foodsOfType.isEmpty())
        {
            result += foodsOfType.remove().toString() + "\n";
        }
        return result;
    }
}