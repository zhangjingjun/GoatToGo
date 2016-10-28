package gtg_view_subsystem;

import java.awt.Point;
import java.util.ArrayList;

/**
 */
public class PathData {
	private Point startPoint;
	private Point endPoint;
	private ArrayList<Point> wayPoints = new ArrayList<Point>();
	private ArrayList<String> mapNames = new ArrayList<String>();
	private String mapURL = "";

	public PathData(){
		
	}
	
	/**
	 * Method setStartPoint.
	 * @param startPoint Point
	 */
	public void setStartPoint(Point startPoint){
		this.startPoint = startPoint;
	}

	/**
	 * Method getStartPoint.
	 * @return Point
	 */
	public Point getStartPoint(){
		return this.startPoint;
	}
	
	/**
	 * Method setEndPoint.
	 * @param endPoint Point
	 */
	public void setEndPoint(Point endPoint){
		this.endPoint = endPoint;
	}

	/**
	 * Method getEndPoint.
	 * @return Point
	 */
	public Point getEndPoint(){
		return this.endPoint;
	}
	
	/**
	 * Method setWayPoints.
	 * @param wayPoints ArrayList<Point>
	 */
	public void setWayPoints(ArrayList<Point> wayPoints){
		this.wayPoints = wayPoints;
	}
	
	/**
	 * Method getWayPoints.
	 * @return ArrayList<Point>
	 */
	public ArrayList<Point> getWayPoints(){
		return this.wayPoints;
	}
	
	/**
	 * Method setArrayOfMapNames.
	 * @param mapNames ArrayList<String>
	 */
	public void setArrayOfMapNames(ArrayList<String> mapNames){
		this.mapNames = mapNames;
	}
	
	/**
	 * Method getArrayOfMapNames.
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getArrayOfMapNames(){
		return this.mapNames;
	}
	
	/**
	 * Method setMapURL.
	 * @param mapURL String
	 */
	public void setMapURL(String mapURL){
		this.mapURL = mapURL;
	}
	
	/**
	 * Method getMapURL.
	 * @return String
	 */
	public String getMapURL(){
		return this.mapURL;
	}
}
