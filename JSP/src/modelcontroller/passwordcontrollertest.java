package modelcontroller;


import user.Password;
import junit.framework.TestCase;


public class passwordcontrollertest extends TestCase  {
	 private Password pass;
    private passwordcontroller pw; 
    

@Override
protected void setUp() throws Exception {
		pass=new Password(1,"ccampagn","newyork",0);
		pw=new passwordcontroller(pass);
}
public void testSetModel() throws Exception {
   pw.setModel(pass); 
   assertEquals(pass, pw.getModel());
   assertEquals(pass.getID(), pw.getModel().getID());
   assertEquals(pass.getUsername(), pw.getModel().getUsername());
   assertEquals(pass.getPassword(), pw.getModel().getPassword());
}
public void testgetModel() throws Exception{
	assertEquals(pass, pw.getModel());
	assertEquals(pass.getID(), pw.getModel().getID());
	assertEquals(pass.getUsername(), pw.getModel().getUsername());
	assertEquals(pass.getPassword(), pw.getModel().getPassword());
}
public void testUpdateID() throws Exception {
	pw.setModel(pass); 
	pw.updateID(2);
	assertEquals(pass, pw.getModel());
}
public void testUpdateUsername() throws Exception {
	pw.setModel(pass); 
	pw.updateUsername("bjones");
	assertEquals(pass, pw.getModel());
}
public void testUpdatePassword() throws Exception {
	pw.setModel(pass); 
	pw.updatePassword("apple");
	assertEquals(pass, pw.getModel());
}



}