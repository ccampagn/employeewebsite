package user;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class timeoffrequest {
	int id;
    int year;
    int month;
    int day;
    int year2;
    int month2;
    int day2;
    Time starttime;
    Time endtime;
    String reason;
    int status;
    int employee;
    int respond;
    public timeoffrequest(int id,int year,int month,int day,int year2,int month2,int day2,Time starttime,Time endtime, String reason,int status,int employee,int respond){
    	this.id=id;
    	this.year=year;
    	this.month=month;
    	this.day=day;
    	this.year2=year2;
    	this.month2=month2;
    	this.day2=day2;
    	this.starttime=starttime;
    	this.endtime=endtime;
    	this.reason=reason;
    	this.status=status;
    	this.employee=employee;
    	this.respond=respond;
    }
    public void setID(int id){
    	this.id=id;
    }
   
    public void setStartTime(Time starttime){
    	this.starttime=starttime;
    }
    public void setEndTime(Time endtime){
    	this.endtime=endtime;
    }
    public void setReason(String reason){
    	this.reason=reason;
    }
    public void setStatus(int status){
    	this.status=status;
    }
    public void setEmployee(int employee){
    	this.employee=employee;
    }
    public void setRespond(int respond){
    	this.respond=respond;
    }
    public int getId(){
    	return id;
    }
    public int getYear(){
    	return year;
    }
    public int getMonth(){
    	return month;
    }
    public int getDay(){
    	return day;
    }
    public int getYear2(){
    	return year2;
    }
    public int getMonth2(){
    	return month2;
    }
    public int getDay2(){
    	return day2;
    }
    public Time getStartTime(){
    	return starttime;
    }
    public Time getEndTime(){
    	return endtime;
    }
  
   
    public String getReason(){
    	return reason;
    }
    public int getStatus(){
    	return status;
    }
    public int getEmployee(){
    	return employee;
    }
    public int getRespond(){
    	return respond;
    }
}
