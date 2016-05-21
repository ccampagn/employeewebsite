package user;



public class WorkInfo {
UserData user;
PositionInfo position;
Password password;
public WorkInfo(UserData user,PositionInfo position,Password password){
	this.user=user;
	this.position=position;
	this.password=password;
}
public UserData getUserData() { return user; }

public PositionInfo getPositionInfo() { return position; }

public Password getPassword() { return password; }
public void setUserData( UserData user )
{
    this.user = user;
}
}
