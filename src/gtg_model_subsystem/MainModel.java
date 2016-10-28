package gtg_model_subsystem;

import java.awt.Point;
import java.awt.geom.Point2D;


import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.LinkedHashMap;
/**
 * MainModel is the subsystem within GTG that is a facade class as well as a singleton.
 * This class implements the back end portions of GTG.
 */
public class MainModel {
	
	//Nodes and edges to used for temporary manipulation throughout class
	private List<Node> nodes;
	private List<Edge> edges;
	
	//The a temporary coordinate graph that can be used to store new graphs
	//as they are loaded in
	private CoordinateGraph graph;

	//The main list of admins that are used throughout the application
	private List<Admin> admins;
	
	//The type of processing system we use, this can change since it uses the strategy pattern.
	private ProcessingSystem fileProcessing;
	
	//The main map table that will store all of the string representations for the maps, as well
	//as the associated Map objects
	private Hashtable<String, Map> mapTable;
	
	//Multipath object that will do all of the required path operations for GTG.
	private MultiPath multiPath;
	
	//Error log object to log all of the errors found in the model
	private ErrorLog logError = new ErrorLog();
	
	
	private MainModel(){
		//Singleton instances for the admin list and map table
		admins = AdminList.getInstance();
		mapTable = MapTable.getInstance();
		
		//Forcing the use of the file processing system
		fileProcessing = new ProcessingSystem("file");
		
		//Load all the required information from the files:
		//First: the list of maps that will be used in GTG
		//Second: the admin list
		//Third: the node and edge text files
		loadMapLists();
		loadAdmin();			
		loadFiles();
	}
	
	/**
	 * The next two methods are place holders to create a singleton instance
	 */
    private static class MainModelHolder{
    	private static final MainModel mainModel = new MainModel();
    }
    
    /**
     * The getInstance method returns a singleton instance of a MainModel object
     * @return the singleton instance of the MainModel object
     */
    public static MainModel getInstance(){
    	return MainModelHolder.mainModel;
    }
    
	/**
	 * Load maps from master map text file and store into table of maps
	 * @return true if load and store the map table successfully
	 * 		   false if maps does not exist */
	public boolean loadMapLists()
	{
		ArrayList<Map> masterMapList=null;
		//load maps from master text file
		masterMapList=fileProcessing.loadMapList();		
		
		//IF the master map list failed to load THEN
		if(masterMapList==null){
			return false;
		} else{
			//access each map and store it into map table
			for(Map map:fileProcessing.loadMapList()){
				mapTable.put(map.getMapName(), map);
			}
			
			return true;
		}
	}
	/**
	 * The deleteMap method will delete map from the map table and the master list text file
	 * @param mapName the name of the map the admin wishes to delete
	 * @return true if deletion was successful false otherwise * @throws IOException  
	 * */
	public boolean deleteMap(String mapName){
			boolean deleteSuccess = true;
			if(mapTable.get(mapName) == null){
				System.out.println("Map does not exist");
				deleteSuccess = false;
			}
			//IF map exists in map table THEN
			if(mapTable.get(mapName) != null){
				//REMOVE map from map table
				mapTable.remove(mapName);
				//REMOVE map from master map list so it will not be re-loaded
				fileProcessing.deleteMapFromMaster(mapName);
			}
			return deleteSuccess;
	}
	
	/**
	 * Method to save newly created maps added by admin from admin view to the list of maps.
	 * @param mapName the name of new map to be saved
	 * @param mapType the type of map that we are saving
	 * @param mapImgURL String
	 * @return true if map was successfully created false otherwise * @throws IOException  */
	public boolean saveNewMap(String mapName, String mapImgURL, String mapType) throws IOException{
				boolean saveNewMap = true;
				//IF map does not exist in map table THEN
				if (mapTable.get(mapName) != null){
					logError.logError("Map already exists");
					saveNewMap = false;
				}
				//STORE map in masterMapList 
				else{
					fileProcessing.saveMapToMaster(mapName, mapImgURL, mapType);
				}
				return saveNewMap;
	}
	
