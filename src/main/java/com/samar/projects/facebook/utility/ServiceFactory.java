package com.samar.projects.facebook.utility;

import com.samar.projects.facebook.Interfaces.FaceBookServiceInterface;
import com.samar.projects.facebook.service.FaceBookService;

public class ServiceFactory {
	
	private ServiceFactory() {
		
	}

	public static FaceBookServiceInterface createObject() {
		// TODO Auto-generated method stub
		return new FaceBookService();
	}

}
