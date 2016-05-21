
package user;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;


public class Shift {
int shift;	
int id;
int company;
String location;
String dept;
String sublocation;
String position;
Date start;	
Date end;
String startt;	
String endt;
int clockin;
int breaks;
ArrayList<Break> brek;
int time;
Date actstart;
Date actend;
String startact;
String endact;
ArrayList<Training> training;
public Shift(int shift,int id,int company,String location,String dept,String sublocation,String position,Date start,Date end,String starttime,String endtime,int clockin,int breaks,ArrayList<Break> brek,int time,Date actstart,Date actend,String startact,String endact,ArrayList<Training> training){
	this.shift=shift;
	this.id=id;
	this.company=company;
	this.location=location;
	this.dept=dept;
	this.sublocation=sublocation;
	this.position=position;
	this.start=start;
	this.end=end;
	this.startt=starttime;
	this.endt=endtime;
	this.clockin=clockin;
	this.breaks=breaks;
	this.brek=brek;
	this.time=time;
	this.actstart=actstart;
	this.actend=actend;
	this.startact=startact;
	this.endact=endact;
	this.training=training;
	
}
public int getShift(){
	return shift;
}
public int getID(){
	return id;
}
public int getCompany(){
	return company;
}
public String getLocation(){
	return location;
}
public String getDept(){
	return dept;
}
public String getSublocation(){
	return sublocation;
}
public String getPosition(){
	return position;
}
public Date getStartdate(){
	return start;
}
public Date getEnddate(){
	return end;
}
public String getStarttime(){
	return startt;
}
public String getEndtime(){
	return endt;
}
public int getClockin(){
	return clockin;
}
public ArrayList<Training> getTraining(){
	return training;
}
public String getActStarttime(){
	return startact;
	
}
public String getActEndtime(){
	return endact;
	
}
public ArrayList<Break> getBreak(){
	return brek;
}
public int getTime(){
	return time;
	
}
public int getPoints(){
	return breaks;
	
}
}