	/**
	 * The method load files will load of the map graphs for each of the master maps from the master map list.
	 * Calls the createMapGraph method to achieve this.
	 * @return true if successful and false if otherwise.
	 */
	public boolean loadFiles(){
		//Signal if the map creation was successful or will fail
		boolean mapCreated = false;
		try{
			//FOR EACH map within the master map list
			for(String mapName: mapTable.keySet()){
				//CALL the createMapGraph method to load the map
				//Will return true of false based on success or failure
				mapCreated = createMapGraph(mapName);
			}
		}catch(Exception e){
			//Catch the exception and print it to the er
			logError.logError((e.toString()));
		}		
		return mapCreated;		
	}
	
	/**
	 * Method loadFiles calls the createMapGraph method that will create all of the map graphs for each map.
	 * @param mapName String the name of the map you with to load.
	 * @return boolean true if successful; false otherwise.
	 */
	public boolean loadFiles(String mapName){
		boolean mapCreated = false;
		
		//CALL the createMapGraph method to LOAD and STOR the map information into the map table
		mapCreated = createMapGraph(mapName);
		
		return mapCreated;		
	}
	/**
	 * Method loadAdmin will load all of the admins that can access the admin feature from the fileprocessing system.
	 */
	public void loadAdmin(){
		fileProcessing.readAdmin(admins);
	}
	
	/**
	 * Method createMapGraph will load all of the associated graph information from the processing system of your choice.
	 * Then it will store the temporary nodes and edges for that graph into the master map table.
	 * @param mapName String the name of the map you wish to save
	 * @return boolean returns true if successful; false otherwise
	 */
	public boolean createMapGraph(String mapName){
		
		//Re-instaniate a new temporary node and edges list
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		
		//Instantiate a boolean object as true for success
		boolean createMapGraphSuccess = true;
		
		//IF the map for the given map name already exists THEN
		if(!mapTable.containsKey(mapName)){
			//SIGNAL that it was unsuccessful saving the loaded map into the table
			createMapGraphSuccess = false;
			
			//PRINT it to the error log
			logError.logError("Map does not exist in table");
			
			//RETURN the error
			return createMapGraphSuccess;
		}
		else{
			//LOAD the graph information for the given map into the temporary nodes and edges
			fileProcessing.readGraphInformation(nodes, edges, mapName);
			
			//CREATE a new graph object to store
			graph = new CoordinateGraph(nodes, edges);
			
			//STORE the newly created graph into the map table
			mapTable.get(mapName).setGraph(graph);
		}
		
		return createMapGraphSuccess;
	}
	
	/**
	 * The saveMapGraph method will save the temporarily stored nodes and edges from the controller into their 
	 * appropriate text files based on mapName. First, it will generate the node list by seeing if there is already
	 * a unique node on maps node list. Secondly, it will generate the edge list based on unique node identifiers in the
	 * node list. Lastly, it will save the newly created nodes and edges to their appropriate map specified text files.
	 * @param mapName the name of the map that the nodes and edges belong to
	 * @param tempPntList the temporary points that need to be converted to nodes and saved.
	 * @param tempEdgeList the temporary list that has unique node IDs to generate edges.
	 * @return true of write was successful, false otherwise * @throws IOException 
	 * 
	 */
	public boolean saveMapGraph(String mapName, List<Node> tempNodeList, List<Edge> tempEdgeList){	
		
		//Generate a new coordinate graph to be saved by admin
		graph = new CoordinateGraph(tempNodeList, tempEdgeList);
		
		//STORE it as the new graph and override the graph information that was created on load
		mapTable.get(mapName).setGraph(graph);
		
		
		try{
			//STORE the new nodes and edges
			fileProcessing.saveGraphInformation(mapTable.get(mapName).getGraph().getNodes(), 
												mapTable.get(mapName).getGraph().getEdges(), 
												mapName);
		}
		catch(Exception e){
			//LOG the error that has occurred during save
			logError.logError(e.toString());
			return false;
		}
	
		return true;
	}
	
