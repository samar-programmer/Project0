package com.samar.projects.facebook.queries;

public class Queries {
	public static final String FB_USER_INSERT = "INSERT INTO FB_USER_DETAILS VALUES(?,?,?,?)";
	public static final String VIEW_FB_USER = "SELECT * FROM FB_USER_DETAILS WHERE EMAIL = ?";
	public static final String VIEW_FB_ALL_USER = "SELECT * FROM FB_USER_DETAILS";
	public static final String DELETE_FB_USER = "DELETE FROM FB_USER_DETAILS WHERE EMAIL=?";
	public static final String LOGIN_CHECK = "SELECT PASSWORD FROM FB_USER_DETAILS WHERE EMAIL = ?";
	public static final String AFTER_LOGIN_GET_USER_DETAILS = "SELECT * FROM FB_USER_DETAILS WHERE EMAIL = ?";
	public static final String VIEW_TIMELINR = "SELECT * FROM FB_USER_TIMELINE WHERE FEMAIL = ?";
	public static final String UPLOAD_IMAGE = "INSERT INTO FB_USER_IMAGE VALUES(?,?)";
	public static final String EDIT_USER_DETAILS = "UPDATE FB_USER_DETAILS SET NAME = ?, PASSWORD = ?, ADDRESS=? WHERE EMAIL = ?";
	public static final String UPLOAD_TIMELINE_CONTENT= "INSERT INTO FB_USER_TIMELINE(SENDER, RECEIVER, MESSAGE, MESSAGETIME, FEMAIL) VALUES(?,?,?,?,?)";
	public static final String DELETE_FB_TIMELINE = "DELETE FROM FB_USER_TIMELINE WHERE FEMAIL=?";
	
}
