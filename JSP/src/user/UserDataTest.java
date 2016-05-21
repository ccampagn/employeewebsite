package user;


import junit.framework.TestCase;

public class UserDataTest extends TestCase{

	private UserData user;
	
	@Override
	protected void setUp() throws Exception {
		user= new UserData(1,"Chris","Campagnola","ccampagn@ycp.edu","7327189888","10 Markus","Kendall Park","New Jersey","08824",1991,06,16,true,"USA");
	}
	
	
	public void testGetFirstname() throws Exception {
		assertEquals("Chris", user.getFirstname());
}
	public void testGetLastname() throws Exception {
		assertEquals("Campagnola", user.getLastname());
}
	public void testGetPhonenumber() throws Exception {
		assertEquals("7327189888", user.getPhonenumber());
}
	public void testGetEmail() throws Exception {
		assertEquals("ccampagn@ycp.edu", user.getEmail());
}

	public void testGetAddress() throws Exception {
		assertEquals("10 Markus", user.getAddress());
}
	public void testGetCity() throws Exception {
		assertEquals("Kendall Park", user.getCity());
}
	public void testGetState() throws Exception {
		assertEquals("New Jersey", user.getState());
}
	public void testGetZipcode() throws Exception {
		assertEquals("08824", user.getZipcode());
}
	public void testGetYear() throws Exception {
		assertEquals(1991, user.getYear());
}
	public void testGetMonth() throws Exception {
		assertEquals(6, user.getMonth());
}
	public void testGetDay() throws Exception {
		assertEquals(16, user.getDay());
}
	
	public void testSetFirstname() throws Exception {
		user.setFirstname("Bob");
		assertEquals("Bob", user.getFirstname());
}
	public void testSetLastname() throws Exception {
		user.setLastname("Jones");
		assertEquals("Jones", user.getLastname());
}
	
	public void testSetEmail() throws Exception {
		user.setEmail("bjones@aol.com");
		assertEquals("bjones@aol.com", user.getEmail());
}

	public void testSetAddress() throws Exception {
		user.setAddress("123 Fake");
		assertEquals("123 Fake", user.getAddress());
}
	public void testSetCity() throws Exception {
		user.setAddress("Fakeville");
		assertEquals("Fakeville", user.getAddress());
}
	public void testSetState() throws Exception {
		user.setState("Pennsylvania");
		assertEquals("Pennsylvania", user.getState());
}
	public void testSetZipcode() throws Exception {
		user.setZipcode("12345");
		assertEquals("12345", user.getZipcode());
}
	public void testSetYear() throws Exception {
		user.setYear(1787);
		assertEquals(1787, user.getYear());
}
	public void testSetMonth() throws Exception {
		user.setMonth(3);
		assertEquals(3, user.getMonth());
}
	public void testSetDay() throws Exception {
		user.setDay(12);
		assertEquals(12, user.getDay());
}
	
	
}
