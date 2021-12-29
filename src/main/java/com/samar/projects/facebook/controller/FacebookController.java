package com.samar.projects.facebook.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

import com.samar.projects.facebook.Interfaces.FaceBookServiceInterface;
import com.samar.projects.facebook.Interfaces.FacebookControllerInterface;
import com.samar.projects.facebook.constants.MessageConstant;
import com.samar.projects.facebook.entity.FacebookUser;
import com.samar.projects.facebook.entity.LikeDisLikeReplyEntity;
import com.samar.projects.facebook.entity.TimeLineEntity;
import com.samar.projects.facebook.utility.ControllerFactory;
import com.samar.projects.facebook.utility.ServiceFactory;
import com.samar.projects.facebook.view.FaceBookView;

public class FacebookController implements FacebookControllerInterface {
	Logger log = Logger.getLogger("createProfileController");

	public int createProfileController() {
		// System.out.println(MessageConstant.INSIDE_CREATE_PROFILE_METHOD);
		FacebookUser fbuser = new FacebookUser();

		Scanner scanner = new Scanner(System.in);

		System.out.println(MessageConstant.ENTER_NAME);
		fbuser.setName(scanner.next());
		System.out.println(MessageConstant.ENTER_PASSWORD);
		fbuser.setPassword(scanner.next());
		System.out.println(MessageConstant.ENTER_EMAIL);
		fbuser.setEmail(scanner.next());
		System.out.println(MessageConstant.ENTER_ADDRESS);
		fbuser.setAddress(scanner.next());

		// System.out.println(fbuser);

		FaceBookServiceInterface faceBookServiceInterface = ServiceFactory.createObject();
		int result = faceBookServiceInterface.createProfileService(fbuser);
		// System.out.println(MessageConstant.EXIT_CREATE_PROFILE_METHOD);
		return result;
	}

	@Override
	public int viewProfileController() {
		// System.out.println(MessageConstant.INSIDE_VIEW_PROFILE_METHOD);
		int result = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println(MessageConstant.ENTER_EMAIL);
		FacebookUser fbuser = new FacebookUser();
		fbuser.setEmail(sc.next());
		FaceBookServiceInterface faceBookServiceInterface = ServiceFactory.createObject();
		FacebookUser fbuserDetails = faceBookServiceInterface.viewProfileService(fbuser);
		Optional<FacebookUser> checkNull = Optional.ofNullable(fbuserDetails);
		if (checkNull.isPresent()) {
			System.out.println(MessageConstant.NAME + fbuserDetails.getName());
			System.out.println(MessageConstant.PASSWORD + fbuserDetails.getPassword());
			System.out.println(MessageConstant.EMAIL + fbuserDetails.getEmail());
			System.out.println(MessageConstant.ADDRESS + fbuserDetails.getAddress());
			result = 1;
		}
		// System.out.println(MessageConstant.INSIDE_VIEW_PROFILE_METHOD);
		return result;
	}

	@Override
	public int viewAllProfileController() {
		FaceBookServiceInterface faceBookServiceInterface = ServiceFactory.createObject();
		List<FacebookUser> fbUsersLists = faceBookServiceInterface.viewAllProfileService();

		for (FacebookUser fbUSer : fbUsersLists) {
			System.out.println(MessageConstant.STAR_LINE);
			System.out.println(MessageConstant.NAME + fbUSer.getName());
			System.out.println(MessageConstant.PASSWORD + fbUSer.getPassword());
			System.out.println(MessageConstant.EMAIL + fbUSer.getEmail());
			System.out.println(MessageConstant.ADDRESS + fbUSer.getAddress());

		}

		return fbUsersLists.size();
	}

	@Override
	public int deleteProfileController() {
		Scanner scanner = new Scanner(System.in);
		System.out.println(MessageConstant.ENTER_EMAIL);
		FacebookUser fbuser = new FacebookUser();
		fbuser.setEmail(scanner.next());
		FaceBookServiceInterface faceBookServiceInterface = ServiceFactory.createObject();
		int result = faceBookServiceInterface.deleteProfileService(fbuser);
		return result;
	}

