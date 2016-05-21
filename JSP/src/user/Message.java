package user;

public class Message {
		int id;
		int seriesid;
		int from;	
		int  to;
		String message;
		String date;
		String time;
		String first;
		String last;
	public Message(int id,int seriesid,int from,int to,String message,String date,String time,String first,String last) {
		this.id=id;
		this.seriesid=seriesid;
		this.from=from;
		this.to=to;
		this.message=message;
		this.date=date;
		this.time=time;
		this.first=first;
		this.last=last;
	}
	public String getMessage(){
		return message;
	}
	public String getDate(){
		return date;
	}
	public String getTime(){
		return time;
	}
	public String getFirstname(){
		return first;
	}
	public String getLastname(){
		return last;
	}
	public int getSeriesID() {
		// TODO Auto-generated method stub
		return seriesid;
	}
	public int getFrom() {
		// TODO Auto-generated method stub
		return from;
	}
	public int getTo() {
		// TODO Auto-generated method stub
		return to;
	}
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}


}
