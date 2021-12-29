package com.samar.projects.facebook.utility;

import com.samar.projects.facebook.Interfaces.FaceBookDAOInterface;
import com.samar.projects.facebook.dao.FaceBookDAO;

public class DAOFactory {
	
	private DAOFactory() {
		
	}
	public static FaceBookDAOInterface createObject() {
		// TODO Auto-generated method stub
		return new FaceBookDAO();
	}

}