	@Override
	public int loginProfileController() {
		int loginResult = 0;
		Scanner scanner = new Scanner(System.in);
		FacebookUser fbuser = new FacebookUser();
		System.out.println(MessageConstant.ENTER_EMAIL);
		fbuser.setEmail(scanner.next());
		System.out.println(MessageConstant.ENTER_PASSWORD);
		fbuser.setPassword(scanner.next());
		FaceBookServiceInterface faceBookServiceInterface = ServiceFactory.createObject();
		FacebookUser facebookUser = faceBookServiceInterface.loginProfileService(fbuser);// to get all details of user
		if (!facebookUser.getName().equalsIgnoreCase("0")) {
			loginResult = 1;
			String printStatement = null;
			String detailsFromUser = "Y";
			FacebookControllerInterface facebookControllerInterface = ControllerFactory.createObject();
			System.out.println(
					MessageConstant.WELCOME + facebookUser.getName().toUpperCase() + MessageConstant.LOGIN_SUCCESS);
			while (detailsFromUser.equalsIgnoreCase("y")) {
				FacebookController.printDetails();
				// System.out.println("press 2 to upload photo");
				System.out.println(MessageConstant.USER_CHOICE);
				int operationNum = scanner.nextInt();
				int result = 0;
				switch (operationNum) {
				case 1:
					result = facebookControllerInterface.viewProfileController();// To View Profile
					printStatement = result >= 0 ? MessageConstant.VIEW_PROFILE : MessageConstant.PROFILE_NOT_FOUND;
					System.out.println("\n" + printStatement);
					break;

				case 2:
					result = facebookControllerInterface.viewAllProfileController();// To View All Profile
					printStatement = result >= 0 ? MessageConstant.VIEW_ALL_PROFILE
							: MessageConstant.PROFILES_NOT_FOUND;
					System.out.println("\n" + printStatement);
					break;
				case 3:
					result = facebookControllerInterface.deleteProfileController();// To Delete Profile
					printStatement = result >= 0 ? MessageConstant.DELETE_PROFILE : MessageConstant.ERR_DELETE_PROFILE;
					if (result > 0) {
						detailsFromUser = "N";
					}
					System.out.println("\n" + printStatement);
					break;

				case 4:
					result = faceBookServiceInterface.editProfileService(fbuser);// To Edit Profile
					printStatement = result >= 0 ? MessageConstant.EDIT_PROFILE : MessageConstant.ERR_EDIT_PROFILE;
					System.out.println("\n" + printStatement);
					break;

				case 5:
					System.out.println("Press 1 to upload content to timeline Press 2 to view timeline");
					Scanner sc1 = new Scanner(System.in);
					int insertOrViewTimeLine = sc1.nextInt();
					if (insertOrViewTimeLine == 1) {
						TimeLineEntity timeLineEntity = new TimeLineEntity();
						Scanner sc2 = new Scanner(System.in);
						System.out.println("\n	Enter Sender :");
						timeLineEntity.setSender(sc2.nextLine());
						System.out.println("\n	Enter Receiver :");
						timeLineEntity.setReceiver(sc2.nextLine());
						System.out.println("\n	Enter Message :");
						timeLineEntity.setMessage(sc2.nextLine());
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime currentTime = LocalDateTime.now();
						String dateString;
						dateString = dtf.format(currentTime).toString();
						timeLineEntity.setMessageOfTime(dateString.substring(dateString.indexOf(" ")));
						timeLineEntity.setFemail(fbuser.getEmail());
						int result1 = 0;
						result1 = faceBookServiceInterface.uploadContentToTimeline(timeLineEntity);
						if (result1 > 0) {
							System.out.println("\n	Content Uploaded to Timeline - Successfull!!");
						} else {
							System.out.println("\n	Contetnt Uploaded to Timeline - Failed!!!");
						}
					} else {

						List<TimeLineEntity> timelineresults = faceBookServiceInterface.timelineService(fbuser);
						FacebookController.timeLineFunctionality(timelineresults);
					}
					break;

				case 6:
					FacebookController.imageUploadFunctionality(fbuser.getEmail());
					break;

				}

				if (!"N".equalsIgnoreCase(detailsFromUser)) {
					System.out.println(MessageConstant.USER_CONTINUE_OTHER_OPTION);
					Scanner ss = new Scanner(System.in);
					detailsFromUser = ss.next().equalsIgnoreCase("Y") ? "Y" : "N";
				}

			}

		} else {
			log.info(MessageConstant.LOGIN_FAILED);
		}

		return loginResult;

	}

	private static void imageUploadFunctionality(String Email) {
		int result = 0;
		FaceBookServiceInterface faceBookServiceInterface = ServiceFactory.createObject();
		result = faceBookServiceInterface.imageUploadService(Email);
		if (result > 0) {
			System.out.println("Image Uploaded successfully...");
		} else {
			System.out.println("Image Image can't upload");
		}

	}

