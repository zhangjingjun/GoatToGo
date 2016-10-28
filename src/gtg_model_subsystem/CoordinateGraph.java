package gtg_model_subsystem;


import java.util.List;

/**
 * The CoordinateGraph class stores all of the information required by any map.
 */
public class CoordinateGraph
{
	/**
	* All the nodes stored in a particular graph. 
	*/
	private List<Node> nodes;
	
	/** All of the edges stored in a particular graph.
	*/
	private List<Edge> edges;

	/**
	 * Constructor for CoordinateGraph.
	 * @param nodes List<Node>
	 * @param edges List<Edge>
	 */
	public CoordinateGraph(List<Node> nodes, List<Edge> edges){
		this.nodes = nodes;
		this.edges = edges;
	}

	/**
	 * Method getNodes returns the graphs nodes
	 * @return  List<Node>
	 */
	public List<Node> getNodes(){
		return nodes;
	}
	/**
	 * Method getEdges returns the graphs edges
	 * @return List<Edge>
	 */
	public List<Edge> getEdges(){
		return edges;
	}
	
}
