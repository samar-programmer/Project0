package com.samar.projects.facebook.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.samar.projects.facebook.Interfaces.FaceBookDAOInterface;
import com.samar.projects.facebook.constants.MessageConstant;
import com.samar.projects.facebook.database.DataBaseConnection;
import com.samar.projects.facebook.entity.FacebookUser;
import com.samar.projects.facebook.entity.LikeDisLikeReplyEntity;
import com.samar.projects.facebook.entity.TimeLineEntity;
import com.samar.projects.facebook.queries.Queries;

public class FaceBookDAO implements FaceBookDAOInterface {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	@Override
	public int createProfileDAO(FacebookUser fbuser)  {
		int result = 0;
			try {
				connection = DataBaseConnection.getConnection();
				preparedStatement = connection.prepareStatement(Queries.FB_USER_INSERT);
				preparedStatement.setString(1, fbuser.getName());
				preparedStatement.setString(2, fbuser.getPassword());
				preparedStatement.setString(3, fbuser.getEmail());
				preparedStatement.setString(4, fbuser.getAddress());
				result = preparedStatement.executeUpdate();
				connection.close();
				preparedStatement = null;
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		return result;
	}
	
	@Override
	public FacebookUser viewProfileDAO(FacebookUser fbuser) {
		FacebookUser resultFacebookUser = new FacebookUser();
		try {
			connection = DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(Queries.VIEW_FB_USER);
			preparedStatement.setString(1, fbuser.getEmail());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				resultFacebookUser.setName(resultSet.getString(1));
				resultFacebookUser.setPassword(resultSet.getString(2));
				resultFacebookUser.setEmail(resultSet.getString(3));
				resultFacebookUser.setAddress(resultSet.getString(4));
			}
			connection.close();
			preparedStatement = null;
			resultSet = null;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	return resultFacebookUser;
	}

	@Override
	public List<FacebookUser> viewProfileDAO() {
		List<FacebookUser> fbUserList = new ArrayList<FacebookUser>();

		try {
			connection = DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(Queries.VIEW_FB_ALL_USER);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				FacebookUser facebookUser = new FacebookUser();
				facebookUser.setName(resultSet.getString(1));
				facebookUser.setPassword(resultSet.getString(2));
				facebookUser.setEmail(resultSet.getString(3));
				facebookUser.setAddress(resultSet.getString(4));

				fbUserList.add(facebookUser);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return fbUserList;
	}

	@Override
	public int deleteProfileDAO(FacebookUser fbuser) {
		int result = 0;
		try {
			connection = DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(Queries.DELETE_FB_TIMELINE);
			preparedStatement.setString(1, fbuser.getEmail());
			result = preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(Queries.DELETE_FB_USER);
			preparedStatement.setString(1, fbuser.getEmail());
			result = preparedStatement.executeUpdate();
			connection.close();
			preparedStatement = null;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	return result;
	}

	@Override
	public FacebookUser loginProfileDAO(FacebookUser fbuser) {
		Optional <String> originalPassword = null ;
		FacebookUser facebookUser = null;
		try {
			connection = DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(Queries.LOGIN_CHECK);
			preparedStatement.setString(1, fbuser.getEmail());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				originalPassword = Optional.of( resultSet.getString("PASSWORD"));
			}
			
			  if(originalPassword.isPresent()) {
				  if(originalPassword.get().equalsIgnoreCase(fbuser.getPassword())) {
					  preparedStatement = connection.prepareStatement(Queries.AFTER_LOGIN_GET_USER_DETAILS);
					  preparedStatement.setString(1, fbuser.getEmail());
					  resultSet = preparedStatement.executeQuery();
					  while (resultSet.next()) {
						  	facebookUser = new FacebookUser();
							facebookUser.setName(resultSet.getString(1));
							facebookUser.setPassword(resultSet.getString(2));
							facebookUser.setEmail(resultSet.getString(3));
							facebookUser.setAddress(resultSet.getString(4));
						}
				  }else {
					  facebookUser = new FacebookUser();
					  facebookUser.setName("0");
				  }
			  
			  }
			  
			  connection.close();
			  preparedStatement=null;
			  resultSet=null;
			 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return facebookUser;
	}

	@Override
	public List<TimeLineEntity> timelineDAO(FacebookUser fbuser) {
		List<TimeLineEntity> timelinelist = new ArrayList<TimeLineEntity>();
		try {
			connection = DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(Queries.VIEW_TIMELINR);
			preparedStatement.setString(1, fbuser.getEmail());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				TimeLineEntity timeLineEntity = new TimeLineEntity();
				timeLineEntity.setSender(resultSet.getString(1));
				timeLineEntity.setReceiver(resultSet.getString(2));
				timeLineEntity.setMessage(resultSet.getString(3));
				timeLineEntity.setMessageOfTime(resultSet.getString(4));
				timeLineEntity.setFemail(resultSet.getString(5));
				timeLineEntity.setLikes(resultSet.getInt(6));
				timeLineEntity.setDisikes(resultSet.getInt(7));
				timeLineEntity.setReply(resultSet.getString(8));
				timeLineEntity.setLikeFlag(resultSet.getString(9));
				timeLineEntity.setDislikeFlag(resultSet.getString(10));
				timeLineEntity.setTno(resultSet.getInt(11));
				timelinelist.add(timeLineEntity);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return timelinelist;
	}

	@Override
	public int imageUploadDAO(String email) {
		int result = 0;
		try {
			
			FileInputStream fin=new FileInputStream("C:\\tiger.jpg");
			connection = DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(Queries.UPLOAD_IMAGE);
			preparedStatement.setString(1, email);
			preparedStatement.setBinaryStream(2, fin, fin.available());
			result = preparedStatement.executeUpdate();
			connection.close();
			connection = null;
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}  
		
		return result;
		
	}

	@Override
	public int editProfileDAO(FacebookUser fbuser) {
		System.out.println("	----------Edit Information-------");
		FaceBookDAO faceBookDAO = new FaceBookDAO();
		fbuser = faceBookDAO.viewProfileDAO(fbuser);
		System.out.println("\nName : " + fbuser.getName());
		System.out.println("\nPassword : " + fbuser. getPassword());
		System.out.println("\nEmail : " + fbuser.getEmail());
		System.out.println("\nAddress : " + fbuser.getAddress());
		
		System.out.println("\nPress 1 to Edit OR 2 to Exit ");
		Scanner scanner = new Scanner(System.in);
		int editFlag = 0;
		editFlag = scanner.nextInt();
		int result = 0;
		if(editFlag == 1) {
			try {
				connection = DataBaseConnection.getConnection();
				preparedStatement = connection.prepareStatement(Queries.EDIT_USER_DETAILS);
				System.out.println(MessageConstant.ENTER_NAME);
				preparedStatement.setString(1, scanner.next());
				System.out.println(MessageConstant.ENTER_PASSWORD);
				preparedStatement.setString(2, scanner.next());
				System.out.println(MessageConstant.ENTER_ADDRESS);
				preparedStatement.setString(3, scanner.next());
				preparedStatement.setString(4, fbuser.getEmail());
				result = preparedStatement.executeUpdate();
				connection.close();
				connection = null;
				
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return result;
	}

	@Override
	public int uploadContentToTimeline(TimeLineEntity timeLineEntity) {
		//FacebookUser facebookUser = new FacebookUser() ;
		int result = 0;
		try {
			connection = DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(Queries.UPLOAD_TIMELINE_CONTENT);
			preparedStatement.setString(1, timeLineEntity.getSender());
			preparedStatement.setString(2, timeLineEntity.getReceiver());
			preparedStatement.setString(3, timeLineEntity.getMessage());
			preparedStatement.setString(4, timeLineEntity.getMesssageOfTime());
			preparedStatement.setString(5, timeLineEntity.getFemail());
			result = preparedStatement.executeUpdate();
			connection.close();
			preparedStatement = null;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  	return result;
	}

	@Override
	public int updateLikeDisLikeReply(LikeDisLikeReplyEntity likeDisLikeReplyEntity) {
		int result = 0;
		StringBuilder UPDATE_LDR = new StringBuilder();
		UPDATE_LDR.append("UPDATE FB_USER_TIMELINE SET");
			if(likeDisLikeReplyEntity.getLikeclick() > 0) {
				UPDATE_LDR.append(" LIKES = ?,DISLIKES=0");
				if("Y".equalsIgnoreCase(likeDisLikeReplyEntity.getLikeFag())){
					UPDATE_LDR.append(", LIKEFLAG = 'Y', DISLIKEFLAG = 'N'");
				}
			}
			
			if(likeDisLikeReplyEntity.getDislikeclick() > 0) {
				UPDATE_LDR.append(" DISLIKES = ?,LIKES=0");
				if("Y".equalsIgnoreCase(likeDisLikeReplyEntity.getDisLikeFlag())) {
					UPDATE_LDR.append(", DISLIKEFLAG = 'Y', LIKEFLAG = 'N'");
				}
			}
			
			
			
			
			if(null != likeDisLikeReplyEntity.getReply()) {
				if(likeDisLikeReplyEntity.getLikeclick() == 0 && likeDisLikeReplyEntity.getDislikeclick() ==0) {
					UPDATE_LDR.append(" REPLY = ? ");
				}else {
					UPDATE_LDR.append(", REPLY = ? ");
				}
				
			}
			UPDATE_LDR.append("WHERE FEMAIL = ? AND TNO = ? ");
			
			
			String ldrQuery = UPDATE_LDR.toString();
			
		try {
			connection = DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(ldrQuery);
			int count = 0;
			if(likeDisLikeReplyEntity.getLikeclick() > 0) {
				count++;
				preparedStatement.setInt(count, likeDisLikeReplyEntity.getLike());
			}
			if(likeDisLikeReplyEntity.getDislikeclick() > 0) {
				count++;
				preparedStatement.setInt(count, likeDisLikeReplyEntity.getDisLike());
			}
				
			
			if(null != likeDisLikeReplyEntity.getReply()) {
				count++;
				preparedStatement.setString(count, likeDisLikeReplyEntity.getReply());
			}
			 count ++;
			preparedStatement.setString(count, likeDisLikeReplyEntity.getFemail());
			count ++;
			preparedStatement.setInt(count, likeDisLikeReplyEntity.getTno());
			result = preparedStatement.executeUpdate();
			connection.close();
			preparedStatement = null;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  	return result;
	}


	
	

}
