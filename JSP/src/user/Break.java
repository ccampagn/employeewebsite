package user;

import java.sql.Time;
import java.util.Date;

public class Break {
	String id;
	String start;	
	String end;
	String starttime;
	String endtime;

public Break(String id,String start,String end,String starttime,String endtime) {
	this.id=id;
	this.start=start;
	this.end=end;
	this.starttime=starttime;
	this.endtime=endtime;
}
public String getid(){
	return id;
}
public String getDatestart(){
	return start;
}
public String getDateend(){
	return end;
}
public String getTimestart(){
	return starttime;
}
public String getTimeend(){
	return endtime;
}
}