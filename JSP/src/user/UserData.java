package user;

import java.util.Date;



public class UserData {
	int id;
    String firstname;
    String lastname;
    String email;
    String phonenumber;
    String address;
    String city;
    String state;
    String zipcode;   
    int year;
    int month;
    int day;
    boolean sex;
    String country;
    
    public UserData(int id,String firstname, String lastname,String email,String phonenumber, String address,String city,String state,String zipcode,int year,int month,int day,boolean sex,String country){
    	this.id=id;
    	this.firstname=firstname;
    	this.lastname=lastname;
    	this.email=email;
    	this.phonenumber=phonenumber;
    	this.address=address;
    	this.city=city;
    	this.state=state;
    	this.zipcode=zipcode;
    	this.year=year;
    	this.month=month;
    	this.day=day;
    	this.sex=sex;
    	this.country=country;
    }


    public void setID( int id )
    {
        this.id = id;
    }
    public void setFirstname( String firstname )
    {
        this.firstname = firstname;
    }
    public void setLastname( String lastname )
    {
        this.lastname = lastname;
    }
    public void setEmail( String email )
    {
        this.email = email;
    }
    public void setAddress( String address )
    {
        this.address = address;
    }
    public void setCity( String city )
    {
        this.city = city;
    }
    public void setState( String state )
    {
        this.state = state;
    }
    public void setZipcode( String zipcode )
    {
        this.zipcode = zipcode;
    }
    public void setYear( int year )
    {
        this.year = year;
    }
    public void setMonth( int month)
    {
        this.month = month;
    }
    public void setDay( int day )
    {
        this.day = day;
    }
    public void setSex(boolean sex )
    {
        this.sex = sex;
    }
 
    

    public int getID() { return id; }
    
    public String getFirstname() { return firstname; }
    
    public String getLastname() { return lastname; }
    
    public String getEmail() { return email; }
    
    public String getPhonenumber() { return phonenumber; }
    
    public String getAddress() { return address; }

    public String getCity() { return city; }

    public String getState() { return state; }
    
    public String getZipcode() { return zipcode; }
   
    public int getYear() { return year; } 
    
    public int getMonth() { return month; } 
    
    public int getDay() { return day; } 
    
    public boolean getSex() { 
    	return sex;
    	}


	public String getCountry() {
		// TODO Auto-generated method stub
		return country;
	} 
    
    
    
}
