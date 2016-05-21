package user;

public class Attendance {
int id;
int type;
int companyid;
String startrange;
String endrange;
int points;
String desc;
public Attendance(int id,int type,int companyid,String startrange,String endrange,int points,String desc){
	this.id=id;
	this.type=type;
	this.companyid=companyid;
	this.startrange=startrange;
	this.endrange=endrange;
	this.points=points;
	this.desc=desc;
}
public int getid(){
	return id;
	
}
public int getType(){
	return type;
	
}
public String getStartrange(){
	return startrange;
	
}
public String getEndrange(){
	return endrange;
	
}
public int getPoints(){
	return points;
	
}
public String getDesc(){
	return desc;
	
}
public int getCompanyid() {
	// TODO Auto-generated method stub
	return companyid;
}
}
