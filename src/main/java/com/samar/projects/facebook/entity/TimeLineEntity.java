package com.samar.projects.facebook.entity;

public class TimeLineEntity {
	
	private String sender;
	private String receiver;
	private String message;
	private String messageOfTime;
	private int likes;
	private int disikes;
	private String likeFlag = "N";
	private String dislikeFlag = "N";
	private String femail;
	private String reply;
	private int tno;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMesssageOfTime() {
		return messageOfTime;
	}
	public void setMessageOfTime(String messageOfTime) {
		this.messageOfTime = messageOfTime;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDisikes() {
		return disikes;
	}
	public void setDisikes(int disikes) {
		this.disikes = disikes;
	}
	public String getLikeFlag() {
		return likeFlag;
	}
	public void setLikeFlag(String likeFlag) {
		this.likeFlag = likeFlag;
	}
	public String getDislikeFlag() {
		return dislikeFlag;
	}
	public void setDislikeFlag(String dislikeFlag) {
		this.dislikeFlag = dislikeFlag;
	}
	public String getFemail() {
		return femail;
	}
	public void setFemail(String femail) {
		this.femail = femail;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}

}
