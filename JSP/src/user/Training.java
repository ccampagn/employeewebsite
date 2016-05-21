package user;

import java.util.Date;

public class Training {
	String training;
	String expire;
	public Training(String training,String date) {
		this.training=training;
		this.expire=date;
		
	}
public String getTraining(){
	return training;
}
public String getDate() {
	// TODO Auto-generated method stub
	return expire;
}
}
