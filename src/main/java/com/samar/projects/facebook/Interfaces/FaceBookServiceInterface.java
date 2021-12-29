package com.samar.projects.facebook.Interfaces;

import java.sql.SQLException;
import java.util.List;

import com.samar.projects.facebook.entity.FacebookUser;
import com.samar.projects.facebook.entity.LikeDisLikeReplyEntity;
import com.samar.projects.facebook.entity.TimeLineEntity;

public interface FaceBookServiceInterface {

	int createProfileService(FacebookUser fbuser);

	FacebookUser viewProfileService(FacebookUser fbuser);

	List<FacebookUser> viewAllProfileService();

	int deleteProfileService(FacebookUser facebookUser);

	FacebookUser loginProfileService(FacebookUser fbuser);

	List<TimeLineEntity> timelineService(FacebookUser fbuser);

	int imageUploadService(String email);

	int editProfileService(FacebookUser fbuser);

	int uploadContentToTimeline(TimeLineEntity timeLineEntity);

	int updateLikeDisLikeReply(LikeDisLikeReplyEntity likeDisLikeReplyEntity);

}
