package gtg_model_subsystem;

/**
 */
public class Admin {
		/** userName is the users name from the login page
		*/
		private String username;
		
		/**password is the user password given from login page
		*/
		private String password;
		
		/**
		 * Constructor for Admin.
		 * @param username String
		 * @param password String
		 */
		public Admin(String username, String password){
			this.username = username;
			this.password = password;
		}
		
		/** return string of users name
	 * @return String
			*/
		public String getUsername(){
			return this.username;
		}
		
			/** return string of password for user
	 * @return String
				*/
		public String getPassword(){
			return this.password;
		}

}
