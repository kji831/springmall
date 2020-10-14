package com.jimall.dto;

public class EmailDTO {

	private String senderName;
	private String senderMail;
	private String receiveMail;
	private String subject;
	private String message;
	
	public EmailDTO() {
		this.senderMail = "JIMall";
		this.senderName = "JIMall";
		this.subject = "JIMall 인증번호 입니다.";
		this.message = "이메일 인증을 위해 \n 아래 번호를 이메일 인증번호 란에 입력하세요. \n\n인증번호:";
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderMail() {
		return senderMail;
	}

	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}

	public String getReceiveMail() {
		return receiveMail;
	}

	public void setReceiveMail(String receiveMail) {
		this.receiveMail = receiveMail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "EmailDTO [senderName=" + senderName + ", senderMail=" + senderMail + ", receiveMail=" + receiveMail
				+ ", subject=" + subject + ", message=" + message + "]";
	}
	
	
	
}
