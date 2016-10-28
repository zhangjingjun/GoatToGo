package gtg_control_subsystem;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;

import gtg_model_subsystem.MainModel;
import gtg_model_subsystem.Node;
import gtg_model_subsystem.Edge;
import gtg_model_subsystem.MapPath;

import gtg_view_subsystem.PathData;

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Iterator;

public class MainController{
		
	public MainModel mapModel;
	
	private MapDataController mapDataController;
	private PathSearchController pathSearchController;
	private AdminController userChecker;

	public MainController(MainModel mapModel){
		this.mapModel = mapModel;
		mapDataController = new MapDataController(this);
		pathSearchController = new PathSearchController(this, mapDataController); 
		userChecker = new AdminController(this);		
	}	
	
	/******************************
	 * 
	 * 		  Map Page interface
	 * 
	 *******************************/
	public ArrayList<String> getMapList(String mapRequestCommand){
		return mapDataController.getMapList(mapRequestCommand);
	}
	
	public String getMapURL(String mapName){
		return mapDataController.getMapURL(mapName);
	}
	
	public String getPointDescription(Point pnt){
		return mapDataController.getNodeDescription(pnt);
	}

	// This 3 functions below should be called each time when View is changing page
	public Boolean LoadingPntsAndEdges(String mapName){
		return mapDataController.LoadingPntsAndEdges(mapName);
	}
	public ArrayList<Point> getDisplayPnt(){
		return mapDataController.getDisplayPnt();
	}
	public ArrayList<Point> getDisplayEdge(){
		return mapDataController.getDisplayEdge();
	}
	
	public ArrayList<Point> getFilteredList(String pointType){
		return mapDataController.getFilteredList(pointType);
	}
	
	public String getMouseSelectedBuilding(Point mouseClickedPnt){
		return mapDataController.getClickedBuildingMapName(mouseClickedPnt);
	}
	
	public String getBuildingInfoImageURL(String buildingName){
		return mapDataController.getBuildingInfoImageURL(buildingName);
	}
	
	public String getBuildingInfoDescription(String buildingName){
		return mapDataController.getBuildingInfoDescription(buildingName);		
	}
	
	
	/******************************
	 * 
	 * 		 Path Searching Control
	 * 
	 *******************************/
	public Point setTaskPnt(Point taskPnt, String pntType, String mapName){
		mapName = mapDataController.translateMapNameFromString2Num(mapName);
		return pathSearchController.setTaskPnt(taskPnt, pntType, mapName);
	}
	
	public PathData getDesiredPath(int Index){
		return pathSearchController.getDesiredPath(Index);
	}
	
	public String getStartEndNodeDescription(String pointType){
		return pathSearchController.getStartEndNodeDescription(pointType);
	}	
		
	public boolean getPathData(){
		return pathSearchController.getPathData();
	}
	
	public double getPathLength(){
		return pathSearchController.getPathLength()/2;
	}
	
	public int getEstimateTime(){
		return pathSearchController.getEstimateTime();
	}
	
	public String translateMapNameFromNum2String(String mapName){
		return mapDataController.translateMapNameFromNum2String(mapName);
	}
	public String translateMapNameFromString2Num(String mapName){
		return mapDataController.translateMapNameFromString2Num(mapName);
	}
	
	/******************************
	 * 
	 * 		  Admin Control
	 * 
	 *******************************/
	public Boolean adminQualification(String userName, String passWord){
		return userChecker.adminQualification(userName, passWord);
	}
	
	
	/******************************
	 * 
	 * 		  Map Editor
	 * 
	 *******************************/
	public Boolean addNewMap(String mapName, String mapImageURL, String mapType){
		mapName = mapDataController.translateMapNameFromString2Num(mapName);
		boolean mapSaved = false;
		try{
			if(mapDataController.mapIsInTheOldList(mapName)){
				//System.out.println("Map already in the old list");
				return mapSaved;
			}
			
			// Generate a relative filePath:
			/*int markPos = mapImageURL.lastIndexOf("images");
			if(markPos>0){
				mapImageURL = mapImageURL.substring(markPos);
			}*/
			
			if(mapModel.saveNewMap(mapName, mapDataController.changeBackSeparator(mapImageURL), mapType)){
				mapDataController.addNewMapToList(mapName);
				mapDataController.addNewMapURLToList(mapImageURL);
				mapSaved = true;
			}
		}
		catch(IOException e){
			//System.out.println(e.toString());
		}
		
		return mapSaved;
	}
	
