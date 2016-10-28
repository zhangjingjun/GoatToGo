package gtg_model_subsystem;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The ModelFileURLS class is a list of final static file names that are associated with
 * processing information needed for the model subsystem
 * 
 */
public class ModelFileURLS {
	
	public static final Path currentRelativePath = Paths.get("");
	public static final String s = currentRelativePath.toAbsolutePath().toString() + System.getProperty("file.separator");
	public static final String masterMapListURL = s + "ModelFiles"+System.getProperty("file.separator")+ "masterMapList.txt";
	public static final String masterMapListTemp = s + "ModelFiles"+System.getProperty("file.separator")+ "masterMapList_temp.txt";
	public static final String adminURL = s + "ModelFiles"+System.getProperty("file.separator")+"adminFile.txt";

}
