package modelcontroller;

import user.Password;
import user.UserData;

public class UserDataController {
	private UserData user; 
	
	public UserDataController(UserData user){
		this.user = user; 
	}
	

	public void setModel(UserData user){
		this.user = user; 
	}
	
	public UserData getModel(){
		return user; 
	}


	
	public void updateFirstname(String firstname){
		user.setFirstname(firstname);
	}
	public void updateLastname(String lastname){
		user.setLastname(lastname);
	}
	public void updateAddress(String address){
		user.setAddress(address);
	}
	public void updateCity(String city){
		user.setCity(city);
	}
	public void updateState(String state){
		user.setState(state);
	}
	public void updateYear(int year){
		user.setYear(year);
	}
	public void updateMonth(int month){
		user.setMonth(month);
	}
	public void updateDay(int day){
		user.setMonth(day);
	}
	
	
	
}
