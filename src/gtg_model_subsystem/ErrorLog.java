package gtg_model_subsystem;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ErrorLog {
	
	public void logError(String error){
		final String logName = "GTGLog";
		Logger logger = Logger.getLogger(logName);
		FileHandler fh;
		File f;
		try {
			f = new File(ModelFileURLS.s+"ModelFiles"+System.getProperty("file.separator") + logName + ".log");
			createFile(f);
			fh = new FileHandler(ModelFileURLS.s+"ModelFiles"+System.getProperty("file.separator") + logName + ".log", true );
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.info(error);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method createFile.
	 * @param file File
	 * @return boolean
	 */
	private boolean createFile(File file){
		boolean createFile = true;
		//IF file does not exist create new file
		if(!file.exists()){
			try{
				file.createNewFile();
			}catch(IOException e){
				logError(e.toString());
			}
		}
		else{
			return false;
		}
		return createFile;
	}
}
