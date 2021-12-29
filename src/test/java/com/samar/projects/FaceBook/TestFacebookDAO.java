package com.samar.projects.FaceBook;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.samar.projects.facebook.dao.FaceBookDAO;
import com.samar.projects.facebook.entity.FacebookUser;

public class TestFacebookDAO {
	FaceBookDAO faceBookDAO;
	@Before
	public void setUp() throws Exception {
		faceBookDAO = new FaceBookDAO();
	}

	@After
	public void tearDown() throws Exception {
		faceBookDAO = null;
	}
	@Test
	public void testEditProfileDAO() {
		System.out.println("Entering into Edit profile DAO");
		FacebookUser facebookUser = new FacebookUser();
		facebookUser.setName("samar2Edited");
		facebookUser.setEmail("samar2@gmail.com");
		facebookUser.setPassword("ssss");
		facebookUser.setAddress("BagngolreEdited");
		int result = faceBookDAO.editProfileDAO(facebookUser);
		assert result > 0: "Error while Editing profile";
	}
	
	@Test
	public void testViewAllProfileDAO() {
		System.out.println("Entering into view All profile DAO");
		List<FacebookUser> fbuserList = faceBookDAO.viewProfileDAO();
		int result=0;
		if(fbuserList.size()> 0) {
			result =1;
		}
		assert result > 0: "profiles not found";
	}
	
	@Test
	public void testViewProfileDAO() {
		System.out.println("Entering into view profile DAO");
		FacebookUser facebookUser = new FacebookUser();
		facebookUser.setEmail("samar@gmail.com");
		FacebookUser fbuser = faceBookDAO.viewProfileDAO(facebookUser);
		int result=0;
		if(null != fbuser.getName()) {
			result =1;
		}
		assert result > 0: "profile not found";
	}

	@Test
	public void testCreateProfileDAO() {
		System.out.println("Entering into create profile DAO");
		FacebookUser facebookUser = new FacebookUser();
		facebookUser.setName("samar2");
		facebookUser.setEmail("samar2@gmail.com");
		facebookUser.setPassword("ssss");
		facebookUser.setAddress("chennai");
		int result = faceBookDAO.createProfileDAO(facebookUser);
		assert result > 0: "Error while creating profile";
	}

}
