package gtg_model_subsystem;
import java.util.List;
/**
 */
public class MapPath
{
		/**store the start point;
		*/
		private Node startPoint;
		
		/**store the end point;
		*/
		private Node endPoint;
		
		/**store the way points
		*/
		private List<Node> wayPoints;
		
		private double distance;
		/**
		 * Constructor for Path.
		 * @param startPoint Node
		 * @param endPoint Node
		 * @param wayPoints List<Node>
		 */
		public MapPath(Node startPoint, Node endPoint, List<Node> wayPoints){
			this.startPoint = startPoint;
			this.endPoint = endPoint;
			this.wayPoints = wayPoints;
		}
		
		
		public void setStartPoint(Node startPoint){
			this.startPoint = startPoint;
		}
		/**
		 * Method setEndPoint.
		 * @param endPoint Node
		 */
		public void setEndPoint(Node endPoint){
			this.endPoint = endPoint;
		}
		/**
		 * Method setPath.
		 * @param wayPoints List<Node>
		 */
		public void setPath(List<Node> wayPoints){
			this.wayPoints = wayPoints;
			calculatePathDistance();
		}
		/**return start point
	 * @return Node
			*/
		public Node getStartPoint(){
			return this.startPoint;
		}
		
		/**return end point
	 * @return Node
			*/
		public Node getEndPoint(){
			return this.endPoint;
		}
	    
		/**return a List of Nodes
		 * @return List<Node>
		 */
		public List<Node> getWayPoints(){
			return this.wayPoints;
		}
		
		//Created by Libin to calculate the shortest distance for each map.
		public void calculatePathDistance()
		{
		  double sum=0;
		  Node lastNode=null;	  
		  for(Node node:this.wayPoints){
			  if(lastNode!=null)
			    sum=sum+Math.sqrt(Math.pow(node.getX()-lastNode.getX(), 2)+ Math.pow(node.getY() - lastNode.getY(), 2));
			  lastNode=node;
		  }
		  this.distance=sum;
		  System.out.println(this.distance);
		}

		public double getDistance() {
			return distance;
		}	
	}
