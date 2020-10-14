package com.jimall.service;

import com.jimall.dto.EmailDTO;

public interface EmailService {

	public void sendMail(EmailDTO dto, String authcode);
}
