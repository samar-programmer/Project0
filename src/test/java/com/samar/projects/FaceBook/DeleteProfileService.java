package com.samar.projects.FaceBook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.samar.projects.facebook.entity.FacebookUser;
import com.samar.projects.facebook.service.FaceBookService;

public class DeleteProfileService {

	FaceBookService faceBookService;

	@Before
	public void setUp() throws Exception {
		faceBookService = new FaceBookService();
	}

	@After
	public void tearDown() throws Exception {
		faceBookService = null;
	}

	@Test
	public void deleteProfileService() {
		FacebookUser fbuser = new FacebookUser();
		fbuser.setEmail("king@gmail.com");
		int result = faceBookService.deleteProfileService(fbuser);
		assert result > 0 : "profiles not found";
	}

}
