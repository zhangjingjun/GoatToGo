package gtg_model_subsystem;

import java.util.ArrayList;
import java.util.List;
/**
 * Singleton class for the admin list
 * @author JT
 *
 */
public class AdminList {
	private AdminList(){}
	private static class AdminListHolder{
		private static final  List<Admin> adminList = new ArrayList<Admin>();
	}
	public static List<Admin> getInstance(){
		return AdminListHolder.adminList;
	}
}
