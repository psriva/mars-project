/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 236605
 */
public class Colonist 
{
    private String firstName;
    private String lastName;
    private String rankID;
    private String job;
    /**
     * Summary: builds Colonist object and defines firstName, lastName, rankID, and job PIVs
     * @param name; full name of Colonist
     * @param rank; rankID of Colonist
     * @param jobs; Job of Colonist
     */
    public Colonist(String name, String rank, String jobs)
    {
        String[] names = name.split(" ");
        firstName = names[0];
        lastName = names[1];
        rankID = rank;
        job = jobs;
    }
    /**
     * Summary: gets firstName PIV
     * Return: first name of Colonist
     */
    public String getFirstName()
    {
        return firstName;
    }
    /**
     * Summary: gets lastName PIV
     * Return: last name of Colonist
     */
    public String getLastName()
    {
        return lastName;
    }
    /**
     * Summary: concatenates firstName PIV and lastName PIV and returns
     * Return: full name of Colonist
     */
    public String getName()
    {
        return firstName + " " + lastName;
    }
    /**
     * Summary: sets lastName PIV to newName parameter
     * @param newName; new last name of Colonist
     */
    public void setLastName(String newName)
    {
        lastName = newName;
    }
    /**
     * Summary: gets rankID PIV
     * Return: rankID of Colonist
     */
    public String getRankID()
    {
        return rankID;
    }
    /**
     * Summary: sets rankID PIV to newRankID parameter
     * @param newRankID; new rankID of Colonist
     */
    public void setRankID(String newRankID)
    {
        rankID = newRankID;
    }
    /**
     * Summary: gets job PIV
     * Return: job of Colonist
     */
    public String getJob()
    {
        return job;
    }
    /**
     * Summary: prints all PIVs for Colonist
     * Return: first name, last name, rankID, and job of Colonist
     */
    public String toString()
    {
        return firstName + " " + lastName + "\n" + rankID + "\n" + job;
    }
}