	/**
	 * The multiPathCalculate method will take in start and end points and run the multiple path algorithim associated with the multiPath object.
	 * It will then store all associated paths with the start and end location
	 * @param startNode the start Node
	 * @param endNode the end Node
	 * @return true if calculating the multiple paths was successful
	 *         false if calculation fails 
	 */
	public boolean multiPathCalculate(Node start, Node end){
		
		//SIGNAL true for the base case
		boolean multiPathCalculateSuccess = true;

		//CREATE a new MultiPath object for these two given nodes
		multiPath = new MultiPath();
		
		//RUN the multiPathCalculate method to generate the multiple paths
		multiPathCalculateSuccess = multiPath.multiPathCalculate(start, end, mapTable);
		
		return multiPathCalculateSuccess;
	}
	
	/**
	 * isValidAdmin method validates if the user that has chosen to login as admin is an admin.
	 * @param userName the string representation of the login username
	 * @param password the string representation of the login password
	 * @return true if user is admin; otherwise false. 
	 */
	public boolean isValidAdmin(String userName, String password){
		//Assume user is not an admin
		boolean isAdmin = false;
		
		//FOR EACH admin in admin list
		for(Admin admin: admins){
			
			//IF current admin user name EQUALS param username AND admin password EQUALS param password
			if((admin.getUsername().equals(userName)) && (admin.getPassword().equals(password))){
				//SET isAdmin as valid
				isAdmin =true;
			}
		}
		
		return isAdmin;
	}
	
	/**
	 * Validates the point for the view. By returning a Node for the controller to extract a point.
	 * Take in x and y values for user chosen node points, then cycle through each node on current coordinate graph node list.
	 * Then keep track of current difference in x and y value. If the current difference is less then the previous difference;
	 * of both x and y points then set the new validated point as the new closest point.
	 * @param x the x value for the given point
	 * @param y the y value for the given point
	 * @param mapName String
	 * @return the more accurate validated point 
	 */
	public Node validatePoint(String mapName, int x, int y, String lastName){
		
		//CREATE a null validated node to signal a possible failure
		Node validatedNode = null;
		
		//Create two double variables to get precision in calculating node difference between nodes
		double currentDiff = 0.0;
		double previousDiff = Double.POSITIVE_INFINITY;
		
		//IF the map table is empty THEN
		if(mapTable.isEmpty()){
			
			//LOG that the map table was empty
			logError.logError("MapTable is empty, Validation Failed");
			
			//RETURN a null node to signal failure
			return validatedNode;
		}
		
		//FOR EACH node within the map table
		for(Node node: mapTable.get(mapName).getGraph().getNodes()){
			
			//Call the calculateDistance method to run the distance equation between two points
			currentDiff = calculateDistance(node.getX(),x,node.getY(),y);
			
			//IF the current difference is less then the previous difference THEN
			if(currentDiff < previousDiff){
				
				//The node is closer and should be the one requested by the user
				previousDiff = currentDiff;
				
				//SET the closer node as the validated node
				validatedNode = node;
			}				
		}
		
		return validatedNode;
	}
	public Point validatePoint(String mapName, int x, int y){
		
		Node validatedNode = null;
		if(mapTable.isEmpty()){
			Point pnt = new Point();
			logError.logError("MapTable is empty, Validation Failed");
			return pnt;
		}
		double currentDiff = 0.0;
		double previousDiff = Double.POSITIVE_INFINITY;
		for(Node node: mapTable.get(mapName).getGraph().getNodes()){
			currentDiff = calculateDistance(node.getX(),x,node.getY(),y);
			if(currentDiff < previousDiff){
				previousDiff = currentDiff;
				validatedNode = node;
			}				
		}
		Point correctedPoint = new Point(validatedNode.getX(), validatedNode.getY());
		logError.logError(correctedPoint.getX() + " " + correctedPoint.getY());
		return correctedPoint;
	}
	
