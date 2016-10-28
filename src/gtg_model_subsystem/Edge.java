package gtg_model_subsystem;


/**
 */
public class Edge
{
		/**One endpoint of the edge
		*/
		private Node source;
		
		/**The other endpoint of the edge
		*/
		private Node destination;
		
		/**the ID of the edge
		*/
		private int edgeID;
		
		/**the length of the edge
		*/
		private double edgeLength;
		
		
		/**
		 * Constructor for Edge.
		 * @param edgeID int
		 * @param source Node
		 * @param destination Node
		 * @param edgeLength double
		 */
		public Edge(int edgeID, Node source, Node destination, double edgeLength){
			this.source = source;
			this.destination = destination;
			this.edgeID = edgeID;
			this.edgeLength = edgeLength;
		}
		
		/**return source of the edge
	 * @return Node
			*/
		public Node getSource(){
			return this.source;
		}
		
		/**return destination of the edge
	 * @return Node
			*/
		public Node getDestination() {
			return this.destination;
		}
		
		/**return id of the edge
	 * @return int
			*/
		public int getEdgeID() {
			return this.edgeID;
		}
		
		/**return length of the edge
	 * @return double
			*/
		public double getEdgeLength() {
			return this.edgeLength;
		}
}

