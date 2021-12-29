package com.samar.projects.facebook.utility;

import com.samar.projects.facebook.Interfaces.FacebookControllerInterface;
import com.samar.projects.facebook.controller.FacebookController;

public class ControllerFactory {
	
	private ControllerFactory() {
		
	}

	public static FacebookControllerInterface createObject() {
		// TODO Auto-generated method stub
		return new FacebookController();
	}

}