	/**
	 * Method printNodes.
	 * @param mapName String
	 */
	public void printNodes(String mapName){
		int count = 0;
		for(Node node: mapTable.get(mapName).getGraph().getNodes()){
			count++;
			System.out.print(node.getID() + " " + node.getX() + " " + node.getY() + " " + node.getFloorNum());
			System.out.println();
		}
		System.out.println(count);
		System.out.println("END PRINT NODES");
	}
	/**
	 * Method printNodes.
	 * @param nodes List<Node>
	 */
	public void printNodes(List<Node> nodes){
		int count = 0;
		System.out.println("PRINTING NODES");
		for(Node node: nodes){
			count++;
			System.out.print(node.getID() + " " + node.getX() + " " + node.getY());
			System.out.println();
		}
		System.out.println("END PRINT NODES");
	}
	public void printEdges(){
		for(Edge edge: edges){
			System.out.print(edge.getEdgeID() + " " + edge.getSource().getID() + " " + edge.getDestination().getID() + " " + edge.getEdgeLength());
			System.out.println();
		}
		System.out.println("END PRINT EDGES");
	}
	

	public void printMaps(){
		for(String value: mapTable.keySet()){
			System.out.println(value);
		}
	}
	
	/**
	 * Method getArrayOfMapNames.
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getArrayOfMapNames(){
		ArrayList<String> tempArrayOfMapNames = new ArrayList<String>();
		Enumeration e = mapTable.keys();
		while(e.hasMoreElements()){
			String mapName = (String) e.nextElement();
			tempArrayOfMapNames.add(mapName);
		}
		return tempArrayOfMapNames;
	}
	/**
	 * Method getImgURLS returns a string arraylist of map URLS
	 * @return array list of string urls
	 */
	public ArrayList<String> getImgURLS(){
		ArrayList<String> mapImgURLS = new ArrayList<String>();
		for(String map: mapTable.keySet()){
			mapImgURLS.add(mapTable.get(map).getMapImgURL());
		}
		return mapImgURLS;
	}
	/**
	 * Method getMapTypes returns a string array list of map types
	 * @return arraylist of map types
	 */
	public ArrayList<String> getMapTypes(){
		ArrayList<String> mapTypes = new ArrayList<String>();
		for(String map: mapTable.keySet()){
			mapTypes.add(mapTable.get(map).getMapType());
		}
		return mapTypes;
	}
	public LinkedHashMap<String,MapPath> getMapPaths(){
		return this.multiPath.getMapPaths();
	}
	/**
	 * Method getNodeList.
	 * @param mapName String
	 * @return List<Node>
	 */
	public List<Node> getNodeList(String mapName){
		List<Node> currentNodeList = mapTable.get(mapName).getGraph().getNodes();	
		return currentNodeList;		
	}
	/**
	 * Method getEdgeList.
	 * @param mapName String
	 * @return List<Edge>
	 */
	public List<Edge> getEdgeList(String mapName){
		List<Edge> currentEdgeList = mapTable.get(mapName).getGraph().getEdges();	
		return currentEdgeList;
	}
	/**
	 * Method calculateDistance.
	 * @param x1 double
	 * @param x2 double
	 * @param y1 double
	 * @param y2 double
	 * @return double
	 */
	public double calculateDistance(double x1, double x2, double y1, double y2){
		return Math.sqrt(Math.pow(x2-x1, 2)+ Math.pow(y2 - y1, 2));
	}

    //find node Id for the start of the edge
    /**
     * Method findNodeId.
     * @param mapName String
     * @param point Point
     * @return int
     */
    public int findNodeId(String mapName,Point point){
    	for(Node node: mapTable.get(mapName).getGraph().getNodes())
    	{
			if((node.getX()==point.x)&&(node.getY()==point.y))
			{ 
				return node.getID();   
			}
    	}
		return 0;
    }
    
    /**
     * get the Info Image urls of campus
     * @return info images of campus map
     */
    public HashMap<String,String> getCampusImageUrl()
    {
    	HashMap<String,String> imageUrls=mapTable.get("CampusMap_0").getCampusImageUrl();
    	return imageUrls;
    }
    
}

    
