package user;

public class TimeOffInfo {
	timeoffrequest req;
	PositionInfo pos;
	UserData user;
	public TimeOffInfo(timeoffrequest req,PositionInfo pos,UserData user){
		this.req=req;
		this.pos=pos;
		this.user=user;
	}
	 public timeoffrequest gettor(){
	    	return req;
	    }
	    public PositionInfo getPositionInfo(){
	    	return pos;
	    }
	    public UserData getUserData(){
	    	return user;
	    }
}
