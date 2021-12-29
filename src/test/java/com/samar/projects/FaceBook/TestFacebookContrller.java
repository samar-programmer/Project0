package com.samar.projects.FaceBook;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.samar.projects.facebook.Interfaces.FacebookControllerInterface;
import com.samar.projects.facebook.constants.MessageConstant;
import com.samar.projects.facebook.utility.ControllerFactory;
	
public class TestFacebookContrller {
	FacebookControllerInterface facebookControllerInterface;
	@Before
	public void setUp() throws Exception {
		facebookControllerInterface = ControllerFactory.createObject();
	}

	@After
	public void tearDown() throws Exception {
		facebookControllerInterface = null;
	}

	
	@Test
	public void testcreateProfileController() {
		System.out.println("Inside Create Profile Controller");
		int result = facebookControllerInterface.createProfileController();
		assert result > 0 : MessageConstant.JUNIT_PROFILE_CREATED_YN;
	}
	
	@Test
	public void testViewProfileController() {
		System.out.println("Inside View Profile Controller");
		int result = facebookControllerInterface.viewProfileController();
		assert result > 0 : MessageConstant.JUNIT_VIEW_PROFILE;
	}
	 
	@Test
	public void testViewAllProfileController() {
		System.out.println("Inside View All Profile Controller");
		int result = facebookControllerInterface.viewAllProfileController();
		assert result > 0 : "Error during view all profiles";
	}
	
	@Test
	public void testLoginController() {
		System.out.println("Inside Login Controller");
		int result = facebookControllerInterface.loginProfileController();
		assert result > 0 : "Error during Login profiles";
	}
	
	
	/*
	 * @Test public void testDeleteProfileController() {
	 * System.out.println("Inside Delete Profile Controller"); int result =
	 * facebookControllerInterface.deleteProfileController(); assert result > 0 :
	 * "Error during delete profiles"; }
	 */
	
	

}
