package com.samar.projects.facebook.Interfaces;

import java.sql.SQLException;
import java.util.List;

import com.samar.projects.facebook.entity.FacebookUser;
import com.samar.projects.facebook.entity.LikeDisLikeReplyEntity;
import com.samar.projects.facebook.entity.TimeLineEntity;

public interface FaceBookDAOInterface {

	int createProfileDAO(FacebookUser fbuser);

	FacebookUser viewProfileDAO(FacebookUser fbuser);

	List<FacebookUser> viewProfileDAO();

	int deleteProfileDAO(FacebookUser fbuser);

	FacebookUser loginProfileDAO(FacebookUser fbuser);

	List<TimeLineEntity> timelineDAO(FacebookUser fbuser);

	int imageUploadDAO(String email);

	int editProfileService(FacebookUser fbuser);

	int uploadContentToTimeline(TimeLineEntity timeLineEntity);

	int updateLikeDisLikeReply(LikeDisLikeReplyEntity likeDisLikeReplyEntity);

}
