/*
 * Copyright (C) 2013 The Zorba Open Source Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.bbytes.zorba.job;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.bbytes.zorba.domain.AbstractJob;
import com.bbytes.zorba.domain.IJob;
import com.bbytes.zorba.exception.JobExecutionException;

/**
 * Sample job that sends emails
 * 
 * @author Thanneer
 * 
 * @version 0.0.1
 */
public class SendMailJob extends AbstractJob implements IJob {


	private Map<String, String> result = new HashMap<String, String>();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.domain.AbstractJob#getJobName()
	 */
	@Override
	public String getJobName() {
		return "Send-Mail-Job";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.domain.AbstractJob#execute(java.util.Map)
	 */
	@Override
	public void execute(Map<String, ? extends Serializable> data) throws JobExecutionException {
		try {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/zorba-mail-context.xml");
			MailSender mailSender = applicationContext.getBean(MailSender.class);
			sendMail((String) data.get("from"), (String) data.get("to"), (String) data.get("subject"),
					(String) data.get("body"), mailSender);
		} catch (Exception e) {
			throw new JobExecutionException(getJobName() + " failed to execute", e);
		}
		result.put("RESPONSE", "MAIL SENT");
	}

	public void sendMail(String from, String to, String subject, String body, MailSender mailSender) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);

	}

	/* (non-Javadoc)
	 * @see com.bbytes.zorba.domain.IJob#getJobDescription()
	 */
	@Override
	public String getJobDescription() {
		return "Job that sends email to the given address ,body and subject";
	}

	@Override
	public Map<String, ? extends Serializable> getResult() {
		return result;
	}
}
