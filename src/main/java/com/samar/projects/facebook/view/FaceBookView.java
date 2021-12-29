package com.samar.projects.facebook.view;

import java.util.Scanner;
import java.util.logging.Logger;

import com.samar.projects.facebook.Interfaces.FacebookControllerInterface;
import com.samar.projects.facebook.constants.MessageConstant;
import com.samar.projects.facebook.utility.ControllerFactory;

public class FaceBookView {

	private static void loginOrSignUpDetails() {
		System.out.println(MessageConstant.FACEBOOK_APPLICATION);
		System.out.println(MessageConstant.FACEBOOK_UNDERLINE);
		System.out.println(MessageConstant.SIGNUP);
		System.out.println(MessageConstant.LOGIN);

	}

	public static void main(String[] args) {
		String detailsFromUser = "Y";
		String signUpFlag = "N";

		String printStatement = null;

		int result = 0;
		while ("Y".equalsIgnoreCase(detailsFromUser)) {
			FaceBookView.loginOrSignUpDetails();

			int signUpOrLogin = 0;

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println(MessageConstant.USER_CHOICE);
			signUpOrLogin = scanner.nextInt();
			FacebookControllerInterface facebookControllerInterface = ControllerFactory.createObject();

			if (signUpOrLogin == 1) {
				result = facebookControllerInterface.createProfileController();// To Sign Up
				printStatement = result >= 0 ? MessageConstant.PROFILE_CREATED : MessageConstant.PROFILE_ABORTED;
				if(result > 0 ) {
					signUpFlag = "Y";
				}
				System.out.println("\n" + printStatement);
			} else {
				result = facebookControllerInterface.loginProfileController();
				signUpFlag = "N";// To Login Profile
			}
			
			if("Y".equalsIgnoreCase(signUpFlag)) {
				System.out.println(MessageConstant.USER_SIGNUP_MESSAGE);
			}else {
				System.out.println(MessageConstant.USER_CONTINUE_YN);
			}
			

			detailsFromUser = scanner.next().equalsIgnoreCase("Y") ? "Y" : "N";
		}

	}

}