	private static void timeLineFunctionality(List<TimeLineEntity> timelineresults) {
		int i = 1;
		for (TimeLineEntity timelineresult : timelineresults) {
			System.out.println("\n	" + i + ") " + MessageConstant.TIMELINE_DETAILS);
			i++;
			System.out.println(MessageConstant.SENDER + timelineresult.getSender());
			System.out.println(MessageConstant.RECEIVER + timelineresult.getReceiver());
			System.out.println(MessageConstant.MESSAGE + timelineresult.getMessage());
			System.out.println(MessageConstant.TIME + timelineresult.getMesssageOfTime());

			System.out.println(MessageConstant.LIKE + timelineresult.getLikes());
			if ("Y".equalsIgnoreCase(timelineresult.getLikeFlag())) {
				System.out.println(MessageConstant.LIKED);
			}
			System.out.println(MessageConstant.DISLIKE + timelineresult.getDisikes());
			if ("Y".equalsIgnoreCase(timelineresult.getDislikeFlag())) {
				System.out.println(MessageConstant.DIS_LIKED);
			}
			if (null != timelineresult.getReply()) {
				System.out.println("\n	You Already replied !!");
				System.out.println("\n	Reply : " + timelineresult.getReply());
			}

			int likeDislikeReplyFunctionNum = 0;
			int threeStageUpdate = 0;
			String likeDislikeReplyYN = "Y";
			System.out.println("\n" + MessageConstant.LIKE_DISLIKE_REPLY_START);
			Scanner scanner = new Scanner(System.in);
			likeDislikeReplyYN = scanner.next();
			LikeDisLikeReplyEntity likeDisLikeReplyEntity = new LikeDisLikeReplyEntity();
			while (likeDislikeReplyYN.equalsIgnoreCase("Y")) {// && threeStageUpdate<=3
				System.out.println("\n" + MessageConstant.LIKE_DISLIKE_REPLY);// like dislike reply
				Scanner scanner1 = new Scanner(System.in);
				likeDislikeReplyFunctionNum = scanner1.nextInt();
				likeDisLikeReplyEntity.setFemail(timelineresult.getFemail());
				likeDisLikeReplyEntity.setTno(timelineresult.getTno());

				switch (likeDislikeReplyFunctionNum) {
				case 1:
					int likes = 0;
					System.out.println("\n" + MessageConstant.LIKES);// likes
					likes = scanner1.nextInt();
					if (likes == 1) {
						// likeDisLikeReplyEntity.setLike(timelineresult.getLikes()+likes);
						likeDisLikeReplyEntity.setLike(1);
						likeDisLikeReplyEntity.setLikeFag("Y");
						likeDisLikeReplyEntity.setLikeclick(1);
						likeDisLikeReplyEntity.setDislikeclick(0);
						threeStageUpdate++;
						/*
						 * if("Y".equalsIgnoreCase(timelineresult.getDislikeFlag())) {
						 * likeDisLikeReplyEntity.setDisLike(timelineresult.getDisikes() - 1); }
						 */

					}
					break;
				case 2:
					int dislikes = 0;
					System.out.println("\n" + MessageConstant.DIS_LIKES);// likes
					dislikes = scanner1.nextInt();
					if (dislikes == 1) {
						// likeDisLikeReplyEntity.setDisLike(timelineresult.getDisikes()+dislikes);
						likeDisLikeReplyEntity.setDisLike(1);
						likeDisLikeReplyEntity.setDisLikeFlag("Y");
						likeDisLikeReplyEntity.setDislikeclick(1);
						likeDisLikeReplyEntity.setLikeclick(0);
						threeStageUpdate++;
						/*
						 * if("Y".equalsIgnoreCase(timelineresult.getLikeFlag())) {
						 * likeDisLikeReplyEntity.setLike(timelineresult.getLikes() - 1); }
						 */

					}
					break;
				case 3:
					int replyCount = 0;
					String reply = null;
					System.out.println("\n" + MessageConstant.REPLY);// likes
					Scanner sc1 = new Scanner(System.in);
					replyCount = scanner.nextInt();
					if (replyCount == 1) {
						System.out.println("\n Enter Reply : ");
						reply = sc1.nextLine();
						likeDisLikeReplyEntity.setReply(reply);
						threeStageUpdate++;
					}
					break;

				}
				Scanner sc3 = new Scanner(System.in);
				System.out.println("\n" + MessageConstant.LIKE_DISLIKE_REPLY_YN);//
				likeDislikeReplyYN = sc3.next();
			}

			// Update timeLine messages likes dislikes;
			if (threeStageUpdate > 0) {
				int LDR_Result = 0;
				FaceBookServiceInterface faceBookServiceInterface = ServiceFactory.createObject();
				LDR_Result = faceBookServiceInterface.updateLikeDisLikeReply(likeDisLikeReplyEntity);
				if (LDR_Result > 0) {
					System.out.println("\n Likes DisLike Reply updated successfully...");
				} else {
					System.out.println("\n Likes DisLike Reply not updated");
				}
			}

		}

	}

	private static void printDetails() {
		System.out.println(MessageConstant.GET_USER_DETAILS);
		System.out.println(MessageConstant.USER_ENTER_DETAILS);
	}

}
