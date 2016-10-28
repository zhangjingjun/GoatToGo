package gtg_model_subsystem;

import java.util.Hashtable;

/**
 * Singleton class for the map list.
 * @author JT
 *
 */
public class MapTable {
	private MapTable(){}
	private static class MapTableHolder{
		private static final Hashtable<String, Map> mapList = new Hashtable<String, Map>();
	}
	public static Hashtable<String, Map> getInstance(){
		return MapTableHolder.mapList;
	}
}
