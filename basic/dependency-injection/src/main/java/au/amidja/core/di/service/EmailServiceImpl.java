package au.amidja.core.di.service;

import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements MessageService{

	@Override
	public void sendMessage(String msg, String rec) {
		//logic to send email
		System.out.println("Email sent to "+rec+ " with Message="+msg);
	}
}
