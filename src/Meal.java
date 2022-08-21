
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 179923
 */
public class Meal 
{
    private MealType protein;
    private MealType fruitOrVeggie;
    private MealType starch;
    private SingleLinkedList<Food> meal;
    private Colony colony;
    
    /**
     * Summary: creates an object of Meal using the private instance variables above and initializes the meal linked list and the Colony class object
     * @param protein: all of the foods that have Protein meal type
     * @param fruitOrVeggie: all of the foods that have Veggie/Fruit meal type
     * @param starch: all of the foods that have Starch meal type 
     */
    public Meal(MealType protein, MealType fruitOrVeggie, MealType starch) throws FileNotFoundException
    {
        this.protein = protein;
        this.fruitOrVeggie = fruitOrVeggie;
        this.starch = starch;
        meal = new SingleLinkedList<Food>();
        colony = new Colony();
    }
    
    /**
     * Summary: gets the three foods from each meal type which have the lowest expiry time in their respective groups and adds them into the meal linked list
     */
    public void getThreeFoods() throws IOException
    {
        System.out.println(colony.size());
        meal.add(protein.seeFood());
        meal.add(fruitOrVeggie.seeFood());
        meal.add(starch.seeFood());
    }
    
    /**
     * Summary: gets the mealType of food findMealTypeOf using the getFoodType() method 
     * @param findMealTypeOf: the food whose mealType needs to be found
     * @return the MealType priorityQueue for the respective foodType
     */
    public MealType getMealType(Food findMealTypeOf)
    {
    	if(findMealTypeOf.getFoodType().equals("Protein"))
    	{
            return protein;
    	}
    	else if(findMealTypeOf.getFoodType().equals("Fruit/Veggie"))
    	{
            return fruitOrVeggie;
    	}
    	else
    	{
            return starch;
    	}
    }
    
    /**
     * Summary: creates the menu with protein, veggie/fruit, and starch by comparing the servings to the amount of people who need to be fed (from the Colony class object). 
     * Pulls another food if there's not enough servings for one food 
     */
    public void createMenu() throws IOException
    {
        getThreeFoods();
        for(int i = meal.size() - 1; i >= 0; i--)
        {
            if(meal.get(i).getServings() > colony.size())
            {
                meal.get(i).setServings(meal.get(i).getServings() - colony.size());
            }
            else if(meal.get(i).getServings() == colony.size())
            {
            	meal.get(i).setServings(0);
            	getMealType(meal.get(i)).removeFood();
            }
            else
            {
                MealType type = getMealType(meal.get(i));
                type.removeFood();
                Food nextFood = type.seeFood();
                nextFood.setServings(nextFood.getServings() - (colony.size() - meal.get(i).getServings()));
                meal.add(nextFood);
            }
        }
    }
    
    /**
     * Summary: returns the String representation of the meal linked list
     * @return a String filled with the different foods in the day's meal 
     */
    public String toString()
    {
    	String result = "";
    	for(int i = 0; i < meal.size() - 1; i++)
    	{
            result += meal.get(i).getFoodName() + "; ";
    	}
    	result += meal.get(meal.size() - 1).getFoodName();
    	return result;
    }
}  
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                