package user;


import junit.framework.TestCase;

public class PasswordTest extends TestCase{

	private Password pw;
	
	@Override
	protected void setUp() throws Exception {
		pw=new Password(1,"ccampagn","newyork",0);
	}
	
	public void testGetUsername() throws Exception {
		assertEquals("ccampagn", pw.getUsername());
}
	public void testGetPasswork() throws Exception {
		assertEquals("newyork", pw.getPassword());
}
	
	public void testGetID() throws Exception {
		assertEquals(1, pw.getID());
}
	public void testSetUsername() throws Exception {
		pw.setUsername("bjones");
		assertEquals("bjones", pw.getUsername());
}
	public void testSetPasswork() throws Exception {
		pw.setPassword("apple");
		assertEquals("apple", pw.getPassword());
}
	
	public void testSetID() throws Exception {
		pw.setID(2);
		assertEquals(2, pw.getID());
}
	
}
