package com.samar.projects.facebook.service;

import java.sql.SQLException;
import java.util.List;

import com.samar.projects.facebook.Interfaces.FaceBookDAOInterface;
import com.samar.projects.facebook.Interfaces.FaceBookServiceInterface;
import com.samar.projects.facebook.entity.FacebookUser;
import com.samar.projects.facebook.entity.LikeDisLikeReplyEntity;
import com.samar.projects.facebook.entity.TimeLineEntity;
import com.samar.projects.facebook.utility.DAOFactory;

public class FaceBookService implements FaceBookServiceInterface {
	@Override
	public int createProfileService(FacebookUser fbuser)  {
		FaceBookDAOInterface faceBookDAOInterface=DAOFactory.createObject();
		return faceBookDAOInterface.createProfileDAO(fbuser);
	}

	@Override
	public FacebookUser viewProfileService(FacebookUser fbuser) {
		FaceBookDAOInterface faceBookDAOInterface=DAOFactory.createObject();
		return faceBookDAOInterface.viewProfileDAO(fbuser);
	}

	@Override
	public List<FacebookUser> viewAllProfileService() {
		FaceBookDAOInterface faceBookDAOInterface=DAOFactory.createObject();
		return faceBookDAOInterface.viewProfileDAO();
	}

	@Override
	public int deleteProfileService(FacebookUser fbuser) {
		FaceBookDAOInterface faceBookDAOInterface=DAOFactory.createObject();
		return faceBookDAOInterface.deleteProfileDAO(fbuser);
	}

	@Override
	public FacebookUser loginProfileService(FacebookUser fbuser) {
		FaceBookDAOInterface faceBookDAOInterface=DAOFactory.createObject();
		return faceBookDAOInterface.loginProfileDAO(fbuser);
	}

	@Override
	public List<TimeLineEntity> timelineService(FacebookUser fbuser) {
		FaceBookDAOInterface faceBookDAOInterface=DAOFactory.createObject();
		return faceBookDAOInterface.timelineDAO(fbuser);
	}

	@Override
	public int imageUploadService(String email) {
		 FaceBookDAOInterface faceBookDAOInterface=DAOFactory.createObject();
		 return faceBookDAOInterface.imageUploadDAO(email);
		//return faceBookDAOInterface.timelineDAO(fbuser);
		
	}

	@Override
	public int editProfileService(FacebookUser fbuser) {
		FaceBookDAOInterface faceBookDAOInterface=DAOFactory.createObject();
		 return faceBookDAOInterface.editProfileDAO(fbuser);
	}

	@Override
	public int uploadContentToTimeline(TimeLineEntity timeLineEntity) {
		FaceBookDAOInterface faceBookDAOInterface=DAOFactory.createObject();
		 return faceBookDAOInterface.uploadContentToTimeline(timeLineEntity);
	}

	@Override
	public int updateLikeDisLikeReply(LikeDisLikeReplyEntity likeDisLikeReplyEntity) {
		FaceBookDAOInterface faceBookDAOInterface=DAOFactory.createObject();
		 return faceBookDAOInterface.updateLikeDisLikeReply(likeDisLikeReplyEntity);
	}

}
