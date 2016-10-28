package gtg_model_subsystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Builder Design pattern for a company to use their own processing system at run time.
 * @author JT
 *
 */
public class ProcessingSystem {
	protected ProcessingSystemType st;
	public ProcessingSystem(String type){
		if(type.equals("file")){
			st = new FileProcessing();
		}
		else{
			//
		}
	}
	public ProcessingSystem(){
		this("file");
	}
	boolean readAdmin(List<Admin> admins){
		return st.readAdmin(admins);
	}
	public ArrayList<Map> loadMapList(){
		return st.loadMapList();
	}
	public boolean deleteMapFromMaster(String mapName){
		return st.deleteMapFromMaster(mapName);
	}
	public boolean saveMapToMaster(String mapName, String mapImgURL, String mapType){
		return st.saveMapToMaster(mapName, mapImgURL, mapType);
	}
	public boolean readGraphInformation(List<Node> nodes, List<Edge> edges, String mapName){
		return st.readGraphInformation(nodes, edges, mapName);
	}
	public boolean saveGraphInformation(List<Node> nodes, List<Edge> edges, String mapName){
		return st.saveGraphInformation(nodes, edges, mapName);
	}
}
