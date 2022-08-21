import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 236605
 */
public class Colony 
{
    private HashSet<Colonist> colonies;
    private ArrayList<Colonist> colonyList;
    /**
     * Summary: builds Colony object and populates colonies PIV with data from Colonists.dat file
     */
    public Colony() throws FileNotFoundException
    {
        colonies = new HashSet<Colonist>();
        Scanner file = new Scanner(new File("Colonists.dat"));
        while(file.hasNextLine())
        {
            String name = file.nextLine();
            String rankID = file.nextLine();
            String job = file.nextLine();
            if(file.hasNextLine())
            {
                file.nextLine();
            }
            Colonist colonistInstance = new Colonist(name, rankID, job);
            colonies.add(colonistInstance);
        }
    }
    /**
     * Summary: determines whether given ID belongs to a Colonist in colonies
     * @param ID; building name where a select group of Colonists work
     * Return: true or false, depending on if given ID belongs to a real colonist
     */
    public boolean validIdentity(String ID)
    {
        if(ID.charAt(0) <= 64)
        {
            for(Colonist col: colonies)
            {
                if(col.getRankID().equals(ID))
                {
                    return true;
                }
            }
            return false;
        }
        else
        {
            for(Colonist col: colonies)
            {
                if(col.getLastName().equals(ID))
                {
                    return true;
                }
            }
            return false;
        }
    }
    /**
     * Summary: displays and orders Top Commander, 2nd Commander, and Department Heads in colonies PIV
     * Return: Top Commander, 2nd Commander, and Department Heads in Colony
     */
    public String leadership() throws FileNotFoundException, IOException
    {
        Scanner console = new Scanner(new File("AssignedCleaners.dat"));
        while(console.hasNextLine())
        {
            String name = console.nextLine();
            String rankID = console.nextLine();
            String job = console.nextLine();
            if(console.hasNextLine())
            {
                console.nextLine();
            }
            Colonist colonistInstance = new Colonist(name, rankID, job);
            colonies.add(colonistInstance);
        }
        ArrayList<String> ranks = new ArrayList<String>();
        Scanner file = new Scanner(new File("Ranks.dat"));
        ArrayList<String> leaders = new ArrayList<String>();
        String answer = "";
        boolean topCommanderFound = false;
        while(file.hasNextLine())
        {
            ranks.add(file.nextLine());
        }
        for(Colonist col: colonies)
        {
            for(int j = 0; j < ranks.size(); j++)
            {
                String[] rank = new String[colonies.size()];
                rank[0] = ranks.get(j).substring(0,2);
                rank[1] = ranks.get(j).substring(3);
                if(col.getRankID().substring(0,2).equals("00"))
                {
                    continue;
                }
                else if(col.getRankID().substring(0,2).equals(rank[0]))
                {
                    String result = col.getName() + " - " + rank[1];
                    if(rank[0].equals("01"))
                    {
                        leaders.add(0, result);
                        topCommanderFound = true;
                    }
                    else if(rank[0].equals("02"))
                    {
                        if(topCommanderFound)
                        {
                            leaders.add(1, result);
                        }
                        else
                        {
                            leaders.add(0,result);
                        }
                    }     
                    else
                    {
                        leaders.add(result);
                    }
                }
            } 
        }
        for(int i = 0; i < leaders.size() - 1; i++)
        {
            answer += leaders.get(i) + "\n";
        }
        answer += leaders.get(leaders.size()-1);
        return answer;
    }
    /**
     * Summary: displays all Colonists working in building designated by buildingName parameter
     * @param buildingName; building name where a select group of Colonists work
     * Return: Department Head and all Colonists working in the buildingName parameter building
     */
    public String building(String buildingName) throws FileNotFoundException, IOException
    {
        Scanner reader = new Scanner(new File("AssignedCleaners.dat"));
        while(reader.hasNextLine())
        {
            String name = reader.nextLine();
            String rankID = reader.nextLine();
            String job = reader.nextLine();
            if(reader.hasNextLine())
            {
                reader.nextLine();
            }
            Colonist colonistInstance = new Colonist(name, rankID, job);
            colonies.add(colonistInstance);
        }
        ArrayList<String> buildings = new ArrayList<String>();
        Scanner file = new Scanner(new File("BuildingsPersonnel.dat"));
        ArrayList<String> workers = new ArrayList<String>();
        String department = "";
        String answer = "";
        while(file.hasNextLine())
        {
            buildings.add(file.nextLine());
        }
        for(int i = 0; i < buildings.size(); i++)
        {
            if(buildingName.equals(buildings.get(i).substring(3)))
            {
                department = buildings.get(i).substring(0,2);
            }
        }
        Scanner console = new Scanner(new File("Ranks.dat"));
        ArrayList<String> ranks = new ArrayList<String>();
        String leader = "";
        while(console.hasNextLine())
        {
            ranks.add(console.nextLine());
        }
        for(int i = 0; i < ranks.size(); i++)
        {
            if(ranks.get(i).contains(buildingName))
            {
                leader = ranks.get(i).substring(0,2);
            }
        }
        for(Colonist col: colonies)
        {
            if(col.getRankID().substring(2,4).equals(department))
            {
                String result = col.getName() + " - " + col.getJob();
                if(col.getRankID().substring(0,2).equals(leader))
                {
                    workers.add(0, result + " (Department Head)");
                }
                else
                {
                    workers.add(result);
                }
            }
        }
        if(workers.size() == 0)
        {
            return "No Workers In Building";
        }
        else if(workers.size() == 1)
        {
            answer += workers.get(0);
            return answer;
        }
        for(int i = 0; i < workers.size() - 1; i++)
        {
            answer += workers.get(i) + "\n";
        }
        answer += workers.get(workers.size() - 1);
        return answer;
    }
    /**
     * Summary: Changes the rankID of a Colonist based on the newLocation parameter
     * @param ID; either rankID or lastName of selected Colonist
     * @param newLocation; new building for selected Colonist to be moved to
     */
    public void jobLocationChange(String ID, String newLocation) throws IOException
    {
        Scanner console = new Scanner(new File("AssignedCleaners.dat"));
        while(console.hasNextLine())
        {
            String name = console.nextLine();
            String rankID = console.nextLine();
            String job = console.nextLine();
            if(console.hasNextLine())
            {
                console.nextLine();
            }
            Colonist colonistInstance = new Colonist(name, rankID, job);
            colonies.add(colonistInstance);
        }

        if(validIdentity(ID))
        {   
            if(ID.charAt(0) <= 64)
            {
                String newRank = ID.substring(0,2) + newLocation + ID.substring(4);
                for(Colonist col: colonies)
                {
                    if(col.getRankID().equals(ID))
                    {
                        col.setRankID(newRank);
                    }
                }
            }
            else
            {
                for(Colonist col: colonies)
                {
                    if(col.getLastName().equals(ID))
                    {
                        String newRank = col.getRankID().substring(0,2) + newLocation + col.getRankID().substring(4);
                        col.setRankID(newRank);
                    }
                }
            }
            FileWriter fw = new FileWriter("Colonists.dat", false);
            PrintWriter output = new PrintWriter(fw);
            for(Colonist col: colonies)
            {
                output.println(col + "\n");
                output = new PrintWriter(fw, true);
            }
            output.close( );
            fw.close( );
        }
    }
    /**
     * Summary: Changes the rankID of a Colonist based on the newPosition parameter
     * @param ID; either rankID or lastName of selected Colonist
     * @param newPosition; new Position for selected Colonist to be moved to
     */
    public void promote(String ID, String newPosition) throws FileNotFoundException, IOException
    {
        Scanner console = new Scanner(new File("AssignedCleaners.dat"));
        while(console.hasNextLine())
        {
            String name = console.nextLine();
            String rankID = console.nextLine();
            String job = console.nextLine();
            if(console.hasNextLine())
            {
                console.nextLine();
            }
            Colonist colonistInstance = new Colonist(name, rankID, job);
            colonies.add(colonistInstance);
        }
        ArrayList<String> rankBuildings = new ArrayList<String>();
        rankBuildings.add("00 00");
        rankBuildings.add("01 10");
        rankBuildings.add("02 20");
        rankBuildings.add("11 30");
        rankBuildings.add("21 40");
        rankBuildings.add("31 50");
        rankBuildings.add("41 60");
        rankBuildings.add("51 70");
        String newLocation = "";
        for(int i = 0; i < rankBuildings.size(); i++)
        {
            if(rankBuildings.get(i).substring(0,2).equals(newPosition))
            {
                newLocation = rankBuildings.get(i).substring(3);
            }
        }
        if(validIdentity(ID))
        {
            if(ID.charAt(0) <= 64)
            {
                String newRank = newPosition + ID.substring(2);
                for(Colonist col: colonies)
                {
                    if(col.getRankID().equals(ID))
                    {
                        
                        newRank = newPosition + newLocation + ID.substring(4);
                        col.setRankID(newRank);
                    }
                }
            }
            else
            {
                for(Colonist col: colonies)
                {
                    if(col.getLastName().equals(ID))
                    {
                        String newRank = newPosition + newLocation + col.getRankID().substring(4);
                        col.setRankID(newRank);
                    }
                }
            }
            FileWriter fw = new FileWriter("Colonists.dat", false);
            PrintWriter output = new PrintWriter(fw);
            for(Colonist col: colonies)
            {
                output.println(col + "\n");
                output = new PrintWriter(fw, true);
            }
            output.close( );
            fw.close( );
        }
    }
    /**
     * Summary: Changes the rankID of a Colonist based on the newPosition parameter
     * @param ID; either rankID or lastName of selected Colonist
     */
    public void deceased(String ID) throws FileNotFoundException, IOException
    {
        Scanner console = new Scanner(new File("AssignedCleaners.dat"));
        while(console.hasNextLine())
        {
            String name = console.nextLine();
            String rankID = console.nextLine();
            String job = console.nextLine();
            if(console.hasNextLine())
            {
                console.nextLine();
            }
            Colonist colonistInstance = new Colonist(name, rankID, job);
            colonies.add(colonistInstance);
        }
        if(validIdentity(ID))
        {
            ArrayList<Colonist> death = new ArrayList<Colonist>();
            if(ID.charAt(0) <= 64)
            {
                String newRank = "0000" + ID.substring(4);
                for(Colonist col: colonies)
                {   
                    if(col.getRankID().equals(ID))
                    {
                        death.add(col);
                        colonies.remove(col);
                        col.setRankID(newRank);
                        break;
                    }
                }
            }
            else
            {
                for(Colonist col: colonies)
                {
                    if(col.getLastName().equals(ID))
                    {
                        String newRank = "0000" + col.getRankID().substring(4);
                        death.add(col);
                        colonies.remove(col);
                        col.setRankID(newRank);
                        break; 
                    }
                }
            }
            FileWriter fw = new FileWriter("Colonists.dat", false);
            PrintWriter output = new PrintWriter(fw);
            for(Colonist col: colonies)
            {
                output.println(col + "\n");
                output = new PrintWriter(fw, true);
            }
            FileWriter df = new FileWriter("Memorials.dat", true);
            PrintWriter outdeaths = new PrintWriter(df);
            for(Colonist d: death)
            {
                outdeaths.println(d + "\n");
                outdeaths = new PrintWriter(df, false);
            }
            output.close( );
            fw.close( );
            outdeaths.close( );
            df.close( );
        }
    }
    /**
     * Summary: designates and displays Colonist names assigned to cleaning duty for Living Space A and Living Space B
     * Return: String containing three Colonist names assigned to Living Space and three assigned to Living Space B
     */
    public String cleaningDuty() throws FileNotFoundException, IOException
    {
        if(colonies.size() <= 6)
        {
            Scanner console = new Scanner(new File("AssignedCleaners.dat"));
            while(console.hasNextLine())
            {
                String name = console.nextLine();
                String rankID = console.nextLine();
                String job = console.nextLine();
                if(console.hasNextLine())
                {
                    console.nextLine();
                }
                Colonist colonistInstance = new Colonist(name, rankID, job);
                colonies.add(colonistInstance);
            }
         FileWriter aw = new FileWriter("AssignedCleaners.dat", false);
        PrintWriter outClean = new PrintWriter(aw);
        outClean.println("");
        outClean = new PrintWriter(aw, true);
        }
        String answer = "Living Space A     Living Space B" + "\n";
        ArrayList<Colonist> cleaners = new ArrayList<Colonist>();
        Colonist[] colonists = colonies.toArray(new Colonist[colonies.size()]);
        for(int i = 0; i < 6; i++)
        {
            cleaners.add(colonists[i]);
            colonies.remove(colonists[i]);
        }
        for(int n = 0; n < 6; n+=2)
        {
            answer += cleaners.get(n).getName() + "     " + cleaners.get(n+1).getName();
            if(n < 4)
            {
                answer += "\n";
            }
        }
        FileWriter cw = new FileWriter("Colonists.dat", false);
        PrintWriter outCol = new PrintWriter(cw);
        for(Colonist col: colonies)
        {
            outCol.println(col + "\n");
            outCol = new PrintWriter(cw, true);
        }
        outCol.close( );
        cw.close( );
        FileWriter fw = new FileWriter("AssignedCleaners.dat", true);
        PrintWriter output = new PrintWriter(fw);
        for(Colonist col: cleaners)
        {
            output.println(col + "\n");
            output = new PrintWriter(fw, false);
        }
        output.close( );
        fw.close( );
        return answer;
    }
    /**
     * Summary: displays the number of colonists in the colony PIV
     * Return: Number of colonists in the colony PIV
     */
    public int size() throws IOException
    {
        Scanner console = new Scanner(new File("AssignedCleaners.dat"));
        if(console.hasNextLine())
        {
            while(console.hasNextLine())
            {
                String name = console.nextLine();
                String rankID = console.nextLine();
                String job = console.nextLine();
                if(console.hasNextLine())
                {
                    console.nextLine();
                }
                Colonist colonistInstance = new Colonist(name, rankID, job);
                colonies.add(colonistInstance);
            }
        }
        return colonies.size();
    }
}
