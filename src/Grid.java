import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class Grid {
    
    private Generator home;
    private Map<Hub, HashSet<Hub>> connections;
    private ArrayList<Hub> allPlaces;
    private ArrayList<Building> buildingsList;
    private ArrayList<Terminal> terminalsList;
    private Scanner updateInfo;
    
    
    /**
     * Summary: Creates objects of instances Generator, Building, and Terminal using
     * 			the data provided from parameter buildingsFile. Adds these objects
     * 			into PIV ArrayList allPlaces, and sets PIV Hub Home to the object
     * 			of instance Generator. Then uses the data from parameter connectionsFile
     * 			to create the map and store the connections in PIV Map connections. 
     * 			After all objects and PIV's are created and stored, the method buildPaths
     * 			is called.
     * @param buildingsF: String name for the file with Building information
     * @param connectionsF: String name for the file with Connections information
     * @param updateF: String name for the file with the update information
     * @throws FileNotFoundException
     */
    public Grid (String buildingsF, String connectionsF, String updateF) throws FileNotFoundException
    {
        connections = new HashMap<Hub, HashSet<Hub>>();
        allPlaces = new ArrayList<Hub>();
        buildingsList = new ArrayList<Building>();
        terminalsList = new ArrayList<Terminal>();
        
        File buildingsFile = new File(buildingsF);
        File connectionsFile = new File(connectionsF);
        File updateFile = new File(updateF);  
        
        Scanner buildings = new Scanner(buildingsFile);
        Scanner conn = new Scanner(connectionsFile);
        updateInfo = new Scanner(updateFile);
        
        String firstLine = buildings.nextLine();
        Generator gen = new Generator(Integer.parseInt(firstLine.substring(firstLine.indexOf(",")+1)));
        home = gen;
        allPlaces.add(gen);
                
        int numOfBuildings = buildings.nextInt();
        buildings.nextLine();
        for (int i = 0; i < numOfBuildings; i++)
        {
            String line = buildings.nextLine();
            String[] info = line.split(",");
            Building newBuilding = new Building(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3]));
            allPlaces.add(newBuilding);
            buildingsList.add(newBuilding);
        }
        
        while (buildings.hasNext())
        {
            Terminal newPT = new Terminal(buildings.nextLine());
            allPlaces.add(newPT);
            terminalsList.add(newPT);
        }
        
        
        while(conn.hasNext())
        {
            String line = conn.nextLine();
            String[] parts = line.split("-");
            String[] connect = parts[1].split(",");
            
            Hub a = findHub(parts[0]);
            HashSet<Hub> b = new HashSet<Hub>();
            
            for (int i = 0; i < connect.length; i++)
            {
                b.add(findHub(connect[i]));
            }
            
            connections.put(a, b);
        }

        callToBuildPaths();
        
    }
    
    
    /**
     * Summary: Acts as a private helper method which takes in a String
     * 			and traverses through PIV ArrayList allPlaces to find
     * 			the Hub matching the parameter String name. 
     * @param name: String name for the hub which needs to be returned
     * @return the Hub with the name matching parameter name
     */
    private Hub findHub(String name)
    {
    	for (Hub x: allPlaces)
    	{
    		if (x.getName().equals(name))
    		{
    			return x;
    		}
    	}
        return null;
    }
    
    
    /**
     * Summary: Iterates through the PIV buildingsList and initializes the 
     * 			parameters for buildPaths for each building then
     * 			calls buildPaths
     */
    private void callToBuildPaths()
    {
    	for (Building building: buildingsList)
        {
        	ArrayList<Hub> newBuildingPath = buildPath(home, building);
        	building.setPath(newBuildingPath);
        }
    }
    

    /**
     * Summary: Iterates through every connection of the nodes, starting from
     *          the start node until it finds the path to reach the end node
     * @param start: the node from which the path should start
     * @param end: the node which the path should reach
     * @return ArrayList<Hub> of the shortest path necessary to get from Hub start
     *              to Hub end
     */
    private ArrayList<Hub> buildPath(Hub start, Hub end)
    {
    	ArrayList<Hub> path = new ArrayList<Hub>();
    	ArrayList<Hub> hubsToVisit = new ArrayList<Hub>();
    	HashMap<Hub, Hub> check = new HashMap<Hub, Hub>();
    	Hub current = start;
    	hubsToVisit.add(current);
    	HashSet<Hub> visited = new HashSet<Hub>();
    	visited.add(current);
    	while (!hubsToVisit.isEmpty())
    	{
    		current = hubsToVisit.remove(0);
    		if (current.getName().equals(end.getName()))
    		{
    			break;
    		}
    		else
    		{
    			for (Hub x: connections.get(current))
    			{
    				if (!visited.contains(x))
    				{
    					hubsToVisit.add(x);
    					visited.add(x);
    					check.put(x,  current);
    				}
    			}
    		}
    	}
    	for (Hub h = end; h != null; h = check.get(h))
    	{
    		path.add(h);
    	}
    	
    	ArrayList<Hub> finalPath = new ArrayList<Hub>();
    	while (!path.isEmpty())
    	{
    		finalPath.add(path.remove(path.size()-1));
    	}
    	
    	return finalPath;
    }
    
    
    /**
     * Summary: Uses the two nodeName parameters to connect the 
     * 			nodes in the map (both ways)
     * @param nodeName1: String name of the first node in the connection
     * @param nodeName2: String name of the second node in the connection
     */
    public void addConnection(String nodeName1, String nodeName2)
    {
    	Hub node1 = findHub(nodeName1);
    	HashSet<Hub> connections1 = connections.get(node1);
    	Hub node2 = findHub(nodeName2);
    	HashSet<Hub> connections2 = connections.get(node2);
    	
    	connections1.add(node2);
    	connections2.add(node1);
    	
    	connections.put(node1, connections1);
    	connections.put(node2, connections2);
    	
    	callToBuildPaths();
    }
    
    
    /**
     * Summary: Uses the two nodeName parameters to remove the connection
     * 			between the nodes in the map (both ways)
     * @param nodeName1: String name of the first node in the connection
     * @param nodeName2: String name of the second node in the connection
     */
    public void removeConnection(String nodeName1, String nodeName2)
    {
    	Hub node1 = findHub(nodeName1);
    	HashSet<Hub> connections1 = connections.get(node1);
    	Hub node2 = findHub(nodeName2);
    	HashSet<Hub> connections2 = connections.get(node2);
    	
    	connections1.remove(node2);
    	connections2.remove(node1);
    	
    	connections.put(node1, connections1);
    	connections.put(node2, connections2);
    	
    	callToBuildPaths();
    }
    
    
    /**
     * Summary: Removes the Hub with the name matching
     * 			hubName from allPlaces and from the 
     * 			connections map. Also removes the Hub	
     * 			from any instances of it acting as a connection
     * 			for another Hub
     * @param hubName: String name of the hub which needs to be removed from the grid
     */
    public void removeHub(String hubName)
    {
    	Hub hubToRemove = findHub(hubName);
    	HashSet<Hub> hubConnections = connections.get(hubToRemove);
    	
    	for (Hub x: hubConnections)
    	{
    		HashSet<Hub> values = connections.get(x);
    		values.remove(hubToRemove);
    		connections.put(x,  values);
    	}
    	
    	connections.remove(hubToRemove);
    	allPlaces.remove(hubToRemove);
    	if (buildingsList.contains(hubToRemove))
    	{
    		buildingsList.remove(hubToRemove);
    	}
    	else
    	{
    		terminalsList.remove(hubToRemove);
    	}
    	
    	callToBuildPaths();
    }
    
    
    /**
     * Summary: Adds a hub of type Terminal to the grid
     * @param hubName: String name of the hub which needs to be added to the grid
     */
    public void addHub(String hubName)
    {
    	Terminal newNode = new Terminal(hubName);
    	allPlaces.add(newNode);
        terminalsList.add(newNode);
        connections.put(newNode, new HashSet<Hub>());
    }
    
    
    /**
     * Summary: Changes the power of a given node
     * @param hubName: String name of the hub whose power needs to be adjusted
     * @param amountOfPower: int amount of the new power of the given node in kw/hr
     */
    public void adjustPower(String hubName, int powerAmount)
    {
    	Building destination = (Building) findHub(hubName);
    	
    	destination.setAmountOfPower(powerAmount);
    	destination.setPowerPercentage((int)((double)powerAmount*100.0/destination.getMaxPower()));
    	
    	checkOperatingBuildings();
    }
    
    
    /**
     * Summary: Calculates the amount of power necessary to power the grid using
     *          the current amount of power of the different buildings and their    
     *          shortest paths
     * @return integer value of the total amount of power required to power the
     *          entire grid
     */
    public int getPowerRequired()
    {
    	int totalPower = 0;
    	
    	for(Building x: buildingsList)
    	{
    		int powerForBuilding = 0;
    		int numOfStops = x.getPath().size()-2;
    		powerForBuilding += numOfStops*25;
    		powerForBuilding += x.getAmountOfPower();
    		totalPower += powerForBuilding;
    	}
    	
    	return totalPower;
    }
    
    
    /**
     * Summary: returns the power generated by the power generator
     * @return an integer value of the amount of power generated for the grid
     */
    public int getPower()
    {
        return home.getPower();
    }
    
    
    /**
     * Summary: Checks if the power generator is producing enough power to 
     *          account for all the buildings in the grid.
     * @return a boolean value displaying whether the grid is producing enough 
     *          power to support the buildings
     */
    public boolean checkOperatingPower()
    {
        boolean result = true;
    	int totalPower = getPowerRequired();
    	
    	if (totalPower > home.getPower())
    	{
    		result = false;
    	}
        
    	return result;
    }
    
    
    /**
     * Summary: Recurses through the PIV buildingsList to see if all
     * 			buildings are powered at operating capacity. 
     * @return a boolean value representing whether all buildings in
     * 		   the grid are operating or not. Returns false if one or	
     * 			more buildings are not operating.
     */
    public boolean checkOperatingBuildings()
    {
    	boolean result = true;
    	for (Building x: buildingsList)
    	{
    		boolean running = x.checkIsOperating();
    		if (running == false)
    		{
    			result = false;
    		}
    	}
    	return result;
    }
    
    
    /**
     * Summary: Recurses through PIV buildingsList and checks to see
     * 			which buildings are not operating.
     * @return a String with the names of all buildings which are not 
     * 			operating with at least minimum capacity
     */
    public String getFailingBuildings()
    {
        String result = "";
        for (Building x: buildingsList)
        {
            if (x.checkIsOperating() == false)
            {
                result += x.getName() + " ";
            }
        }
        return result.trim();
    }
    
    
    /**
     * Summary: Recurses through the PIV buildingsList and checks if any 
     * 			buildings have become disconnected from the generator.
     * @return a boolean value displaying whether or not all buildings
     * 			in the grid are connected to the power generator.
     */
    public boolean checkOperatingConnections()
    {
        boolean result = true;
        
        for (Building x: buildingsList)
        {
            if (x.getPath().size() == 1)
            {
                result = false;
            }
        }
        
        return result;
    }
    
    
    /**
     * Summary: Recurses through the PIV buildingsList and checks to see
     * 			which buildings are not in connection with the power generator 
     * 			by checking the value of their shortest path, with a shortest
     * 			path size of 1 indicating that the path was unsuccessfully built
     * 			and the building is not connected to the generator.
     * @return a String of the buildings which are not connected to the power 
     * 			generator
     */
    public String getDisconnectedBuildings()
    {
        String result = "";
        for (Building x: buildingsList)
        {
            if (x.getPath().size() == 1)
            {
                result += x.getName() + " ";
            }
        }
        return result.trim();
    }
    
    
    /**
     * Summary: Creates a display of all the Hubs in the power grid, including the 
     * 			power generator and how much power it is producing, how much power the 
     * 			system is using, the buildings and their name, maximum power, minimum power
     * 			percentage, and current power percentage, as well as a list of all the terminals.
     * @return a String display of the colony Hub information
     */
    public String displayInformation()
    {
        String result = "";
    	result += home.getName() + " is producing " + home.getPower() + " kw/hr" + "\n";
        result += "Power Required: " + getPowerRequired() + " kw/hr" + "\n";
    	result += "\n";
    	
    	for (Building x: buildingsList)
    	{
    		result += x.getName() + "  " + x.getMaxPower() + "  " + x.getMinimumPercentage() + "  " + x.getPowerPercentage() + "\n";
    	}
    	result += "\n";
    	
    	for (Terminal x: terminalsList)
    	{
    		result += x.getName() + "   " + "\n";
    	}
        
        return result;
    }
    
    
    /**
     * Summary: Iterates through the PIV allPlaces and creates a display of the 
     * 			PIV HashMap connections, displaying each of the Hubs in the 
     * 			colonies and which Hubs they are each connected to.
     * @return a String display of the power grid connection information
     */
    public String displayConnections()
    {
        String result = "";
        
    	for (Hub x: allPlaces)
    	{
    		result += x + " - ";
    		String connect = "";
    		for (Hub y: connections.get(x))
    		{
    			connect += y + ", ";
    		}
    		if (connect.length() >= 2)
    		{
        		result += connect.substring(0, connect.length()-2) + "\n";
    		}
    	}
        
        return result;
    }
    
    
    /**
     * Summary: Iterates through the PIV buildingsList to create a display
     * 			of the shortest path from the power generator to the given 
     * 			building for each of the buildings. 
     * @return a String display of the most optimal paths power should take to
     * 			reach each building.
     */
    public String displayShortestPaths()
    {
        String result = "";
        
    	for (Building b: buildingsList)
    	{
            if (b.getPath().size() == 1)
            {
                result += b + ": " + "ERROR" + "\n";
            }
            else
            {
    		result += b + ": " + b.getPath() + "\n";
            }
    	}
        
        return result;
    }
    
    
    /**
     * Summary: Takes in information using the PIV Scanner updateInfo, and 
     * 			updates the grid by adjusting power, removing connections, and 
     * 			power terminals. 
     * @return a String of the connections which were removed and the Power
     * 			Terminals which were removed, separated by a '/'
     */
    public String updateGrid()
    {
        String result = "";
        
        String nextLine = updateInfo.nextLine();
        
        if(nextLine.equals("**")) //only for first instance
        {
        	nextLine = updateInfo.nextLine();
        }
        
        String powerGeneratorInfo = nextLine;
        int newPowerAmount = Integer.parseInt(powerGeneratorInfo.split(",")[1]);
        home.setPower(newPowerAmount);
        
        nextLine = updateInfo.nextLine(); //X
        nextLine = updateInfo.nextLine(); //info for removed connections
        
        result += "Lost Connections: \n";
        while(!nextLine.equals("XX"))
        {
            result += "     " + nextLine + " \n";
            String[] toRemove = nextLine.split(",");
            removeConnection(toRemove[0], toRemove[1]);
            nextLine = updateInfo.nextLine();
        }
        
        result += "/";
        nextLine = updateInfo.nextLine();
        
        result += "Lost Terminals: \n";
        while(updateInfo.hasNext() && !nextLine.equals("**"))
        {
            result += "     " + nextLine + " \n";
            String terminalToRemove = nextLine;
            removeHub(terminalToRemove);
            nextLine = updateInfo.nextLine();
        }
        
        callToBuildPaths();
        checkOperatingPower();
        checkOperatingConnections();
        
        return result;
    }
    
    
    /**
     * Summary: Rewrites the files "Buildings.dat" & "Connections.dat" with the
     * 			updated information from the grid using the PIVs connections, 
     * 			allPlaces, buildingsList, and terminalsList.
     * @throws IOException
     */
    public void rewriteFile() throws IOException
    {
    	FileWriter bw = new FileWriter("Buildings.dat", false);
    	PrintWriter outBuildings = new PrintWriter(bw);
    	String generatorInfo = home.getName() + "," + home.getPower();
    	String numBuildings = buildingsList.size() + "";
    	outBuildings.println(generatorInfo);
    	outBuildings = new PrintWriter(bw, true);
    	outBuildings.println(numBuildings);
    	outBuildings = new PrintWriter(bw, true);
    	for(Building x: buildingsList)
    	{
    		String buildingInfo = x.getName() + "," + x.getMaxPower() + "," + (100 - x.getMinimumPercentage()) + "," + x.getPowerPercentage();
    		outBuildings.println(buildingInfo);
    		outBuildings = new PrintWriter(bw, true);
    	}
    	for(Terminal x: terminalsList)
    	{
    		String terminalInfo = x.getName();
    		outBuildings.println(terminalInfo);
    		outBuildings = new PrintWriter(bw, true);
    	}
    	
    	outBuildings.close();
    	bw.close();
    	
    	FileWriter cw = new FileWriter("Connections.dat", false);
    	PrintWriter outConnections = new PrintWriter(cw);
    	for(Hub x: allPlaces)
    	{
    		String connectionsInfo = x.getName() + "-";
    		for (Hub y: connections.get(x))
    		{
    			connectionsInfo += y.getName() + ",";
    		}
    		connectionsInfo = connectionsInfo.substring(0, connectionsInfo.length()-1);
    		outConnections.println(connectionsInfo);
    		outConnections = new PrintWriter(cw, true);
    	}	
    	
    	outConnections.close();
    	cw.close();
    }
}