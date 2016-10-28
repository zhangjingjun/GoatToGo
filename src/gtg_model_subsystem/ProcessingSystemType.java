package gtg_model_subsystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface for builder design pattern
 */
interface ProcessingSystemType {
	ArrayList<Map> loadMapList();
	boolean readAdmin(List<Admin> admins);
	boolean deleteMapFromMaster(String mapName);
	boolean saveMapToMaster(String mapName, String mapImgURL, String mapType);
	boolean readGraphInformation(List<Node> nodes, List<Edge> edges, String mapName);
	boolean saveGraphInformation(List<Node> nodes, List<Edge> edges, String mapName);
}
