package com.samar.projects.FaceBook;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.samar.projects.facebook.Interfaces.FacebookControllerInterface;
import com.samar.projects.facebook.utility.ControllerFactory;

public class DeletePfofileController {

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
	public void testDeleteProfileController() {
		System.out.println("Inside Delete Profile Controller");
		int result = facebookControllerInterface.deleteProfileController();
		assert result > 0 : "Error during delete profiles";
	}

}
