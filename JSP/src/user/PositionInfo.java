package user;

import java.util.ArrayList;

public class PositionInfo {
	int id;
String company;
String location;
String dept;
String position;
String comment;
boolean rights;
String sublocation;
ArrayList<Training> training;
int points;
public PositionInfo(int id,String company,String location,String dept,String position,String comment,Boolean rights,String sublocation,ArrayList<Training> training,int points){
	this.id=id;
	this.company=company;
	this.location=location;
	this.dept=dept;
	this.position=position;
	this.comment=comment;
	this.rights=rights;
	this.sublocation=sublocation;
	this.training=training;
	this.points=points;
}
public int getID() {
	// TODO Auto-generated method stub
	return id;
}
public int getPoints() {
	// TODO Auto-generated method stub
	return points;
}
public String getCompany() {
	// TODO Auto-generated method stub
	return company;
}
public String getLocation() {
	// TODO Auto-generated method stub
	return location;
}
public String getDept() {
	// TODO Auto-generated method stub
	return dept;
}
public String getPosition() {
	// TODO Auto-generated method stub
	return position;
}
public String getComment() {
	// TODO Auto-generated method stub
	return comment;
}
public boolean getRight() {
	// TODO Auto-generated method stub
	return rights;
}
public String getSublocation() {
	// TODO Auto-generated method stub
	return sublocation;
}
public ArrayList<Training> getTraining() {
	// TODO Auto-generated method stub
	return training;
}
}
