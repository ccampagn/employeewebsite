package user;

public class ShiftInfo {
Shift shift;
String firstname;
String lastname;
public ShiftInfo(Shift shift,String firstname,String lastname){
	this.shift=shift;
	this.firstname=firstname;
	this.lastname=lastname;
}
public Shift getShift(){
	return shift;
	
}
public String getFirstname(){
	return firstname;
	
}
public String getLastname(){
	return lastname;
	
}
}