	public Boolean deleteMap(String mapName){
		mapName = mapDataController.translateMapNameFromString2Num(mapName);
		boolean mapDeleted = false;
		try{
			if(mapModel.deleteMap(mapName)){
				mapDataController.removeMapFromList(mapName);
				mapDeleted = true;
			}
		}
		catch(Exception e){
			//System.out.println(e.toString());	
		}
		return mapDeleted;
	}

	public Boolean createCoordinateGraph(String mapName){
		mapName = mapDataController.translateMapNameFromString2Num(mapName);
		Boolean success = false;
		try{
			List<Node> nodeToBeSaved =  mapDataController.getNodeList();
			List<Edge> edgeToBeSaved =  mapDataController.getEdgeList();
			
			mapModel.saveMapGraph(mapName, 
								  nodeToBeSaved, 
								  edgeToBeSaved);
		}
		catch(Exception e){
			success = false;
			//System.out.println(e.toString());
		}
		return success;
	}

	public Boolean addPoint(Point inputPnt, int floorNum, int entranceID, String buildingName, String pointType, String pointDescription){
		return mapDataController.addPoint(inputPnt, floorNum, entranceID, buildingName, pointType, pointDescription);
	}
	
	public boolean EditNode(int nodeID, String BuildingName, int floorNum, int entranceID, String pointType,String pointDescription){
		return mapDataController.editExistNode(nodeID,
											   BuildingName,
											   floorNum,				
											   entranceID,
											   pointType,
											   pointDescription);	
	}

	public Boolean createEdge(Point pnt1, Point pnt2){
		return mapDataController.createEdge(pnt1, pnt2);
	}

	public boolean deletePoint(Point inputPnt){
		return mapDataController.deletePoint(inputPnt);
	}

	public boolean deleteEdge(Point p){
		return mapDataController.deleteEdge(p);
	}

	public void clearAllTempData(){
		mapDataController.clearAllTempData();
	}
	
	public boolean setDescriptionOfNode (int nodeID){
		return mapDataController.setDescriptionOfNode(nodeID);
	}
	
	public boolean setEntranceIDOfNode (int nodeID){
		return mapDataController.setEntranceIDOfNode(nodeID);
	}
	
	public boolean setTypeOfNode (int nodeID){
		return mapDataController.setTypeOfNode(nodeID);
	}
	
	public int getNodeID(Point inputPnt){
		return mapDataController.CheckPntExistence(inputPnt);
	}
	
	public String getNodeBuildingName(int nodeID){
		return mapDataController.getBuildingNameofNode(nodeID);
	}
	
	public int getNodeFloorNum(int nodeID){
		return mapDataController.getFloorNumofNode(nodeID);
	}
	
	public String getDescriptionOfNode (int nodeID){
		return mapDataController.getDescriptionOfNode(nodeID);
	}
	
	public int getEntranceIDOfNode (int nodeID){
		return mapDataController.getEntranceIDOfNode(nodeID);
	}
	
	public String getTypeOfNode (int nodeID){
		return mapDataController.getTypeOfNode(nodeID);
	}

	public Point pointMapping(Point inputPnt){
		return mapDataController.pointMapping(inputPnt);
	}

	public Point getLastPnt(){
		return mapDataController.getLastPntInPntList();
	}

}
