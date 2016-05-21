package user;

public class NewStories {
	int id;
	int companyid;
	String location;
	String dept;
	String sublocation;
	String position;
	String date;
	String title;
	String stories;
	public NewStories(	int id,int companyid,String location,String dept,String sublocation,String position,String date,String title,String stories){
		this.id=id;
		this.companyid=companyid;
		this.location=location;
		this.dept=dept;
		this.sublocation=sublocation;
		this.position=position;
		this.date=date;
		this.title=title;
		this.stories=stories;
		
	}
	public int getid(){
		return id;
	}
	public int getcompanyid(){
		return companyid;
	}
	public String getlocation(){
		return location;
	}
	public String getdept(){
		return dept;
	}
	public String getsublocation(){
		return sublocation;
	}
	public String getposition(){
		return position;
	}
	public String getdate(){
		return date;
	}
	public String gettitle(){
		return title;
	}
	public String getstory(){
		return stories;
	}
}
