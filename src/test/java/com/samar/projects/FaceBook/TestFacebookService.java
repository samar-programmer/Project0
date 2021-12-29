package com.samar.projects.FaceBook;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.samar.projects.facebook.entity.FacebookUser;
import com.samar.projects.facebook.service.FaceBookService;
import com.samar.projects.facebook.utility.ControllerFactory;

public class TestFacebookService {
	FaceBookService faceBookService;
	@Before
	public void setUp() throws Exception {
		faceBookService = new FaceBookService();
	}

	@After
	public void tearDown() throws Exception {
		faceBookService = null;
	}
	
	/*
	 * @Test public void deleteProfileService() { FacebookUser fbuser = new
	 * FacebookUser(); fbuser.setEmail("king@gmail.com"); int result=
	 * faceBookService.deleteProfileService(fbuser); assert result > 0:
	 * "profiles not found"; }
	 */
	
	@Test
	public void testViewAllProfileService() {
		System.out.println("Entering into View all profile Sevice");
		List<FacebookUser> fbuserList = faceBookService.viewAllProfileService();
		int result=0;
		if(fbuserList.size()> 0) {
			result =1;
		}
		assert result > 0: "profiles not found";
	}
	
	@Test
	public void testViewProfileService() {
		System.out.println("Entering into view profile Service");
		FacebookUser facebookUser = new FacebookUser();
		facebookUser.setEmail("samar@gmail.com");
		FacebookUser fbuser = faceBookService.viewProfileService(facebookUser);
		int result=0;
		if(null != fbuser.getName()) {
			result =1;
		}
		assert result > 0: "profile not found";
	}

	@Test
	public void testCreateProfileService() {
		System.out.println("Entering into create profile Service");
		FacebookUser facebookUser = new FacebookUser();
		facebookUser.setName("samar1");
		facebookUser.setEmail("samar1@gmail.com");
		facebookUser.setPassword("ssss");
		facebookUser.setAddress("chennai");
		int result = faceBookService.createProfileService(facebookUser);
		assert result > 0: "Error while creating profile";
	}

}
