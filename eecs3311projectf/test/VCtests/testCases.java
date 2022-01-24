package VCtests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import application.CustomerDB;
import application.CustomerModel;
import application.MainSceneController;
import application.MovieDB;
import application.MovieModel;
import application.RegisterNewCController;

public class testCases {
	
	MainSceneController mainScene;
	RegisterNewCController registerScene;
	CustomerDB custDB;
	MovieDB movieDB;
	ArrayList<MovieModel> r;
	
	@Before
	public void init() throws Exception {
		mainScene = new MainSceneController();
		registerScene = new RegisterNewCController();
    }
	
	@Test
	public void loginTest1() throws Exception {
		int result = mainScene.customerLogin("f", "gfgf");
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void loginTest2() throws Exception {
		int result = mainScene.customerLogin("f", "ff");
		boolean actual = false;
		if (result != 0 && result != -1) {
			actual = true;
		}
		Assert.assertEquals(true, actual);
	}
	
	@Test
	public void loginTest3() throws Exception {
		int result = mainScene.customerLogin("", "");
		
		Assert.assertEquals(-1, result);
	}
	
	@Test
	public void regCustTest1() throws Exception {
		
		String eStr = "wer";
		boolean result = registerScene.authenticateNewAccount(eStr, eStr+eStr, eStr+eStr,eStr+"@gmail.com");
		
		Assert.assertEquals(result, true);
		
		custDB = new CustomerDB();
		String resultAcc = "";
		for(CustomerModel u: custDB.getCustomerList()){
			if (u.getName().equals(eStr) && u.getPassword().equals(eStr+eStr) && u.getEmail().equals(eStr+"@gmail.com")) {
				System.out.println(u);
				resultAcc = resultAcc + u.toString();
				break;
			}
		}
		System.out.println(resultAcc); 
		//pay id or order id might be different if the total no. customers at the time of running the test is more than 12
		Assert.assertEquals("User [name= "+ eStr+ ", id= " + custDB.getCustomerList().size() + ", email= " + eStr+ "@gmail.com, password= "+ eStr+eStr + ", address= , pay id= 1662, order id=72, OrderList = []]", resultAcc);
	}
	
	@Test
	public void regCustTest2() throws Exception {
		
		String eStr = "pol";
		boolean result = registerScene.authenticateNewAccount(eStr, eStr+eStr, eStr+"90", eStr+"@gmail.com");
		Assert.assertEquals(result, false);
		
		custDB = new CustomerDB();
		String resultAcc = "";
		for(CustomerModel u: custDB.getCustomerList()){
			if (u.getName().equals(eStr) && u.getPassword().equals(eStr+eStr) && u.getEmail().equals(eStr+"@gmail.com")) {
				System.out.println(u);
				resultAcc = resultAcc + u.toString();
				break;
			}
		}
		Assert.assertEquals("", resultAcc);
	}
	
	@Test
	public void regCustTest3() throws Exception {
		
		String eStr = "";
		boolean result = registerScene.authenticateNewAccount(eStr, eStr+eStr, eStr+"90", eStr+"@gmail.com");
		Assert.assertEquals(result, false);
	}
	
	@Test
	public void movieSearchTest1() throws Exception {
		movieDB = new MovieDB();
		int actual=	movieDB.filterMovies("movie", "").size();
		Assert.assertEquals(10, actual);
	}
	
	@Test
	public void movieSearchTest2() throws Exception {
		movieDB = new MovieDB();
		int actual=	movieDB.filterMovies("movie", "the").size();
		Assert.assertEquals(2, actual);
	}
	
	@Test
	public void movieSearchTest3() throws Exception {
		movieDB = new MovieDB();
		int actual=	movieDB.filterMovies("genre", "romance").size();
		Assert.assertEquals(3, actual);
	}
	
	
}
