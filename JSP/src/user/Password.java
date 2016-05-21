package user;

import java.util.Properties;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class Password {
	private int id;
	private String username;
	private String password;
	private int attempt;
	public Password(int id,String username,String password,int attempt){
		this.id=id;
		this.username=username;
		this.password=password;
		this.attempt=attempt;
	}

	public void setID( int id )
    {
        this.id = id;
    }
	public void setAttempt( int attempt )
    {
        this.attempt = attempt;
    }
	public void setUsername( String username )
    {
        this.username = username;
    }
    public void setPassword( String password )
    {
        this.password = password;
    }
    
    public int getID() { return id; }
    
    public int getAttempt() { return attempt; }
    
 public String getUsername() { return username; }
    
    public String getPassword() { return password; }
	
	}
    

