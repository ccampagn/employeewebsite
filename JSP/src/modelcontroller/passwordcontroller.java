package modelcontroller;


import user.Password;



public class passwordcontroller {
	

		private Password pw; 
		
		public passwordcontroller(Password pw){
			this.pw = pw; 
		}
		

		public void setModel(Password pw){
			this.pw = pw; 
		}
		
		public Password getModel(){
			return pw; 
		}
	
		public void updateID(int id){
			pw.setID(id);
		}
		
		public void updateUsername(String username){
			pw.setUsername(username);
		}
		
		public void updatePassword(String password){
			pw.setUsername(password);
		}
		
		
}
